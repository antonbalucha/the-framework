package framework.utils;

import org.apache.commons.lang3.StringUtils;
import org.apache.shiro.SecurityUtils;

public class SessionUtils {

	public static final String SESSION_ATTRIBUTE_USERS_LANGUAGE = "UsersLanguage";
	
	public static String getUserSessionLanguage() {
		String language = (String) SecurityUtils.getSubject().getSession().getAttribute(SESSION_ATTRIBUTE_USERS_LANGUAGE);
		return StringUtils.isBlank(language) ? LanguageUtils.getDefaultLanguage() : language;
	}
	
	public static void setUserSessionLanguage(String language) {
		SecurityUtils.getSubject().getSession().setAttribute(SESSION_ATTRIBUTE_USERS_LANGUAGE, language);
	}
}
