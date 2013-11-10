<%@page import="com.erp.china.demo.model.*, com.erp.china.demo.service.LookupService, com.erp.china.demo.util.Constants" %>
<%@page import="java.util.List" %>
<%@taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<%@taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<html>
<head>
	<title>ERP Demo - Customer Listing</title>
</head>
<body>
<h2><spring:message code="module.customer.list.title"/></h2>
<table class="data">
<tr>
	<th><spring:message code="entity.customer.name"/></th>
	<th><spring:message code="entity.customer.sales"/></th>
	<th><spring:message code="entity.customer.type"/></th>
	<th><spring:message code="entity.customer.payment.type"/></th>
	<th><spring:message code="entity.customer.contact"/></th>
	<th><spring:message code="entity.customer.phone"/></th>
	<th><spring:message code="entity.customer.fax"/></th>
	<th><spring:message code="entity.customer.email"/></th>
	<th><spring:message code="entity.customer.address"/></th>
	<th><spring:message code="entity.createdDate"/></th>
	<th><spring:message code="entity.lastModifiedDate"/></th>
	<th colspan="2"><spring:message code="module.action"/></th>
</tr>
<c:if test="${!empty customerList}">
<c:forEach items="${customerList}" var="customer">
<%
Customer customer = (Customer) pageContext.getAttribute("customer");
String custTypeCriteria = Constants.CUST_TYPE + "_" + customer.getCustomerType();
String paymentTypeCriteria = Constants.PAYMENT_TYPE + "_" + customer.getPaymentType();
LookupService lookupService = LookupService.getInstance();
List<Lookup> custTypeList = lookupService.getLookupList(custTypeCriteria);
List<Lookup> paymentTypeList = lookupService.getLookupList(paymentTypeCriteria);
%>
	<tr>
		<td>${customer.customerName}</td>
		<td>${customer.sales.salesName}</td>
		<td><%=custTypeList!=null?custTypeList.get(0).getLookupValue():""%></td>
		<td><%=paymentTypeList!=null?paymentTypeList.get(0).getLookupValue():""%></td>
		<td>${customer.customerContact}</td>
		<td>${customer.customerPhone}</td>
		<td>${customer.customerFax}</td>
		<td>${customer.customerEmail}</td>
		<td>${customer.customerAddress}</td>
		<td>${customer.createdDate}</td>
		<td>${customer.lastModifiedDate}</td>
		<td><a href="002/${customer.customerId}.html">modify</a></td>
		<td><a href="003/${customer.customerId}.html">delete</a></td>
    </tr>
</c:forEach>
</table>
</c:if>
</body>
</html>