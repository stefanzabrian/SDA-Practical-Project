package com.sda.practicalproject.utils;

import org.hibernate.SessionFactory;

import org.hibernate.cfg.Configuration;

public class SessionManager extends AbstractSessionManager {
    private static final SessionManager INSTANCE = new SessionManager();

    private SessionManager(){
    }
    public static SessionFactory getSesstionFactory(){
        return INSTANCE.getSessionFactory("pet_clinic");
    }
    public static void shutDown(){
        INSTANCE.shutdownSessionManager();
    }
    protected void setAnnotatedClasses(Configuration configuration){

    }

}
