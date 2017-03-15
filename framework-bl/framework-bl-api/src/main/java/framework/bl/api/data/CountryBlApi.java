package framework.bl.api.data;

import javax.ws.rs.core.Response;

/** 
 * Interface contains definitions of methods used for manipulation with data about countries. <br>
 */
public interface CountryBlApi {

	public Response getByAlpha2(String alpha2);
	
	public Response getByAlpha3(String alpha3);
	
	public Response getByCountryCode(Integer countryCode);
	
	public Response getList();
}
