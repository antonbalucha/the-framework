package framework.web.api.action.registeruser;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/action/activate-account/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface ActivateAccountApiV1 {

	@GET
	@Path("/activation-token/{activationToken}")
	public Response activateAccount(@PathParam("activationToken") String activationToken);
}
