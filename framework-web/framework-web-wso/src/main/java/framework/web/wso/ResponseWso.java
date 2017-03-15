package framework.web.wso;

import java.util.ArrayList;
import java.util.List;

public class ResponseWso extends Wso {

	private int httpStatusCode;
	
	private List<String> messageCodes = new ArrayList<String>(1);

	public ResponseWso() {}
	
	public ResponseWso(int httpStatusCode) {
		this.setHttpStatusCode(httpStatusCode);
	}
	
	public ResponseWso(int httpStatusCode, String messageCode) {
		this.setHttpStatusCode(httpStatusCode);
		this.addMessageCode(messageCode);
	}

	public ResponseWso(int httpStatusCode, List<String> messageCodes) {
		this.setHttpStatusCode(httpStatusCode);
		this.setMessageCodes(messageCodes);
	}
	
	public List<String> getMessageCodes() {
		return this.messageCodes;
	}

	public ResponseWso setMessageCodes(List<String> messageCodes) {
		this.messageCodes = messageCodes;
		return this;
	}
	
	public ResponseWso addMessageCode(String messageCode) {
		this.messageCodes.add(messageCode);
		return this;
	}
	
	public int getHttpStatusCode() {
		return this.httpStatusCode;
	}

	public ResponseWso setHttpStatusCode(int httpStatusCode) {
		this.httpStatusCode = httpStatusCode;
		return this;
	}
	
	@Override
	public String toString() {
		return toJson();
	}
}
