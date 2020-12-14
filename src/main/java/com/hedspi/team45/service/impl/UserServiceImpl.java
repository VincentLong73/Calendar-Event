package com.hedspi.team45.service.impl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.hedspi.team45.entity.User;
import com.hedspi.team45.repository.UserRepository;
import com.hedspi.team45.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;

	@Override
	public User getUserById(int id) {
		
		return userRepository.getOne(id);
	}

	@Override
	public List<User> getListCustomer() {
		
		return userRepository.findByRoleId(1);
	}

	@Override
	public void addUser(User user) {
		userRepository.save(user);
		
	}

	@Override
	public boolean checkLogin(String email, String password) {
		User user = userRepository.findByEmail(email);
		if(user!=null) {
			if(user.getPassword().equals(password)) {
				return true;
			}
		}
		return false;
	}

	@Override
	public User getUserByEmail(String email) {
		
		return userRepository.findByEmail(email);
	}

}
