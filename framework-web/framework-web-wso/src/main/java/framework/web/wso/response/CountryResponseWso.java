package framework.web.wso.response;

import framework.web.wso.Wso;

public class CountryResponseWso extends Wso {

	private String alpha2;
	
	private String alpha3;

	private Integer countryCode;
	
	private String countryNameEN;
	
	private String countryNameSK;
	
	private String countryNameOriginal;
	
	private String region;
	
	private String regionCode;
	
	private String subRegion;
	
	private String subRegionCode;
	
	public String getAlpha2() {
		return this.alpha2;
	}

	public CountryResponseWso setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
		return this;
	}

	public String getAlpha3() {
		return this.alpha3;
	}

	public CountryResponseWso setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
		return this;
	}
	
	public Integer getCountryCode() {
		return this.countryCode;
	}

	public CountryResponseWso setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	public String getCountryNameEN() {
		return this.countryNameEN;
	}

	public CountryResponseWso setCountryNameEN(String countryNameEN) {
		this.countryNameEN = countryNameEN;
		return this;
	}

	public String getCountryNameSK() {
		return this.countryNameSK;
	}

	public CountryResponseWso setCountryNameSK(String countryNameSK) {
		this.countryNameSK = countryNameSK;
		return this;
	}

	public String getCountryNameOriginal() {
		return this.countryNameOriginal;
	}

	public CountryResponseWso setCountryNameOriginal(String countryNameOriginal) {
		this.countryNameOriginal = countryNameOriginal;
		return this;
	}

	public String getRegion() {
		return this.region;
	}

	public CountryResponseWso setRegion(String region) {
		this.region = region;
		return this;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public CountryResponseWso setRegionCode(String regionCode) {
		this.regionCode = regionCode;
		return this;
	}

	public String getSubRegion() {
		return this.subRegion;
	}

	public CountryResponseWso setSubRegion(String subRegion) {
		this.subRegion = subRegion;
		return this;
	}

	public String getSubRegionCode() {
		return this.subRegionCode;
	}

	public CountryResponseWso setSubRegionCode(String subRegionCode) {
		this.subRegionCode = subRegionCode;
		return this;
	}

	@Override
	public String toString() {
		return toJson();
	}
}
