package spring;

/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
import POJO.Artisti;
import POJO.Categorie;
import POJO.Eventi;
import POJO.Followers;
import POJO.Locations;
import POJO.Recensioni;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author FSEVERI\guidolin3172
 */
public class HibernateUtil {

    private static final SessionFactory sessionFactory;
    
    static {
        try {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .addAnnotatedClass(Artisti.class)
                    .addAnnotatedClass(Categorie.class)
                    .addAnnotatedClass(Eventi.class)
                    .addAnnotatedClass(Followers.class)
                    .addAnnotatedClass(Locations.class)
                    .addAnnotatedClass(Recensioni.class).configure().buildSessionFactory();
        } catch (Throwable ex) {
            // Log the exception. 
            System.err.println("Initial SessionFactory creation failed." + ex);
            throw new ExceptionInInitializerError(ex);
        }
    }
    
    public static SessionFactory getSessionFactory() {
        return sessionFactory;
    }
}
