package framework.bl.api.action.registeruser;

import javax.ws.rs.core.Response;

import framework.web.wso.request.UserReqistrationRequestWso;

/** 
 * Interface contains definitions of methods used for registration of new users. <br>
 */
public interface RegisterUserBlApi {

	public Response registerUser(UserReqistrationRequestWso registerUserRequest);
}
