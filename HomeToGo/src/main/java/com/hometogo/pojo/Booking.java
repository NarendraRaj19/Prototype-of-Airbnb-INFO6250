/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.pojo;

import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.AbstractController;

/**
 *
 * @author naren
 */
@Entity(name = "Booking")
@Table(name = "Booking")
public class Booking {

    @Id
    @Column(name = "Booking_ID")
    private int bookingID;

    @Basic
    @Column(name = "Property_Booked")
    private String propertyBooked;

    @Basic
    @Column(name = "Booked_By_User")
    private String bookedByUser;

    @Basic
    @Column(name = "Property_Owner")
    private String propertyOwner;

    @Basic
    @Column(name = "Start_Date")
    private String startDate;

    @Basic
    @Column(name = "End_Date")
    private String endDate;

    public int getBookingID() {
        return bookingID;
    }

    public void setBookingID(int bookingID) {
        this.bookingID = bookingID;
    }

    public String getPropertyBooked() {
        return propertyBooked;
    }

    public void setPropertyBooked(String propertyBooked) {
        this.propertyBooked = propertyBooked;
    }

    public String getBookedByUser() {
        return bookedByUser;
    }

    public void setBookedByUser(String bookedByUser) {
        this.bookedByUser = bookedByUser;
    }

    public String getPropertyOwner() {
        return propertyOwner;
    }

    public void setPropertyOwner(String propertyOwner) {
        this.propertyOwner = propertyOwner;
    }

    public String getStartDate() {
        return startDate;
    }

    public void setStartDate(String startDate) {
        this.startDate = startDate;
    }

    public String getEndDate() {
        return endDate;
    }

    public void setEndDate(String endDate) {
        this.endDate = endDate;
    }

}
