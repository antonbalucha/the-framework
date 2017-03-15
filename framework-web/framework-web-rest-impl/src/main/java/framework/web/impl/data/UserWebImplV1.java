package framework.web.impl.data;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.data.UserBlImpl;
import framework.web.api.data.UserWebApiV1;
import framework.web.wso.request.UserReqistrationRequestWso;

@Path("/action/user/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserWebImplV1 implements UserWebApiV1 {

	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") Long id) {
		return new UserBlImpl().get(id);
	}
	
	@GET
	@Path("/")
	public Response list(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset) {
		return (limit == null || limit < 0 || offset == null || offset < 0) ? new UserBlImpl().list() : new UserBlImpl().list(limit, offset);
	}
	
	@GET
	@Path("/user-name/{userName}")
	public Response get(@PathParam("userName") String userName) {
		return new UserBlImpl().get(userName);
	}

	@POST
	@Path("/register/")
	public Response register(UserReqistrationRequestWso registerUserRequest) {
		return new UserBlImpl().register(registerUserRequest);
	}

	@POST
	@Path("/activate/{userName}")
	public Response activate(String userName) {
		return new UserBlImpl().activate(userName);
	}

	@POST
	@Path("/inactivate/{userName}")
	public Response inactivate(String userName) {
		return new UserBlImpl().inactivate(userName);
	}

	@POST
	@Path("/ban/{userName}")
	public Response ban(String userName) {
		return new UserBlImpl().ban(userName);
	}

	@POST
	@Path("/delete/{userName}")
	public Response delete(String userName) {
		return new UserBlImpl().delete(userName);
	}
}
