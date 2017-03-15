package framework.db.dao.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.EntityTransaction;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import framework.db.dao.CountryDao;
import framework.db.dbo.CountryDbo;
import framework.db.utils.ConnectorManager;

/**
 * <p>
 * Class which stores to database/retrieves from database data about countries.
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
public class CountryDaoImpl implements CountryDao {

	private static final Logger logger = LoggerFactory.getLogger(CountryDaoImpl.class);
	
	@Override
	public CountryDbo getByAlpha2(String alpha2) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<CountryDbo> countries = em.createQuery("SELECT c FROM CountryDbo c WHERE c.alpha2 = :alpha2", CountryDbo.class)
					.setParameter("alpha2", alpha2)
					.setMaxResults(1)
					.setFirstResult(0)
					.getResultList();
					
			et.commit();

			return (countries == null || countries.isEmpty()) ? null : countries.get(0);
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage() + "; Problem when selecting object '" + CountryDbo.class.getSimpleName() + "' with 'alpha2' = '" + alpha2 + "'", e);
			throw e;
		}
	}

	@Override
	public CountryDbo getByAlpha3(String alpha3) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<CountryDbo> countries = em.createQuery("SELECT c FROM CountryDbo c WHERE c.alpha3 = :alpha3", CountryDbo.class)
					.setParameter("alpha3", alpha3)
					.setMaxResults(1)
					.setFirstResult(0)
					.getResultList();
					
			et.commit();

			return (countries == null || countries.isEmpty()) ? null : countries.get(0);
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage() + "; Problem when selecting object '" + CountryDbo.class.getSimpleName() + "' with 'alpha3' = '" + alpha3 + "'", e);
			throw e;
		}
	}

	@Override
	public CountryDbo getByCountryCode(Integer countryCode) {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<CountryDbo> countries = em.createQuery("SELECT c FROM CountryDbo c WHERE c.countryCode = :countryCode", CountryDbo.class)
					.setParameter("countryCode", countryCode)
					.setMaxResults(1)
					.setFirstResult(0)
					.getResultList();
					
			et.commit();

			return (countries == null || countries.isEmpty()) ? null : countries.get(0);
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage() + "; Problem when selecting object '" + CountryDbo.class.getSimpleName() + "' with 'countryCode' = '" + countryCode + "'", e);
			throw e;
		}
	}
	
	@Override
	public List<CountryDbo> getList() {
		
		try {
			EntityManager em = ConnectorManager.getDefaultEntityManager();
			EntityTransaction et = em.getTransaction();
			et.begin();
			
			List<CountryDbo> countries = em.createQuery("SELECT c FROM CountryDbo c", CountryDbo.class)
					.getResultList();
					
			et.commit();

			return (countries == null || countries.isEmpty()) ? null : countries;
		} catch (RuntimeException e) {
			logger.error("RuntimeException: " + e.getMessage() + "; Problem when selecting list of objects of '" + CountryDbo.class.getSimpleName() + "'", e);
			throw e;
		}
	}
}
