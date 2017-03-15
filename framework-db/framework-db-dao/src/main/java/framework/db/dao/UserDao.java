package framework.db.dao;

import java.util.List;

import framework.db.dbo.UserDbo;

public interface UserDao {

	public UserDbo select(Long id);
		
	public UserDbo select(String userName);
	
	public String selectLanguage(String userName);
	
	public Boolean existsById(Long id);
	
	public Boolean existsByUserName(String userName);
	
	public Boolean existsByEmail(String email);
	
	public UserDbo insert(UserDbo userDbo);
		
	public Boolean updateStatusByActivationToken(UserDbo userDbo);
	
	public Boolean updateStatusByUserName(UserDbo userDbo);
	
	public Boolean updateLanguageByUserName(UserDbo userDbo);
	
	public List<UserDbo> list();
	
	public List<UserDbo> list(Integer limit, Integer offset);
}
