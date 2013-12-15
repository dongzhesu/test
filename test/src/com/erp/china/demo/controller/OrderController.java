package com.erp.china.demo.controller;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import net.sf.jasperreports.engine.JRDataSource;
import net.sf.jasperreports.engine.data.JRMapCollectionDataSource;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import com.erp.china.demo.model.Booking;
import com.erp.china.demo.model.Order;
import com.erp.china.demo.model.Customer;
import com.erp.china.demo.service.BookingService;
import com.erp.china.demo.service.OrderService;
import com.erp.china.demo.service.CustomerService;
import com.erp.china.demo.util.Constants;

@Controller
@RequestMapping("/OD")
public class OrderController {
	private static OrderService orderService;
	private static BookingService bookingService;
	private Logger logger;
	private static Map<String, String> orderMap;

	public OrderController() {
		logger = Logger.getLogger(OrderController.class);
		orderMap = new ConcurrentHashMap();
		if (orderService == null) {
			orderService = OrderService.getInstance();
		}
		if (bookingService == null) {
			bookingService = BookingService.getInstance();
		}
		initOrderMap();
	}

	public static void initOrderMap() {
		List<Order> orderList = orderService.getOrderList();
		for (Order order : orderList) {
			orderMap.put(order.getOrderId(), order.getOrderNumber());
		}
		orderMap.put(Constants.SELECTED, orderList.get(0).getOrderId());
	}

	public static Map<String, String> getOrderMap(Order order) {
		orderMap.put(Constants.SELECTED, order.getOrderId());
		return orderMap;
	}

	public static Map<String, String> getOrderMap() {
		return orderMap;
	}

	@RequestMapping("/004")
	public String orderList(Map<String, Object> map) {
		map.put("orderList", orderService.getOrderList());
		return "OD/OD004";
	}

	@RequestMapping(value = "/001")
	public String createOrderPage(Map<String, Object> map) {
		map.put("order", new Order());
		initOrderPage(map, "001");
		return "OD/OD001";
	}

