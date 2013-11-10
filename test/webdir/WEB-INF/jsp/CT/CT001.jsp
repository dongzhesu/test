<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Creating New Customer</title>
</head>
<script src="../scripts/jquery/jquery.min.js" type="text/javascript" charset="utf-8"></script>
<script src="../scripts/jquery/jeditable.min.js" type="text/javascript" charset="utf-8"></script>
<script type="text/javascript" charset="utf-8">
$jquery = jQuery.noConflict();
$jquery(function() {
	$jquery(".sales_list").editable("../SL/004/setSales.json", {
		indicator: '<img src="../images/loading.gif">',
		loadurl: "../SL/004/salesList.json",
		callback: function(value) {
			var selectedSalesId = jQuery.parseJSON(value).selected;
			$jquery(this).html(jQuery.parseJSON(value)[selectedSalesId]);
			$jquery('[name=salesId]').val(selectedSalesId);
		},
		type: "select",
		submit: "SELECT",
		style: "inherit"
	});
});
</script>
<body>
<h2><spring:message code="module.customer.create.title"/></h2>
<form:form method="post" action="save.html" commandName="customer">
	<input type="hidden" name="salesId" value="${salesId}"/>
	<table>
	<tr>
		<td><form:label path="customerName"><spring:message code="entity.customer.name"/></form:label></td>
		<td><form:input path="customerName" /></td>
	</tr>
	<tr>
		<td><form:label path="sales"><spring:message code="entity.customer.sales"/></form:label></td>
		<td><div class="sales_list" style="display: inline">${salesName}</div></td>
	</tr>
	<tr>
		<td><form:label path="customerType"><spring:message code="entity.customer.type"/></form:label></td>
		<td><form:select path="customerType"><form:options items="${customerType}" /></form:select></td>
	</tr>
	<tr>
		<td><form:label path="paymentType"><spring:message code="entity.customer.payment.type"/></form:label></td>
		<td><form:select path="paymentType"><form:options items="${paymentType}" /></form:select></td>
	</tr>
	<tr>
		<td><form:label path="customerContact"><spring:message code="entity.customer.contact"/></form:label></td>
		<td><form:input path="customerContact" /></td>
	</tr>
	<tr>
		<td><form:label path="customerPhone"><spring:message code="entity.customer.phone"/></form:label></td>
 		<td><form:input path="customerPhone" /></td>
	</tr>
	<tr>
		<td><form:label path="customerFax"><spring:message code="entity.customer.fax"/></form:label></td>
 		<td><form:input path="customerFax" /></td>
	</tr>
	<tr>
		<td><form:label path="customerEmail"><spring:message code="entity.customer.email"/></form:label></td>
		<td><form:input path="customerEmail" /></td>
	</tr>
	<tr>
		<td><form:label path="customerAddress"><spring:message code="entity.customer.address"/></form:label></td>
		<td><form:input path="customerAddress" /></td>
	</tr>
	<tr>
		<td colspan="2">
			<input type="submit" value="<spring:message code="module.customer.button.add"/>"/>
		</td>
	</tr>
	</table>
</form:form>
</body>
</html>