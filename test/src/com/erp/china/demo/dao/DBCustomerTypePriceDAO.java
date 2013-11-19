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

import com.erp.china.demo.model.Customer;
import com.erp.china.demo.model.CustomerTypePrice;
import com.erp.china.demo.model.Lookup;
import com.erp.china.demo.model.Product;
import com.erp.china.demo.util.Constants;

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

	public void createCustomerTypePrice(CustomerTypePrice customerTypePrice, Product product, List<Lookup> custTypeList) {
		logger.info("creating new cutomer type price");
		for (Lookup custType : custTypeList) {
			String typeId = keyGen.generateId();
			customerTypePrice.setTypeId(typeId);
			customerTypePrice.setCustomerType(custType);
			customerTypePrice.setProduct(product);
			customerTypePrice.setProductPrice(product.getProductPrice());
			Session session = getCurrentSession(sessionFactory);
			Transaction tx = session.beginTransaction();
			session.save(customerTypePrice);
			tx.commit();
		}
		logger.info("succeed to create new cutomer type price");
	}

	public List<CustomerTypePrice> customerTypePriceList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List customerTypePriceList = session.createQuery("from CustomerTypePrice").list();
		if (customerTypePriceList == null) customerTypePriceList = new ArrayList();
		return customerTypePriceList;
	}

	public List<CustomerTypePrice> customerTypePriceList(Product product) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Query query = session.createQuery("from CustomerTypePrice where product_id = '"+product.getProductId()+"'");
		List customerTypePriceList = query.list();
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

	public void updateCustomerTypePrice(Product product, List<Lookup> custTypeList) {
		logger.info("modifying existing cutomer type price");
		for (Lookup custType : custTypeList) {
			CustomerTypePrice customerTypePrice = loadCustomerTypePrice(custType.getLookupId(), product.getProductId());
			if (customerTypePrice != null) {
				customerTypePrice.setProductPrice(product.getProductPrice());
				Session session = getCurrentSession(sessionFactory);
				Transaction tx = session.beginTransaction();
				session.saveOrUpdate(customerTypePrice);
				tx.commit();
			}
		}
		logger.info("succeed to modify existing cutomer type price");
	}

	public void removeCustomerTypePrice(String productId, List<Lookup> custTypeList) {
		for (Lookup custType : custTypeList) {
			CustomerTypePrice customerTypePrice = loadCustomerTypePrice(custType.getLookupId(), productId);
			if (customerTypePrice != null) {
				Session session = getCurrentSession(sessionFactory);
				Transaction tx = session.beginTransaction();
				sessionFactory.getCurrentSession().delete(customerTypePrice);
				tx.commit();
			}
		}
	}
}