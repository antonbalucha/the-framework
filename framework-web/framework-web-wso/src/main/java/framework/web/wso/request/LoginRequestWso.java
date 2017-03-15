package framework.web.wso.request;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import framework.web.wso.Wso;

@JsonIgnoreProperties(ignoreUnknown = true)
public class LoginRequestWso extends Wso {

	private String userName;

	private String password;

	private Boolean rememberMe;

	public String getUserName() {
		return this.userName;
	}

	public LoginRequestWso setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getPassword() {
		return this.password;
	}

	public LoginRequestWso setPassword(String password) {
		this.password = password;
		return this;
	}

	public Boolean getRememberMe() {
		return this.rememberMe;
	}

	public LoginRequestWso setRememberMe(Boolean rememberMe) {
		this.rememberMe = rememberMe;
		return this;
	}

	@Override
	public String toString() {
		return toJson();
	}
}
