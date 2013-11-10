<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Creating New Order</title>
	<link href="../themes/jquery-ui.min.css" media="screen" rel="stylesheet" type="text/css"/>
	<link href="../themes/form.css" media="screen" rel="stylesheet" type="text/css"/>
</head>
<script src="../scripts/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../scripts/jquery/jquery-ui.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../scripts/jquery/jeditable.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../scripts/jquery/jeditable.datepicker.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
$jquery = jQuery.noConflict();
$jquery(function() {
	$jquery(".customer_list").editable("../CT/004/setCustomer.json", {
		indicator: '<img src="../images/loading.gif">',
		loadurl: "../CT/004/customerList.json",
		callback: function(value) {
			var selectedSalesId = jQuery.parseJSON(value).selected;
			$jquery(this).html(jQuery.parseJSON(value)[selectedSalesId]);
			$jquery('[name=customerId]').val(selectedSalesId);
		},
		type: "select",
		submit: "SELECT",
		style: "inherit"
	});
	$jquery(".order_date").editable(function(value){
		$jquery(this).html(value);
		$jquery('[name=order_date]').val(value);
		},{
		type: 'datepicker',
		datepicker: {
			dateFormat: 'yy-mm-dd',
			changeMonth: true,
			changeYear: true
		}
	});
});
</script>
<body>
<h2><spring:message code="module.order.create.title"/></h2>
<form:form method="post" action="save.html" commandName="order">
	<input type="hidden" name="customerId" value="${customerId}"/>
	<input type="hidden" name="order_date" value="${orderDate}"/>
	<table>
	<tr>
		<td><form:label path="orderNumber"><spring:message code="entity.order.number"/></form:label></td>
		<td><form:input path="orderNumber" /></td>
	</tr>
	<tr>
		<td><form:label path="customer"><spring:message code="entity.order.customer"/></form:label></td>
		<td><div class="customer_list" style="display: inline">${customerName}</div></td>
	</tr>
	<tr>
		<td><form:label path="orderPrice"><spring:message code="entity.order.price"/></form:label></td>
		<td><form:input path="orderPrice" /></td>
	</tr>
	<tr>
		<td><form:label path="orderDate"><spring:message code="entity.order.date"/></form:label></td>
		<td><div class="order_date" style="display: inline">${orderDate}</div>&nbsp;<spring:message code="module.order.date.format"/></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.order.button.add"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>