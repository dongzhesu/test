package com.erp.china.demo.dao;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.SessionFactory;
import org.hibernate.Session;

import com.erp.china.demo.util.KeyGenerator;

public abstract class AbstractDAO {
	/** Logging the system for trace and debugging. **/
	protected Logger logger;

	/** Key generator to random generate a key id. **/
	protected KeyGenerator keyGen = new KeyGenerator();

	/** Get current available hibernate session. **/
	protected Session getCurrentSession(SessionFactory sessionFactory) {
		Session session = null;
		try {
			session = sessionFactory.getCurrentSession();
		} catch(HibernateException he) {
			he.printStackTrace();
		}
		if(session == null && !((SessionFactory) session).isClosed()) session = sessionFactory.openSession();
		return session;
	}
}