package framework.web.impl.data;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import framework.bl.impl.data.CountryBlImpl;
import framework.web.api.data.CountryWebApiV1;

@Path("/data/country/v1")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class CountryWebImplV1 implements CountryWebApiV1 {

	@GET
	@Path("/byAlpha2/{alpha2}")
	public Response getByAplha2(@PathParam("alpha2") String alpha2) {
		return new CountryBlImpl().getByAlpha2(alpha2);
	}

	@GET
	@Path("/byAlpha3/{alpha3}")
	public Response getByAlpha3(@PathParam("alpha3") String alpha3) {
		return new CountryBlImpl().getByAlpha3(alpha3);
	}

	@GET
	@Path("/byCountryCode/{countryCode}")
	public Response getByCountryCode(@PathParam("countryCode") Integer countryCode) {
		return new CountryBlImpl().getByCountryCode(countryCode);
	}
	
	@GET
	public Response getList() {
		return new CountryBlImpl().getList();
	}
}
