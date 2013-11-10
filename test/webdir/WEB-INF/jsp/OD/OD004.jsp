<%@page import="com.erp.china.demo.util.Constants, com.erp.china.demo.model.Order" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Order Listing</title>
</head>
<body>
<h2><spring:message code="module.order.list.title"/></h2>
<table class="data">
<tr>
	<th><spring:message code="entity.order.number"/></th>
	<th><spring:message code="entity.order.customer"/></th>
	<th><spring:message code="entity.order.price"/></th>
	<th><spring:message code="entity.order.date"/></th>
	<th><spring:message code="entity.createdDate"/></th>
	<th><spring:message code="entity.lastModifiedDate"/></th>
	<th colspan="2"><spring:message code="module.action"/></th>
</tr>
<c:if test="${!empty orderList}">
<c:forEach items="${orderList}" var="order">
	<tr>
		<td>${order.orderNumber}</td>
		<td>${order.customer.customerName}</td>
		<td>${order.orderPrice}</td>
		<td>
		<%
			Order order = (Order) pageContext.getAttribute("order");
			out.println(Constants.sdf.format(order.getOrderDate()));
		%>
		</td>
		<td>${order.createdDate}</td>
		<td>${order.lastModifiedDate}</td>
		<td><a href="002/${order.orderId}.html">modify</a></td>
		<td><a href="003/${order.orderId}.html">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>