package framework.web.impl.action.registeruser;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.action.registeruser.RegisterUserBlImpl;
import framework.web.api.action.registeruser.RegisterUserWebApiV1;
import framework.web.wso.request.UserReqistrationRequestWso;

@Path("/action/register-user/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class RegisterUserWebImplV1 implements RegisterUserWebApiV1 {
	
	@POST
	public Response registerUser(UserReqistrationRequestWso registerUserRequest) {
		return new RegisterUserBlImpl().registerUser(registerUserRequest);
	}
}
