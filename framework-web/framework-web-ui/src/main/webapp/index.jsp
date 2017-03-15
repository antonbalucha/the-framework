<%@ page import="org.slf4j.LoggerFactory"%>
<%@ page import="org.slf4j.Logger"%>
<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" isErrorPage="false" %>
<%@ page trimDirectiveWhitespaces="true" %>

<% request.setCharacterEncoding("UTF-8"); %>
<% response.setCharacterEncoding("UTF-8"); %>

<% 
	Logger logger = LoggerFactory.getLogger("JSP_FILES");
%>

<!DOCTYPE html>
<html>
	<head>
		<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
		<title>index.jsp</title>
	</head>
	<body>
		<h1>Hello world!</h1>
		<p>It works!</p>
	</body>
</html>