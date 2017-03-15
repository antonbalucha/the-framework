package framework.web.wso.request;

import org.codehaus.jackson.annotate.JsonIgnoreProperties;

import framework.web.wso.Wso;

@JsonIgnoreProperties(ignoreUnknown = true)
public class UserReqistrationRequestWso extends Wso {
	
	private String userName;
	
	private String password;
	
	private String email;
	
	public String getUserName() {
		return this.userName;
	}

	public void setUserName(String userName) {
		this.userName = userName;
	}

	public String getPassword() {
		return this.password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getEmail() {
		return this.email;
	}

	public void setEmail(String email) {
		this.email = email;
	}
	
	@Override
	public String toString() {
		return toJson();
	}
}
