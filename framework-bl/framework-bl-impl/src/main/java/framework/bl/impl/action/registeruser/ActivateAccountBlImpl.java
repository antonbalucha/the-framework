package framework.bl.impl.action.registeruser;

import javax.ws.rs.core.Response;

import org.apache.commons.lang.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.action.registeruser.ActivateAccountBlApi;
import framework.db.constant.UserStatus;
import framework.db.dao.impl.UserDaoImpl;
import framework.db.dbo.UserDbo;
import framework.utils.translations.UIErrorKey;
import framework.web.wso.utils.ResponseUtil;

/**
 * Class contains business logic for activation of user account. <br>
 */
public class ActivateAccountBlImpl implements ActivateAccountBlApi {

	private static final Logger logger = LoggerFactory.getLogger(ActivateAccountBlImpl.class);
	
	/**
	 * Method, which provides activation of user account. User account is activated by valid activation token which user retrieved via e-mail and is used as input of this method.
	 * 
	 * <p>
	 * If token is valid, database will be updated and user account will be activated - flag of user status will be changed to {@linkplain framework.db.constant.UserStatus#A A} (as activated). 
	 * Then, method returns {@linkplain javax.ws.rs.core.Response Response} object with HTTP status 200 and content with flag 
	 * {@linkplain framework.utils.translations.UIErrorKey#ACTIVATION_SUCCESSFUL ACTIVATION_SUCCESSFUL}.
	 * </p>
	 * 
	 * <p>
	 * If token is not valid, database will not be updated or effected. Then, method returns {@linkplain javax.ws.rs.core.Response Response} object 
	 * with HTTP status 400 and content with flag {@linkplain framework.utils.translations.UIErrorKey#ACTIVATION_NOT_SUCCESSFUL ACTIVATION_NOT_SUCCESSFUL}.
	 * </p>
	 * 
	 * @param activation token - activation token sent via e-mail for e-mail activation
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#ACTIVATION_SUCCESSFUL ACTIVATION_SUCCESSFUL} - activation was successful <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#ACTIVATION_NOT_SUCCESSFUL ACTIVATION_NOT_SUCCESSFUL} - activation was not successful <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - when internal server error occurs <br>
	 * @see framework.db.constant.UserStatus
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override	
	public Response activateAccount(String activationToken) {
		
		// validate activation token
		if (StringUtils.isBlank(activationToken)) {
			return ResponseUtil.response(400, UIErrorKey.ACTIVATION_TOKEN_IS_EMPTY);
		} else {
		
			try {
				
				UserDbo user = new UserDbo()
					.setStatus(UserStatus.A)
					.setActivationToken(activationToken);
				
				// update account and its status identified by activation token
				Boolean isUserUpdated = new UserDaoImpl().updateStatusByActivationToken(user);
				
				if (!isUserUpdated) {
					logger.error("Problem when activating user with entered 'activation token': '" + activationToken + "'");
					return ResponseUtil.response(400, UIErrorKey.ACTIVATION_NOT_SUCCESSFUL);
				} else {
					return ResponseUtil.response(200, UIErrorKey.ACTIVATION_SUCCESSFUL);
				}
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'activation token': '" + activationToken + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
