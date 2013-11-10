<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Sales Listing</title>
</head>
<body>
<h2><spring:message code="module.sales.list.title"/></h2>
<table class="data">
<tr>
	<th><spring:message code="entity.sales.name"/></th>
	<th><spring:message code="entity.sales.email"/></th>
	<th><spring:message code="entity.sales.phone"/></th>
	<th><spring:message code="entity.createdDate"/></th>
	<th><spring:message code="entity.lastModifiedDate"/></th>
	<th colspan="2"><spring:message code="module.action"/></th>
</tr>
<c:if test="${!empty salesList}">
<c:forEach items="${salesList}" var="sales">
	<tr>
		<td>${sales.salesName}</td>
		<td>${sales.salesEmail}</td>
		<td>${sales.salesPhone}</td>
		<td>${sales.createdDate}</td>
		<td>${sales.lastModifiedDate}</td>
		<td><a href="002/${sales.salesId}.html">modify</a></td>
		<td><a href="003/${sales.salesId}.html">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>