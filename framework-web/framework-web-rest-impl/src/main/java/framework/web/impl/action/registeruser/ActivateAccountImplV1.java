package framework.web.impl.action.registeruser;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.action.registeruser.ActivateAccountBlImpl;
import framework.web.api.action.registeruser.ActivateAccountApiV1;

@Path("/action/activate-account/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ActivateAccountImplV1 implements ActivateAccountApiV1 {

	@GET
	@Path("/activation-token/{activationToken}")
	public Response activateAccount(@PathParam("activationToken") String activationToken) {
		return new ActivateAccountBlImpl().activateAccount(activationToken);
	}
}
