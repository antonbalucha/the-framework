package framework.db.utils;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;

import framework.configuration.ConfigurationReader;

/**
 * Class, which contains helper methods for database connection. <br> 
 */
public class ConnectorManager {

	/** 
	 * Method, which returns EntityManager. <br>
	 * 
	 * @return EntityManager - initialized EntityManager <br>
	 */
	public static final EntityManager getDefaultEntityManager() {
		EntityManagerFactory entityManagerFactory = Persistence.createEntityManagerFactory(ConfigurationReader.getDatabasePersistenceUnitName());
		return entityManagerFactory.createEntityManager();
	}
	
	public static final String getDefaultPersistenceUnitName() {
		return ConfigurationReader.getDatabasePersistenceUnitName();
	}
	
	public static final void close(EntityManager entityManager) {
		if (entityManager != null && entityManager.isJoinedToTransaction() && entityManager.isOpen()) {
			entityManager.clear();
			entityManager.close();
		}
	}

	public static final void close(EntityManagerFactory entityManagerFactory) {
		if (entityManagerFactory != null && entityManagerFactory.isOpen()) {
			entityManagerFactory.close();
		}
	}
}
