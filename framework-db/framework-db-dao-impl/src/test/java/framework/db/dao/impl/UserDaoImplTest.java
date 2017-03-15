package framework.db.dao.impl;

import javax.persistence.Persistence;

import org.junit.Assert;
import org.junit.BeforeClass;
import org.junit.Test;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.db.constant.UserStatus;
import framework.db.dao.UserDao;
import framework.db.dbo.UserDbo;
import framework.db.utils.ConnectorManager;

public class UserDaoImplTest {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImplTest.class);
	
	private static final UserDao userDao = new UserDaoImpl();
	
	@BeforeClass
	public static void initialize() {
		createDatabaseTables();
	}
	
	public static void createDatabaseTables() {
		Persistence.generateSchema(ConnectorManager.getDefaultPersistenceUnitName(), null);
	}
	
	public static UserDbo getNewUserDbo() {

		long time = System.currentTimeMillis();
		
		UserDbo userDbo = new UserDbo()
			.setUserName("testUserName" + time)
			.setEmail("testEmail" + time)
			.setPassword("testPassword" + time)
			.setSalt("testSalt" + time)
			.setStatus(UserStatus.D)
			.setActivationToken("testActivationToken" + time)
			.setLanguage("en");
		
		return userDbo;
	}
	
	@Test
	public void testInsert() {
		logger.info("****************************************************************************************************");
		logger.info("testInsert() - insert new user, check if inserted and id != null and id > 0");
		
		UserDbo userDbo = getNewUserDbo();
		UserDbo insertedUser = userDao.insert(userDbo);
		logger.info("Id of inserted user = " + insertedUser.getId());
		
		Assert.assertNotNull(insertedUser.getId());
		Assert.assertTrue(insertedUser.getId() > 0);
	}
	
	@Test
	public void testSelect() {
		logger.info("****************************************************************************************************");
		logger.info("testSelect() - insert new user, select inserted, check if attributes are qeual");
		
		UserDbo userDbo = getNewUserDbo();
		UserDbo insertedUser = userDao.insert(userDbo);
		logger.info("Id of inserted user = " + insertedUser.getId());
		
		UserDbo selectedUser = userDao.select(insertedUser.getId());
		logger.info("Id of selected user = " + selectedUser.getId());
		
		Assert.assertEquals(insertedUser.getId(), selectedUser.getId());
		Assert.assertEquals(insertedUser.getUserName(), selectedUser.getUserName());
		Assert.assertEquals(insertedUser.getEmail(), selectedUser.getEmail());
		Assert.assertEquals(insertedUser.getPassword(), selectedUser.getPassword());
		Assert.assertEquals(insertedUser.getSalt(), selectedUser.getSalt());
		Assert.assertEquals(insertedUser.getStatus(), selectedUser.getStatus());
	}
	
	@Test
	public void testExistsById() {
		logger.info("****************************************************************************************************");
		logger.info("testExistsById() - insert new user and verify if exists by it`s id");
		
		UserDbo userDbo = getNewUserDbo();
		UserDbo insertedUser = userDao.insert(userDbo);
		logger.info("Id of inserted user = " + insertedUser.getId());
		
		Boolean userExists = userDao.existsById(insertedUser.getId());
		logger.info("Status - user exists = " + userExists);
		
		Assert.assertEquals(userDbo.getId(), insertedUser.getId());
		Assert.assertEquals(true, userExists);
	}
	
	@Test
	public void testExistsByUserName() {
		logger.info("****************************************************************************************************");
		logger.info("testExistsByUserName() - insert new user and verify if exists by it`s user name");
		
		UserDbo userDbo = getNewUserDbo();
		UserDbo insertedUser = userDao.insert(userDbo);
		logger.info("User name of inserted user = " + insertedUser.getUserName());
		
		Boolean userExists = userDao.existsByUserName(insertedUser.getUserName());
		logger.info("Status - user exists = " + userExists);
		
		Assert.assertEquals(userDbo.getUserName(), insertedUser.getUserName());
		Assert.assertEquals(true, userExists);
	}
	
	@Test
	public void testExistsByEmail() {
		logger.info("****************************************************************************************************");
		logger.info("testExistsByEmail() - insert new user and verify if exists by it`s email");
		
		UserDbo userDbo = getNewUserDbo();
		UserDbo insertedUser = userDao.insert(userDbo);
		logger.info("E-mail name of inserted user = " + insertedUser.getEmail());
		
		Boolean userExists = userDao.existsByEmail(insertedUser.getEmail());
		logger.info("Status - user exists = " + userExists);
		
		Assert.assertEquals(userDbo.getEmail(), insertedUser.getEmail());
		Assert.assertEquals(true, userExists);
	}
}
