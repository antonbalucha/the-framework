package framework.bl.impl.data;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.crypto.hash.Sha512Hash;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.data.UserBlApi;
import framework.bl.impl.mapper.UserMapper;
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
 * Class contains business logic for processing of users data. <br>
 */
public class UserBlImpl implements UserBlApi {

	private static final Logger logger = LoggerFactory.getLogger(UserBlImpl.class);
	
	/** 
	 * Method, which returns user data based on users id. <br>
	 * 
	 * @param id - id of searched user
	 * @return 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.UserResponseWso users data} - information about user <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no user was found <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#INVALID_PARAMETER_ID INVALID_PARAMETER_ID} - if entered parameter 'id' is not valid <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}- if an internal server occurs <br>
	 * @see framework.web.wso.response.UserResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response get(Long id) {

		if (id == null || id < 0) {
			return ResponseUtil.response(400, UIErrorKey.INVALID_PARAMETER_ID);
		} else {
		
			try {

				UserDbo dbo = new UserDaoImpl().select(id);
				return (dbo == null) ? ResponseUtil.response(204) : ResponseUtil.response(200, UserMapper.map(dbo));
			} catch (MappingException e) {
				logger.error("MappingException: " + e.getMessage() + " (error when mapping objects); Entered parameter 'id' is '" + id + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered parameter 'id' is '" + id + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * Method, which returns user data based on user name. <br>
	 * 
	 * @param userName - name of user
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.UserResponseWso users data} - information about user <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no user was found <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#INVALID_PARAMETER_ID INVALID_PARAMETER_ID} - if entered parameter 'id' is not valid <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}- if an internal server occurs <br>
	 * @see framework.web.wso.response.UserResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response get(String userName) {
		
		if (StringUtils.isBlank(userName)) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else {
			
			try {
			
				UserDbo dbo = new UserDaoImpl().select(userName);
				return (dbo == null) ? ResponseUtil.response(204) : ResponseUtil.response(200, UserMapper.map(dbo));
			} catch (MappingException e) {
				logger.error("MappingException: " + e.getMessage() + " (error when mapping objects); Entered parameter 'userName' is '" + userName + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered parameter 'userName' is '" + userName + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * Method, which returns list of all users. <br>
	 * 
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.ListOfUsersResponseWso users data} - list of information about users <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no user was found <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}- if an internal server occurs <br>
	 * @see framework.web.wso.response.ListOfUsersResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response list() {
		
		try {
		
			List<UserDbo> listOfDbo = new UserDaoImpl().list();
			return (listOfDbo == null || listOfDbo.isEmpty()) ? ResponseUtil.response(204) : ResponseUtil.response(200, UserMapper.mapToResponse(listOfDbo));
		} catch (MappingException e) {
			logger.error("MappingException: " + e.getMessage(), e);
			return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage(),  e);
			return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
		}
	}

	/** 
	 * Method, which returns list of users base on limit and offset. <br>
	 * 
	 * @param limit - number of users, which should be returned <br>
	 * @param offset - certain page with users/together with limit it calculates how many records will be skipped => 'limit' * 'offset' = number of skipped records <br>
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.ListOfUsersResponseWso users data} - list of information about users <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no user was found <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#INVALID_LIMIT_VALUE INVALID_LIMIT_VALUE} - if entered parameter 'limit' is not valid <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#INVALID_OFFSET_VALUE INVALID_OFFSET_VALUE} - if entered parameter 'offset' is not valid <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR}- if an internal server occurs <br>
	 * @see framework.web.wso.response.ListOfUsersResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response list(Integer limit, Integer offset) {
		
		if (limit == null || limit < 1) {
			return ResponseUtil.response(400, UIErrorKey.INVALID_LIMIT_VALUE);
		} else if (offset == null || offset < 0) {
			return ResponseUtil.response(400, UIErrorKey.INVALID_OFFSET_VALUE);
		} else {
		
			try {
			
				List<UserDbo> listOfDbo = new UserDaoImpl().list(limit, offset);
				return (listOfDbo == null || listOfDbo.isEmpty()) ? ResponseUtil.response(204) : ResponseUtil.response(200, UserMapper.mapToResponse(listOfDbo));
			} catch (MappingException e) {
				logger.error("MappingException: " + e.getMessage(), e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage(),  e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/** 
	 * Method provides registration of new user. Method should by called only by administrator/from administrators interface,
	 * because of possible different implementation then {@linkplain framework.bl.impl.action.registeruser.RegisterUserBlImpl RegisterUserBlImpl}. 
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
	public Response register(UserReqistrationRequestWso registerUserRequest) {
		
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
					.setActivationToken(activationToken);
				
				new UserDaoImpl().insert(newUser);
				
				// generate an activation link specific for entered user
				String activationLink = String.format(ConfigurationReader.getUserRegistrationActivationLink(), activationToken); 
				
				// send an email notification for account activation
				new Email()
					.setSender(ConfigurationReader.getEmailAdminRegistrationFrom())
					.setRecipient(newUser.getEmail())
					.setSubject(ConfigurationReader.getEmailAdminRegistrationSubject(SessionUtils.getUserSessionLanguage()))
					.setText(String.format(ConfigurationReader.getEmailAdminRegistrationText(SessionUtils.getUserSessionLanguage()), activationLink))
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

	/** 
	 * Method, which will activate user. <br>
	 * 
	 * @param userName - name of user, which should be activated
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#ACTIVATION_SUCCESSFUL ACTIVATION_SUCCESSFUL}- if activation was successful <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_IS_EMPTY USER_NAME_IS_EMPTY}- if entered user name is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#ACTIVATION_NOT_SUCCESSFUL ACTIVATION_NOT_SUCCESSFUL} - if activation was not successful <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
	 * @see framework.db.constant.UserStatus
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response activate(String userName) {
		
		if (StringUtils.isBlank(userName)) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else {

			try {
			
				UserDbo userDbo = new UserDbo()
					.setUserName(userName)
					.setStatus(UserStatus.A);

				// update of account and its status identified by used name
				Boolean isUserUpdated = new UserDaoImpl().updateStatusByUserName(userDbo);
			
				if (!isUserUpdated) {
					logger.error("Problem when activating user with entered 'user name': '" + userName + "'");
					return ResponseUtil.response(400, UIErrorKey.ACTIVATION_NOT_SUCCESSFUL);
				} else {
					return ResponseUtil.response(200, UIErrorKey.ACTIVATION_SUCCESSFUL);
				}
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'user name': '" + userName + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/** 
	 * Method, which will inactivate user. <br>
	 * 
	 * @param userName - name of user, which should be inactivated
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#INACTIVATION_SUCCESSFUL INACTIVATION_SUCCESSFUL}- if inactivation was successful <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_IS_EMPTY USER_NAME_IS_EMPTY}- if entered user name is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#INACTIVATION_NOT_SUCCESSFUL INACTIVATION_NOT_SUCCESSFUL} - if inactivation was not successful <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
	 * @see framework.db.constant.UserStatus
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response inactivate(String userName) {
		
		if (StringUtils.isBlank(userName)) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else {

			try {
			
				UserDbo userDbo = new UserDbo()
					.setUserName(userName)
					.setStatus(UserStatus.I);

				// update of account and its status identified by used name
				Boolean isUserUpdated = new UserDaoImpl().updateStatusByUserName(userDbo);
			
				if (!isUserUpdated) {
					logger.error("Problem when inactivating user with entered 'user name': '" + userName + "'");
					return ResponseUtil.response(400, UIErrorKey.INACTIVATION_NOT_SUCCESSFUL);
				} else {
					return ResponseUtil.response(200, UIErrorKey.INACTIVATION_SUCCESSFUL);
				}
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'user name': '" + userName + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/** 
	 * Method, which will ban user. <br>
	 * 
	 * @param userName - name of user, which should be banned
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#BANNING_SUCCESSFUL BANNING_SUCCESSFUL}- if banning was successful <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_IS_EMPTY USER_NAME_IS_EMPTY}- if entered user name is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#BANNING_NOT_SUCCESSFUL BANNING_NOT_SUCCESSFUL} - if banning was not successful <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
	 * @see framework.db.constant.UserStatus
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response ban(String userName) {
		
		if (StringUtils.isBlank(userName)) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else {

			try {
			
				UserDbo userDbo = new UserDbo()
					.setUserName(userName)
					.setStatus(UserStatus.B);

				// update of account and its status identified by used name
				Boolean isUserUpdated = new UserDaoImpl().updateStatusByUserName(userDbo);
			
				if (!isUserUpdated) {
					logger.error("Problem when banning user with entered 'user name': '" + userName + "'");
					return ResponseUtil.response(400, UIErrorKey.BANNING_NOT_SUCCESSFUL);
				} else {
					return ResponseUtil.response(200, UIErrorKey.BANNING_SUCCESSFUL);
				}
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'user name': '" + userName + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/** 
	 * Method, which will delete user. <br>
	 * 
	 * @param userName - name of user, which should be deleted
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#DELETING_SUCCESSFUL DELETING_SUCCESSFUL}- if deleting was successful <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_IS_EMPTY USER_NAME_IS_EMPTY}- if entered user name is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#DELETING_NOT_SUCCESSFUL DELETING_NOT_SUCCESSFUL} - if deleting was not successful <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
	 * @see framework.db.constant.UserStatus
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response delete(String userName) {
		
		if (StringUtils.isBlank(userName)) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else {

			try {
			
				UserDbo userDbo = new UserDbo()
					.setUserName(userName)
					.setStatus(UserStatus.D);

				// update of account and its status identified by used name
				Boolean isUserUpdated = new UserDaoImpl().updateStatusByUserName(userDbo);
			
				if (!isUserUpdated) {
					logger.error("Problem when deleting user with entered 'user name': '" + userName + "'");
					return ResponseUtil.response(400, UIErrorKey.DELETING_NOT_SUCCESSFUL);
				} else {
					return ResponseUtil.response(200, UIErrorKey.DELETING_SUCCESSFUL);
				}
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'user name': '" + userName + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
