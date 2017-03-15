package framework.bl.api.data;

import javax.ws.rs.core.Response;

import framework.web.wso.request.UsersLanguageRequestWso;

/**
 * Interface defines interface for changing language of user. <br>
 */
public interface UsersLanguageBlApi {

	public Response getUsersLanguage();
	
	public Response setUsersLanguage(UsersLanguageRequestWso userLanguageReguest);
}
