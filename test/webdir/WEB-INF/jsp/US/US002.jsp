<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Update User</title>
</head>
<body>
<h2><spring:message code="module.user.modify.title"/></h2>
<form:form method="post" action="../update.html" commandName="user">
	<form:hidden path="userId" />
	<input type="hidden" name="salesId" value="${salesId}"/>
	<input type="hidden" name="userLogin" value="${user.userLogin}"/>
	<table>
	<tr>
		<td><form:label path="userLogin"><spring:message code="entity.user.login"/></form:label></td>
		<td><div class="userLogin" style="display: inline">${user.userLogin}</div></td>
	</tr>
	<tr>
		<td><form:label path="userLanguage"><spring:message code="entity.user.lang"/></form:label></td>
		<td><form:select path="userLanguage"><form:options items="${userLanguage}" /></form:select></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.user.button.modify"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>