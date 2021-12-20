/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.controller;

import com.hometogo.dao.AdminDAO;
import com.hometogo.pojo.User;
import java.util.HashMap;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

/**
 *
 * @author naren
 */
@Controller
public class AdminController {

    @GetMapping(value = "/admin-manage.htm")
    protected ModelAndView listProperties(HttpServletRequest request, AdminDAO admindao) throws Exception {
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("properties", admindao.listALLProperties());
        return new ModelAndView("adminManageProperties", modelMap);
    }
    
    @RequestMapping(value = "/admin/{propertyID}/approve.htm", method = RequestMethod.GET)
    protected ModelAndView approveProperty(@PathVariable("propertyID") int pid,HttpServletRequest request, AdminDAO admindao) throws Exception {
        
        System.out.println("##############################");
        System.out.println("Inside Admin Controller1 for Approving Properties !!!");
        System.out.println("");
        System.out.println("##############################");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("properties", admindao.approveProperty(pid));
        return new ModelAndView("propertyApproved", modelMap);
    }
    
    @RequestMapping(value = "/admin/{propertyID}/reject.htm", method = RequestMethod.GET)
    protected ModelAndView rejectProperty(@PathVariable("propertyID") int pid,HttpServletRequest request, AdminDAO admindao) throws Exception {
        
        System.out.println("##############################");
        System.out.println("Inside Admin Controller1 for Rejecting Properties !!!");
        System.out.println("");
        System.out.println("##############################");
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("properties", admindao.rejectProperty(pid));
        return new ModelAndView("propertyRejected", modelMap);
    }
}
