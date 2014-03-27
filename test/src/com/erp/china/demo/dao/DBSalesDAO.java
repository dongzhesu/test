package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.Sales;

public class DBSalesDAO extends AbstractDAO implements SalesDAO {
	private static SessionFactory sessionFactory;

	public DBSalesDAO() {
		logger = Logger.getLogger(DBSalesDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public void createSales(Sales sales) {
		logger.info("creating new Sales");
		String salesId = keyGen.generateId();
		sales.setSalesId(salesId);
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(sales);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to create new Sales");
	}

	public List<Sales> salesList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List salesList = session.createQuery("from Sales").list();
		if (salesList == null) salesList = new ArrayList();
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return salesList;
	}

	public Sales loadSales(String salesId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Sales sales = (Sales) session.load(Sales.class, salesId);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return sales;
	}

	public void updateSales(Sales sales) {
		logger.info("modifying existing Sales");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Sales existingSales = (Sales) session.load(Sales.class, sales.getSalesId());
		existingSales.setSalesName(sales.getSalesName());
		existingSales.setSalesEmail(sales.getSalesEmail());
		existingSales.setSalesPhone(sales.getSalesPhone());
		existingSales.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingSales);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to modify existing Sales");
	}

	public void removeSales(String salesId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Sales sales = (Sales) session.load(Sales.class, salesId);
		if (sales != null) {
			sessionFactory.getCurrentSession().delete(sales);
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