package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBSalesDAO;
import com.erp.china.demo.dao.SalesDAO;
import com.erp.china.demo.model.Sales;

public class SalesService {
	private static Logger logger = Logger.getLogger(SalesService.class);
	private static SalesService instance = null;

	private static SalesDAO salesDAO;

	public static synchronized SalesService getInstance() {
		if (instance == null) {
			instance = new SalesService();
		}
		return instance;
	}

	private SalesService() {
		salesDAO = new DBSalesDAO();
	}

	@Transactional
    public void createSales(Sales sales) {
        salesDAO.createSales(sales);
    }

	@Transactional
    public List<Sales> getSalesList() {
        return salesDAO.salesList();
    }

	@Transactional
    public Sales loadSales(String salesId) {
        return salesDAO.loadSales(salesId);
    }

	@Transactional
    public void updateSales(Sales sales) {
        salesDAO.updateSales(sales);
    }

	@Transactional
	public void removeSales(String salesId) {
		salesDAO.removeSales(salesId);
	}
}