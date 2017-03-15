package framework.bl.impl.mapper;

import java.util.ArrayList;
import java.util.List;

import framework.db.dbo.UserDbo;
import framework.utils.MapperUtils;
import framework.web.wso.response.ListOfUsersResponseWso;
import framework.web.wso.response.UserResponseWso;

/**
 * Helper class which contains methods used for mapping between various objects. <br> 
 */
public class UserMapper {

	public static final UserResponseWso map(UserDbo dbo) {
		UserResponseWso wso = new UserResponseWso();
		MapperUtils.getMapper().map(dbo, wso);
		return wso;
	}
	
	public static final List<UserResponseWso> map(List<UserDbo> listOfUsersDbo) {
		
		List<UserResponseWso> listOfUsersWso = new ArrayList<UserResponseWso>(listOfUsersDbo.size());
		
		for (int i = 0; i < listOfUsersDbo.size(); i++) {
			listOfUsersWso.add(map(listOfUsersDbo.get(i)));
		}
		
		return listOfUsersWso;
	}
	
	public static final ListOfUsersResponseWso mapToResponse(List<UserDbo> listOfUsersDbo) {
		return new ListOfUsersResponseWso().setListOfUsers(map(listOfUsersDbo));
	}
}
