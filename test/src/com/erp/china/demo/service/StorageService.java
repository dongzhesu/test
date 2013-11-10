package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBStorageDAO;
import com.erp.china.demo.dao.StorageDAO;
import com.erp.china.demo.model.Storage;

public class StorageService {
	private static Logger logger = Logger.getLogger(StorageService.class);
	private static StorageService instance = null;

	private static StorageDAO storageDAO;

	public static synchronized StorageService getInstance() {
		if (instance == null) {
			instance = new StorageService();
		}
		return instance;
	}

	private StorageService() {
		storageDAO = new DBStorageDAO();
	}

	@Transactional
    public void createStorage(Storage storage) {
        storageDAO.createStorage(storage);
    }

	@Transactional
    public List<Storage> getStorageList() {
        return storageDAO.storageList();
    }

	@Transactional
    public Storage loadStorage(String storageId) {
        return storageDAO.loadStorage(storageId);
    }

	@Transactional
    public void updateStorage(Storage storage) {
        storageDAO.updateStorage(storage);
    }

	@Transactional
	public void removeStorage(String storageId) {
		storageDAO.removeStorage(storageId);
	}
}