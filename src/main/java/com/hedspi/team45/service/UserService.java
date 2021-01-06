package com.hedspi.team45.service;

import java.sql.SQLException;
import java.text.ParseException;

import org.springframework.http.HttpStatus;

import com.hedspi.team45.entity.User;

public interface UserService {
	boolean checkLogin(User user);									//kiem tra email va password khi login
	HttpStatus addUser(User User) throws SQLException;				//Them nguoi dung (khi dang ki)
	User findByEmail(String email) throws SQLException;				//Tim kiem nguoi dung bang email(khi admin thay mat khau cho nguoi dung)
	User findByEmailAfterLogin(String email) throws SQLException;	
	//void applyNewPassword(User user) throws SQLException;			//Cap nhat mat khau cho nguoi dung va admin gui mail thong bao
	void editUser(User user) throws SQLException, ParseException;	//Thay doi thong tin nguoi dung(nguoi dung)
	User getUser(int id) throws SQLException;						//Lay thong tin nguoi dung (nguoi dung)
	boolean checkEmailExist(String email);
	
}
