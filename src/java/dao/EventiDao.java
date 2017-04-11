/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Evento;
import pojo.Post;
import pojo.PostPK;

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
    
    public static void addEvento(String titolo, String luogo, Date data, Integer categoria, String descrizione, String immagine){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Evento evento = new Evento(titolo, luogo, data, descrizione, immagine, categoria);
        
        if(immagine==null)
            immagine="/WebCommunity/resources/event.png";
        
        try
        {
            transaction = session.beginTransaction();
            
            session.save(evento);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
    }
}
