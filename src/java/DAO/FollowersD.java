/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package DAO;

/**
 *
 * @author tosos
 */

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import POJO.Followers;
import spring.HibernateUtil;


public class FollowersD
{

    
    
    public static Followers registrazione(String nickname, String password, String nome, String cognome, String provincia, String email, String icona)
    {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        
        Followers f = null;
        
        try
        {
            transaction = session.beginTransaction();

            f = new Followers(nickname, password, nome, cognome, provincia, email, icona);
            
            session.save(f);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }

        return f;
    }

    public static Followers checkLogin(String username, String password)
    {        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        
        
        String hql = "FROM Followers WHERE nickname = :nick";
        Query query = session.createQuery(hql);
        query.setString("nick", username);
        Followers registered = (Followers) query.uniqueResult();

        if (registered != null && (username.equals(registered.getNickname()) && password.equals(registered.getPassword())))
        {
            return registered;
        }
        return null;
    }

}