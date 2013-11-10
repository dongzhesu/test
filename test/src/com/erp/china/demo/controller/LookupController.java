package com.erp.china.demo.controller;

import java.util.Map;

import org.apache.log4j.Logger;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.erp.china.demo.model.Lookup;
import com.erp.china.demo.service.LookupService;

@Controller
@RequestMapping("/LU")
public class LookupController {
	private LookupService lookupService;
	private Logger logger;

	public LookupController() {
		logger = Logger.getLogger(LookupController.class);
		if (lookupService == null) {
			lookupService = LookupService.getInstance();
		}
	}

	@RequestMapping("/004")
	public String lookupList(Map<String, Object> map) {
		map.put("lookupList", lookupService.getLookupList());
		return "LU/LU004";
	}

	@RequestMapping(value = "/001")
	public String createLookupPage(Map<String, Object> map) {
		map.put("lookup", new Lookup());
		//initLookupPage(map);
		return "LU/LU001";
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveLookup(@ModelAttribute("lookup") Lookup lookup) {
		lookupService.createLookup(lookup);
		return "redirect:/LU/004.html";
	}

	@RequestMapping(value = "/002/{lookupId}")
	public String updateLookupPage(@PathVariable("lookupId") String lookupId, Map<String, Object> map) {
		Lookup lookup = lookupService.loadLookup(lookupId);
		map.put("lookup", lookup);
		return "LU/LU002";
	}

	@RequestMapping(value = "/update", method = RequestMethod.POST)
	public String updateLookup(@ModelAttribute("lookup") Lookup lookup) {
		lookupService.updateLookup(lookup);
		return "redirect:/LU/004.html";
	}

	@RequestMapping("/003/{lookupId}")
	public String deleteLookup(@PathVariable("lookupId") String lookupId) {
		lookupService.removeLookup(lookupId);
		return "redirect:/LU/004.html";
	}
}