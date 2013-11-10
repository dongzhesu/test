package com.erp.china.demo.dao;
 
import java.util.List;

import com.erp.china.demo.model.CustomerTypePrice;

public interface CustomerTypePriceDAO {
	public List<CustomerTypePrice> customerTypePriceList();
	public CustomerTypePrice loadCustomerTypePrice(String typeId);
	public CustomerTypePrice loadCustomerTypePrice(String customerType, String productId);
}