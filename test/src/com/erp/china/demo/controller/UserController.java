package com.erp.china.demo.controller;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
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
import com.erp.china.demo.model.User;
import com.erp.china.demo.service.UserService;
import com.erp.china.demo.util.Constants;

@Controller
@RequestMapping("/US")
public class UserController {
	private UserService userService;
	private Logger logger;

	public UserController() {
		logger = Logger.getLogger(UserController.class);
		if (userService == null) {
			userService = UserService.getInstance();
		}
	}

	@RequestMapping("/004")
	public String userList(Map<String, Object> map) {
		//map.put("userList", userService.getUserList());
		return "US/US004";
	}

	@RequestMapping(value = "/001")
	public String createUserPage(Map<String, Object> map) {
		map.put("user", new User());
		initUserPage(map);
		return "US/US001";
	}

	public void initUserPage(Map<String, Object> map) {
		Map userLanguage = new java.util.HashMap();
		userLanguage.put(Locale.ENGLISH, Constants.LANGUAGE_EN);
		userLanguage.put(Locale.TRADITIONAL_CHINESE, Constants.LANGUAGE_ZH_HK);
		userLanguage.put(Locale.SIMPLIFIED_CHINESE, Constants.LANGUAGE_ZH_CN);
		map.put("userLanguage", userLanguage);
	}

	@RequestMapping(value = "/save", method = RequestMethod.POST)
	public String saveUser(@ModelAttribute("user") User user) {
		userService.createUser(user);
		return "redirect:/US/004.html";
	}

	@RequestMapping(value = "/002/{userId}")
	public String updateUserPage(@PathVariable("userId") String userId, Map<String, Object> map) {
		User user = userService.loadUser(userId);
		map.put("user", user);
		initUserPage(map);
		return "US/US002";
	}

	@RequestMapping("/003/{userId}")
	public String deleteUser(@PathVariable("userId") String userId) {
		userService.removeUser(userId);
		return "redirect:/US/004.html";
	}

	@RequestMapping(value="/004/userList", method = RequestMethod.GET)
	public @ResponseBody Map jsonUserList() {
		Map resultMap = new HashMap();
		List<Map> userMapList = new ArrayList<Map>();
		List<User> userList = userService.getUserList();
		for (User user : userList) {
			Map<String, String> userMap = new HashMap<String, String>();
			userMap.put("tuid", user.getUserId());
			userMap.put("user_login", user.getUserLogin());
			userMap.put("user_language", user.getUserLanguage());
			userMap.put("user_password", user.getUserPassword());

			userMap.put(Constants.CREATED_DATE, user.getCreatedDate().toString());
			userMap.put(Constants.LAST_MODIFIED_DATE, user.getLastModifiedDate().toString());
			userMapList.add(userMap);
		}
		resultMap.put("success", true);
		resultMap.put("users", userMapList);
		return resultMap;
	}

	@RequestMapping(value="update", method = RequestMethod.POST)
	public String updateUser(@RequestBody Map requestMap) {
		User user = new User();
		user.setUserId(requestMap.get("user_id")!=null?requestMap.get("user_id").toString():"");
		user.setUserLogin(requestMap.get("user_login")!=null?requestMap.get("user_login").toString():"");
		user.setUserLanguage(requestMap.get("user_language")!=null?requestMap.get("user_language").toString():"");
		userService.updateUser(user);
		return "redirect:/US/004.html";
	}
	
	@RequestMapping(value="update", method = RequestMethod.PUT)
	public @ResponseBody Map update(@RequestBody Map requestMap) {
		User entity = new User();
		entity.setUserId(requestMap.get("tuid")!=null?requestMap.get("tuid").toString():"");
		entity.setUserLogin(requestMap.get("user_login")!=null?requestMap.get("user_login").toString():"");
		entity.setUserLanguage(requestMap.get("user_language")!=null?requestMap.get("user_language").toString():"");
		userService.updateUser(entity);
		if(requestMap.get("user_password")!=null&&!requestMap.get("user_password").toString().equals("")){
			entity.setUserPassword(requestMap.get("user_password").toString());
			userService.updatePassword(entity);
		}
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="create", method = RequestMethod.POST)
	public @ResponseBody Map create(@RequestBody Map requestMap) {
		User entity = new User();
		entity.setUserLogin(requestMap.get("user_login")!=null?requestMap.get("user_login").toString():"");
		entity.setUserLanguage(requestMap.get("user_language")!=null?requestMap.get("user_language").toString():"");
		entity.setUserPassword(requestMap.get("user_password")!=null?requestMap.get("user_password").toString():"");
		userService.createUser(entity);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
	
	@RequestMapping(value="delete", method = RequestMethod.DELETE)
	public @ResponseBody Map delete(@RequestBody Map requestMap) {
		String tuid = requestMap.get("tuid").toString();
		userService.removeUser(tuid);
		Map resultMap = new HashMap();
		resultMap.put("success", true);
		return resultMap;
	}
}