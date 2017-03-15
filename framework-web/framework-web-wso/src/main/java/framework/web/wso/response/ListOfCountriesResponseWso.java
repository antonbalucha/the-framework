package framework.web.wso.response;

import java.util.ArrayList;
import java.util.List;

import framework.web.wso.Wso;

public class ListOfCountriesResponseWso extends Wso {

	private List<CountryResponseWso> listOfCountries = new ArrayList<CountryResponseWso>(0);

	public List<CountryResponseWso> getListOfCountries() {
		return this.listOfCountries;
	}

	public ListOfCountriesResponseWso setListOfCountries(List<CountryResponseWso> listOfCountries) {
		this.listOfCountries = listOfCountries;
		return this;
	}	
	
	@Override
	public String toString() {
		return toJson();
	}
}
