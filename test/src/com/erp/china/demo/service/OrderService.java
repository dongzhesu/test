package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBOrderDAO;
import com.erp.china.demo.dao.OrderDAO;
import com.erp.china.demo.model.Order;

public class OrderService {
	private static Logger logger = Logger.getLogger(OrderService.class);
	private static OrderService instance = null;

	private static OrderDAO orderDAO;

	public static synchronized OrderService getInstance() {
		if (instance == null) {
			instance = new OrderService();
		}
		return instance;
	}

	private OrderService() {
		orderDAO = new DBOrderDAO();
	}

	@Transactional
    public String createOrder(Order order) {
        return orderDAO.createOrder(order);
    }

	@Transactional
    public List<Order> getOrderList() {
        return orderDAO.orderList();
    }

	@Transactional
    public Order loadOrder(String orderId) {
        return orderDAO.loadOrder(orderId);
    }

	@Transactional
    public void updateOrder(Order order) {
		orderDAO.updateOrder(order);
    }

	@Transactional
	public void removeOrder(String orderId) {
		orderDAO.removeOrder(orderId);
	}
}