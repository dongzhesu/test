<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Creating New Lookup</title>
</head>
<body>
<h2><spring:message code="module.lookup.create.title"/></h2>
<form:form method="post" action="save.html" commandName="lookup">
	<table>
	<tr>
		<td><form:label path="lookupKey"><spring:message code="entity.lookup.key"/></form:label></td>
		<td><form:input path="lookupKey" /></td>
	</tr>
	<tr>
		<td><form:label path="lookupValue"><spring:message code="entity.lookup.value"/></form:label></td>
 		<td><form:input path="lookupValue" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.lookup.button.add"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>