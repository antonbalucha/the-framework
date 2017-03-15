package framework.bl.impl.data;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;
import org.apache.shiro.subject.Subject;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.data.UsersLanguageBlApi;
import framework.db.dao.impl.UserDaoImpl;
import framework.db.dbo.UserDbo;
import framework.utils.LanguageUtils;
import framework.utils.SessionUtils;
import framework.utils.translations.UIErrorKey;
import framework.web.wso.request.UsersLanguageRequestWso;
import framework.web.wso.response.UsersLanguageResponseWso;
import framework.web.wso.utils.ResponseUtil;

/**
 * Class contains business logic for processing of users language.
 */
public class UsersLanguageBlImpl implements UsersLanguageBlApi {

	private static final Logger logger = LoggerFactory.getLogger(UsersLanguageBlImpl.class);
	
	/**
	 * Method, which returns users language. <br>
	 * 
	 * @return 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.UsersLanguageResponseWso users language} - returns users language <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server occurs <br>
	 * @see framework.web.wso.response.UsersLanguageResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response getUsersLanguage() {
		try {
			UsersLanguageResponseWso usersLanguageResponse = new UsersLanguageResponseWso().setUsersLanguage(SessionUtils.getUserSessionLanguage());
			return ResponseUtil.response(200, usersLanguageResponse);
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage(), e);
			return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
		}
	}

	/**
	 * Method, which set up users language. <br>
	 * 
	 * @param usersLanguageReguest - language, which should be set to user
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and flag {@linkplain framework.utils.translations.UIErrorKey#LANGUAGE_SUCCESSFULLY_SET LANGUAGE_SUCCESSFULLY_SET} - if users language was correctly set <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#REQUEST_IS_EMPTY REQUEST_IS_EMPTY} - if request is null <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#USERS_LANGUAGE_IS_EMPTY USERS_LANGUAGE_IS_EMPTY} - if entered language is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#LANGUAGE_IS_NOT_SUPPORTED LANGUAGE_IS_NOT_SUPPORTED} - if language is not supported <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server occurs <br>
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response setUsersLanguage(UsersLanguageRequestWso usersLanguageReguest) {
		
		if (usersLanguageReguest == null) {
			return ResponseUtil.response(400, UIErrorKey.REQUEST_IS_EMPTY);
		} else if (StringUtils.isBlank(usersLanguageReguest.getUsersLanguage())) {
			return ResponseUtil.response(400, UIErrorKey.USERS_LANGUAGE_IS_EMPTY);
		} else if (LanguageUtils.isSupportedLanguage(usersLanguageReguest.getUsersLanguage())) {
			return ResponseUtil.response(400, UIErrorKey.LANGUAGE_IS_NOT_SUPPORTED);
		} else {
			
			try {
			
				// set users language to session
				SessionUtils.setUserSessionLanguage(usersLanguageReguest.getUsersLanguage());
				
				// save language to database
				Subject subject = SecurityUtils.getSubject();
				
				if (subject != null && subject.isAuthenticated()) {
					String userName = (String) subject.getPrincipal();
					
					UserDbo user = new UserDbo()
						.setUserName(userName)
						.setLanguage(usersLanguageReguest.getUsersLanguage());
					
					new UserDaoImpl().updateLanguageByUserName(user);
				}
				
				return ResponseUtil.response(200, UIErrorKey.LANGUAGE_SUCCESSFULLY_SET);
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage(), e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}
}
