package framework.web.wso.utils;

import javax.ws.rs.core.Response;

import framework.web.wso.ResponseWso;
import framework.web.wso.Wso;

public class ResponseUtil {
	
	public static final Response response(ResponseWso messageReponse) {
		return response(messageReponse.getHttpStatusCode(), messageReponse);
	}
	
	public static final Response response(Response.Status httpStatusCode, String message) {
		return response(httpStatusCode.getStatusCode(), message);
	}
	
	public static final Response response(int httpStatusCode, String messageCode) {
		return response(httpStatusCode, new ResponseWso(httpStatusCode, messageCode));
	}
	
	public static final Response response(int httpStatusCode, Wso wso) {
		return Response
				.status(httpStatusCode)
				.entity(wso.toJson())
				.build();
	}
	
	public static final Response response(int httpStatusCode) {
		return Response
				.status(httpStatusCode)
				.build();
	}
}
