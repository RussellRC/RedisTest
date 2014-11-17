package com.russell.test.redis.web;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.validation.groups.Default;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import com.russell.test.redis.web.form.Login;
import com.russell.test.redis.web.form.UserSignUp;
import com.russell.test.redis.web.validator.DomainLogic;
import com.russell.test.redis.web.validator.UserLoginValidator;
import com.russell.test.redis.web.validator.UserSignUpValidator;

@Controller
//@RequestMapping(value = "/")
public class RootController {
	Logger logger = LoggerFactory.getLogger(RootController.class);
    
    
    @Resource
    TFacade facade;
    
    @InitBinder("userSignUp")
    protected void initBinder(WebDataBinder binder) {
        binder.addValidators(new UserSignUpValidator());
    }
    
	@RequestMapping("/")
	public String root() {
		return "/login";
	}
	
	@RequestMapping(value = "/login", method = RequestMethod.GET)
    public String login(Model model) {
        model.addAttribute(new Login());
        return "login";
    }

    @RequestMapping(value = "/login", method = RequestMethod.POST)
    public String loginPost(Model model) {
        model.addAttribute(new Login());
        return "login";
    }
	
	@RequestMapping("/signUp")
	public String signUp(Model model, HttpServletRequest request, HttpServletResponse response) {
	    model.addAttribute(new UserSignUp());
		return "signup";
	}
	
	@RequestMapping(value="/register", method=RequestMethod.POST)
	public String register(@Validated({Default.class, DomainLogic.class}) @ModelAttribute UserSignUp userSignUp, 
	                       BindingResult bindingResults) {
	    if (bindingResults.hasErrors()) {
	        return "signup";
	    }
	    
	    facade.register(userSignUp);
	    
	    return "signup";
	}
	
	@RequestMapping("/secure/home")
    public String home(Model model, HttpServletRequest request, HttpServletResponse response) {
	    logger.debug("/secure/home: Any authorized user should have access.");
        return "home";
    }
	
}
