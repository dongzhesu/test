<%@page contentType="text/html;charset=UTF-8"%>
<%
Object userLoginObj = request.getSession().getAttribute("userLogin");
if (userLoginObj == null) response.sendRedirect("LoginServlet");
else {
%>
<html>
<head>
<meta http-equiv=Content-Type content="text/html; charset=UTF-8"/>
<title>ERP CHINA</title>
    <!--Import CSS-->
    <link rel="stylesheet" type="text/css" href="libs/extjs/resources/css/ext-all.css"/>
	<link rel="stylesheet" type="text/css" href="resources/css/app.css"/>
	<!--Import EXTJS-->
	<script type="text/javascript" src="libs/extjs/ext-all.js"></script>
	<script type="text/javascript" src="test/patch.js"></script>
	<!--Import App Luncher-->
    <script type="text/javascript" src="test.js"></script>
</head>
<body>
</body>
</html>
<%
}
%>