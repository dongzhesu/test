<%@page contentType="text/html" pageEncoding="UTF-8"%>
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
	<title>JSP Page</title>
	<link rel="stylesheet" type="text/css" href="resources/css/app.css"/>
</head>
<body style="background:#f1f3f5">
	<form action="LoginServlet" method="post">
	<table width=100% border=0 align="center">
		<tr><th>Login Page</th></tr>
		<%
		if (session.getAttribute("errorLogin") != null) {
			Boolean errorLogin = Boolean.valueOf(session.getAttribute("errorLogin").toString());
			if (errorLogin) {
		%>
		<tr><td><span class="error_login">The username or password you entered is incorrect.</span></td></tr>
		<%
			}
			session.removeAttribute("errorLogin");
		}
		if (session.getAttribute("userLogin") != null) session.removeAttribute("userLogin");
		%>
		<tr><td>
			<table border=0 align="center">
			<tr><td rowspan=4 width=66><div><img height=64 alt=security src="images/security.png" width=64></div></td></tr>
			<tr><td>Username</td><td>:</td><td><input type="text" name="username"></td></tr>
			<tr><td>Password</td><td>:</td><td><input type="password" name="password"></td></tr>
			<tr><td colspan="3" align="center"><input type=submit value=Login name=submit></td></tr>
			</table>
		</td></tr>
		<tr><td align="center">
			<P><font size=1>Welcome to ERP demo system!</font></P>
			<P><font size=1> a valid username and password to gain access to the system console.</font></P>
		</td></tr>
	</table>
	</form>
</body>
</html>