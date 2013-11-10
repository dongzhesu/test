package com.erp.china.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

import javax.servlet.http.HttpServletRequest;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.china.demo.model.Order;
import com.erp.china.demo.model.Product;
import com.erp.china.demo.model.User;
import com.erp.china.demo.service.ProductService;
import com.erp.china.demo.util.Constants;

@Controller
@RequestMapping("/PD")
public class ProductController {
	private static ProductService productService;
	private Logger logger;
	private static Map<String, String> productMap;

	public ProductController() {
		logger = Logger.getLogger(ProductController.class);
		productMap = new ConcurrentHashMap();
		if (productService == null) {
			productService = ProductService.getInstance();
		}
		initProductMap();
	}

	public static void initProductMap() {
		List<Product> productList = productService.getProductList();
		for (Product product : productList) {
			productMap.put(product.getProductId(), product.getProductName());
		}
		productMap.put(Constants.SELECTED, productList.get(0).getProductId());
	}

	public static Map<String, String> getProductMap(Product product) {
		productMap.put(Constants.SELECTED, product.getProductId());
		return productMap;
	}

	public static Map<String, String> getProductMap() {
		return productMap;
	}

	@RequestMapping("/004")
	public String productList(Map<String, Object> map) {
		map.put("productList", productService.getProductList());
		return "PD/PD004";
	}

	@RequestMapping(value = "/001")
	public String createProductPage(Map<String, Object> map) {
		map.put("product", new Product());
		return "PD/PD001";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveProduct(@ModelAttribute("product") Product product) {
		productService.createProduct(product);
		return "redirect:/PD/004.html";
	}

	@RequestMapping(value = "/002/{productId}")
	public String updateProductPage(@PathVariable("productId") String productId, Map<String, Object> map) {
		Product product = productService.loadProduct(productId);
		map.put("product", product);
		return "PD/PD002";
	}

//	@RequestMapping(value = "/update", method = RequestMethod.POST)
//	public String updateProduct(@ModelAttribute("product") Product product) {
//		productService.updateProduct(product);
//		return "redirect:/PD/004.html";
//	}

	@RequestMapping("/003/{productId}")
	public String deleteProduct(@PathVariable("productId") String productId) {
		productService.removeProduct(productId);
		return "redirect:/PD/004.html";
	}

	@RequestMapping(value="/004/productList", method = RequestMethod.GET)
	public @ResponseBody Map jsonProductList() {
		Map resultMap = new HashMap();
		List<Map> productMapList = new ArrayList<Map>();
		List<Product> productList = productService.getProductList();
		for (Product product : productList) {
			Map<String, String> entityMap = new HashMap<String, String>();
			logger.debug("name:"+product.getProductName()+"; year: "+product.getProductYear());
			entityMap.put("product_id", product.getProductId());
			entityMap.put("product_name", product.getProductName());
			entityMap.put("product_year", product.getProductYear());
			entityMap.put("product_price", Double.toString(product.getProductPrice()));
			entityMap.put("product_price2", Double.toString(product.getProductPrice2()));
			entityMap.put("product_qty", Integer.toString(product.getProductQty()));
			
			entityMap.put(Constants.CREATED_DATE, product.getCreatedDate().toString());
			entityMap.put(Constants.LAST_MODIFIED_DATE, product.getLastModifiedDate().toString());
			productMapList.add(entityMap);
		}
		resultMap.put("success", true);
		resultMap.put("stats", productMapList);
		return resultMap;
	}

	@RequestMapping(value="/004/setProduct", method = RequestMethod.POST)
	public @ResponseBody Map setProductMap(HttpServletRequest request) {
		productMap.put(Constants.SELECTED, request.getParameter("value"));
		return productMap;
	}
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody Map update(@RequestBody Map requestMap) {
		Product entity = new Product();
		entity.setProductId(requestMap.get("product_id")!=null?requestMap.get("product_id").toString():"");
		entity.setProductName(requestMap.get("product_name")!=null?requestMap.get("product_name").toString():"");
		entity.setProductYear(requestMap.get("product_year")!=null?requestMap.get("product_year").toString():"");
		entity.setProductPrice(requestMap.get("product_price")!=null?Double.parseDouble(requestMap.get("product_price").toString()):0);
		entity.setProductPrice2(requestMap.get("product_price2")!=null?Double.parseDouble(requestMap.get("product_price2").toString()):0);
		entity.setProductQty(requestMap.get("product_qty")!=null?Integer.parseInt(requestMap.get("product_qty").toString()):0);
		productService.updateProduct(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Map create(@RequestBody Map requestMap) {
		Product entity = new Product();
		entity.setProductName(requestMap.get("product_name")!=null?requestMap.get("product_name").toString():"");
		entity.setProductYear(requestMap.get("product_year")!=null?requestMap.get("product_year").toString():"");
		entity.setProductPrice(requestMap.get("product_price")!=null?Double.parseDouble(requestMap.get("product_price").toString()):0);
		entity.setProductPrice2(requestMap.get("product_price2")!=null?Double.parseDouble(requestMap.get("product_price2").toString()):0);
		entity.setProductQty(requestMap.get("product_qty")!=null?Integer.parseInt(requestMap.get("product_qty").toString()):0);
		productService.createProduct(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public @ResponseBody Map delete(@RequestBody Map requestMap) {
		Product entity = new Product();
		String productId = requestMap.get("product_id").toString();
		productService.removeProduct(productId);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
}