package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;
import org.hibernate.criterion.Expression;

import com.erp.china.demo.model.User;
import com.erp.china.demo.model.System;
import com.erp.china.demo.util.Constants;

public class DBUserDAO extends AbstractDAO implements UserDAO {
	private static SessionFactory sessionFactory;

	public DBUserDAO() {
		logger = Logger.getLogger(DBUserDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public void createUser(User user) {
		logger.info("creating new User");
		SystemDAO systemDAO = new DBSystemDAO();
		System system = systemDAO.loadSystem(Constants.DEFAULT_SYSTEM_ID);
		String userId = keyGen.generateId();
		user.setUserId(userId);
		user.setSystem(system);
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(user);
		tx.commit();
		logger.info("succeed to create new User");
	}

	public List<User> userList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List userList = session.createQuery("from User").list();
		if (userList == null) userList = new ArrayList();
		return userList;
	}

	public User loadUser(String userId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, userId);
		return user;
	}

	public void updateUser(User user) {
		logger.info("modifying existing User");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		User existingUser = (User) session.load(User.class, user.getUserId());
		existingUser.setUserLogin(user.getUserLogin());
		existingUser.setUserLanguage(user.getUserLanguage());
		existingUser.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingUser);
		tx.commit();
		logger.info("succeed to modify existing User");
	}

	public void updatePassword(User user) {
		logger.info("modifying User's password with user ID: "+user.getUserId());
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		User existingUser = (User) session.load(User.class, user.getUserId());
		existingUser.setUserPassword(user.getUserPassword());
		session.saveOrUpdate(existingUser);
		tx.commit();
		logger.info("succeed to modify User's password with user ID: "+user.getUserId());
	}

	public void removeUser(String userId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		User user = (User) session.load(User.class, userId);
		if (user != null) {
			sessionFactory.getCurrentSession().delete(user);
		}
		tx.commit();
	}

	public User fetchUserLogin(String username, String password) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Criteria criteria = session.createCriteria(User.class);
		if (username!=null) {
			criteria.add(Expression.eq("userLogin", username));
		}
		if (password!=null) {
			criteria.add(Expression.eq("userPassword", password));
		}
		List<User> userList = criteria.list();
		if (userList == null) return null;
		if (userList.size() == 0) return null;
		User user = userList.get(0);
		return user;
	}
}