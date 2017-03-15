package framework.bl.impl.data;

import java.util.List;

import javax.ws.rs.core.Response;

import org.apache.commons.lang3.StringUtils;
import org.dozer.MappingException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.bl.api.data.CountryBlApi;
import framework.bl.impl.mapper.CountryMapper;
import framework.db.dao.impl.CountryDaoImpl;
import framework.db.dbo.CountryDbo;
import framework.utils.translations.UIErrorKey;
import framework.web.wso.utils.ResponseUtil;

/**
 * Class contains business logic for manipulation with data about countries. <br>
 */
public class CountryBlImpl implements CountryBlApi {

	private static final Logger logger = LoggerFactory.getLogger(CountryBlImpl.class);

	/**
	 * Method, which returns information about country identified by ISO 3166-1 alpha-2 code. <br>
	 * 
	 * @param alpha2 - alpha-2 code, 2 characters which identifies country
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.CountryResponseWso country} - information about country <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no country was found <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#ALPHA2_CODE_IS_EMPTY ALPHA2_IS_EMPTY} - when entered alpha2 code is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#ALPHA2_CODE_LENGTH_IS_NOT_NVALID ALPHA2_LENGTH_IS_NOT_NVALID} - when length of alpha2 code is not equal to 2 characters <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server occurs <br>
	 * @see framework.web.wso.response.CountryResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response getByAlpha2(String alpha2) {
		
		if (StringUtils.isBlank(alpha2)) {
			return ResponseUtil.response(400, UIErrorKey.ALPHA2_CODE_IS_EMPTY);
		} else if (alpha2.length() != 2) {
			return ResponseUtil.response(400, UIErrorKey.ALPHA2_CODE_LENGTH_IS_NOT_NVALID);
		} else {
			
			try {
				
				CountryDbo countryDbo = new CountryDaoImpl().getByAlpha2(alpha2);
				return (countryDbo == null) ? ResponseUtil.response(204) : ResponseUtil.response(200, CountryMapper.map(countryDbo));
			} catch (MappingException e) {
				logger.error("MappingException: " + e.getMessage() + " (error when mapping objects); Entered parameter 'alpha2' is '" + alpha2 + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);	
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered parameter 'alpha2' is '" + alpha2 + "'");
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * Method, which returns information about country identified by ISO 3166-1 alpha-3 code. <br>
	 * 
	 * @param alpha3 - alpha-3 code, 3 characters which identifies country
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.CountryResponseWso country} - information about country <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no country was found <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#ALPHA3_CODE_IS_EMPTY ALPHA3_CODE_IS_EMPTY} - when entered alpha3 code is empty <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#ALPHA3_CODE_LENGTH_IS_NOT_NVALID ALPHA3_CODE_LENGTH_IS_NOT_NVALID} - when length of alpha3 code is not equal to 3 characters <br> 
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server occurs <br>
	 * @see framework.web.wso.response.CountryResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response getByAlpha3(String alpha3) {
		
		if (StringUtils.isBlank(alpha3)) {
			return ResponseUtil.response(400, UIErrorKey.ALPHA3_CODE_IS_EMPTY);
		} else if (alpha3.length() != 3) {
			return ResponseUtil.response(400, UIErrorKey.ALPHA3_CODE_LENGTH_IS_NOT_VALID);
		} else {
			
			try {
				
				CountryDbo countryDbo = new CountryDaoImpl().getByAlpha3(alpha3);
				return (countryDbo == null) ? ResponseUtil.response(204) : ResponseUtil.response(200, CountryMapper.map(countryDbo));
			} catch (MappingException e) {
				logger.error("MappingException: " + e.getMessage() + " (error when mapping objects); Entered parameter 'alpha3' is '" + alpha3 + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);	
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered parameter 'alpha3' is '" + alpha3 + "'");
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * Method, which returns information about country identified by numeric country code. <br>
	 * 
	 * @param countryCode - numeric country code which identifies country
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.CountryResponseWso country} - information about country <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no country was found <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 400 and flag {@linkplain framework.utils.translations.UIErrorKey#COUNTRY_CODE_IS_EMPTY COUNTRY_CODE_IS_EMPTY} - when entered country code is empty or less then 0<br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server occurs <br>
	 * @see framework.web.wso.response.CountryResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response getByCountryCode(Integer countryCode) {
		
		if (countryCode == null || countryCode < 0) {
			return ResponseUtil.response(400, UIErrorKey.COUNTRY_CODE_IS_EMPTY);
		} else {
			
			try {
				
				CountryDbo countryDbo = new CountryDaoImpl().getByCountryCode(countryCode);
				return (countryDbo == null) ? ResponseUtil.response(204) : ResponseUtil.response(200, CountryMapper.map(countryDbo));
			} catch (MappingException e) {
				logger.error("MappingException: " + e.getMessage() + " (error when mapping objects); Entered parameter 'countryCode' is '" + countryCode + "'", e);
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);	
			} catch (RuntimeException e) {
				logger.error("RuntimeException: " + e.getMessage() + "; Entered parameter 'country code' is '" + countryCode + "'");
				return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
			}
		}
	}

	/**
	 * Method, which returns list of all countries stored in the system and information related to them. <br>
	 * 
	 * @return
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 200 and {@linkplain framework.web.wso.response.ListOfCountriesResponseWso list of countries} - list of all countries <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 204 and empty content - if no country was found <br>
	 * {@linkplain javax.ws.rs.core.Response Response} with HTTP status 500 and flag {@linkplain framework.utils.translations.UIErrorKey#INTERNAL_SERVER_ERROR INTERNAL_SERVER_ERROR} - if an internal server occurs <br>
	 * @see framework.web.wso.response.ListOfCountriesResponseWso
	 * @see framework.web.wso.response.CountryResponseWso
	 * @see framework.utils.translations.UIErrorKey
	 * @see javax.ws.rs.core.Response
	 */
	@Override
	public Response getList() {
		
		try {
			
			List<CountryDbo> listOfCountriesDbo = new CountryDaoImpl().getList();
			return (listOfCountriesDbo == null || listOfCountriesDbo.isEmpty()) ? ResponseUtil.response(204) : ResponseUtil.response(200, CountryMapper.mapToResponse(listOfCountriesDbo));
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage());
			return ResponseUtil.response(500, UIErrorKey.INTERNAL_SERVER_ERROR);
		}
	}
}
