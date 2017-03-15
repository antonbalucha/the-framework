package framework.web.wso.response;

import framework.web.wso.Wso;

public class UserResponseWso extends Wso {

	private Long id;
	
	private String userName;
	
	private String email;
	
	private String status;

	public Long getId() {
		return this.id;
	}
	
	public UserResponseWso setId(Long id) {
		this.id = id;
		return this;
	}

	public String getUserName() {
		return this.userName;
	}

	public UserResponseWso setUserName(String userName) {
		this.userName = userName;
		return this;
	}

	public String getEmail() {
		return this.email;
	}

	public UserResponseWso setEmail(String email) {
		this.email = email;
		return this;
	}
	
	public String getStatus() {
		return this.status;
	}

	public UserResponseWso setStatus(String status) {
		this.status = status;
		return this;
	}

	@Override
	public String toString() {
		return toJson();
	}
}
