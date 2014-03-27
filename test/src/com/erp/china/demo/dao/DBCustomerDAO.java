package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.Customer;

public class DBCustomerDAO extends AbstractDAO implements CustomerDAO {
	private static SessionFactory sessionFactory;

	public DBCustomerDAO() {
		logger = Logger.getLogger(DBCustomerDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public void createCustomer(Customer customer) {
		logger.info("creating new Customer");
		String customerId = keyGen.generateId();
		customer.setCustomerId(customerId);
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(customer);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to create new Customer");
	}

	public List<Customer> customerList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List customerList = session.createQuery("from Customer").list();
		if (customerList == null) customerList = new ArrayList();
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return customerList;
	}

	public Customer loadCustomer(String customerId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Customer customer = (Customer) session.load(Customer.class, customerId);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return customer;
	}

	public void updateCustomer(Customer customer) {
		logger.info("modifying existing Customer");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Customer existingCustomer = (Customer) session.load(Customer.class, customer.getCustomerId());
		existingCustomer.setSales(customer.getSales());
		existingCustomer.setCustomerNumber(customer.getCustomerNumber());
		existingCustomer.setCustomerName(customer.getCustomerName());
		existingCustomer.setCustomerType(customer.getCustomerType());
		existingCustomer.setPaymentType(customer.getPaymentType());
		existingCustomer.setCustomerContact(customer.getCustomerContact());
		existingCustomer.setCustomerPhone(customer.getCustomerPhone());
		existingCustomer.setCustomerFax(customer.getCustomerFax());
		existingCustomer.setCustomerEmail(customer.getCustomerEmail());
		existingCustomer.setCustomerAddress(customer.getCustomerAddress());
		existingCustomer.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingCustomer);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to modify existing Customer");
	}

	public void removeCustomer(String customerId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Customer customer = (Customer) session.load(Customer.class, customerId);
		if (customer != null) {
			sessionFactory.getCurrentSession().delete(customer);
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