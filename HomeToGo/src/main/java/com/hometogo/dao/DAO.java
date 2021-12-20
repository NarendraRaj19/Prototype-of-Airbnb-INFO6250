/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hometogo.dao;

import java.util.logging.Level;
import java.util.logging.Logger;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.cfg.Configuration;

/**
 *
 * @author naren
 */
public class DAO {

    private static final Logger log = Logger.getAnonymousLogger();

    @SuppressWarnings("rawtypes")
    private static final ThreadLocal sessionThread = new ThreadLocal();
    protected static final SessionFactory sessionFactory = new Configuration().configure("hibernate.cfg.xml").buildSessionFactory();

    protected DAO() {
    }

    @SuppressWarnings("unchecked")
    public static Session getSession() {
        Session session = (Session) DAO.sessionThread.get();

        if (session == null) {
            session = sessionFactory.openSession();
            DAO.sessionThread.set(session);
        }
        return session;
    }

    protected void begin() {
        getSession().beginTransaction();
    }

    protected void commit() {
        getSession().getTransaction().commit();
    }

    @SuppressWarnings("unchecked")
    protected void rollback() {
        try {
            getSession().getTransaction().rollback();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Could not rollback", e);
        }
        try {
            getSession().close();
        } catch (HibernateException e) {
            log.log(Level.WARNING, "Could not close connection", e);
        }
        DAO.sessionThread.set(null);
    }

    @SuppressWarnings("unchecked")
    public static void close() {
        getSession().close();
        DAO.sessionThread.set(null);
    }

}
