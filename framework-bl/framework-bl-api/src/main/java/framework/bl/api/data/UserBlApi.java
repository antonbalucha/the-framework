package framework.bl.api.data;

import javax.ws.rs.core.Response;

import framework.web.wso.request.UserReqistrationRequestWso;

/** 
 * Interface contains definitions of methods used for manipulation with data about users. <br>
 */
public interface UserBlApi {

	public Response get(Long id);
		
	public Response get(String userName);
	
	public Response list();
	
	public Response list(Integer limit, Integer offset);
	
	public Response register(UserReqistrationRequestWso registerUserRequest); 
	
	public Response activate(String userName);
	
	public Response inactivate(String userName);
	
	public Response ban(String userName);
	
	public Response delete(String userName);
}
