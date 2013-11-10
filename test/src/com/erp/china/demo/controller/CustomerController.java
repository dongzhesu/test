package com.erp.china.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.china.demo.model.Booking;
import com.erp.china.demo.model.Customer;
import com.erp.china.demo.model.Lookup;
import com.erp.china.demo.model.Product;
import com.erp.china.demo.model.Sales;
import com.erp.china.demo.service.CustomerService;
import com.erp.china.demo.service.LookupService;
import com.erp.china.demo.service.SalesService;
import com.erp.china.demo.util.Constants;

@Controller
@RequestMapping("/CT")
public class CustomerController {
	private static CustomerService customerService;
	private Logger logger;
	private static Map<String, String> customerMap;

	public CustomerController() {
		logger = Logger.getLogger(CustomerController.class);
		customerMap = new ConcurrentHashMap();
		if (customerService == null) {
			customerService = CustomerService.getInstance();
		}
		initCustomerMap();
	}

	public static void initCustomerMap() {
		List<Customer> customerList = customerService.getCustomerList();
		for (Customer customer : customerList) {
			customerMap.put(customer.getCustomerId(), customer.getCustomerName());
		}
		customerMap.put(Constants.SELECTED, customerList.get(0).getCustomerId());
	}

	public static Map<String, String> getCustomerMap(Customer customer) {
		customerMap.put(Constants.SELECTED, customer.getCustomerId());
		return customerMap;
	}

	public static Map<String, String> getCustomerMap() {
		return customerMap;
	}

	@RequestMapping("/004")
	public String customerList(Map<String, Object> map) {
		map.put("customerList", customerService.getCustomerList());
		return "CT/CT004";
	}

	@RequestMapping(value = "/001")
	public String createCustomerPage(Map<String, Object> map) {
		map.put("customer", new Customer());
		initCustomerPage(map, "001");
		return "CT/CT001";
	}

