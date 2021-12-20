package com.hometogo.controller;

import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

@Controller
@RequestMapping("/authorization/*")
public class RoleController {

	@RequestMapping(value = "/authorization/error.htm", method = RequestMethod.GET)
	protected ModelAndView postMenuError(HttpServletRequest request) throws Exception {
		return new ModelAndView("authError");
	}

}
