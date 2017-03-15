package framework.bl.api.action.registeruser;

import javax.ws.rs.core.Response;

/** 
 * Interface contains definitions of methods used for verification of data entered on UI for better UX. <br>
 */
public interface VerifyRegistrationDataBlApi {

	public Response verifyUserName(String userName);
	
	public Response verifyEmail(String email);
}
