package com.russell.test.redis.web;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class RootController {
	
	@RequestMapping("/")
	public String root() {
		return "";
	}
	
	@RequestMapping("/signUp")
	public String signUp(@ModelAttribute UserSignUp userSignUp, HttpServletRequest request, HttpServletResponse response) {
		return "signup";
	}
	
}
