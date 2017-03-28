/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import pojo.Evento;

/**
 *
 * @author alb07y
 */
public class EventiDao {
    
    public static List<Evento> retrieveByCat(String cat)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Evento> eventoList = session.createQuery("from Evento e where e.categoria = " + cat).list();
        
        return eventoList;
    }

    public static Evento retrieveSingle(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Evento> eventi = session.createQuery("from Evento e where e.id = " + id).list();
        
        for(Evento e : eventi){
            return e;
        }
        return null;
    }

    public static List<Evento> retrieveAll() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Evento> eventoList = session.createQuery("from Evento").list();
        
        return eventoList;
    }
}
