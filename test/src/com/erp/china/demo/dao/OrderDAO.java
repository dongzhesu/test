package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Order;

public interface OrderDAO {
	public String createOrder(Order order);
	public List<Order> orderList();
	public Order loadOrder(String orderId);
	public void updateOrder(Order order);
	public void removeOrder(String orderId);
}