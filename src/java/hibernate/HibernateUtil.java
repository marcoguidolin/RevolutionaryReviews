/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;
import pojo.*;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author FSEVERI\parlato2889
 */
public class HibernateUtil
{

    private static final SessionFactory sessionFactory;
    
    static
    {
        try
        {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration().configure()
                    .addPackage("pojo")
                    .addAnnotatedClass(Artisti.class)
                    .addAnnotatedClass(Categorie.class)
                    .addAnnotatedClass(Eventi.class)
                    .addAnnotatedClass(Membri.class)
                    .addAnnotatedClass(Post.class)
                    .addAnnotatedClass(PostPK.class)
                    .buildSessionFactory();
        }
        catch (Throwable ex)
        {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory()
    {
        return sessionFactory;
    }
}
