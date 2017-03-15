package framework.web.wso.response;

import framework.web.wso.Wso;

public class UsersLanguageResponseWso extends Wso {

	private String usersLanguage;
	
	public String getUsersLanguage() {
		return this.usersLanguage;
	}

	public UsersLanguageResponseWso setUsersLanguage(String usersLanguage) {
		this.usersLanguage = usersLanguage;
		return this;
	}

	@Override
	public String toString() {
		return toJson();
	}
}
