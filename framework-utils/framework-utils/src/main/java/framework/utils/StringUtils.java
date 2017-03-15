package framework.utils;

import java.util.UUID;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class StringUtils {

	private final static Logger logger = LoggerFactory.getLogger(StringUtils.class);
	
	public static boolean isBlank(Character ch) {
		if (ch == null) {
			return true;
		} else if (Character.isWhitespace(ch)) {
			return true;
		} else {
			return false;
		}
	}
	
	public static boolean isNotBlank(Character ch) {
		return !isBlank(ch);
	}
	
	public static String toString(Object object) {
		if (object == null) {
			logger.error("Entered parameter 'object' is null, parameter for conversion cannot be null");
			throw new IllegalArgumentException("Entered parameter 'object' is null, parameter for conversion cannot be null");
		} else if (object instanceof byte[]) {
			return new String((byte[]) object);
		} else if (object instanceof char[]) {
			return new String((char[]) object);
		} else if (object instanceof String) {
			return (String) object;
		} else {
			return object.toString();
		}
	}
	
	public static String generateRandomString() {
		return UUID.randomUUID().toString().replaceAll("-", "") + System.currentTimeMillis();
	}
}
