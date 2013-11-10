<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Update Booking</title>
</head>
<body>
<h2><spring:message code="module.booking.modify.title"/></h2>
<form:form method="post" action="../../../../update.html" commandName="booking">
	<input type="hidden" name="orderId" value="${orderId}"/>
	<input type="hidden" name="productId" value="${productId}"/>
	<table>
	<tr>
		<td><spring:message code="entity.booking.order"/></td>
		<td><div class="order_list" style="display: inline">${orderNumber}</div></td>
	</tr>
	<tr>
		<td><spring:message code="entity.booking.product"/></td>
		<td><div class="product_list" style="display: inline">${productName}</div></td>
	</tr>
	<tr>
		<td><form:label path="bookingQty"><spring:message code="entity.booking.qty"/></form:label></td>
		<td><form:input path="bookingQty" /></td>
	</tr>
	<tr>
		<td><form:label path="unitPrice"><spring:message code="entity.booking.unit.price"/></form:label></td>
		<td><form:input path="unitPrice" /></td>
	</tr>
	<tr>
		<td><form:label path="discount"><spring:message code="entity.booking.disc"/></form:label></td>
		<td><form:input path="discount" /></td>
	</tr>
	<tr>
		<td><form:label path="bookingPrice"><spring:message code="entity.booking.price"/></form:label></td>
 		<td><form:input path="bookingPrice" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.booking.button.modify"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>