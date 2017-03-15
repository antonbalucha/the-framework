<%@ page import="org.slf4j.LoggerFactory" %>
<%@ page import="org.slf4j.Logger" %>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="true" %>
<%@ page trimDirectiveWhitespaces="true" %>

<% 
	Logger logger = LoggerFactory.getLogger("JSP_FILES");

	// log error/exception
	if (logger != null) {
		if (exception != null) {
			logger.error("Exception: " + exception.getMessage() + " processed by error.jsp", exception);
		} else {
			logger.error("Unknown error/exception occured and handled by error.jsp");
		}
	}

	// display error message
	if (exception != null) {
		response.setStatus(404);
		response.setContentType("application/json");
		response.setCharacterEncoding("UTF-8");
%>
{"httpStatusCode":404,"messageCodes":["BAD_REQUEST"]}
<%
	} else {
		response.setStatus(404);
		response.setContentType("text/html; charset=utf-8");
		response.setCharacterEncoding("UTF-8");
%>
<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>Unknown error</title>
	</head>
	<body>
		<h1>Unknown error</h1>
		<p>Something somewhere went wrong. Please, try again later or contact our administrator.</p>
	</body>
</html>
<%
	}
%>
