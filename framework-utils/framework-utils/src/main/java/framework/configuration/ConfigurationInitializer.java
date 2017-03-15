package framework.configuration;

import org.apache.commons.configuration.ConfigurationException;
import org.apache.commons.configuration.PropertiesConfiguration;
import org.apache.commons.configuration.reloading.FileChangedReloadingStrategy;
import org.apache.commons.lang3.StringUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/** 
 * Class contains methods used for initialization of configuration loader. <br>
 */
public class ConfigurationInitializer {

	/** Name of the configuration file used for storing of configuration properties is 'configuration.properties' */
	public static final String CONFIGURATION_PROPERTIES = "configuration.properties";
	
	/** Encoding of configuration file is 'UTF-8' */
	public static final String UTF_8 = "UTF-8";
	
	private static final Logger logger = LoggerFactory.getLogger(ConfigurationInitializer.class);
	
	private static PropertiesConfiguration config;
	
	static {
		init();
	}

	private static final void init() {
		try {
			config = new PropertiesConfiguration(CONFIGURATION_PROPERTIES);
			config.setEncoding(UTF_8);
			config.setThrowExceptionOnMissing(false);
			config.setReloadingStrategy(new FileChangedReloadingStrategy());
		} catch (ConfigurationException e) {
			logger.error("ConfigurationException: " + e.getMessage(), e);
		}
	}
	
	static final String loadAsString(String propertyName) {
		return StringUtils.isBlank(propertyName) ? null : config.getString(propertyName, null);
	}
	
	static final Integer loadAsInteger(String propertyName) {
		return StringUtils.isBlank(propertyName) ? null : config.getInteger(propertyName, null);
	}
}
