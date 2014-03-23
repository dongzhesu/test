package com.erp.china.demo.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.System;

public class DBSystemDAO extends AbstractDAO implements SystemDAO {
	private static SessionFactory sessionFactory;

	public DBSystemDAO() {
		logger = Logger.getLogger(DBSystemDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public System loadSystem(String systemId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		System system = (System) session.load(System.class, systemId);
		tx.commit();
		return system;
	}

	public void updateSystem(System system) {
		logger.info("modifying current existing System");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		System existingSystem = (System) session.load(System.class, system.getSystemId());
		existingSystem.setSystemVersion(system.getSystemVersion());
		existingSystem.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingSystem);
		tx.commit();
		logger.info("succeed to modify current existing Sales");
	}
}