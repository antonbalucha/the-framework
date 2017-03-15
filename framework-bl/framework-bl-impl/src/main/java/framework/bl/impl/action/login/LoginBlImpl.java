package framework.bl.impl.action.login;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.authc.AuthenticationException;
import org.apache.shiro.authc.IncorrectCredentialsException;
import org.apache.shiro.authc.LockedAccountException;
import org.apache.shiro.authc.UnknownAccountException;
import org.apache.shiro.authc.UsernamePasswordToken;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.action.login.LoginBlApi;
import framework.db.dao.impl.UserDaoImpl;
import framework.utils.SessionUtils;
import framework.utils.translations.UIErrorKey;
import framework.web.wso.request.LoginRequestWso;
import framework.web.wso.utils.ResponseUtil;

/** 
 * Class contains business logic for processing of log in into the system. <br>
 */
public class LoginBlImpl implements LoginBlApi {

	private static final Logger logger = LoggerFactory.getLogger(LoginBlImpl.class);
	
	/**
	 * Method provides log in into system. You may be logged into the system only when:
	 * <ul>
	 * <li>user is correctly registered with all its data</li>
	 * <li>user status is {@linkplain framework.db.constant.UserStatus#A A} (active)</li>
	 * <li>user entered valid credentials</li>
	 * </ul>
	 * 
	 * @param loginRequest - object, which contains data necessary for logging to the system
	 * @return 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_SUCCESSFULLY_LOGGED_IN USER_SUCCESSFULLY_LOGGED_IN} - if credentials are valid <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#REQUEST_IS_EMPTY REQUEST_IS_EMPTY} - if entered loginRequest is null <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_IS_EMPTY USER_NAME_IS_EMPTY} - if entered user name is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#PASSWORD_IS_EMPTY PASSWORD_IS_EMPTY} - if entered password is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 401 and flag {@linkplain framework.utils.translations.UIErrorKey#CREDENTIALS_NOT_VALID CREDENTIALS_NOT_VALID} - if entered credentials are not valid <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 401 and flag {@linkplain framework.utils.translations.UIErrorKey#LOCKED_ACCOUNT LOCKED_ACCOUNT} - if account is locked <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 401 and flag {@linkplain framework.utils.translations.UIErrorKey#UNKNOWN_PROBLEM_WITH_AUTHENTICATION UNKNOWN_PROBLEM_WITH_AUTHENTICATION} - if there was an unknown problem with authentication <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if internal server error occurs <br>
	 * @see javax.ws.rs.core.Response
	 * @see framework.utils.translations.UIErrorKey
	 * @see framework.db.constant.UserStatus
	 */
	@Override
	public Response login(LoginRequestWso loginRequest) {
		
		// verify input
		if (loginRequest == null) {
			return ResponseUtil.response(400, UIErrorKey.REQUEST_IS_EMPTY);
		} else if (StringUtils.isBlank(loginRequest.getUserName())) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else if (StringUtils.isBlank(loginRequest.getPassword())) {	
			return ResponseUtil.response(400, UIErrorKey.PASSWORD_IS_EMPTY);
		} else {
			
			// login into system
			try {
				UsernamePasswordToken token = new UsernamePasswordToken(loginRequest.getUserName(), loginRequest.getPassword(), loginRequest.getRememberMe());
				Subject subject = SecurityUtils.getSubject();
				
				if (subject.isAuthenticated()) {
					subject.logout();
				}

				// login
				subject.login(token);
				
				// actions after log in
				// set up user's language
				SessionUtils.setUserSessionLanguage(new UserDaoImpl().selectLanguage(loginRequest.getUserName()));
				
				return ResponseUtil.response(200, UIErrorKey.USER_SUCCESSFULLY_LOGGED_IN);
			
			// catch exceptions in case of some problems	
			} catch (UnknownAccountException e) {
				logger.error("UnknownAccountException: " + e.getMessage() + "; Entered 'user name': '" + loginRequest.getUserName() + "'");
				return ResponseUtil.response(401, UIErrorKey.CREDENTIALS_NOT_VALID);
			} catch (IncorrectCredentialsException e) {
				logger.error("IncorrectCredentialsException: " + e.getMessage() + "; Entered 'user name': '" + loginRequest.getUserName() + "'");
				return ResponseUtil.response(401, UIErrorKey.CREDENTIALS_NOT_VALID);
			} catch (LockedAccountException e) {
				logger.error("LockedAccountException: " + e.getMessage() + "; Entered 'user name': '" + loginRequest.getUserName() + "'");
				return ResponseUtil.response(401, UIErrorKey.LOCKED_ACCOUNT);
			} catch (AuthenticationException e) {
				logger.error("AuthenticationException: " + e.getMessage() + "; Entered 'user name': '" + loginRequest.getUserName() + "'");
				return ResponseUtil.response(401, UIErrorKey.UNKNOWN_PROBLEM_WITH_AUTHENTICATION);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'user name': '" + loginRequest.getUserName() + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
