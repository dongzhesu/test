package com.erp.china.demo.dao;
 
import java.util.List;
import com.erp.china.demo.model.User;

public interface UserDAO {
	public void createUser(User user);
	public List<User> userList();
	public User loadUser(String userId);
	public void updateUser(User user);
	public void updatePassword(User user);
	public void removeUser(String userId);
	public User fetchUserLogin(String username, String password);
}