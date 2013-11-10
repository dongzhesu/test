package com.erp.china.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
import com.erp.china.demo.model.BookingKey;
import com.erp.china.demo.model.Customer;
import com.erp.china.demo.model.Order;
import com.erp.china.demo.model.Product;
import com.erp.china.demo.model.Sales;
import com.erp.china.demo.service.BookingService;
import com.erp.china.demo.service.OrderService;
import com.erp.china.demo.service.ProductService;
import com.erp.china.demo.service.SalesService;
import com.erp.china.demo.util.Constants;

@Controller
@RequestMapping("/BK")
public class BookingController {
	private BookingService bookingService;
	private Logger logger;

	public BookingController() {
		logger = Logger.getLogger(BookingController.class);
		if (bookingService == null) {
			bookingService = BookingService.getInstance();
		}
	}

	@RequestMapping("/004")
	public String bookingList(Map<String, Object> map) {
		map.put("bookingList", bookingService.getBookingList());
		return "BK/BK004";
	}

	@RequestMapping(value = "/001")
	public String createBookingPage(Map<String, Object> map) {
		map.put("booking", new Booking());
		initBookingPage(map, "001");
		return "BK/BK001";
	}

	public void initBookingPage(Map<String, Object> map, String type) {
		Map<String, String> initOrderMap = new HashMap();
		Map<String, String> initProductMap = new HashMap();
		if ("001".equals(type)) {
			initOrderMap = OrderController.getOrderMap();
			initProductMap = ProductController.getProductMap();
		} else if ("002".equals(type)) {
			Booking booking = (Booking) map.get("booking");
			initOrderMap = OrderController.getOrderMap(booking.getBookingId().getOrder());
			initProductMap = ProductController.getProductMap(booking.getBookingId().getProduct());
		}
		String orderId = initOrderMap.get(Constants.SELECTED);
		String orderNumber = initOrderMap.get(orderId);
		String productId = initProductMap.get(Constants.SELECTED);
		String productName = initProductMap.get(productId);
		map.put("orderId", orderId);
		map.put("orderNumber", orderNumber);
		map.put("productId", productId);
		map.put("productName", productName);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveBooking(@ModelAttribute("booking") Booking booking, HttpServletRequest request) {
		Order order = OrderService.getInstance().loadOrder(request.getParameter("orderId"));
		Product product = ProductService.getInstance().loadProduct(request.getParameter("productId"));
		BookingKey bookingId = new BookingKey();
		bookingId.setOrder(order);
		bookingId.setProduct(product);
		booking.setBookingId(bookingId);
		bookingService.createBooking(booking);
		OrderController.initOrderMap();
		ProductController.initProductMap();
		return "redirect:/BK/004.html";
	}

	@RequestMapping(value = "/002/order/{orderId}/product/{productId}")
	public String updateBookingPage(@PathVariable("orderId") String orderId, @PathVariable("productId") String productId, Map<String, Object> map) {
		Order order = OrderService.getInstance().loadOrder(orderId);
		Product product = ProductService.getInstance().loadProduct(productId);
		BookingKey bookingId = new BookingKey();
		bookingId.setOrder(order);
		bookingId.setProduct(product);
		Booking booking = bookingService.loadBooking(bookingId);
		map.put("booking", booking);
		initBookingPage(map, "002");
		return "BK/BK002";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateBooking(@ModelAttribute("booking") Booking booking, HttpServletRequest request) {
		Order order = OrderService.getInstance().loadOrder(request.getParameter("orderId"));
		Product product = ProductService.getInstance().loadProduct(request.getParameter("productId"));
		BookingKey bookingId = new BookingKey();
		bookingId.setOrder(order);
		bookingId.setProduct(product);
		booking.setBookingId(bookingId);
		bookingService.updateBooking(booking);
		return "redirect:/BK/004.html";
	}

	@RequestMapping("/003/order/{orderId}/product/{productId}")
	public String deleteBooking(@PathVariable("orderId") String orderId, @PathVariable("productId") String productId) {
		Order order = OrderService.getInstance().loadOrder(orderId);
		Product product = ProductService.getInstance().loadProduct(productId);
		BookingKey bookingId = new BookingKey();
		bookingId.setOrder(order);
		bookingId.setProduct(product);
		bookingService.removeBooking(bookingId);
		return "redirect:/BK/004.html";
	}
	
	@RequestMapping(value="/004/bookingList", method = RequestMethod.GET)
	public @ResponseBody Map jsonBookingList() {
		Map resultMap = new HashMap();
		List<Map> productMapList = new ArrayList<Map>();
		List<Booking> entityList = bookingService.getBookingList();
		for (Booking entity : entityList) {
			Map<String, String> entityMap = new HashMap<String, String>();
			BookingKey bookingId = new BookingKey();
			bookingId = entity.getBookingId();
			entityMap.put("product_id", bookingId.getProduct().getProductId());
			entityMap.put("product_name", bookingId.getProduct().getProductName());
			entityMap.put("order_id", bookingId.getOrder().getOrderId());
			entityMap.put("order_number", bookingId.getOrder().getOrderNumber());
			entityMap.put("booking_price", Double.toString(entity.getBookingPrice()));
			entityMap.put("unit_price", Double.toString(entity.getUnitPrice()));
			entityMap.put("booking_qty", Integer.toString(entity.getBookingQty()));
			entityMap.put("discount", Integer.toString(entity.getDiscount()));
			
			entityMap.put(Constants.CREATED_DATE, entity.getCreatedDate().toString());
			entityMap.put(Constants.LAST_MODIFIED_DATE, entity.getLastModifiedDate().toString());
			productMapList.add(entityMap);
		}
		resultMap.put("success", true);
		resultMap.put("stats", productMapList);
		return resultMap;
	}
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody Map update(@RequestBody Map requestMap) {
		Booking entity = new Booking();
		Order order = OrderService.getInstance().loadOrder(requestMap.get("order_id").toString());
		Product product = ProductService.getInstance().loadProduct(requestMap.get("product_id").toString());
		BookingKey bookingId = new BookingKey();
		bookingId.setOrder(order);
		bookingId.setProduct(product);
		entity.setBookingId(bookingId);
		entity.setBookingPrice(requestMap.get("booking_price")!=null?Double.parseDouble(requestMap.get("booking_price").toString()):0);
		entity.setUnitPrice(requestMap.get("unit_price")!=null?Double.parseDouble(requestMap.get("unit_price").toString()):0);
		entity.setBookingQty(requestMap.get("booking_qty")!=null?Integer.parseInt(requestMap.get("booking_qty").toString()):0);
		entity.setDiscount(requestMap.get("discount")!=null?Integer.parseInt(requestMap.get("discount").toString()):100);
		bookingService.updateBooking(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Map create(@RequestBody Map requestMap) {
		Booking entity = new Booking();
		Order order = OrderService.getInstance().loadOrder(requestMap.get("order_id").toString());
		Product product = ProductService.getInstance().loadProduct(requestMap.get("product_id").toString());
		BookingKey bookingId = new BookingKey();
		bookingId.setOrder(order);
		bookingId.setProduct(product);
		entity.setBookingId(bookingId);
		entity.setBookingPrice(requestMap.get("booking_price")!=null?Double.parseDouble(requestMap.get("booking_price").toString()):0);
		entity.setUnitPrice(requestMap.get("unit_price")!=null?Double.parseDouble(requestMap.get("unit_price").toString()):0);
		entity.setBookingQty(requestMap.get("booking_qty")!=null?Integer.parseInt(requestMap.get("booking_qty").toString()):0);
		entity.setDiscount(requestMap.get("discount")!=null?Integer.parseInt(requestMap.get("discount").toString()):100);
		bookingService.createBooking(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public @ResponseBody Map delete(@RequestBody Map requestMap) {
		Order order = OrderService.getInstance().loadOrder(requestMap.get("order_id").toString());
		Product product = ProductService.getInstance().loadProduct(requestMap.get("product_id").toString());
		BookingKey bookingId = new BookingKey();
		bookingId.setOrder(order);
		bookingId.setProduct(product);
		bookingService.removeBooking(bookingId);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
}