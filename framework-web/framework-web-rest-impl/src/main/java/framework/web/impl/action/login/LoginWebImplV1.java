package framework.web.impl.action.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.action.login.LoginBlImpl;
import framework.web.api.action.login.LoginWebApiV1;
import framework.web.wso.request.LoginRequestWso;

@Path("/action/login/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LoginWebImplV1 implements LoginWebApiV1 {

	@POST
	public Response login(LoginRequestWso loginRequest) {
		return new LoginBlImpl().login(loginRequest);
	}
}
