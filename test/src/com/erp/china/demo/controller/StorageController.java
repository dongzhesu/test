package com.erp.china.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import com.erp.china.demo.model.Product;
import com.erp.china.demo.model.Sales;
import com.erp.china.demo.model.Storage;
import com.erp.china.demo.service.StorageService;
import com.erp.china.demo.util.Constants;

@Controller
@RequestMapping("/SR")
public class StorageController {
	private StorageService storageService;
	private Logger logger;

	public StorageController() {
		logger = Logger.getLogger(StorageController.class);
		if (storageService == null) {
			storageService = StorageService.getInstance();
		}
	}

	@RequestMapping("/004")
	public String storageList(Map<String, Object> map) {
		map.put("storageList", storageService.getStorageList());
		return "SR/SR004";
	}

	@RequestMapping(value = "/001")
	public String createStoragePage(Map<String, Object> map) {
		map.put("storage", new Storage());
		return "SR/SR001";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveStorage(@ModelAttribute("storage") Storage storage) {
		storageService.createStorage(storage);
		return "redirect:/SR/004.html";
	}

	@RequestMapping(value = "/002/{storageId}")
	public String updateStoragePage(@PathVariable("storageId") String storageId, Map<String, Object> map) {
		Storage storage = storageService.loadStorage(storageId);
		map.put("storage", storage);
		return "SR/SR002";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateStorage(@ModelAttribute("storage") Storage storage) {
		storageService.updateStorage(storage);
		return "redirect:/SR/004.html";
	}

	@RequestMapping("/003/{storageId}")
	public String deleteStorage(@PathVariable("storageId") String storageId) {
		storageService.removeStorage(storageId);
		return "redirect:/SR/004.html";
	}
	
	@RequestMapping(value="/004/storageList", method = RequestMethod.GET)
	public @ResponseBody Map jsonSalesList() {
		Map resultMap = new HashMap();
		List<Map> entityMapList = new ArrayList<Map>();
		List<Storage> entityList = storageService.getStorageList();
		for (Storage entity : entityList) {
			Map<String, String> entityMap = new HashMap<String, String>();
			
			entityMap.put("storage_id", entity.getStorageId());
			entityMap.put("storage_name", entity.getStorageName());
			
			entityMap.put(Constants.CREATED_DATE, entity.getCreatedDate().toString());
			entityMap.put(Constants.LAST_MODIFIED_DATE, entity.getLastModifiedDate().toString());
			entityMapList.add(entityMap);
		}
		resultMap.put("success", true);
		resultMap.put("stats", entityMapList);
		return resultMap;
	}
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody Map update(@RequestBody Map requestMap) {
		Storage entity = new Storage();
		entity.setStorageId(requestMap.get("storage_id")!=null?requestMap.get("storage_id").toString():"");
		entity.setStorageName(requestMap.get("storage_name")!=null?requestMap.get("storage_name").toString():"");
		
		storageService.updateStorage(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Map create(@RequestBody Map requestMap) {
		Storage entity = new Storage();
		entity.setStorageName(requestMap.get("storage_name")!=null?requestMap.get("storage_name").toString():"");
		
		storageService.createStorage(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public @ResponseBody Map delete(@RequestBody Map requestMap) {
		String id = requestMap.get("storage_id").toString();
		storageService.removeStorage(id);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
}