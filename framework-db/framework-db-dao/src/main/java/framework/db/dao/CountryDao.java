package framework.db.dao;

import java.util.List;

import framework.db.dbo.CountryDbo;

public interface CountryDao {

	public CountryDbo getByAlpha2(String alpha2);

	public CountryDbo getByAlpha3(String alpha3);

	public CountryDbo getByCountryCode(Integer countryCode);

	public List<CountryDbo> getList();
}
