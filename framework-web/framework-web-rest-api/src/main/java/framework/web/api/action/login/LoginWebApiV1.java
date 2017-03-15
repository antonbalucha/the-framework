package framework.web.api.action.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.web.wso.request.LoginRequestWso;

@Path("/action/login/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface LoginWebApiV1 {

	@POST
	public Response login(LoginRequestWso loginRequest);
}
