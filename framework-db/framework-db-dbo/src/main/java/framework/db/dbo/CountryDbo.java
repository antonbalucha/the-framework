package framework.db.dbo;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;

import org.hibernate.validator.constraints.NotBlank;

import framework.utils.translations.DbErrorKey;

@Entity(name = "CountryDbo")
@Table(name = "Country")
public class CountryDbo implements Serializable {

	private static final long serialVersionUID = 8993949165050076304L;

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "Id", nullable = false, unique = true)
	private Long id;
	
	@NotNull(message = DbErrorKey.ALPHA2_IS_EMPTY)
	@NotBlank(message = DbErrorKey.ALPHA2_IS_EMPTY)
	@Column(name = "Alpha2", nullable = false, unique = true, insertable = true, updatable = true)
	private String alpha2;
	
	@NotNull(message = DbErrorKey.ALPHA3_IS_EMPTY)
	@NotBlank(message = DbErrorKey.ALPHA3_IS_EMPTY)
	@Column(name = "Alpha3", nullable = false, unique = true, insertable = true, updatable = true)
	private String alpha3;
	
	@NotNull(message = DbErrorKey.COUNTRY_CODE_IS_EMPTY)
	@NotBlank(message = DbErrorKey.COUNTRY_CODE_IS_EMPTY)
	@Column(name = "CountryCode", nullable = false, unique = true, insertable = true, updatable = true)
	private Integer countryCode;
	
	@Column(name = "CountryNameEN", nullable = true, unique = true, insertable = true, updatable = true)
	private String countryNameEN;
	
	@Column(name = "CountryNameSK", nullable = true, unique = true, insertable = true, updatable = true)
	private String countryNameSK;
	
	@Column(name = "CountryNameOriginal", nullable = true, unique = true, insertable = true, updatable = true)
	private String countryNameOriginal;
	
	@Column(name = "Region", nullable = true, unique = false, insertable = true, updatable = true)
	private String region;
	
	@Column(name = "RegionCode", nullable = true, unique = false, insertable = true, updatable = true)
	private String regionCode;
	
	@Column(name = "SubRegion", nullable = true, unique = false, insertable = true, updatable = true)
	private String subRegion;
	
	@Column(name = "SubRegionCode", nullable = true, unique = false, insertable = true, updatable = true)
	private String subRegionCode;

	public Long getId() {
		return this.id;
	}

	public CountryDbo setId(Long id) {
		this.id = id;
		return this;
	}

	public String getAlpha2() {
		return this.alpha2;
	}

	public CountryDbo setAlpha2(String alpha2) {
		this.alpha2 = alpha2;
		return this;
	}

	public String getAlpha3() {
		return this.alpha3;
	}

	public CountryDbo setAlpha3(String alpha3) {
		this.alpha3 = alpha3;
		return this;
	}

	public Integer getCountryCode() {
		return this.countryCode;
	}

	public CountryDbo setCountryCode(Integer countryCode) {
		this.countryCode = countryCode;
		return this;
	}

	public String getCountryNameEN() {
		return this.countryNameEN;
	}

	public CountryDbo setCountryNameEN(String countryNameEN) {
		this.countryNameEN = countryNameEN;
		return this;
	}

	public String getCountryNameSK() {
		return this.countryNameSK;
	}

	public CountryDbo setCountryNameSK(String countryNameSK) {
		this.countryNameSK = countryNameSK;
		return this;
	}

	public String getCountryNameOriginal() {
		return this.countryNameOriginal;
	}

	public CountryDbo setCountryNameOriginal(String countryNameOriginal) {
		this.countryNameOriginal = countryNameOriginal;
		return this;
	}
	
	public String getRegion() {
		return this.region;
	}

	public CountryDbo setRegion(String region) {
		this.region = region;
		return this;
	}

	public String getRegionCode() {
		return this.regionCode;
	}

	public CountryDbo setRegionCode(String regionCode) {
		this.regionCode = regionCode;
		return this;
	}

	public String getSubRegion() {
		return this.subRegion;
	}

	public CountryDbo setSubRegion(String subRegion) {
		this.subRegion = subRegion;
		return this;
	}

	public String getSubRegionCode() {
		return this.subRegionCode;
	}

	public CountryDbo setSubRegionCode(String subRegionCode) {
		this.subRegionCode = subRegionCode;
		return this;
	}

	@Override
	public String toString() {
		StringBuilder builder = new StringBuilder(CountryDbo.class.getSimpleName());
		builder.append(" [id=");
		builder.append(this.id);
		builder.append(", alpha2=");
		builder.append(this.alpha2);
		builder.append(", alpha3=");
		builder.append(this.alpha3);
		builder.append(", countryCode=");
		builder.append(this.countryCode);
		builder.append(", countryNameEN=");
		builder.append(this.countryNameEN);
		builder.append(", countryNameSK=");
		builder.append(this.countryNameSK);
		builder.append(", countryNameOriginal=");
		builder.append(this.countryNameOriginal);
		builder.append(", region=");
		builder.append(this.region);
		builder.append(", regionCode=");
		builder.append(this.regionCode);
		builder.append(", subRegion=");
		builder.append(this.subRegion);
		builder.append(", subRegionCode=");
		builder.append(this.subRegionCode);
		builder.append("]");
		return builder.toString();
	}
}
