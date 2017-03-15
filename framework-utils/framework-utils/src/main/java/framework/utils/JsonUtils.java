package framework.utils;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;

public class JsonUtils {

	private final static Logger logger = LoggerFactory.getLogger(JsonUtils.class);
	
	public static String getJson(Object object) {
		if (object == null) {
			return "";
		} else {
			try {
				return new ObjectMapper().writeValueAsString(object);
			} catch (JsonProcessingException e) {
				logger.error("JsonProcessingException: " + e.getMessage() + "; Error when creating JSON from processed object: '" + object.getClass().getSimpleName() + "' with values: '" + object.toString() + "'");
				return "";
			}
		}
	}
}
