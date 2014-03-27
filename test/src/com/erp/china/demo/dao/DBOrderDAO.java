package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.Order;

public class DBOrderDAO extends AbstractDAO implements OrderDAO {
	private static SessionFactory sessionFactory;

	public DBOrderDAO() {
		logger = Logger.getLogger(DBOrderDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public String createOrder(Order order) {
		logger.info("creating new Order");
		String orderId = keyGen.generateId();
		order.setOrderId(orderId);
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(order);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to create new Order");
		return orderId;
	}

	public List<Order> orderList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List orderList = session.createQuery("from Order").list();
		if (orderList == null) orderList = new ArrayList();
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return orderList;
	}

	public Order loadOrder(String orderId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Order order = (Order) session.load(Order.class, orderId);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return order;
	}

	public void updateOrder(Order order) {
		logger.info("modifying existing Order");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Order existingOrder = (Order) session.load(Order.class, order.getOrderId());
		existingOrder.setCustomer(order.getCustomer());
		existingOrder.setOrderType(order.getOrderType());
		existingOrder.setOrderNumber(order.getOrderNumber());
		existingOrder.setOrderPrice(order.getOrderPrice());
		existingOrder.setOrderDate(order.getOrderDate());
		existingOrder.setDeliveryDate(order.getDeliveryDate());
		existingOrder.setRemarks(order.getRemarks());
		//TODO: need to add order status here
		existingOrder.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingOrder);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to modify existing Order");
	}

	public void removeOrder(String orderId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Order order = (Order) session.load(Order.class, orderId);
		if (order != null) {
			sessionFactory.getCurrentSession().delete(order);
		}
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
	}
}