package framework.web.impl.action.login;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.action.login.LogoutBlImpl;
import framework.web.api.action.login.LogoutWebApiV1;

@Path("/action/logout/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class LogoutWebImplV1 implements LogoutWebApiV1 {

	@GET
	public Response logout() {
		return new LogoutBlImpl().logout();
	}
}
