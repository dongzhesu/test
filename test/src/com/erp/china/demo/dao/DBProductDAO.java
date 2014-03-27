package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.Product;

public class DBProductDAO extends AbstractDAO implements ProductDAO {
	private static SessionFactory sessionFactory;

	public DBProductDAO() {
		logger = Logger.getLogger(DBProductDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public void createProduct(Product product) {
		logger.info("creating new Product");
		String productId = keyGen.generateId();
		product.setProductId(productId);
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(product);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to create new Product");
	}

	public List<Product> productList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List productList = session.createQuery("from Product").list();
		if (productList == null) productList = new ArrayList();
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return productList;
	}

	public Product loadProduct(String productId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Product product = (Product) session.load(Product.class, productId);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return product;
	}

	public void updateProduct(Product product) {
		logger.info("modifying existing Product");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Product existingProduct = (Product) session.load(Product.class, product.getProductId());
		existingProduct.setProductType(product.getProductType());
		existingProduct.setProductCode(product.getProductCode());
		existingProduct.setProductName(product.getProductName());
		existingProduct.setProductDesc(product.getProductDesc());
		existingProduct.setProductYear(product.getProductYear());
		existingProduct.setProductCountry(product.getProductCountry());
		existingProduct.setProductPrice(product.getProductPrice());
		existingProduct.setProductPrice2(product.getProductPrice2());
		existingProduct.setProductQty(product.getProductQty());
		existingProduct.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingProduct);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to modify existing Product");
	}

	public void removeProduct(String productId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Product product = (Product) session.load(Product.class, productId);
		if (product != null) {
			sessionFactory.getCurrentSession().delete(product);
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