package com.hedspi.team45.controller;

import java.sql.SQLException;

import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import com.hedspi.team45.entity.User;
import com.hedspi.team45.service.impl.UserServiceImpl;



@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;
	
	@RequestMapping("/")
    public String home() {
        return "home-page";
    }
    @GetMapping(value = "/login")
	public String login(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "login";

	}
    @GetMapping(value = "/register")
	public String register(Model model) {

		User user = new User();
		model.addAttribute("user", user);

		return "register";

	}
    @GetMapping(value = "/index")
	public String index(Model model) {
    	//int idUser = 0;
//		User user = new User();
//		model.addAttribute("user", user);
    	//model.addAttribute("idUser",idUser);
		return "main";

	}
	@PostMapping(value = "/afterLogin")
	public String afterLogin(HttpServletResponse response, User user,Model model) {

		User userModel = new User();
		try {
			userService.checkLogin(user);
			userModel = userService.findByEmailAfterLogin(user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("idUser",userModel.getId());
		//return "redirect:http://localhost:8282/index";
		return "main";
	}
	
	
	@GetMapping(value = "/logout")
	public String logout(HttpServletResponse response) {
		return "redirect:/";
	}
	
//	@PostMapping(value = "/register", produces = MediaType.APPLICATION_FORM_URLENCODED_VALUE)
//	public ResponseEntity<String> createUser(User user) {
//		HttpStatus httpStatus = null;
//		try {
//			httpStatus = userService.addUser(user);
//		} catch (SQLException e) {
//			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//			e.printStackTrace();
//		}
//		return new ResponseEntity<String>(httpStatus);
//	}
//
//	@PostMapping(value = "/login")
//	public ResponseEntity<Object> login(@RequestBody User user, HttpServletResponse response, HttpServletRequest request) {
//		HttpStatus httpStatus = null;
//		User userModel = new User();
//		try {
//			if (userService.checkLogin(user)) {
//				httpStatus = HttpStatus.OK;
//				userModel = userService.findByEmailAfterLogin(user.getEmail());
//			} else {
//				httpStatus = HttpStatus.BAD_REQUEST;
//			}
//		} catch (Exception ex) {
//			System.out.println(ex.getMessage());
//			httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
//		}
//
//		return new ResponseEntity<Object>(userModel,httpStatus);
//	}
//
//	@RequestMapping(value = "/logout", method = RequestMethod.GET)
//	public ResponseEntity<String> logoutPage(HttpServletRequest request, HttpServletResponse response) {
//		HttpStatus httpStatus = null;
//		httpStatus = HttpStatus.OK;
//		assert httpStatus != null;
//		return new ResponseEntity<String>(httpStatus);
//	}
	
}
