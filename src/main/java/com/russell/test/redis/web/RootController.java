package com.russell.test.redis.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.russell.test.redis.service.TService;
import com.russell.test.redis.web.validator.UserSignUpValidator;

@Controller
public class RootController {
	
    @Resource
    TService service;
    
    @InitBinder
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserSignUpValidator());
    }
    
	@RequestMapping("/")
	public String root() {
		return "";
	}
	
	@RequestMapping("/signUp")
	public String signUp(Model model, HttpServletRequest request, HttpServletResponse response) {
	    model.addAttribute(new UserSignUp());
		return "signup";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@Valid @ModelAttribute UserSignUp userSignUp, BindingResult bindingResults) {
	    if (bindingResults.hasErrors()) {
	        return "signup";
	    }
	    return "signup";
	}
	
}
