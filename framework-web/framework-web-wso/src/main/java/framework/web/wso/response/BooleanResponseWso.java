package framework.web.wso.response;

import framework.web.wso.Wso;

/**
 * Object wraps/represents boolean value. <br>
 */
public class BooleanResponseWso extends Wso {

	private Boolean result;
	
	public Boolean getResult() {
		return this.result;
	}

	public BooleanResponseWso setResult(Boolean result) {
		this.result = result;
		return this;
	}

	@Override
	public String toString() {
		return toJson();
	}
}