	public void initCustomerPage(Map<String, Object> map, String type) {
		Map customerType = new java.util.HashMap();
		Map paymentType = new java.util.HashMap();
		LookupService lookupService = LookupService.getInstance();
		List<Lookup> custTypeList = lookupService.getLookupList(Constants.CUST_TYPE);
		for (Lookup custType : custTypeList) {
			String customerTypeKey = Constants.CUST_TYPE_PERSON_KEY;
			if (custType.getLookupKey().endsWith(Constants.CUST_TYPE_COMPANY_KEY)) customerTypeKey = Constants.CUST_TYPE_COMPANY_KEY;
			customerType.put(customerTypeKey, custType.getLookupValue());
		}
		List<Lookup> paymentTypeList = lookupService.getLookupList(Constants.PAYMENT_TYPE);
		for (Lookup paymentTypeOption : paymentTypeList) {
			String paymentTypeKey = Constants.PAYMENT_TYPE_CASH;
			if (paymentTypeOption.getLookupKey().endsWith(Constants.PAYMENT_TYPE_CREDIT_CARD_KEY)) paymentTypeKey = Constants.PAYMENT_TYPE_CREDIT_CARD_KEY;
			paymentType.put(paymentTypeKey, paymentTypeOption.getLookupValue());
		}
		map.put("customerType", customerType);
		map.put("paymentType", paymentType);
		Map<String, String> initSalesMap = new HashMap();
		if ("001".equals(type)) {
			initSalesMap = SalesController.getSalesMap();
		} else if ("002".equals(type)) {
			Customer customer = (Customer) map.get("customer");
			initSalesMap = SalesController.getSalesMap(customer.getSales());
		}
		String salesId = initSalesMap.get(Constants.SELECTED);
		String salesName = initSalesMap.get(salesId);
		map.put("salesId", salesId);
		map.put("salesName", salesName);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveCustomer(@ModelAttribute("customer") Customer customer, HttpServletRequest request) {
		Sales sales = SalesService.getInstance().loadSales(request.getParameter("salesId"));
		customer.setSales(sales);
		customerService.createCustomer(customer);
		SalesController.initSalesMap();
		return "redirect:/CT/004.html";
	}

	@RequestMapping(value = "/002/{customerId}")
	public String updateCustomerPage(@PathVariable("customerId") String customerId, Map<String, Object> map) {
		Customer customer = customerService.loadCustomer(customerId);
		map.put("customer", customer);
		initCustomerPage(map, "002");
		return "CT/CT002";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateCustomer(@ModelAttribute("customer") Customer customer, HttpServletRequest request) {
		Sales sales = SalesService.getInstance().loadSales(request.getParameter("salesId"));
		customer.setSales(sales);
		customerService.updateCustomer(customer);
		return "redirect:/CT/004.html";
	}

	@RequestMapping("/003/{customerId}")
	public String deleteCustomer(@PathVariable("customerId") String customerId) {
		customerService.removeCustomer(customerId);
		return "redirect:/CT/004.html";
	}

	@RequestMapping(value="/004/customerList", method = RequestMethod.GET)
	public @ResponseBody Map jsonCustomerList() {
		Map resultMap = new HashMap();
		List<Map> entityMapList = new ArrayList<Map>();
		List<Customer> entityList = customerService.getCustomerList();
		for (Customer entity : entityList) {
			Map<String, String> entityMap = new HashMap<String, String>();
			
			entityMap.put("customer_id", entity.getCustomerId());
			entityMap.put("sales_id", entity.getSales().getSalesId());
			entityMap.put("customer_number", entity.getCustomerNumber());
			entityMap.put("customer_name", entity.getCustomerName());
			entityMap.put("customer_type", entity.getCustomerType());
			entityMap.put("payment_type", entity.getPaymentType());
			entityMap.put("customer_contact", entity.getCustomerContact());
			entityMap.put("customer_phone", entity.getCustomerPhone());
			entityMap.put("customer_fax", entity.getCustomerFax());
			entityMap.put("customer_email", entity.getCustomerEmail());
			entityMap.put("customer_address", entity.getCustomerAddress());
			
			entityMap.put(Constants.CREATED_DATE, entity.getCreatedDate().toString());
			entityMap.put(Constants.LAST_MODIFIED_DATE, entity.getLastModifiedDate().toString());
			entityMapList.add(entityMap);
		}
		resultMap.put("success", true);
		resultMap.put("stats", entityMapList);
		return resultMap;
	}

	@RequestMapping(value="/004/setCustomer", method = RequestMethod.POST)
	public @ResponseBody Map setCustomerMap(HttpServletRequest request) {
		customerMap.put(Constants.SELECTED, request.getParameter("value"));
		return customerMap;
	}
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody Map update(@RequestBody Map requestMap) {
		Customer entity = new Customer();
		Sales sales = SalesService.getInstance().loadSales(requestMap.get("sales_id").toString());
		entity.setSales(sales);
		entity.setCustomerId(requestMap.get("customer_id")!=null?requestMap.get("customer_id").toString():"");
		entity.setCustomerName(requestMap.get("customer_number")!=null?requestMap.get("customer_number").toString():"");
		entity.setCustomerName(requestMap.get("customer_name")!=null?requestMap.get("customer_name").toString():"");
		entity.setCustomerContact(requestMap.get("customer_contact")!=null?requestMap.get("customer_contact").toString():"");
		entity.setCustomerType(requestMap.get("customer_type")!=null?requestMap.get("customer_type").toString():"");
		entity.setPaymentType(requestMap.get("payment_type")!=null?requestMap.get("payment_type").toString():"");
		entity.setCustomerFax(requestMap.get("customer_fax")!=null?requestMap.get("customer_fax").toString():"");
		entity.setCustomerEmail(requestMap.get("customer_email")!=null?requestMap.get("customer_email").toString():"");
		entity.setCustomerPhone(requestMap.get("customer_phone")!=null?requestMap.get("customer_phone").toString():"");
		entity.setCustomerAddress(requestMap.get("customer_address")!=null?requestMap.get("customer_address").toString():"");
		customerService.updateCustomer(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Map create(@RequestBody Map requestMap) {
		Customer entity = new Customer();
		Sales sales = SalesService.getInstance().loadSales(requestMap.get("sales_id").toString());
		entity.setSales(sales);
		entity.setCustomerName(requestMap.get("customer_number")!=null?requestMap.get("customer_number").toString():"");
		entity.setCustomerName(requestMap.get("customer_name")!=null?requestMap.get("customer_name").toString():"");
		entity.setCustomerContact(requestMap.get("customer_contact")!=null?requestMap.get("customer_contact").toString():"");
		entity.setCustomerType(requestMap.get("customer_type")!=null?requestMap.get("customer_type").toString():"");
		entity.setPaymentType(requestMap.get("payment_type")!=null?requestMap.get("payment_type").toString():"");
		entity.setCustomerFax(requestMap.get("customer_fax")!=null?requestMap.get("customer_fax").toString():"");
		entity.setCustomerEmail(requestMap.get("customer_email")!=null?requestMap.get("customer_email").toString():"");
		entity.setCustomerPhone(requestMap.get("customer_phone")!=null?requestMap.get("customer_phone").toString():"");
		entity.setCustomerAddress(requestMap.get("customer_address")!=null?requestMap.get("customer_address").toString():"");
		customerService.createCustomer(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public @ResponseBody Map delete(@RequestBody Map requestMap) {
		Product entity = new Product();
		String customer_id = requestMap.get("customer_id").toString();
		customerService.removeCustomer(customer_id);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	
}