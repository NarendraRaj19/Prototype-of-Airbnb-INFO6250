/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.controller;

import com.captcha.botdetect.web.servlet.Captcha;
import com.hometogo.dao.BookingDAO;
import com.hometogo.dao.UserDAO;
import com.hometogo.dao.PropertyDAO;
import com.hometogo.exception.UserException;
import com.hometogo.pojo.Booking;
import com.hometogo.pojo.Property;
import com.hometogo.pojo.User;
import com.hometogo.validator.UserValidator;
import java.text.SimpleDateFormat;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.Authenticator;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.support.SessionStatus;
import org.springframework.web.servlet.ModelAndView;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.mindrot.jbcrypt.BCrypt;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

/**
 *
 * @author naren
 */
@Controller
public class UserFormController {

    @Autowired //Non-thread Safe and used to create instance of a class
    UserValidator userValidator;

    @GetMapping(value = "/user/register.htm")
    public String showForm(ModelMap model, User user) {
        //Command Class
        model.addAttribute("user", user);
        return "user-form";
    }

    @PostMapping(value = "/user/register.htm")
    public ModelAndView showSuccess(HttpServletRequest request, @ModelAttribute("user") User user, BindingResult result, SessionStatus status, UserDAO userdao) throws UserException {

        userValidator.validate(user, result);

        if (result.hasErrors()) {
            return new ModelAndView("user-form");
        }

        try {
            System.out.println("The result values are: " + result.getModel());
            System.out.println("The User Entered Password in Plain Text is: " + user.getPassword());
            String plainPwd = user.getPassword();
            String hashedPwd = hashPassword(plainPwd);
            user.setPassword(hashedPwd);

            User userReg = userdao.saveItem(user);
            request.getSession().setAttribute("user", userReg);
            status.setComplete();
            return new ModelAndView("user-success", "user", userReg);
        } catch (Exception e) {
            return new ModelAndView("error", "errorMessage", "An error occurred while Registering the User !");
        }

    }

    @RequestMapping(value = "/user/login.htm", method = RequestMethod.GET)
    protected String goToHome(HttpServletRequest request) throws Exception {
        return "login-form";
    }

    @RequestMapping(value = "/user/login.htm", method = RequestMethod.POST)
    protected ModelAndView loginUser(HttpServletRequest request, UserDAO userdao) throws Exception {
        HttpSession session = (HttpSession) request.getSession();
        try {

            String authToken = request.getParameter("authToken");
            System.out.println("! ----- " + authToken);

            User user = userdao.getUser(request.getParameter("username"), request.getParameter("password"));
            if (user == null) {
                session.setAttribute("errorMessage", "Invalid username and password! Please try again!");
                return new ModelAndView("error");
            }
            session.setAttribute("user", user);
            String title = user.getUserRole().trim();

            if (title.equals("admin")) {
                return new ModelAndView("adminLanding");
            } else if (title.equals("customer")) {

                Map<String, Object> modelMap = new HashMap<String, Object>();

                //For Dynamically populating the City Field
                List<Property> listProp = new ArrayList<>();
                listProp = userdao.listCities();
                List<String> distinctCities = new ArrayList<>();
                if (listProp.size() > 0) {
                    System.out.println("Inside If Condition Of LoginUser Handler");
                    String city = null;
                    for (Property item : listProp) {
                        city = item.getPropertyCity();
                        if (!distinctCities.contains(city)) {
                            distinctCities.add(city);
                        }
                    }
                }

                modelMap.put("properties", distinctCities);
                return new ModelAndView("customerLanding", modelMap);
            } else if (title.equals("propertyowner")) {
                return new ModelAndView("propertyOwnerLanding");
            } else {
                return null;
            }
        } catch (UserException e) {
            session.setAttribute("errorMessage", "An error occurred while logging in");
            return new ModelAndView("error");
        }
    }

    //For Signing Out Users and Invalidating Session
    @RequestMapping(value = "/signOut.htm", method = RequestMethod.GET)
    protected String signOutUser(HttpServletRequest request) throws Exception {

        System.out.println("Inside Sign Out Controller !");
        HttpSession session = (HttpSession) request.getSession();
        session.invalidate();

        return "Home";
    }

    @RequestMapping(value = "/user/search.htm", method = RequestMethod.POST)
    protected ModelAndView searchResults(HttpServletRequest request, UserDAO userdao) throws Exception {

        String cityName = request.getParameter("propertyCity");
        String propertyType = request.getParameter("propertyType");

        List<Property> listProp = new ArrayList<>();
        listProp = userdao.getPropertiesForUser(cityName, propertyType);
        Map<String, Object> modelMap = new HashMap<String, Object>();
        modelMap.put("searchResults", listProp);

        return new ModelAndView("userSearchResults", modelMap);
    }

