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
import com.erp.china.demo.model.Sales;
import com.erp.china.demo.service.SalesService;
import com.erp.china.demo.util.Constants;

@Controller
@RequestMapping("/SL")
public class SalesController {
	private static SalesService salesService;
	private Logger logger;
	private static Map<String, String> salesMap;

	public SalesController() {
		logger = Logger.getLogger(SalesController.class);
		salesMap = new ConcurrentHashMap();
		if (salesService == null) {
			salesService = SalesService.getInstance();
		}
		initSalesMap();
	}

	public static void initSalesMap() {
		List<Sales> salesList = salesService.getSalesList();
		for (Sales sales : salesList) {
			salesMap.put(sales.getSalesId(), sales.getSalesName());
		}
		salesMap.put(Constants.SELECTED, salesList.get(0).getSalesId());
	}

	public static Map<String, String> getSalesMap(Sales sales) {
		salesMap.put(Constants.SELECTED, sales.getSalesId());
		return salesMap;
	}

	public static Map<String, String> getSalesMap() {
		return salesMap;
	}

	@RequestMapping("/004")
	public String salesList(Map<String, Object> map) {
		map.put("salesList", salesService.getSalesList());
		return "SL/SL004";
	}

	@RequestMapping(value = "/001")
	public String createSalesPage(Map<String, Object> map) {
		map.put("sales", new Sales());
		return "SL/SL001";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveSales(@ModelAttribute("sales") Sales sales) {
		salesService.createSales(sales);
		return "redirect:/SL/004.html";
	}

	@RequestMapping(value = "/002/{salesId}")
	public String updateSalesPage(@PathVariable("salesId") String salesId, Map<String, Object> map) {
		Sales sales = salesService.loadSales(salesId);
		map.put("sales", sales);
		return "SL/SL002";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateSales(@ModelAttribute("sales") Sales sales) {
		salesService.updateSales(sales);
		return "redirect:/SL/004.html";
	}

	@RequestMapping("/003/{salesId}")
	public String deleteSales(@PathVariable("salesId") String salesId) {
		salesService.removeSales(salesId);
		return "redirect:/SL/004.html";
	}

	@RequestMapping(value="/004/salesList", method = RequestMethod.GET)
	public @ResponseBody Map jsonSalesList() {
		Map resultMap = new HashMap();
		List<Map> entityMapList = new ArrayList<Map>();
		List<Sales> entityList = salesService.getSalesList();
		for (Sales entity : entityList) {
			Map<String, String> entityMap = new HashMap<String, String>();
			
			entityMap.put("sales_id", entity.getSalesId());
			entityMap.put("sales_name", entity.getSalesName());
			entityMap.put("sales_email", entity.getSalesEmail());
			entityMap.put("sales_phone", entity.getSalesPhone());

			entityMap.put(Constants.CREATED_DATE, entity.getCreatedDate().toString());
			entityMap.put(Constants.LAST_MODIFIED_DATE, entity.getLastModifiedDate().toString());
			entityMapList.add(entityMap);
		}
		resultMap.put("success", true);
		resultMap.put("stats", entityMapList);
		return resultMap;
	}

	@RequestMapping(value="/004/setSales", method = RequestMethod.POST)
	public @ResponseBody Map setSalesMap(HttpServletRequest request) {
		salesMap.put(Constants.SELECTED, request.getParameter("value"));
		return salesMap;
	}
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody Map update(@RequestBody Map requestMap) {
		Sales entity = new Sales();
		entity.setSalesId(requestMap.get("sales_id")!=null?requestMap.get("sales_id").toString():"");
		entity.setSalesName(requestMap.get("sales_name")!=null?requestMap.get("sales_name").toString():"");
		entity.setSalesEmail(requestMap.get("sales_email")!=null?requestMap.get("sales_email").toString():"");
		entity.setSalesPhone(requestMap.get("sales_phone")!=null?requestMap.get("sales_phone").toString():"");
		salesService.updateSales(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Map create(@RequestBody Map requestMap) {
		Sales entity = new Sales();
		entity.setSalesName(requestMap.get("sales_name")!=null?requestMap.get("sales_name").toString():"");
		entity.setSalesEmail(requestMap.get("sales_email")!=null?requestMap.get("sales_email").toString():"");
		entity.setSalesPhone(requestMap.get("sales_phone")!=null?requestMap.get("sales_phone").toString():"");
		
		salesService.createSales(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public @ResponseBody Map delete(@RequestBody Map requestMap) {
		
		String salesId = requestMap.get("sales_id").toString();
		salesService.removeSales(salesId);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
}