package framework.web.wso.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import framework.web.wso.Wso;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UsersLanguageRequestWso extends Wso {

	private String usersLanguage;
	
	public String getUsersLanguage() {
		return this.usersLanguage;
	}

	public UsersLanguageRequestWso setUsersLanguage(String usersLanguage) {
		this.usersLanguage = usersLanguage;
		return this;
	}

	@Override
	public String toString() {
		return toJson();
	}
}
