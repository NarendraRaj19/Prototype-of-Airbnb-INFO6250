/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.dao;

import static com.hometogo.dao.DAO.getSession;
import com.hometogo.exception.OrganizationException;
import com.hometogo.exception.UserException;
import com.hometogo.pojo.Property;
import com.hometogo.pojo.User;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author naren
 */
public class PropertyDAO extends DAO {

    //To Save Property Records in Property Table when Owner Adds
    public Property saveItem(Property property) throws UserException {
        try {
            begin();
            getSession().save(property);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("User could not be created " + e.getMessage());
        }
        return property;
    }

    //To Fetch the Properties owned by a particular Owner inside Manage Properties
    public List<Property> getOwnerPropertyList(String username) throws UserException, Exception {
        try {
            begin();
            @SuppressWarnings("rawtypes")
            TypedQuery<Property> query = getSession().createQuery("from Property where Owner = :username");
            query.setParameter("username", username);
            //Property propertyList = (Property) query.uniqueResult();
            List<Property> propertyList = query.getResultList();
            commit();
            return propertyList;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Owner's Properties could not be retrieved" + username, e);
        }
    }
    
    @SuppressWarnings("empty-statement")
    public Property getProperty(int propertyID) throws UserException, Exception {
        try {
            begin();
            @SuppressWarnings("rawtypes")
            Query query = getSession().createQuery("from Property where Property_ID = :propertyID");
            query.setParameter("propertyID", propertyID);
            System.out.println("From PropDAO "+propertyID);
            Property property = (Property) query.uniqueResult();
            commit();
            return property;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("Owner's Properties could not be retrieved" + propertyID, e);
        }
    }
    
    public void delete(Property property) throws UserException {
		try {
			begin();
			getSession().delete(property);
			commit();
		} catch (HibernateException e) {
			rollback();
			throw new UserException("Property could not be retrieved" + property.getPropertyID(), e);
		}
	}
    
    public int updatePropertyByOwner(int propertyID, String address, String type, String cost) throws OrganizationException {
		try {
			begin();
			@SuppressWarnings("unchecked")
			TypedQuery<Property> query = getSession().createQuery("UPDATE Property SET Property_Address = :propertyAddress, Property_Type = :propertyType, Property_Cost = :propertyCost  WHERE Property_ID = :propertyID");
                        query.setParameter("propertyID", propertyID);
                        query.setParameter("propertyAddress", address);
                        query.setParameter("propertyType", type);
                        query.setParameter("propertyCost", cost);
                        
                        int result = query.executeUpdate();
			//List<Property> orgList = query.getResultList();
			commit();
			return result;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not update the organizations", e);
		}
	}
    
    

}
