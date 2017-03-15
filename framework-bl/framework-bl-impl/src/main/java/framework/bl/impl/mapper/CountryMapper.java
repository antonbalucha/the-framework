package framework.bl.impl.mapper;

import java.util.ArrayList;
import java.util.List;

import framework.db.dbo.CountryDbo;
import framework.utils.MapperUtils;
import framework.web.wso.response.CountryResponseWso;
import framework.web.wso.response.ListOfCountriesResponseWso;

/**
 * Helper class which contains methods used for mapping between various objects. <br> 
 */
public class CountryMapper {

	public static final CountryResponseWso map(CountryDbo dbo) {
		CountryResponseWso wso = new CountryResponseWso();
		MapperUtils.getMapper().map(dbo, wso);
		return wso;
	}
	
	public static final List<CountryResponseWso> map(List<CountryDbo> listOfCountriesDbo) {
		
		List<CountryResponseWso> listOfCountriesWso = new ArrayList<CountryResponseWso>(listOfCountriesDbo.size());
		
		for (int i = 0; i < listOfCountriesDbo.size(); i++) {
			listOfCountriesWso.add(map(listOfCountriesDbo.get(i)));
		}
		
		return listOfCountriesWso;
	}

	public static ListOfCountriesResponseWso mapToResponse(List<CountryDbo> listOfCountriesDbo) {
		return new ListOfCountriesResponseWso().setListOfCountries(map(listOfCountriesDbo));
	}
}
