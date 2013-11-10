package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBCustomerDAO;
import com.erp.china.demo.dao.CustomerDAO;
import com.erp.china.demo.model.Customer;

public class CustomerService {
	private static Logger logger = Logger.getLogger(CustomerService.class);
	private static CustomerService instance = null;

	private static CustomerDAO customerDAO;

	public static synchronized CustomerService getInstance() {
		if (instance == null) {
			instance = new CustomerService();
		}
		return instance;
	}

	private CustomerService() {
		customerDAO = new DBCustomerDAO();
	}

	@Transactional
    public void createCustomer(Customer customer) {
        customerDAO.createCustomer(customer);
    }

	@Transactional
    public List<Customer> getCustomerList() {
        return customerDAO.customerList();
    }

	@Transactional
    public Customer loadCustomer(String customerId) {
        return customerDAO.loadCustomer(customerId);
    }

	@Transactional
    public void updateCustomer(Customer customer) {
		customerDAO.updateCustomer(customer);
    }

	@Transactional
	public void removeCustomer(String customerId) {
		customerDAO.removeCustomer(customerId);
	}
}