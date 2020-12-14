package com.hedspi.team45.service;

import java.util.List;

import com.hedspi.team45.entity.User;

public interface UserService {
	
	void addUser(User user);
	boolean checkLogin(String email, String password);
	User getUserById(int id);
	User getUserByEmail(String email);
	List<User> getListCustomer();
}
