package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Customer;

public interface CustomerDAO {
	public void createCustomer(Customer customer);
	public List<Customer> customerList();
	public Customer loadCustomer(String customerId);
	public void updateCustomer(Customer customer);
	public void removeCustomer(String customerId);
}