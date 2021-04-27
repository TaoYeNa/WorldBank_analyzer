import java.util.HashMap;

public class countryList {
	private String fullName, abbName, countryComments, iso2, iso3, endYear,startYear;
	private int countryCode;
	HashMap countryMap = new HashMap();
	
	public String getFullName() {
		return fullName;
	}
	public String getAbbName() {
		return abbName;
	}
	public String getCountryComments() {
		return countryComments;
	}
	public String getIso2() {
		return iso2;
	}
	public String getIso3() {
		return iso3;
	}
	public int getCountryCode() {
		return countryCode;
	}
	public String getStartYear() {
		return startYear;
	}
	public String getEndYear() {
		return endYear;
	}
	public void setFullName(String fullName) {
		this.fullName = fullName;
	}
	public void setAbbName(String abbName) {
		this.abbName = abbName;
	}
	public void setCountryComments(String countryComments) {
		this.countryComments = countryComments;
	}
	public void setIso2(String iso2) {
		this.iso2 = iso2;
	}
	public void setIso3(String iso3) {
		this.iso3 = iso3;
	}
	public void setCountryCode(int countryCode) {
		this.countryCode = countryCode;
	}
	public void setStartYear(String startYear) {
		this.startYear = startYear;
	}
	public void setEndYear(String endYear) {
		this.endYear = endYear;
	}
	
	public HashMap getCountryMap() {
		return countryMap;
	}
	public void setCountryMap(HashMap countryMap) {
		this.countryMap = countryMap;
	}
	@Override
	public String toString() {
		return "countryList [fullName=" + fullName + ", abbName=" + abbName + ", countryComments=" + countryComments
				+ ", iso2=" + iso2 + ", iso3=" + iso3 + ", countryCode=" + countryCode + ", startYear=" + startYear
				+ ", endYear=" + endYear + "]";
	}
	
	
}