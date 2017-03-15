package framework.bl.impl.action.registeruser;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.action.registeruser.VerifyRegistrationDataBlApi;
import framework.db.dao.impl.UserDaoImpl;
import framework.utils.translations.UIErrorKey;
import framework.web.wso.response.BooleanResponseWso;
import framework.web.wso.utils.ResponseUtil;

/**
 * Class contains business logic verification of registration data entered on UI for better UX. <br>
 */
public class VerifyRegistrationDataBlImpl implements VerifyRegistrationDataBlApi {

	private static final Logger logger = LoggerFactory.getLogger(VerifyRegistrationDataBlImpl.class);

	/**
	 * Method verifies whether entered user name exists or not. <br>
	 * 
	 * @param userName - user name which will be searched whether it exists or not
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.BooleanResponseWso true} - if user already exists <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.BooleanResponseWso false} - if user do not exists <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USER_NAME_IS_EMPTY USER_NAME_IS_EMPTY}- if entered user name is null or empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
	 * @see framework.web.wso.response.BooleanResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response verifyUserName(String userName) {
		
		if (StringUtils.isBlank(userName)) {
			return ResponseUtil.response(400, UIErrorKey.USER_NAME_IS_EMPTY);
		} else {
			
			try {
				BooleanResponseWso response = new BooleanResponseWso().setResult(new UserDaoImpl().existsByUserName(userName));
				return ResponseUtil.response(200, response);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'userName': '" + userName + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * Method verifies whether entered e-mail exists or not. <br>
	 * 
	 * @param email - e-mail which will be searched whether it exists or not
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.BooleanResponseWso true} - if e-mail exists <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.BooleanResponseWso false} - if e-mail do not exists <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#EMAIL_IS_EMPTY EMAIL_IS_EMPTY}- if entered e-mail is null or empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server error occurs <br>
	 * @see framework.web.wso.response.BooleanResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response verifyEmail(String email) {
		
		if (StringUtils.isBlank(email)) {
			return ResponseUtil.response(400, UIErrorKey.EMAIL_IS_EMPTY);
		} else {
			
			try {
				BooleanResponseWso response = new BooleanResponseWso().setResult(new UserDaoImpl().existsByEmail(email));
				return ResponseUtil.response(200, response);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered 'email': '" + email + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
