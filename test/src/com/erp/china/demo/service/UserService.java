package com.erp.china.demo.service;

import java.util.List;

import org.apache.log4j.Logger;
import org.springframework.transaction.annotation.Transactional;

import com.erp.china.demo.dao.DBUserDAO;
import com.erp.china.demo.dao.UserDAO;
import com.erp.china.demo.model.User;

public class UserService {
	private static Logger logger = Logger.getLogger(UserService.class);
	private static UserService instance = null;

	private static UserDAO userDAO;

	public static synchronized UserService getInstance() {
		if (instance == null) {
			instance = new UserService();
		}
		return instance;
	}

	private UserService() {
		userDAO = new DBUserDAO();
	}

	@Transactional
    public void createUser(User user) {
        userDAO.createUser(user);
    }

	@Transactional
    public List<User> getUserList() {
        return userDAO.userList();
    }

	@Transactional
    public User loadUser(String userId) {
        return userDAO.loadUser(userId);
    }

	@Transactional
    public void updateUser(User user) {
        userDAO.updateUser(user);
    }

	@Transactional
    public void updatePassword(User user) {
        userDAO.updatePassword(user);
    }

	@Transactional
	public void removeUser(String userId) {
		userDAO.removeUser(userId);
	}

	@Transactional
    public User fetchUserLogin(String username, String password) {
        return userDAO.fetchUserLogin(username, password);
    }
}