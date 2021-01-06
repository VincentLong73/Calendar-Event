package com.hedspi.team45.controller;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.apache.http.client.ClientProtocolException;

import com.hedspi.team45.entity.User;
import com.hedspi.team45.service.impl.UserServiceImpl;
import com.hedspi.team45.utils.GoogleUtils;

@Controller
public class UserController {
	@Autowired
	private UserServiceImpl userService;

	@Autowired
	private GoogleUtils googleUtils;

	@RequestMapping("/")
	public String home() {
		return "home-page";
	}

	@GetMapping(value = "/index")
	public String index(Model model) {
		return "main";

	}

	@GetMapping(value = "/login")
	public String login(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		String methodLogin = null;
		model.addAttribute("methodLogin", methodLogin);

		return "login";

	}

	@RequestMapping("/login-google")
	public String loginGoogle(HttpServletRequest request,Model model) throws ClientProtocolException, IOException, SQLException {
		String code = request.getParameter("code");

		if (code == null || code.isEmpty()) {
			return "redirect:/login";
		}


		String accessToken = googleUtils.getToken(code);

		User user = googleUtils.getUserInfo(accessToken);
		if (!userService.checkEmailExist(user.getEmail())) {
			user.setUserName(user.getEmail());
			user.setEmail(user.getEmail());
			userService.addUser(user);
		}
		User userModel = new User();
		userModel = userService.findByEmailAfterLogin(user.getEmail());
		model.addAttribute("idUser", userModel.getId());
		return "main";
	}

	@PostMapping(value = "/afterLogin")
	public String afterLogin(HttpServletResponse response, User user, String methodLogin, Model model) {

		User userModel = new User();
		try {

			if (!userService.checkLogin(user)) {
				model.addAttribute("result", "Email or password is incorrect!");
				return "login";
			}
			userModel = userService.findByEmailAfterLogin(user.getEmail());
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		model.addAttribute("idUser", userModel.getId());
		return "main";
	}

	@GetMapping(value = "/logout")
	public String logout(HttpServletResponse response) {
		return "redirect:/";
	}

	@GetMapping(value = "/register")
	public String register(Model model) {

		User user = new User();
		model.addAttribute("user", user);
		return "register";

	}

	@PostMapping(value = "/afterRegister")
	public String afterRegister(HttpServletResponse response, User user, Model model) {

		HttpStatus httpStatus = HttpStatus.INTERNAL_SERVER_ERROR;
		String result = null;
		try {
			httpStatus = userService.addUser(user);
			if (httpStatus.equals(HttpStatus.OK)) {
				result = "Register succeesfully!";
			} else if (httpStatus.equals(HttpStatus.CREATED)) {
				result = "Email registerd!";
			}
		} catch (SQLException e) {

			e.printStackTrace();
		}
		model.addAttribute("result", result);
		return "register";
	}
}
