package framework.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.db.dao.UserDao;
import framework.db.dbo.UserDbo;
import framework.db.utils.ConnectorManager;

/**
 * <p>
 * Class which stores to database/retrieves from database data about user.
 * </p>
 * 
 * <p>
 * Methods:
 * <ul>
 * <li>do not contains validations of entered parameters because they are checked by business logic layer due to simplicity </li>
 * <li>do not check on logger if error is enabled - assumption is that logger will be always enabled at least on error level </li>
 * </ul>
 * </p> 
 */
public class UserDaoImpl implements UserDao {

	private static final Logger logger = LoggerFactory.getLogger(UserDaoImpl.class);
	
	@Override
	public UserDbo select(Long id) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<UserDbo> listOfUsers = em.createQuery("SELECT u FROM UserDbo u WHERE u.id = :id", UserDbo.class)
					.setParameter("id", id)
					.setMaxResults(1)
					.setFirstResult(0)
					.getResultList();
					
			et.commit();
			
			return (listOfUsers == null || listOfUsers.isEmpty()) ? null : listOfUsers.get(0);
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting object '%s' with 'id' = '%d'", e.getMessage(), UserDbo.class.getSimpleName(), id), e);
			throw e;
		}
	}

	@Override
	public UserDbo select(String userName) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<UserDbo> listOfUsers  = em.createQuery("SELECT u FROM UserDbo u WHERE u.userName = :userName", UserDbo.class)
					.setParameter("userName", userName)
			 		.setMaxResults(1)
			 		.setFirstResult(0)
			 		.getResultList();
			
			et.commit();

			return (listOfUsers == null || listOfUsers.isEmpty()) ? null : listOfUsers.get(0);
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting object '%s' with 'userName' = '%s'", e.getMessage(), UserDbo.class.getSimpleName(), userName), e);
			throw e;
		}
	}
	
	@Override
	public String selectLanguage(String userName) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			String language = (String) em.createQuery("SELECT u.language FROM UserDbo u WHERE u.userName = :userName", String.class)
					.setParameter("userName", userName)
					.setMaxResults(1)
					.setFirstResult(0)
					.getResultList()
					.get(0);
			
			et.commit();

			return language;
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting language for user with 'user name' = '%s'", e.getMessage(), userName), e);
			throw e;
		}
	}

	@Override
	public Boolean existsById(Long id) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			Long count = (Long) em.createQuery("SELECT COUNT(u.id) FROM UserDbo u WHERE u.id = :id", Long.class)
					.setParameter("id", id)
					.setMaxResults(1)
					.setFirstResult(0)
					.getResultList()
					.get(0);
			
			et.commit();

			return count != null && count > 0;
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting count of objects '%s' with 'id' = '%d'", e.getMessage(), UserDbo.class.getSimpleName(), id), e);
			throw e;
		}
	}
	
	@Override
	public Boolean existsByUserName(String userName) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			Long count = (Long) em.createQuery("SELECT COUNT(u.id) FROM UserDbo u WHERE u.userName = :userName", Long.class)
					.setParameter("userName", userName)
					.setFirstResult(0)
					.setMaxResults(1)
					.getResultList()
					.get(0);
			
			et.commit();

			return count != null && count > 0;
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting count of objects '%s' with 'userName' = '%s'\n", e.getMessage(), UserDbo.class.getSimpleName(), userName), e);
			throw e;
		}
	}
	
	@Override
	public Boolean existsByEmail(String email) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			Long count = (Long) em.createQuery("SELECT COUNT(u.id) FROM UserDbo u WHERE u.email = :email", Long.class)
					.setParameter("email", email)
					.setFirstResult(0)
					.setMaxResults(1)
					.getResultList()
					.get(0);
			
			et.commit();

			return count != null && count > 0;
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting count of objects '%s' with 'email' = '%s'", e.getMessage(), UserDbo.class.getSimpleName(), email), e);
			throw e;
		}
	}
	
	@Override
	public UserDbo insert(UserDbo userDbo) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			em.persist(userDbo);
			
			et.commit();
			
			return userDbo;
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when inserting new record of '%s' with values of 'userDbo' = '%s'", e.getMessage(), UserDbo.class.getSimpleName(), userDbo.toString()), e);
			throw e;
		}
	}

	@Override
	public Boolean updateStatusByActivationToken(UserDbo userDbo) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			int numberOfUpdatedUsers = em.createQuery("UPDATE UserDbo u SET u.status = :newStatus WHERE u.activationToken = :activationToken")
					.setParameter("newStatus", userDbo.getStatus())
					.setParameter("activationToken", userDbo.getActivationToken())
					.executeUpdate();
			
			if (numberOfUpdatedUsers == 1) {
				et.commit();
				return true;
			} else if (numberOfUpdatedUsers == 0) {
				et.rollback();
				return false;
			} else {
				logger.error(String.format("Number of updated records of '%s' is greater then zero; used activation token = '%s'", UserDbo.class.getSimpleName(), userDbo.getActivationToken()));
				et.rollback();
				return false;
			}
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when updating existing record of '%s' with values of 'userDbo' = '%s'", e.getMessage(), UserDbo.class.getName(), userDbo.toString()), e);
			throw e;
		}
	}

	@Override
	public Boolean updateStatusByUserName(UserDbo userDbo) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			int numberOfUpdatedUsers = em.createQuery("UPDATE UserDbo u SET u.status = :newStatus WHERE u.userName = :userName")
					.setParameter("newStatus", userDbo.getStatus())
					.setParameter("userName", userDbo.getUserName())
					.executeUpdate();
			
			if (numberOfUpdatedUsers == 1) {
				et.commit();
				return true;
			} else if (numberOfUpdatedUsers == 0) {
				et.rollback();
				return false;
			} else {
				logger.error(String.format("Number of updated records of '%s' is greater then zero; used user name = '%s'", UserDbo.class.getSimpleName(), userDbo.getUserName()));
				et.rollback();
				return false;
			}
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when updating existing record of '%s' with values of 'userDbo' = '%s'", e.getMessage(), UserDbo.class.getName(), userDbo.toString()), e);
			throw e;
		}
	}
	
	@Override
	public Boolean updateLanguageByUserName(UserDbo userDbo) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			int numberOfUpdatedUsers = em.createQuery("UPDATE UserDbo u SET u.language = :newLanguage WHERE u.userName = :userName")
					.setParameter("newLanguage", userDbo.getLanguage())
					.setParameter("userName", userDbo.getUserName())
					.executeUpdate();
			
			if (numberOfUpdatedUsers == 1) {
				et.commit();
				return true;
			} else if (numberOfUpdatedUsers == 0) {
				et.rollback();
				return false;
			} else {
				logger.error(String.format("Number of updated records of '%s' is greater then zero; used user name = '%s'", UserDbo.class.getSimpleName(), userDbo.getUserName()));
				et.rollback();
				return false;
			}
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when updating existing record of '%s' with values of 'userDbo' = '%s'", e.getMessage(), UserDbo.class.getName(), userDbo.toString()), e);
			throw e;
		}
	}
	
	@Override
	public List<UserDbo> list() {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<UserDbo> listOfUsers  = em.createQuery("SELECT u FROM UserDbo u", UserDbo.class)
			 		.getResultList();
			
			et.commit();

			return (listOfUsers == null || listOfUsers.isEmpty()) ? null : listOfUsers;
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting list of objects of '%s'", e.getMessage(), UserDbo.class.getSimpleName()), e);
			throw e;
		}
	}
	
	@Override
	public List<UserDbo> list(Integer limit, Integer offset) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<UserDbo> listOfUsers  = em.createQuery("SELECT u FROM UserDbo u", UserDbo.class)
					.setFirstResult(limit * offset)
					.setMaxResults(limit)
			 		.getResultList();
			
			et.commit();

			return (listOfUsers == null || listOfUsers.isEmpty()) ? null : listOfUsers;
		} catch (RuntimeException e) {
			logger.error(String.format("RuntimeException: %s; Problem when selecting list of objects of '%s'", e.getMessage(), UserDbo.class.getSimpleName()), e);
			throw e;
		}
	}
}
