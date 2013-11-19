package com.erp.china.demo.dao;
 
import java.util.List;

import com.erp.china.demo.model.CustomerTypePrice;
import com.erp.china.demo.model.Lookup;
import com.erp.china.demo.model.Product;

public interface CustomerTypePriceDAO {
	public void createCustomerTypePrice(CustomerTypePrice customerTypePrice, Product product, List<Lookup> custTypeList);
	public List<CustomerTypePrice> customerTypePriceList();
	public List<CustomerTypePrice> customerTypePriceList(Product product);
	public CustomerTypePrice loadCustomerTypePrice(String typeId);
	public CustomerTypePrice loadCustomerTypePrice(String customerType, String productId);
	public void updateCustomerTypePrice(Product product, List<Lookup> custTypeList);
	public void removeCustomerTypePrice(String productId, List<Lookup> custTypeList);
}