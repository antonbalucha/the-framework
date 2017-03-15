package framework.web.impl.action.registeruser;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.action.registeruser.VerifyRegistrationDataBlImpl;
import framework.web.api.action.registeruser.VerifyRegistrationDataWebApiV1;

@Path("/action/verify/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class VerifyRegistrationDataWebImplV1 implements VerifyRegistrationDataWebApiV1 {

	@GET
	@Path("/user-name/{userName}")
	public Response verifyUserName(@PathParam("userName") String userName) {
		return new VerifyRegistrationDataBlImpl().verifyUserName(userName);
	}
	
	@GET
	@Path("/email/{email}")
	public Response verifyEmail(@PathParam("email") String email) {
		return new VerifyRegistrationDataBlImpl().verifyEmail(email);
	}
}
