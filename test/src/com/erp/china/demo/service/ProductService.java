package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBProductDAO;
import com.erp.china.demo.dao.ProductDAO;
import com.erp.china.demo.model.Product;

public class ProductService {
	private static Logger logger = Logger.getLogger(ProductService.class);
	private static ProductService instance = null;

	private static ProductDAO productDAO;

	public static synchronized ProductService getInstance() {
		if (instance == null) {
			instance = new ProductService();
		}
		return instance;
	}

	private ProductService() {
		productDAO = new DBProductDAO();
	}

	@Transactional
    public void createProduct(Product product) {
        productDAO.createProduct(product);
    }

	@Transactional
    public List<Product> getProductList() {
        return productDAO.productList();
    }

	@Transactional
    public Product loadProduct(String productId) {
        return productDAO.loadProduct(productId);
    }

	@Transactional
    public void updateProduct(Product product) {
        productDAO.updateProduct(product);
    }

	@Transactional
	public void removeProduct(String productId) {
		productDAO.removeProduct(productId);
	}
}