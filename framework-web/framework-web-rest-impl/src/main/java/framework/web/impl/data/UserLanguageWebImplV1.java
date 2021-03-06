package framework.web.impl.data;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.data.UsersLanguageBlImpl;
import framework.web.api.data.UserLanguageApiV1;
import framework.web.wso.request.UsersLanguageRequestWso;

@Path("/data/language/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class UserLanguageWebImplV1 implements UserLanguageApiV1 {
	
	@GET
	public Response getUsersLanguage() {
		return new UsersLanguageBlImpl().getUsersLanguage();
	}
	
	@POST
	public Response setUsersLanguage(UsersLanguageRequestWso userLanguageReguest) {
		return new UsersLanguageBlImpl().setUsersLanguage(userLanguageReguest);
	}
}
