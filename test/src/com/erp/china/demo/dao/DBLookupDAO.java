package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Example;
import org.hibernate.criterion.Restrictions;

import com.erp.china.demo.model.Lookup;
import com.erp.china.demo.model.System;
import com.erp.china.demo.util.Constants;

public class DBLookupDAO extends AbstractDAO implements LookupDAO {
	private static SessionFactory sessionFactory;

	public DBLookupDAO() {
		logger = Logger.getLogger(DBLookupDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public void createLookup(Lookup lookup) {
		logger.info("creating new Lookup");
		SystemDAO systemDAO = new DBSystemDAO();
		System system = systemDAO.loadSystem(Constants.DEFAULT_SYSTEM_ID);
		String lookupId = keyGen.generateId();
		lookup.setLookupId(lookupId);
		lookup.setSystem(system);
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(lookup);
		tx.commit();
		logger.info("succeed to create new Lookup");
	}

	public List<Lookup> lookupList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List lookupList = session.createQuery("from Lookup").list();
		if (lookupList == null) lookupList = new ArrayList();
		tx.commit();
		return lookupList;
	}

	public List<Lookup> lookupList(String criteria) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List lookupList = session.createCriteria(Lookup.class).add(Restrictions.like("lookupKey", criteria+"%")).list();
		if (lookupList == null) lookupList = new ArrayList();
		tx.commit();
		return lookupList;
	}

	public Lookup loadLookup(String lookupId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Lookup lookup = (Lookup) session.load(Lookup.class, lookupId);
		tx.commit();
		return lookup;
	}

	public void updateLookup(Lookup lookup) {
		logger.info("modifying existing Lookup");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Lookup existingLookup = (Lookup) session.load(Lookup.class, lookup.getLookupId());
		existingLookup.setLookupValue(lookup.getLookupValue());
		session.saveOrUpdate(existingLookup);
		tx.commit();
		logger.info("succeed to modify existing Lookup");
	}

	public void removeLookup(String lookupId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Lookup lookup = (Lookup) session.load(Lookup.class, lookupId);
		if (lookup != null) {
			sessionFactory.getCurrentSession().delete(lookup);
		}
		tx.commit();
	}
}