package framework.configuration;

import org.apache.commons.lang3.StringUtils;

/** 
 * Class contains methods used for loading of configuration properties. It encapsulates direct calls of {@linkplain org.apache.commons.configuration.PropertiesConfiguration PropertiesConfiguration}
 * and simplifies selection of correct configuration property. <br> 
 */
public class ConfigurationReader {

	private static final String VALUE_DELIMITER = ";";
	
	private static final String DATABASE_PERSISTENCE_UNIT_NAME = "database.persistence_unit_name";
	
	private static final String EMAIL_SMTP_HOST = "email.smtp.host";
	private static final String EMAIL_SMTP_SOCKET_FACTORY_PORT = "email.smtp.socketFactory.port";
	private static final String EMAIL_SMTP_SOCKET_FACTORY_CLASS = "email.smtp.socketFactory.class";
	private static final String EMAIL_SMTP_AUTH = "email.smtp.auth";
	private static final String EMAIL_SMTP_PORT = "email.smtp.port";

	private static final String EMAIL_USERNAME = "email.username";
	private static final String EMAIL_PASSWORD = "email.password";

	private static final String LANGUAGE_DEFAULT_LANGUAGE = "language.defaultlanguage";
	private static final String LANGUAGE_SUPPORTED_LANGUAGES = "language.supportedlanguages";
	
	private static final String EMAIL_USER_REGISTRATION_FROM = "email.user.registration.from";
	private static final String EMAIL_USER_REGISTRATION_SUBJECT = "email.user.registration.subject";
	private static final String EMAIL_USER_REGISTRATION_TEXT = "email.user.registration.text";
	
	private static final String EMAIL_ADMIN_REGISTRATION_FROM = "email.admin.registration.from";
	private static final String EMAIL_ADMIN_REGISTRATION_SUBJECT = "email.admin.registration.subject";
	private static final String EMAIL_ADMIN_REGISTRATION_TEXT = "email.admin.registration.text";
	
	private static final String USER_REGISTRATION_ACTIVATION_LINK = "user.registration.activationlink";
	
	public static String getDatabasePersistenceUnitName() {
		return ConfigurationInitializer.loadAsString(DATABASE_PERSISTENCE_UNIT_NAME);
	}
	
	public static String getEmailSmtpHost() {
		return ConfigurationInitializer.loadAsString(EMAIL_SMTP_HOST);
	}
	
	public static String getEmailSmtpSocketFactoryPort() {
		return ConfigurationInitializer.loadAsString(EMAIL_SMTP_SOCKET_FACTORY_PORT);
	}
	
	public static String getEmailSmtpSocketFactoryClass() {
		return ConfigurationInitializer.loadAsString(EMAIL_SMTP_SOCKET_FACTORY_CLASS);
	}
	
	public static String getEmailSmtpAuth() {
		return ConfigurationInitializer.loadAsString(EMAIL_SMTP_AUTH);
	}
	
	public static String getEmailSmtpPort() {
		return ConfigurationInitializer.loadAsString(EMAIL_SMTP_PORT);
	}
	
	public static String getEmailUserName() {
		return ConfigurationInitializer.loadAsString(EMAIL_USERNAME);
	}
	
	public static String getEmailPassword() {
		return ConfigurationInitializer.loadAsString(EMAIL_PASSWORD);
	}
	
	public static String getLanguageDefaultLanguage() {
		return ConfigurationInitializer.loadAsString(LANGUAGE_DEFAULT_LANGUAGE);
	}
	
	public static String[] getLanguageSupportedLanguages() {
		return StringUtils.split(ConfigurationInitializer.loadAsString(LANGUAGE_SUPPORTED_LANGUAGES), VALUE_DELIMITER);
	}
	
	public static String getEmailUserRegistrationFrom() {
		return ConfigurationInitializer.loadAsString(EMAIL_USER_REGISTRATION_FROM);
	}
	
	public static String getEmailUserRegistrationSubject(String language) {
		return ConfigurationInitializer.loadAsString(EMAIL_USER_REGISTRATION_SUBJECT + "." + language);
	}
	
	public static String getEmailUserRegistrationText(String language) {
		return ConfigurationInitializer.loadAsString(EMAIL_USER_REGISTRATION_TEXT + "." + language);
	}
	
	public static String getEmailAdminRegistrationFrom() {
		return ConfigurationInitializer.loadAsString(EMAIL_ADMIN_REGISTRATION_FROM);
	}
	
	public static String getEmailAdminRegistrationSubject(String language) {
		return ConfigurationInitializer.loadAsString(EMAIL_ADMIN_REGISTRATION_SUBJECT + "." + language);
	}
	
	public static String getEmailAdminRegistrationText(String language) {
		return ConfigurationInitializer.loadAsString(EMAIL_ADMIN_REGISTRATION_TEXT + "." + language);
	}
	
	public static String getUserRegistrationActivationLink() {
		return ConfigurationInitializer.loadAsString(USER_REGISTRATION_ACTIVATION_LINK);
	}
}
