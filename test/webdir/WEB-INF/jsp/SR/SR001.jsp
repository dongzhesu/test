<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Creating New Storage</title>
</head>
<body>
<h2><spring:message code="module.storage.create.title"/></h2>
<form:form method="post" action="save.html" commandName="storage">
	<table>
	<tr>
		<td><form:label path="storageName"><spring:message code="entity.storage.name"/></form:label></td>
		<td><form:input path="storageName" /></td>
	</tr>
	<tr>
		<td><form:label path="storageDesc"><spring:message code="entity.storage.desc"/></form:label></td>
		<td><form:input path="storageDesc" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.storage.button.add"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>