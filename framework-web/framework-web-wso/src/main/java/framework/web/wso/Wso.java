package framework.web.wso;

import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import framework.utils.JsonUtils;

@JsonIgnoreProperties(ignoreUnknown = true)
public abstract class Wso {

	public String toJson() {
		return JsonUtils.getJson(this);
	}
}
