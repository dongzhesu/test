package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.Product;

public interface ProductDAO {
	public void createProduct(Product product);
	public List<Product> productList();
	public Product loadProduct(String productId);
	public void updateProduct(Product product);
	public void removeProduct(String productId);
}