    //To Allow the User to Book A Property in PropertyBookingPage.jsp
    @RequestMapping(value = "/user/{propertyID}/booking.htm", method = RequestMethod.GET)
    protected ModelAndView showPropertyInfo(@PathVariable("propertyID") int pid, HttpServletRequest request, UserDAO userdao, PropertyDAO propertydao, BookingDAO bookingdao, Booking booking) throws Exception {

        List<String> datesList = new ArrayList<>();
        Map<String, Object> modelMap = new HashMap<String, Object>();
        datesList = generateDates();
        System.out.println("Size of the list of Dates: " + datesList.size());
        modelMap.put("property", propertydao.getProperty(pid));

        //For Handling Date in the dropdown based on previous bookings if any
        List<Booking> bookingsList = new ArrayList<>();
        bookingsList = bookingdao.getBookingsOfAProperty(propertydao.getProperty(pid).getPropertyAddress());
        if (bookingsList.size() > 0) {
            System.out.println("Size of Bookings List: " + bookingsList.size());
            booking = bookingsList.get(bookingsList.size() - 1);
            System.out.println("End Date of Bookings List: " + booking.getEndDate());

            String removeSD = null, removeED = null;
            int indexSD, indexED;
            for (Booking item : bookingsList) {
                removeSD = item.getStartDate();
                removeED = item.getEndDate();

                indexSD = datesList.indexOf(removeSD);
                indexED = datesList.indexOf(removeED);
                int j = indexSD;
                for (int i = indexSD; i <= indexED; i++) {
                    datesList.remove(j);
                }
            }

            for (String i : datesList) {
                System.out.println("Date Element Available is: " + i);
            }
            System.out.println("Size of the list of Dates After Deletion: " + datesList.size());
        }

        //getBookingsOfAProperty
        System.out.println("##################################");
        modelMap.put("populatedDates", datesList);
        return new ModelAndView("propertyBookingPage", modelMap);
    }

    @RequestMapping(value = "/user/{propertyID}/{userName}/bookingSuccess.htm", method = RequestMethod.POST)
    protected ModelAndView bookProperty(@PathVariable("propertyID") int pid, @PathVariable("userName") String userName, HttpServletRequest request, UserDAO userdao, PropertyDAO propertydao, BookingDAO bookingdao, Booking booking, Property property) throws Exception {

        System.out.println("##################################");
        System.out.println("################# Inside POST METHOD && The ID of this Property is: " + pid);
        Map<String, Object> modelMap = new HashMap<String, Object>();

        String startDate = request.getParameter("startDate");
        String endDate = request.getParameter("endDate");
        System.out.println("The Start & End Dates are: " + startDate + " " + endDate);

        //Retrieve the Property Details using pid to populate the booking table
        property = propertydao.getProperty(pid);
        System.out.println("The Property Address is: " + property.getPropertyAddress() + " UserName is: " + userName);

        booking.setPropertyBooked(property.getPropertyAddress());
        booking.setPropertyOwner(property.getPropertyOwner());
        booking.setStartDate(startDate);
        booking.setEndDate(endDate);
        booking.setBookedByUser(userName);

        bookingdao.saveItem(booking);

        System.out.println("##################################");
        //sendMail(userName, booking.getPropertyBooked());
        return new ModelAndView("bookingSuccessful", modelMap);
    }

    //To Generate Hashed Password
    private String hashPassword(String plainTextPassword) {
        return BCrypt.hashpw(plainTextPassword, BCrypt.gensalt());
    }

    //To Generate Dates
    private List<String> generateDates() {

        List<String> dates = new ArrayList<>();
        SimpleDateFormat sdf = new SimpleDateFormat("YYYY/MM/dd");
        //String strDate = sdf.format(date);
        Calendar cal = new GregorianCalendar(2021, 11, 16);
        //System.out.println("Before Adding: "+sdf.format(cal.getTime()));
        int j = 1;
        for (int i = 1; i < 10; i++) {
            cal.add(Calendar.DAY_OF_MONTH, j);
            //System.out.println("After Adding: "+sdf.format(cal.getTime()));
            dates.add(sdf.format(cal.getTime()));
        }

        return dates;
    }

    public static void sendMail(String recipient, String messageBody) {

        Properties properties = new Properties();

        properties.put("mail.smtp.port", "587");
        properties.put("mail.smtp.starttls.enable", "true");
        properties.put("mail.smtp.auth", "true");
        properties.put("mail.smtp.host", "smtp.mail.yahoo.com");

        final String myEmailID = "narenraj_95@yahoo.com";
        final String password = "tzahwcxzojsjblvl";

        Session session = Session.getInstance(properties, new Authenticator() {
            @Override
            protected PasswordAuthentication getPasswordAuthentication() {
                return new PasswordAuthentication(myEmailID, password);
            }
        });
        System.out.println("Preparing to Send Email");
        session.setDebug(true);
        try {
            MimeMessage message = new MimeMessage(session);

            message.setFrom(new InternetAddress(myEmailID));
            message.addRecipient(Message.RecipientType.TO, new InternetAddress(recipient));
            message.setSubject("Booking Confirmation from HomeToGo!");
            message.setText("Your Booking at " + messageBody + " is Confirmed!");

            System.out.println("sending...");
            Transport.send(message);
            System.out.println("Sent message successfully....");
        } catch (MessagingException mex) {
            mex.printStackTrace();
        }
    }

}
