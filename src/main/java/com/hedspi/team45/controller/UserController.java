package com.hedspi.team45.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

import com.hedspi.team45.entity.User;
import com.hedspi.team45.service.UserService;

@RestController
public class UserController {
	
	@Autowired
	private UserService userService;

	@PostMapping("/api/user/login")
    ResponseEntity<String> registerUser(@RequestBody User user) {
		String result = "";
	    HttpStatus httpStatus = null;
	    try {
	      if (userService.checkLogin(user.getEmail(),user.getPassword())) {
	        //result = jwtService.generateTokenLogin( user.getUserName());
	        httpStatus = HttpStatus.OK;
	      } else {
	        result = "Wrong userId and password";
	        httpStatus = HttpStatus.BAD_REQUEST;
	      }
	    } catch (Exception ex) {
	      result = "Server Error";
	      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return new ResponseEntity<String>(result, httpStatus);
		
    }
	@RequestMapping(value="/logout", method=RequestMethod.GET)  
    public ResponseEntity<String> logoutPage(HttpServletRequest request, HttpServletResponse response) {  
        Authentication auth = SecurityContextHolder.getContext().getAuthentication(); 
        
        HttpStatus httpStatus = null;
        String result = "error";
        if (auth != null){      
           new SecurityContextLogoutHandler().logout(request, response, auth);  
           httpStatus = HttpStatus.OK;
           result="ok";
        }  
         return new ResponseEntity<String>(result,httpStatus);
     }  
	@PostMapping("/api/user/create")
    void createUser(@RequestBody User user) {

        userService.addUser(user);
    }
	
}
