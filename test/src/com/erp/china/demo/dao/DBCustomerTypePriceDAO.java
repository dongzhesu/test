package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.CustomerTypePrice;

public class DBCustomerTypePriceDAO extends AbstractDAO implements CustomerTypePriceDAO {
	private static SessionFactory sessionFactory;

	public DBCustomerTypePriceDAO() {
		logger = Logger.getLogger(DBCustomerTypePriceDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public List<CustomerTypePrice> customerTypePriceList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List customerTypePriceList = session.createQuery("from CustomerTypePrice").list();
		if (customerTypePriceList == null) customerTypePriceList = new ArrayList();
		return customerTypePriceList;
	}

	public CustomerTypePrice loadCustomerTypePrice(String typeId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		CustomerTypePrice customerTypePrice = (CustomerTypePrice) session.load(CustomerTypePrice.class, typeId);
		return customerTypePrice;
	}

	public CustomerTypePrice loadCustomerTypePrice(String customerType, String productId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CustomerTypePrice where customer_type = '"+customerType+"' and product_id = '"+productId+"'");
		List customerTypePriceList = query.list();
		CustomerTypePrice customerTypePrice = null;
		if (customerTypePriceList.size() > 0) {
			customerTypePrice = (CustomerTypePrice) customerTypePriceList.get(0);
		}
		return customerTypePrice;
	}
}