package framework.web.wso.response;

import java.util.ArrayList;
import java.util.List;

import framework.web.wso.Wso;

public class ListOfUsersResponseWso extends Wso {

	private List<UserResponseWso> listOfUsers = new ArrayList<UserResponseWso>(0);

	public List<UserResponseWso> getListOfUsers() {
		return this.listOfUsers;
	}

	public ListOfUsersResponseWso setListOfUsers(List<UserResponseWso> listOfUsers) {
		this.listOfUsers = listOfUsers;
		return this;
	}
	
	@Override
	public String toString() {
		return toJson();
	}	
}
