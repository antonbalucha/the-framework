package framework.bl.impl.action.registeruser;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.action.registeruser.RegisterUserBlApi;
import framework.configuration.ConfigurationReader;
import framework.db.constant.UserStatus;
import framework.db.dao.impl.UserDaoImpl;
import framework.db.dbo.UserDbo;
import framework.security.PasswordUtils;
import framework.utils.SessionUtils;
import framework.utils.email.Email;
import framework.utils.email.EmailUtils;
import framework.utils.translations.UIErrorKey;
import framework.web.wso.request.UserReqistrationRequestWso;
import framework.web.wso.utils.ResponseUtil;

/** 
 * Class contains business logic for registration of user. <br>
 */
public class RegisterUserBlImpl implements RegisterUserBlApi {

	private static final Logger logger = LoggerFactory.getLogger(RegisterUserBlImpl.class);

	/** 
	 * Method provides registration of user. It will: 
	 * 
	 * <ul>
	 * <li>validates users input</li>
	 * <li>verifies, whether the same user name or password is already registered or not</li>
	 * <li>encrypts entered password</li>
	 * <li>generates activation token</li>
	 * <li>sends confirmation e-mail with generated activation link</li>
	 * <li>sets default users state as {@linkplain framework.db.constant.UserStatus#R R} (registered)</li>
	 * </ul>
	 * 
	 * @param registerUserRequest - object, which contains data for registration of user
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 201 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_SUCCESSFULLY_REGISTERED USER_SUCCESSFULLY_REGISTERED}- if user was successfully registered <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#REQUEST_IS_EMPTY REQUEST_IS_EMPTY}- if entered registerUserRequest is null <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_IS_EMPTY USER_NAME_IS_EMPTY} - if user name is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#PASSWORD_IS_EMPTY PASSWORD_IS_EMPTY} - if password is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#EMAIL_IS_EMPTY EMAIL_IS_EMPTY} - if email is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#EMAIL_HAS_INVALID_FORM EMAIL_HAS_INVALID_FORM} - if e-mail has invalid form <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_ALREADY_REGISTERED USER_NAME_ALREADY_REGISTERED} - if users name is already registered <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_EMAIL_ALREADY_REGISTERED USER_EMAIL_ALREADY_REGISTERED} - if users e-mail is already registered <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
	 * @see framework.db.constant.UserStatus
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response registerUser(UserReqistrationRequestWso registerUserRequest) {

		if (registerUserRequest == null) {
			return ResponseUtil.response(400, UIErrorKey.REQUEST_IS_EMPTY);
		} else if (StringUtils.isBlank(registerUserRequest.getUserName())) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else if (StringUtils.isBlank(registerUserRequest.getPassword())) {
			return ResponseUtil.response(400, UIErrorKey.PASSWORD_IS_EMPTY);
		} else if (StringUtils.isBlank(registerUserRequest.getEmail())) {
			return ResponseUtil.response(400, UIErrorKey.EMAIL_IS_EMPTY);
		} else if (!EmailUtils.isValid(registerUserRequest.getEmail())) {
			return ResponseUtil.response(400, UIErrorKey.EMAIL_HAS_INVALID_FORM);
		} else if (new UserDaoImpl().existsByUserName(registerUserRequest.getUserName())) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_ALREADY_REGISTERED);
		} else if (new UserDaoImpl().existsByEmail(registerUserRequest.getEmail())) {
			return ResponseUtil.response(400, UIErrorKey.USER_EMAIL_ALREADY_REGISTERED);
		} else {
		
			try {

				// generate salt, hash
				Sha512Hash hash = PasswordUtils.getSha512Hash(registerUserRequest.getPassword());
				
				// generate random string for activation of account
				String activationToken = framework.utils.StringUtils.generateRandomString();
				
				// save values to database
				UserDbo newUser = new UserDbo()
					.setUserName(registerUserRequest.getUserName())
					.setPassword(hash.toBase64())
					.setSalt(PasswordUtils.getSalt(hash))
					.setEmail(registerUserRequest.getEmail())
					.setStatus(UserStatus.R)
					.setActivationToken(activationToken)
					.setLanguage(SessionUtils.getUserSessionLanguage());
				
				new UserDaoImpl().insert(newUser);
				
				// generate an activation link specific for entered user
				String activationLink = String.format(ConfigurationReader.getUserRegistrationActivationLink(), activationToken); 
				
				// send an email notification for account activation
				new Email()
					.setSender(ConfigurationReader.getEmailUserRegistrationFrom())
					.setRecipient(newUser.getEmail())
					.setSubject(ConfigurationReader.getEmailUserRegistrationSubject(SessionUtils.getUserSessionLanguage()))
					.setText(String.format(ConfigurationReader.getEmailUserRegistrationText(SessionUtils.getUserSessionLanguage()), activationLink))
					.send();
				
				// send response back to UI
				return ResponseUtil.response(201, UIErrorKey.USER_SUCCESSFULLY_REGISTERED);
				
			} catch (MappingException e) {
				logger.error("MappingException: " + e.getMessage() + " (error when mapping objects); Entered 'registerUserRequest' object: '" + registerUserRequest + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'registerUserRequest' object: '" + registerUserRequest + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
