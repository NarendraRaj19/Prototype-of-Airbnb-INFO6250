/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.dao;

import com.hometogo.pojo.Property;
import com.hometogo.exception.OrganizationException;
import java.util.List;
import javax.persistence.TypedQuery;
import org.hibernate.HibernateException;

/**
 *
 * @author naren
 */
public class AdminDAO extends DAO{
    
    
    public List<Property> listALLProperties() throws OrganizationException {
		try {
			begin();
			@SuppressWarnings("unchecked")
			TypedQuery<Property> query = getSession().createQuery("FROM Property where STATUS = 'PENDING'");
			List<Property> orgList = query.getResultList();
			commit();
			return orgList;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not list the organizations", e);
		}
	}
    
    public int approveProperty(int propertyID) throws OrganizationException {
		try {
			begin();
			@SuppressWarnings("unchecked")
			TypedQuery<Property> query = getSession().createQuery("UPDATE Property SET STATUS = 'APPROVED' WHERE Property_ID = :propertyID");
                        query.setParameter("propertyID", propertyID);
                        int result = query.executeUpdate();
			//List<Property> orgList = query.getResultList();
			commit();
			return result;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not approve the organizations", e);
		}
	}
    
    public int rejectProperty(int propertyID) throws OrganizationException {
		try {
			begin();
			@SuppressWarnings("unchecked")
			TypedQuery<Property> query = getSession().createQuery("UPDATE Property SET STATUS = 'DENIED' WHERE Property_ID = :propertyID");
                        query.setParameter("propertyID", propertyID);
                        int result = query.executeUpdate();
			//List<Property> orgList = query.getResultList();
			commit();
			return result;
		} catch (HibernateException e) {
			rollback();
			throw new OrganizationException("Could not approve the organizations", e);
		}
	}
    
}
