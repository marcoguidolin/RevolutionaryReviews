/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package hibernate;

import pojo.Artista;
import pojo.Post;
import pojo.PostPK;
import pojo.Evento;
import pojo.Categoria;
import pojo.Membro;
import org.hibernate.cfg.AnnotationConfiguration;
import org.hibernate.SessionFactory;

/**
 * Hibernate Utility class with a convenient method to get Session Factory
 * object.
 *
 * @author FSEVERI\parlato2889
 */
public class HibernateUtil
{

    private static final SessionFactory sessionFactory;
    
    public static final String HOST = "jdbc:mysql://10.0.1.252:3306/5ib10";
    public static final String USERNAME = "5ib10";
    public static final String PASSWORD = "5ib10";
    
    static
    {
        try
        {
            // Create the SessionFactory from standard (hibernate.cfg.xml) 
            // config file.
            sessionFactory = new AnnotationConfiguration()
                    .addPackage("pojo")
                    .addAnnotatedClass(Artista.class)
                    .addAnnotatedClass(Categoria.class)
                    .addAnnotatedClass(Evento.class)
                    .addAnnotatedClass(Membro.class)
                    .addAnnotatedClass(Post.class)
                    .addAnnotatedClass(PostPK.class)
                    .configure()
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
