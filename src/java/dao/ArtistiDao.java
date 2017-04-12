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
import pojo.Artista;
import pojo.Evento;

/**
 *
 * @author matte
 */
public class ArtistiDao
{
    public static List<Artista> retrieveAll()
    {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Artista.class).list();
    }
    
    public static void addArtista(String nome, String cognome, String immagine){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Artista artista = new Artista(nome, cognome, immagine);
        
        if(immagine==null)
            immagine="/WebCommunity/resources/event.png";
        
        try
        {
            transaction = session.beginTransaction();
            
            session.save(artista);

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
