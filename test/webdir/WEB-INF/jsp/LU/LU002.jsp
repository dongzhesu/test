<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Update Lookup</title>
</head>
<body>
<h2><spring:message code="module.lookup.modify.title"/></h2>
<form:form method="post" action="../update.html" commandName="lookup">
	<form:hidden path="lookupId" />
	<input type="hidden" name="lookupKey" value="${lookup.lookupKey}"/>
	<table>
	<tr>
		<td><form:label path="lookupKey"><spring:message code="entity.lookup.key"/></form:label></td>
		<td><div class="lookupKey" style="display: inline">${lookup.lookupKey}</div></td>
	</tr>
	<tr>
		<td><form:label path="lookupValue"><spring:message code="entity.lookup.value"/></form:label></td>
 		<td><form:input path="lookupValue" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.lookup.button.modify"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>