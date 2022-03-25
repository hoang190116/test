<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1" isELIgnored="false"%>
<%@ taglib prefix="s" uri="http://www.springframework.org/tags/form"%>
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Remember Me with Spring MVC Framework</title>
</head>
<body>

	<s:form method="post" modelAttribute="account" action="${pageContext.request.contextPath }/account/login">
		<fieldset>
			<legend>Login</legend>
			${error }
			<table cellpadding="2" cellspacing="2">
				<tr>
					<td>Username</td>
					<td><s:input path="username" /></td>
				</tr>
				<tr>
					<td>Password</td>
					<td><s:password path="password" /></td>
				</tr>
				<tr>
					<td>Remember Me?</td>
					<td><input type="checkbox" name="remember" value="true"></td>
				</tr>
				<tr>
					<td>&nbsp;</td>
					<td><input type="submit" value="Login"></td>
				</tr>
			</table>
		</fieldset>
	</s:form>

</body>
</html>