	public void initOrderPage(Map<String, Object> map, String type) {
		Map<String, String> initCustomerMap = new HashMap();
		Calendar calendar = Calendar.getInstance();
		Date orderDate = null;
		if ("001".equals(type)) {
			initCustomerMap = CustomerController.getCustomerMap();
		} else if ("002".equals(type)) {
			Order order = (Order) map.get("order");
			initCustomerMap = CustomerController.getCustomerMap(order.getCustomer());
			orderDate = order.getOrderDate();
		}
		if (orderDate == null) orderDate = calendar.getTime();
		String customerId = initCustomerMap.get(Constants.SELECTED);
		String customerName = initCustomerMap.get(customerId);
		map.put("customerId", customerId);
		map.put("customerName", customerName);
		map.put("orderDate", Constants.sdf.format(orderDate));
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveOrder(@ModelAttribute("order") Order order, HttpServletRequest request) {
		Customer customer = CustomerService.getInstance().loadCustomer(request.getParameter("customerId"));
		order.setCustomer(customer);
		String orderDate = (String) request.getParameter("order_date");
		try {
			if (orderDate != null) order.setOrderDate(Constants.sdf.parse(orderDate));
		} catch (ParseException pe) {
			logger.debug("ParseException in saving new order: "+pe.getMessage());
			order.setOrderDate(new Date());
		}
		orderService.createOrder(order);
		CustomerController.initCustomerMap();
		return "redirect:/OD/004.html";
	}

	@RequestMapping(value = "/002/{orderId}")
	public String updateOrderPage(@PathVariable("orderId") String orderId, Map<String, Object> map) {
		Order order = orderService.loadOrder(orderId);
		map.put("order", order);
		initOrderPage(map, "002");
		return "OD/OD002";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateOrder(@ModelAttribute("order") Order order, HttpServletRequest request) {
		Customer customer = CustomerService.getInstance().loadCustomer(request.getParameter("customerId"));
		order.setCustomer(customer);
		String orderDate = (String) request.getParameter("order_date");
		try {
			if (orderDate != null) order.setOrderDate(Constants.sdf.parse(orderDate));
		} catch (ParseException pe) {
			logger.debug("ParseException in updating existing order: "+pe.getMessage());
			order.setOrderDate(new Date());
		}
		orderService.updateOrder(order);
		return "redirect:/OD/004.html";
	}

	@RequestMapping("/003/{orderId}")
	public String deleteOrder(@PathVariable("orderId") String orderId) {
		orderService.removeOrder(orderId);
		return "redirect:/OD/004.html";
	}

	@RequestMapping(value="/004/orderList", method = RequestMethod.GET)
	public @ResponseBody Map jsonOrderList() {
		Map resultMap = new HashMap();
		List<Map> entityMapList = new ArrayList<Map>();
		List<Order> entityList = orderService.getOrderList();
		for (Order entity : entityList) {
			Map<String, String> entityMap = new HashMap<String, String>();
			
			entityMap.put("order_id", entity.getOrderId());
			entityMap.put("customer_number", entity.getCustomer().getCustomerNumber());
			entityMap.put("customer_name", entity.getCustomer().getCustomerName());
			entityMap.put("customer_id", entity.getCustomer().getCustomerId());
			entityMap.put("order_number", entity.getOrderNumber());
			entityMap.put("order_type", entity.getOrderType());
			entityMap.put("remarks", entity.getRemarks());
			entityMap.put("order_price", Double.toString(entity.getOrderPrice()));
			entityMap.put("order_date", entity.getOrderDate().toString());
			entityMap.put("delivery_date", entity.getDeliveryDate().toString());
			
			
			entityMap.put(Constants.CREATED_DATE, entity.getCreatedDate().toString());
			entityMap.put(Constants.LAST_MODIFIED_DATE, entity.getLastModifiedDate().toString());
			entityMapList.add(entityMap);
		}
		resultMap.put("success", true);
		resultMap.put("stats", entityMapList);
		return resultMap;
	}

	@RequestMapping(value="/004/setOrder", method = RequestMethod.POST)
	public @ResponseBody Map setOrderMap(HttpServletRequest request) {
		orderMap.put(Constants.SELECTED, request.getParameter("value"));
		return orderMap;
	}
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody Map update(@RequestBody Map requestMap) {
		String order_id = requestMap.get("order_id").toString();
		boolean isBookingDeleted = bookingService.deleteBookingByOrderId(order_id);
		if (isBookingDeleted) {
			orderService.removeOrder(order_id);
		}else
			return null;
		Order entity = new Order();
		Customer customer = CustomerService.getInstance().loadCustomer(requestMap.get("customer_id").toString());
		entity.setCustomer(customer);
		//entity.setOrderId(requestMap.get("order_id")!=null?requestMap.get("order_id").toString():"");
		entity.setOrderNumber(requestMap.get("order_number")!=null?requestMap.get("order_number").toString():"");
		entity.setOrderPrice(requestMap.get("order_price")!=null?Double.parseDouble(requestMap.get("order_price").toString()):0);
		entity.setOrderType(requestMap.get("order_type")!=null?requestMap.get("order_type").toString():"");
		entity.setRemarks(requestMap.get("remarks")!=null?requestMap.get("remarks").toString():"");
		if(requestMap.get("delivery_date")!=null&&requestMap.get("delivery_date").toString()!=""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			
			String delivery_date=requestMap.get("delivery_date").toString();
			Date delivery;
			try {
				delivery = sdf.parse(delivery_date);
				entity.setDeliveryDate(delivery);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				entity.setDeliveryDate(new Date());
				e.printStackTrace();
			}
			
		}else 
			entity.setDeliveryDate(new Date());

		entity.setOrderDate(new Date());
		String orderId = orderService.createOrder(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		resultMap.put("orderId", orderId);
		return resultMap;
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Map create(@RequestBody Map requestMap) {
		Order entity = new Order();
		Customer customer = CustomerService.getInstance().loadCustomer(requestMap.get("customer_id").toString());
		entity.setCustomer(customer);
		entity.setOrderNumber(requestMap.get("order_number")!=null?requestMap.get("order_number").toString():"");
		entity.setOrderPrice(requestMap.get("order_price")!=null?Double.parseDouble(requestMap.get("order_price").toString()):0);
		entity.setOrderType(requestMap.get("order_type")!=null?requestMap.get("order_type").toString():"");
		entity.setRemarks(requestMap.get("remarks")!=null?requestMap.get("remarks").toString():"");
		if(requestMap.get("delivery_date")!=null&&requestMap.get("delivery_date").toString()!=""){
			SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss");
			
			String delivery_date=requestMap.get("delivery_date").toString();
			Date delivery;
			try {
				delivery = sdf.parse(delivery_date);
				entity.setDeliveryDate(delivery);
			} catch (ParseException e) {
				// TODO Auto-generated catch block
				entity.setDeliveryDate(new Date());
				e.printStackTrace();
			}
			
		}else 
			entity.setDeliveryDate(new Date());
		entity.setOrderDate(new Date());
		String orderId = orderService.createOrder(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		resultMap.put("orderId", orderId);
		return resultMap;
	}

	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public @ResponseBody Map delete(@RequestBody Map requestMap) {
		String order_id = requestMap.get("order_id").toString();
		boolean isBookingDeleted = bookingService.deleteBookingByOrderId(order_id);
		Map resultMap = new HashMap();
		if (isBookingDeleted) {
			orderService.removeOrder(order_id);
			resultMap.put("success", true);
		}
		return resultMap;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/{orderId}/pdf")
	public ModelAndView generatePdfReport(@PathVariable("orderId") String orderId, ModelAndView modelAndView) {
		logger.debug("--------------generate PDF report----------");
		Map<String, Object> dataSourceMap = new HashMap<String, Object>();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Order order = orderService.loadOrder(orderId);

		parameterMap.put(Constants.ORDER_NO, (order.getOrderNumber()!=null&&!"".equals(order.getOrderNumber()))?order.getOrderNumber():"");
		parameterMap.put(Constants.CUSTOMER_PO_NO, "");
		parameterMap.put(Constants.CUSTOMER_NO, (order.getCustomer().getCustomerNumber()!=null&&!"".equals(order.getCustomer().getCustomerNumber()))?order.getCustomer().getCustomerNumber():"");
		parameterMap.put(Constants.CUSTOMER_NAME, (order.getCustomer().getCustomerName()!=null&&!"".equals(order.getCustomer().getCustomerName()))?order.getCustomer().getCustomerName():"");
		parameterMap.put(Constants.CUSTOMER_ADDRESS, (order.getCustomer().getCustomerAddress()!=null&&!"".equals(order.getCustomer().getCustomerAddress()))?order.getCustomer().getCustomerAddress():"");
		parameterMap.put(Constants.CUSTOMER_CONTACT, (order.getCustomer().getCustomerContact()!=null&&!"".equals(order.getCustomer().getCustomerContact()))?order.getCustomer().getCustomerContact():"");
		parameterMap.put(Constants.CUSTOMER_PHONE, (order.getCustomer().getCustomerPhone()!=null&&!"".equals(order.getCustomer().getCustomerPhone()))?order.getCustomer().getCustomerPhone():"");
		parameterMap.put(Constants.ORDER_DATE, Constants.invoice_sdf.format(order.getOrderDate()));
		parameterMap.put(Constants.DELIVERY_DATE, Constants.invoice_sdf.format(order.getDeliveryDate()));
		parameterMap.put(Constants.SALES_NAME, (order.getCustomer().getSales().getSalesName()!=null&&!"".equals(order.getCustomer().getSales().getSalesName()))?order.getCustomer().getSales().getSalesName():"");
		parameterMap.put(Constants.REMARKS, (order.getRemarks()!=null&&!"".equals(order.getRemarks()))?order.getRemarks():"");
		List parameterList = new ArrayList();
		List bookingList = bookingService.getBookingList(order);
		int i = 0;
		for (Iterator<Booking> bookingItr = bookingList.iterator(); bookingItr.hasNext();) {
			Booking booking = bookingItr.next();
			if (i == 0) {
				parameterMap.put(Constants.PRODUCT_CODE, booking.getBookingId().getProduct().getProductCode()!=null?booking.getBookingId().getProduct().getProductCode():"");
				parameterMap.put(Constants.PRODUCT_DESC, booking.getBookingId().getProduct().getProductDesc()!=null?booking.getBookingId().getProduct().getProductDesc():"");
				parameterMap.put(Constants.PRODUCT_YEAR, booking.getBookingId().getProduct().getProductYear()!=null?booking.getBookingId().getProduct().getProductYear():"");
				parameterMap.put(Constants.PRODUCT_PRICE, String.valueOf(booking.getBookingId().getProduct().getProductPrice()));
				parameterMap.put(Constants.DISCOUNT, String.valueOf(booking.getDiscount()));
				parameterMap.put(Constants.UNIT_PRICE, String.valueOf(booking.getUnitPrice()));
				parameterMap.put(Constants.BOOKING_QTY, String.valueOf(booking.getBookingQty()));
				parameterMap.put(Constants.TOTAL_ITEM_AMOUNT, String.valueOf(booking.getBookingPrice()));
				if (bookingList.size() == 1) parameterMap.put(Constants.TOTAL_AMOUNT, String.valueOf(order.getOrderPrice()));
				parameterList.add(parameterMap);
			} else {
				Map<String, Object> productMap = new HashMap<String, Object>();
				productMap.put(Constants.PRODUCT_CODE, booking.getBookingId().getProduct().getProductCode()!=null?booking.getBookingId().getProduct().getProductCode():"");
				productMap.put(Constants.PRODUCT_DESC, booking.getBookingId().getProduct().getProductDesc()!=null?booking.getBookingId().getProduct().getProductDesc():"");
				productMap.put(Constants.PRODUCT_YEAR, booking.getBookingId().getProduct().getProductYear()!=null?booking.getBookingId().getProduct().getProductYear():"");
				productMap.put(Constants.PRODUCT_PRICE, String.valueOf(booking.getBookingId().getProduct().getProductPrice()));
				productMap.put(Constants.DISCOUNT, String.valueOf(booking.getDiscount()));
				productMap.put(Constants.UNIT_PRICE, String.valueOf(booking.getUnitPrice()));
				productMap.put(Constants.BOOKING_QTY, String.valueOf(booking.getBookingQty()));
				productMap.put(Constants.TOTAL_ITEM_AMOUNT, String.valueOf(booking.getBookingPrice()));
				if (i == (bookingList.size() - 1)) productMap.put(Constants.TOTAL_AMOUNT, String.valueOf(order.getOrderPrice()));
				parameterList.add(productMap);
			}
			i++;
		}

		JRDataSource dataSource = new JRMapCollectionDataSource(parameterList);
		dataSourceMap.put("datasource", dataSource);
		modelAndView = new ModelAndView("pdfReport", dataSourceMap);
		return modelAndView;
	}

	@RequestMapping(method = RequestMethod.GET , value = "/{orderId}/xls")
	public ModelAndView generateExcelReport(@PathVariable("orderId") String orderId, ModelAndView modelAndView) {
		logger.debug("--------------generate Excel report----------");
		Map<String, Object> dataSourceMap = new HashMap<String, Object>();
		Map<String, Object> parameterMap = new HashMap<String, Object>();
		Order order = orderService.loadOrder(orderId);

		parameterMap.put(Constants.ORDER_NO, (order.getOrderNumber()!=null&&!"".equals(order.getOrderNumber()))?order.getOrderNumber():"");
		parameterMap.put(Constants.CUSTOMER_PO_NO, "");
		parameterMap.put(Constants.CUSTOMER_NO, (order.getCustomer().getCustomerNumber()!=null&&!"".equals(order.getCustomer().getCustomerNumber()))?order.getCustomer().getCustomerNumber():"");
		parameterMap.put(Constants.CUSTOMER_NAME, (order.getCustomer().getCustomerName()!=null&&!"".equals(order.getCustomer().getCustomerName()))?order.getCustomer().getCustomerName():"");
		parameterMap.put(Constants.CUSTOMER_ADDRESS, (order.getCustomer().getCustomerAddress()!=null&&!"".equals(order.getCustomer().getCustomerAddress()))?order.getCustomer().getCustomerAddress():"");
		parameterMap.put(Constants.CUSTOMER_CONTACT, (order.getCustomer().getCustomerContact()!=null&&!"".equals(order.getCustomer().getCustomerContact()))?order.getCustomer().getCustomerContact():"");
		parameterMap.put(Constants.CUSTOMER_PHONE, (order.getCustomer().getCustomerPhone()!=null&&!"".equals(order.getCustomer().getCustomerPhone()))?order.getCustomer().getCustomerPhone():"");
		parameterMap.put(Constants.ORDER_DATE, Constants.invoice_sdf.format(order.getOrderDate()));
		parameterMap.put(Constants.DELIVERY_DATE, Constants.invoice_sdf.format(order.getDeliveryDate()));
		parameterMap.put(Constants.SALES_NAME, (order.getCustomer().getSales().getSalesName()!=null&&!"".equals(order.getCustomer().getSales().getSalesName()))?order.getCustomer().getSales().getSalesName():"");
		parameterMap.put(Constants.REMARKS, (order.getRemarks()!=null&&!"".equals(order.getRemarks()))?order.getRemarks():"");
		List parameterList = new ArrayList();
		List bookingList = bookingService.getBookingList(order);
		int i = 0;
		for (Iterator<Booking> bookingItr = bookingList.iterator(); bookingItr.hasNext();) {
			Booking booking = bookingItr.next();
			if (i == 0) {
				parameterMap.put(Constants.PRODUCT_CODE, booking.getBookingId().getProduct().getProductCode()!=null?booking.getBookingId().getProduct().getProductCode():"");
				parameterMap.put(Constants.PRODUCT_DESC, booking.getBookingId().getProduct().getProductDesc()!=null?booking.getBookingId().getProduct().getProductDesc():"");
				parameterMap.put(Constants.PRODUCT_YEAR, booking.getBookingId().getProduct().getProductYear()!=null?booking.getBookingId().getProduct().getProductYear():"");
				parameterMap.put(Constants.PRODUCT_PRICE, String.valueOf(booking.getBookingId().getProduct().getProductPrice()));
				parameterMap.put(Constants.DISCOUNT, String.valueOf(booking.getDiscount()));
				parameterMap.put(Constants.UNIT_PRICE, String.valueOf(booking.getUnitPrice()));
				parameterMap.put(Constants.BOOKING_QTY, String.valueOf(booking.getBookingQty()));
				parameterMap.put(Constants.TOTAL_ITEM_AMOUNT, String.valueOf(booking.getBookingPrice()));
				if (bookingList.size() == 1) parameterMap.put(Constants.TOTAL_AMOUNT, String.valueOf(order.getOrderPrice()));
				parameterList.add(parameterMap);
			} else {
				Map<String, Object> productMap = new HashMap<String, Object>();
				productMap.put(Constants.PRODUCT_CODE, booking.getBookingId().getProduct().getProductCode()!=null?booking.getBookingId().getProduct().getProductCode():"");
				productMap.put(Constants.PRODUCT_DESC, booking.getBookingId().getProduct().getProductDesc()!=null?booking.getBookingId().getProduct().getProductDesc():"");
				productMap.put(Constants.PRODUCT_YEAR, booking.getBookingId().getProduct().getProductYear()!=null?booking.getBookingId().getProduct().getProductYear():"");
				productMap.put(Constants.PRODUCT_PRICE, String.valueOf(booking.getBookingId().getProduct().getProductPrice()));
				productMap.put(Constants.DISCOUNT, String.valueOf(booking.getDiscount()));
				productMap.put(Constants.UNIT_PRICE, String.valueOf(booking.getUnitPrice()));
				productMap.put(Constants.BOOKING_QTY, String.valueOf(booking.getBookingQty()));
				productMap.put(Constants.TOTAL_ITEM_AMOUNT, String.valueOf(booking.getBookingPrice()));
				if (i == (bookingList.size() - 1)) productMap.put(Constants.TOTAL_AMOUNT, String.valueOf(order.getOrderPrice()));
				parameterList.add(productMap);
			}
			i++;
		}

		JRDataSource dataSource = new JRMapCollectionDataSource(parameterList);
		dataSourceMap.put("datasource", dataSource);
		modelAndView = new ModelAndView("xlsReport", dataSourceMap);
		return modelAndView;
	 }
}