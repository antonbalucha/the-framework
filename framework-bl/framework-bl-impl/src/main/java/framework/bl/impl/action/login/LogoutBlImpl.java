package framework.bl.impl.action.login;

import javax.ws.rs.core.Response;

import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.action.login.LogoutBlApi;
import framework.utils.translations.UIErrorKey;
import framework.web.wso.utils.ResponseUtil;

/** 
 * Class contains business logic for processing of log out of the system. <br>
 */
public class LogoutBlImpl implements LogoutBlApi {

	private static final Logger logger = LoggerFactory.getLogger(LogoutBlImpl.class);
	
	/** 
	 * Method which provides log out of the system. There are no special conditions for log out, when log out will be called, user will logged out without any other conditions or questions. <br>
	 * 
     * @return 
     * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_SUCCESSFULLY_LOGGED_OUT USER_SUCCESSFULLY_LOGGED_OUT}- if user was successfully logged out <br>
     * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
     * @see framework.db.constant.UserStatus
     * @see framework.utils.translations.UIErrorKey
     * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response logout() {
		
		try {
		
			Subject subject = SecurityUtils.getSubject();
			
			if (subject.isAuthenticated()) {
				subject.logout();
			}
			
			return ResponseUtil.response(200, UIErrorKey.USER_SUCCESSFULLY_LOGGED_OUT);
			
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage(), e);
			return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
		}
	}
}
