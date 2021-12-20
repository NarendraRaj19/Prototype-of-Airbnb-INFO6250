/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.controller;

import com.hometogo.dao.AdminDAO;
import com.hometogo.dao.BookingDAO;
import com.hometogo.dao.PropertyDAO;
import com.hometogo.dao.UserDAO;
import com.hometogo.pojo.Booking;
import com.hometogo.pojo.Property;
import com.hometogo.pojo.User;
import com.hometogo.validator.PropertyOwnerValidator;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.context.request.RequestScope;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpSession;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author naren
 */
@Controller
public class PropertyOwnerFormController {

    @Autowired //Non-thread Safe and used to create instance of a class
    PropertyOwnerValidator propertyValidator;

    @GetMapping(value = "/property.htm")
    protected ModelAndView showForm(HttpServletRequest request, User user, Property property) {

        ModelAndView mv = new ModelAndView();
        HttpSession session = (HttpSession) request.getSession();
        User user2 = (User) session.getAttribute("user");
        String ownerName = user2.getUsername();
        session.setAttribute("OwnerName", ownerName);

        return new ModelAndView("addProperty", "property", ownerName);
    }

    @PostMapping(value = "/property.htm")
    protected ModelAndView showSuccess(HttpServletRequest request, Property property, User user, BindingResult result, SessionStatus status, PropertyDAO propertydao) {
        HttpSession session = (HttpSession) request.getSession();
        System.out.println("########## Inside Property Post Method###############");
        property.setPropertyAddress(request.getParameter("propertyAddress"));
        property.setPropertyCity(request.getParameter("propertyCity"));
        property.setPropertyState(request.getParameter("propertyState"));
        property.setPropertyType(request.getParameter("propertyType"));
        property.setPropertyCost(request.getParameter("propertyCost"));
        String OwnerName = (String) session.getAttribute("OwnerName");
        property.setPropertyOwner(OwnerName);
        System.out.println("Property Cost is:" + property.getPropertyCost());
        
        try {
            Property propReg = propertydao.saveItem(property);
            status.setComplete();
            return new ModelAndView("propertyRegistered", "property", propReg);
        } catch (Exception e) {
            System.out.println("Inside Catch Block");
            System.out.println("the message is " + e.getMessage());
            return new ModelAndView("error", "errorMessage", "An error occurred while Registering the User !");
        }
    }

    @GetMapping(value = "/manage-property.htm")
    protected ModelAndView showManageProperty(HttpServletRequest request, User user, Property property, PropertyDAO propertydao) throws Exception {
        System.out.println("############Inside Manage Property Get Method###############");
        HttpSession session = (HttpSession) request.getSession();
        User user3 = (User) session.getAttribute("user");
        String ownerName2 = user3.getUsername();
        System.out.println("The Name of the Owner is: " + ownerName2);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("properties", propertydao.getOwnerPropertyList(ownerName2));
        modelMap.put("ownerName", ownerName2);
        return new ModelAndView("manageProperty", modelMap);
    }
    
    @GetMapping(value = "/view-bookings.htm")
    protected ModelAndView showBookings(HttpServletRequest request, User user, Booking booking, BookingDAO bookingdao) throws Exception {
        System.out.println("############Inside View Booking Get Method###############");
        HttpSession session = (HttpSession) request.getSession();
        User user4 = (User) session.getAttribute("user");
        String ownerName3 = user4.getUsername();
        System.out.println("The Name of the Owner is: " + ownerName3);
        List<Booking> bookingsList_Owner = new ArrayList<>();
        bookingsList_Owner = bookingdao.getBookingsOfAPropertyOwner(ownerName3);
        
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("confirmedBookings", bookingsList_Owner);
        modelMap.put("ownerName", ownerName3);
        return new ModelAndView("viewBookings", modelMap);
    }
    

    //Property Owner can delete his own properties from the application in the manageProperty.jsp
    @RequestMapping(value = "/propertyowner/{propertyID}/delete.htm", method = RequestMethod.GET)
    protected ModelAndView deleteProperty(@PathVariable("propertyID") int pid, HttpServletRequest request, PropertyDAO propertydao) throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("deletedProperty", propertydao.getProperty(pid).getPropertyAddress());
        propertydao.delete(propertydao.getProperty(pid));
        return new ModelAndView("ownerDeletedProperty", modelMap);
    }

    @RequestMapping(value = "/propertyowner/{propertyID}/update.htm", method = RequestMethod.GET)
    protected ModelAndView updateProperty(@PathVariable("propertyID") int pid, HttpServletRequest request, PropertyDAO propertydao) throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        modelMap.put("property", propertydao.getProperty(pid));
        modelMap.put("pid", pid);
        return new ModelAndView("ownerUpdateProperty", modelMap);
    }

    @RequestMapping(value = "/propertyowner/{propertyID}/update.htm", method = RequestMethod.POST)
    protected ModelAndView updatePropertyPost(@PathVariable("propertyID") int pid, HttpServletRequest request, PropertyDAO propertydao, Property property) throws Exception {

        Map<String, Object> modelMap = new HashMap<String, Object>();

        int result = propertydao.updatePropertyByOwner(pid, request.getParameter("propertyAddress"), request.getParameter("propertyType"), request.getParameter("propertyCost"));
        modelMap.put("updatedCols", result);
        modelMap.put("pid", pid);
        return new ModelAndView("ownerUpdatePropSuccess", modelMap);
    }

}
