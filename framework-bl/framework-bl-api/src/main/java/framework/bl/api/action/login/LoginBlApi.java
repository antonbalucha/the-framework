package framework.bl.api.action.login;

import javax.ws.rs.core.Response;

import framework.web.wso.request.LoginRequestWso;

/** 
 * Interface contains definitions of methods used for log in to the system. <br>
 */
public interface LoginBlApi {
	
	public Response login(LoginRequestWso loginRequest);
}
