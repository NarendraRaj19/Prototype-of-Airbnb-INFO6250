/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.intercceptor;

import com.hometogo.pojo.User;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.web.servlet.handler.HandlerInterceptorAdapter;

/**
 *
 * @author naren
 */
public class PropertyOwnerInterceptor extends HandlerInterceptorAdapter{
    
    @Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		HttpSession session = (HttpSession) request.getSession();
		if (session.getAttribute("user") != null) {
			User user = (User) session.getAttribute("user");
			if (user.getUserRole().equalsIgnoreCase("propertyowner")) {
				System.out.println("Inside Property Owner Interceptor");
				return true;
			}
		}
		System.out.println("Auth failed, user is not a Property Owner!");
		response.sendRedirect(request.getContextPath() + "/authorization/error.htm");
		return false;

	}
    
}
