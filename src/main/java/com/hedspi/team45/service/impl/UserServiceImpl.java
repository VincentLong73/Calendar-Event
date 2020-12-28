package com.hedspi.team45.service.impl;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;

import com.hedspi.team45.entity.User;
import com.hedspi.team45.repository.UserRepository;
import com.hedspi.team45.service.UserService;

@Service
public class UserServiceImpl implements UserService{
	
	@Autowired
	private UserRepository userRepository;
	@Override
	public boolean checkLogin(User user) {
		User userfind = userRepository.findByEmail(user.getEmail());
		if (userfind == null) {
			return false;
		} else {
			if (user.getPassword().equals(userfind.getPassword()) && userfind.isActive()) {
				return true;
			} else {
				return false;
			}
		}
	}

	@Override
	public HttpStatus addUser(User userModel) throws SQLException {
		HttpStatus status = null;
		try {
			User userCheck = userRepository.findByEmail(userModel.getEmail());
			if(userCheck==null) {
				User user = new User();
				user.setActive(true);
				user.setEmail(userModel.getEmail());
				user.setPassword(userModel.getPassword());
				user.setPhone(userModel.getPhone());
				user.setUserName(userModel.getUserName());
				
				userRepository.saveAndFlush(user);
				status = HttpStatus.OK;
			}else {
				status=HttpStatus.CREATED;
			}
		}catch (Exception e) {
			status = HttpStatus.INTERNAL_SERVER_ERROR;
			System.out.println(e);
		}
			return status;
	}



	@Override
	public void editUser(User userModel) throws ParseException {

		User user = userRepository.findByEmail(userModel.getEmail());
		user.setEmail(userModel.getEmail());
		user.setPassword(userModel.getPassword());
		user.setPhone(userModel.getPhone());
		user.setUserName(userModel.getUserName());

		userRepository.save(user);

	}

	@Override
	public User getUser(int id) {
		return userRepository.getOne(id);
	}

	@Override
	public User findByEmail(String email) {
		return userRepository.findByEmail(email);
	}



	@Override
	public User findByEmailAfterLogin(String email) throws SQLException {
		User userTmp = userRepository.findByEmail(email);
		User userReturn = new User();
		userReturn.setId(userTmp.getId());
		userReturn.setEmail(userTmp.getEmail());
		userReturn.setPhone(userTmp.getPhone());
		userReturn.setUserName(userTmp.getUserName());
		userReturn.setActive(userTmp.isActive());
		return userReturn;
	}

}
