<%@page import="com.erp.china.demo.util.Constants, com.erp.china.demo.model.Order" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Booking Listing</title>
</head>
<body>
<h2><spring:message code="module.booking.list.title"/></h2>
<table class="data">
<tr>
	<th><spring:message code="entity.booking.order"/></th>
	<th><spring:message code="entity.booking.product"/></th>
	<th><spring:message code="entity.booking.qty"/></th>
	<th><spring:message code="entity.booking.unit.price"/></th>
	<th><spring:message code="entity.booking.disc"/></th>
	<th><spring:message code="entity.booking.price"/></th>
	<th><spring:message code="entity.createdDate"/></th>
	<th><spring:message code="entity.lastModifiedDate"/></th>
	<th colspan="2"><spring:message code="module.action"/></th>
</tr>
<c:if test="${!empty bookingList}">
<c:forEach items="${bookingList}" var="booking">
	<tr>
		<td>${booking.bookingId.order.orderNumber}</td>
		<td>${booking.bookingId.product.productName}</td>
		<td>${booking.bookingQty}</td>
		<td>${booking.unitPrice}</td>
		<td>${booking.discount}</td>
		<td>${booking.bookingPrice}</td>
		<td>${booking.createdDate}</td>
		<td>${booking.lastModifiedDate}</td>
		<td><a href="002/order/${booking.bookingId.order.orderId}/product/${booking.bookingId.product.productId}.html">modify</a></td>
		<td><a href="003/order/${booking.bookingId.order.orderId}/product/${booking.bookingId.product.productId}.html">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>