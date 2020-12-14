package com.hedspi.team45.controller;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.env.Environment;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.web.authentication.logout.SecurityContextLogoutHandler;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.hedspi.team45.entity.User;
import com.hedspi.team45.service.impl.JwtService;
import com.hedspi.team45.service.impl.UserServiceImpl;

@Controller
//@RestController
@RequestMapping(value="/Calendar-Event")
public class MainController {
	@Autowired
	private UserServiceImpl userService;
	
	
	@Autowired
	private JwtService jwtService;

	@Autowired
	Environment environment;
	@GetMapping(value = "/")
	public String index(HttpServletRequest request) {

		request.setAttribute("msg", environment.getProperty("message"));
		return "index";

	}
	
	 @RequestMapping("/login")
	  public String login(Model model,User user) {
	  
		 model.addAttribute("user",user);
	    return "login";
	  }

	
	//@RequestMapping(value = "/check_login", method = RequestMethod.POST,produces = MediaType.APPLICATION_JSON_VALUE)
	 @RequestMapping(value = "/check_login", method = RequestMethod.POST)
	  public String login(User user) {
		
		
	    String result = "";
	    HttpStatus httpStatus = null;
	    try {
	      if (userService.checkLogin(user.getEmail(),user.getPassword())) {
	        result = jwtService.generateTokenLogin( user.getEmail());
	        httpStatus = HttpStatus.OK;
	        return "redirect:/index";
	      } else {
	        result = "Wrong userId and password";
	        httpStatus = HttpStatus.BAD_REQUEST;
	      }
	    } catch (Exception ex) {
	      result = "Server Error";
	      httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
	    }
	    return "Error";
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

}
