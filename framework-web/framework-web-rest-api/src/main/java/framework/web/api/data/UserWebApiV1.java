package framework.web.api.data;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.web.wso.request.UserReqistrationRequestWso;

@Path("/data/user/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface UserWebApiV1 {

	@GET
	@Path("/{id}")
	public Response get(@PathParam("id") Long id);
	
	@GET
	@Path("/user-name/{userName}")
	public Response get(@PathParam("userName") String userName);
	
	@GET
	@Path("/")
	public Response list(@QueryParam("limit") Integer limit, @QueryParam("offset") Integer offset);
	
	@POST
	@Path("/register/")
	public Response register(UserReqistrationRequestWso registerUserRequest); 
	
	@POST
	@Path("/activate/{userName}")
	public Response activate(@PathParam("userName") String userName);
	
	@POST
	@Path("/inactivate/{userName}")
	public Response inactivate(@PathParam("userName") String userName);
	
	@POST
	@Path("/ban/{userName}")
	public Response ban(@PathParam("userName") String userName);
	
	@POST
	@Path("/delete/{userName}")
	public Response delete(@PathParam("userName") String userName);
}
