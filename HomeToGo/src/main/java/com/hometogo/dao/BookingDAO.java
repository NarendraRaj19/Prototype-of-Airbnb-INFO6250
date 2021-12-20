/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.dao;

import static com.hometogo.dao.DAO.getSession;
import com.hometogo.exception.UserException;
import com.hometogo.pojo.Booking;
import com.hometogo.pojo.Property;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;

/**
 *
 * @author naren
 */
public class BookingDAO extends DAO {

    public Booking saveItem(Booking booking) throws UserException {
        try {
            begin();
            getSession().save(booking);
            commit();
            System.out.println("Booking Successfully Saved !!");
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Booking could not be confirmed " + e.getMessage());
        }
        return booking;
    }
    
    //To Fetch the Bookings of a particular Property 
    public List<Booking> getBookingsOfAProperty(String propertyName) throws UserException, Exception {
        try {
            begin();
            @SuppressWarnings("rawtypes")
            TypedQuery<Booking> query = getSession().createQuery("from Booking where Property_Booked = :propertyName");
            query.setParameter("propertyName", propertyName);
            //Property propertyList = (Property) query.uniqueResult();
            List<Booking> bookingList = query.getResultList();
            commit();
            return bookingList;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Owner's Properties could not be retrieved" + propertyName, e);
        }
    }
    
    //To Fetch the Bookings of a particular Property Owner
    public List<Booking> getBookingsOfAPropertyOwner(String propertyOwner) throws UserException, Exception {
        try {
            begin();
            @SuppressWarnings("rawtypes")
            TypedQuery<Booking> query = getSession().createQuery("from Booking where Property_Owner = :propertyOwner");
            query.setParameter("propertyOwner", propertyOwner);
            //Property propertyList = (Property) query.uniqueResult();
            List<Booking> bookingList = query.getResultList();
            commit();
            return bookingList;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Owner's Properties could not be retrieved" + propertyOwner, e);
        }
    }

}
