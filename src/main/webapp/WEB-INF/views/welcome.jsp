<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remember Me with Spring MVC Framework</title>
</head>
<body>
	Welcome ${sessionScope.username }
	<br>
	<a href="${pageContext.request.contextPath }/account/logout">Logout</a>
</body>
</html>