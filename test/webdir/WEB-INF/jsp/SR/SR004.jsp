<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Storage Listing</title>
</head>
<body>
<h2><spring:message code="module.storage.list.title"/></h2>
<table class="data">
<tr>
	<th><spring:message code="entity.storage.name"/></th>
	<th><spring:message code="entity.storage.desc"/></th>
	<th><spring:message code="entity.createdDate"/></th>
	<th><spring:message code="entity.lastModifiedDate"/></th>
	<th colspan="2"><spring:message code="module.action"/></th>
</tr>
<c:if test="${!empty storageList}">
<c:forEach items="${storageList}" var="storage">
	<tr>
		<td>${storage.storageName}</td>
		<td>${storage.storageDesc}</td>
		<td>${storage.createdDate}</td>
		<td>${storage.lastModifiedDate}</td>
		<td><a href="002/${storage.storageId}.html">modify</a></td>
		<td><a href="003/${storage.storageId}.html">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>