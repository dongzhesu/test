<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Update Product</title>
</head>
<body>
<h2><spring:message code="module.product.modify.title"/></h2>
<form:form method="post" action="../update.html" commandName="product">
	<form:hidden path="productId" />
	<table>
	<tr>
		<td><form:label path="productName"><spring:message code="entity.product.name"/></form:label></td>
		<td><form:input path="productName" /></td>
	</tr>
	<tr>
		<td><form:label path="productDesc"><spring:message code="entity.product.desc"/></form:label></td>
		<td><form:input path="productDesc" /></td>
	</tr>
	<tr>
		<td><form:label path="productYear"><spring:message code="entity.product.year"/></form:label></td>
 		<td><form:input path="productYear" /></td>
	</tr>
	<tr>
 		<td><form:label path="productPrice"><spring:message code="entity.product.price"/></form:label></td>
 		<td><form:input path="productPrice" /></td>
	</tr>
	<tr>
 		<td><form:label path="productPrice2"><spring:message code="entity.product.price.2"/></form:label></td>
 		<td><form:input path="productPrice2" /></td>
	</tr>
	<tr>
 		<td><form:label path="productQty"><spring:message code="entity.product.qty"/></form:label></td>
 		<td><form:input path="productQty" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.product.button.modify"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>