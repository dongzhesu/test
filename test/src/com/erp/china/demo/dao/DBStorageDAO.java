package com.erp.china.demo.dao;

import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import org.hibernate.cfg.Configuration;

import com.erp.china.demo.model.Storage;

public class DBStorageDAO extends AbstractDAO implements StorageDAO {
	private static SessionFactory sessionFactory;

	public DBStorageDAO() {
		logger = Logger.getLogger(DBStorageDAO.class);
		sessionFactory = configureSessionFactory();
	}

	private static SessionFactory configureSessionFactory() throws HibernateException {
		sessionFactory = new Configuration().configure().buildSessionFactory();
	    return sessionFactory;
	}

	public void createStorage(Storage storage) {
		logger.info("creating new Storage");
		String storageId = keyGen.generateId();
		storage.setStorageId(storageId);
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		session.save(storage);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to create new Storage");
	}

	public List<Storage> storageList() {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		List storageList = session.createQuery("from Storage").list();
		if (storageList == null) storageList = new ArrayList();
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return storageList;
	}

	public Storage loadStorage(String storageId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Storage storage = (Storage) session.load(Storage.class, storageId);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		return storage;
	}

	public void updateStorage(Storage storage) {
		logger.info("modifying existing Storage");
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Storage existingStorage = (Storage) session.load(Storage.class, storage.getStorageId());
		existingStorage.setStorageName(storage.getStorageName());
		existingStorage.setStorageDesc(storage.getStorageDesc());
		existingStorage.setLastModifiedDate(new java.util.Date());
		session.saveOrUpdate(existingStorage);
		try{
			tx.commit();
			tx=null;
		}finally{
			  if(tx != null)
			  {
			    tx.rollback();
			  }
		}
		logger.info("succeed to modify existing Storage");
	}

	public void removeStorage(String storageId) {
		Session session = getCurrentSession(sessionFactory);
		Transaction tx = session.beginTransaction();
		Storage storage = (Storage) session.load(Storage.class, storageId);
		if (storage != null) {
			sessionFactory.getCurrentSession().delete(storage);
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