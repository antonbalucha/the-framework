package framework.bl.api.action.registeruser;

import javax.ws.rs.core.Response;

/** 
 * Interface contains definitions of methods used for activation of users account. <br>
 */
public interface ActivateAccountBlApi {

	public Response activateAccount(String activationToken);
}
