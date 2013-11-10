<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Product Listing</title>
</head>
<body>
<h2><spring:message code="module.product.list.title"/></h2>
<table class="data">
<tr>
	<th><spring:message code="entity.product.name"/></th>
	<th><spring:message code="entity.product.desc"/></th>
	<th><spring:message code="entity.product.year"/></th>
	<th><spring:message code="entity.product.price"/></th>
	<th><spring:message code="entity.product.price.2"/></th>
	<th><spring:message code="entity.product.qty"/></th>
	<th><spring:message code="entity.createdDate"/></th>
	<th><spring:message code="entity.lastModifiedDate"/></th>
	<th colspan="2"><spring:message code="module.action"/></th>
</tr>
<c:if test="${!empty productList}">
<c:forEach items="${productList}" var="product">
	<tr>
		<td>${product.productName}</td>
		<td>${product.productDesc}</td>
		<td>${product.productYear}</td>
		<td>${product.productPrice}</td>
		<td>${product.productPrice2}</td>
		<td>${product.productQty}</td>
		<td>${product.createdDate}</td>
		<td>${product.lastModifiedDate}</td>
		<td><a href="002/${product.productId}.html">modify</a></td>
		<td><a href="003/${product.productId}.html">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>