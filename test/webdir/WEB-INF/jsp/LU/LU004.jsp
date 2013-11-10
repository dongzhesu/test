<%@page import="com.erp.china.demo.util.Constants" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Lookup Listing</title>
</head>
<body>
<h2><spring:message code="module.lookup.list.title"/></h2>
<table class="data">
<tr>
	<th><spring:message code="entity.lookup.key"/></th>
	<th><spring:message code="entity.lookup.value"/></th>
	<th colspan="2"><spring:message code="module.action"/></th>
</tr>
<c:if test="${!empty lookupList}">
<c:forEach items="${lookupList}" var="lookup">
	<tr>
		<td>${lookup.lookupKey}</td>
		<td>${lookup.lookupValue}</td>
		<td><a href="002/${lookup.lookupId}.html">modify</a></td>
		<td><a href="003/${lookup.lookupId}.html">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>