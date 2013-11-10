package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBLookupDAO;
import com.erp.china.demo.dao.LookupDAO;
import com.erp.china.demo.model.Lookup;

public class LookupService {
	private static Logger logger = Logger.getLogger(LookupService.class);
	private static LookupService instance = null;

	private static LookupDAO lookupDAO;

	public static synchronized LookupService getInstance() {
		if (instance == null) {
			instance = new LookupService();
		}
		return instance;
	}

	private LookupService() {
		lookupDAO = new DBLookupDAO();
	}

	@Transactional
    public void createLookup(Lookup lookup) {
        lookupDAO.createLookup(lookup);
    }

	@Transactional
    public List<Lookup> getLookupList() {
        return lookupDAO.lookupList();
    }

	@Transactional
    public List<Lookup> getLookupList(String criteria) {
        return lookupDAO.lookupList(criteria);
    }

	@Transactional
    public Lookup loadLookup(String lookupId) {
        return lookupDAO.loadLookup(lookupId);
    }

	@Transactional
    public void updateLookup(Lookup lookup) {
        lookupDAO.updateLookup(lookup);
    }

	@Transactional
	public void removeLookup(String lookupId) {
		lookupDAO.removeLookup(lookupId);
	}
}