package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.CustomerTypePriceDAO;
import com.erp.china.demo.dao.DBCustomerTypePriceDAO;
import com.erp.china.demo.model.CustomerTypePrice;

public class CustomerTypePriceService {
	private static Logger logger = Logger.getLogger(CustomerTypePriceService.class);
	private static CustomerTypePriceService instance = null;

	private static CustomerTypePriceDAO customerTypePriceDAO;

	public static synchronized CustomerTypePriceService getInstance() {
		if (instance == null) {
			instance = new CustomerTypePriceService();
		}
		return instance;
	}

	private CustomerTypePriceService() {
		customerTypePriceDAO = new DBCustomerTypePriceDAO();
	}

	@Transactional
	public List<CustomerTypePrice> getCustomerTypePriceList() {
		return customerTypePriceDAO.customerTypePriceList();
	}

	@Transactional
	public CustomerTypePrice loadCustomerTypePrice(String typeId) {
		return customerTypePriceDAO.loadCustomerTypePrice(typeId);
	}

	@Transactional
	public CustomerTypePrice loadCustomerTypePrice(String customerType, String productId) {
		return customerTypePriceDAO.loadCustomerTypePrice(customerType, productId);
	}
}