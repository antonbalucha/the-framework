package framework.utils;

import org.apache.commons.lang3.StringUtils;

import framework.configuration.ConfigurationReader;

public class LanguageUtils {

	public static final Boolean isSupportedLanguage(String language) {
		
		if (StringUtils.isBlank(language) || language.length() != 2) {
			return false;
		} else {
			String[] languageSupportedLanguages = ConfigurationReader.getLanguageSupportedLanguages();
			
			for (int i = 0; i < languageSupportedLanguages.length; i++) {
				if (languageSupportedLanguages[i].equalsIgnoreCase(language)) {
					return true;
				}
			}
			
			return false;
		}
	}

	public static String getDefaultLanguage() {
		return ConfigurationReader.getLanguageDefaultLanguage();
	}
}
