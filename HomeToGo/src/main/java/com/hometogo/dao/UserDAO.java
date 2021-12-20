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
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;
import org.hibernate.Criteria;

import org.hibernate.HibernateException;
import org.hibernate.criterion.Criterion;
import org.hibernate.criterion.Restrictions;
import org.hibernate.query.Query;
import org.mindrot.jbcrypt.BCrypt;

/**
 *
 * @author naren
 */
public class UserDAO extends DAO {

    public User saveItem(User user) throws UserException {
        try {
            begin();
            getSession().save(user);
            commit();
        } catch (HibernateException e) {
            rollback();
            throw new UserException("User could not be created " + e.getMessage());
        }
        return user;
    }

    public User getUser(String username, String password) throws UserException, Exception {
        try {
            begin();
            @SuppressWarnings("rawtypes")

            Query query = getSession().createQuery("from User where username = :username");
            query.setParameter("username", username);
            User userResult = (User) query.uniqueResult();
            if(userResult == null){
                throw new UserException("There doesnt exist with the given credentials!");
            }
            
            String hashedPwdDB = userResult.getPassword();
            System.out.println("#################################################################");
            System.out.println("User Entered Password is: " + password);

            if (BCrypt.checkpw(password, hashedPwdDB)) {
                System.out.println("Both the passwords match");
            } else {
                System.out.println("Both the passwords do not match");
                throw new UserException("The provided password is incorred");
            }

            System.out.println("#################################################################");
            commit();
            return userResult;
        } catch (HibernateException e) {
            rollback();
            throw new UserException("User could not be retrieved" + username, e);
        }
    }

    public List<Property> listCities() throws OrganizationException {
        try {
            begin();
            @SuppressWarnings("unchecked")
            TypedQuery<Property> query = getSession().createQuery("FROM Property where STATUS = 'APPROVED'");
            List<Property> orgList = query.getResultList();
            commit();
            return orgList;
        } catch (HibernateException e) {
            rollback();
            throw new OrganizationException("Could not list the organizations", e);
        }
    }

    public List<Property> getPropertiesForUser(String cityName, String propertyType) throws UserException {
        List<Property> resultList;
        try {

            Criteria c = getSession().createCriteria(Property.class);
            Criterion c1 = Restrictions.eq("propertyCity", cityName);
            Criterion c2 = Restrictions.eq("propertyType", propertyType);
            c.add(c1);
            c.add(c2);

            resultList = c.list();

        } catch (Exception e) {
            throw new UserException("Could not get the value", e);
        }
        return resultList;
    }

}
