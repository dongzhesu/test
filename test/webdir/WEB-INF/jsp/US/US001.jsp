<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Creating New User</title>
</head>
<body>
<h2><spring:message code="module.user.create.title"/></h2>
<form:form method="post" action="save.html" commandName="user">
	<table>
	<tr>
		<td><form:label path="userLogin"><spring:message code="entity.user.login"/></form:label></td>
		<td><form:input path="userLogin" /></td>
	</tr>
	<tr>
		<td><form:label path="userPassword"><spring:message code="entity.user.password"/></form:label></td>
 		<td><form:password path="userPassword" /></td>
	</tr>
	<tr>
		<td><form:label path="userLanguage"><spring:message code="entity.user.lang"/></form:label></td>
		<td><form:select path="userLanguage"><form:options items="${userLanguage}" /></form:select></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.user.button.add"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>