package framework.web.api.data;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

@Path("/data/country/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public interface CountryWebApiV1 {

	@GET
	@Path("/byAlpha2/{alpha2}")
	public Response getByAplha2(@PathParam("alpha2") String alpha2);
	
	@GET
	@Path("/byAlpha3/{alpha3}")
	public Response getByAlpha3(@PathParam("alpha3") String alpha3);
	
	@GET
	@Path("/byCountryCode/{countryCode}")
	public Response getByCountryCode(@PathParam("countryCode") Integer countryCode);
	
	@GET
	public Response getList();
}
