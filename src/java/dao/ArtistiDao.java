/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Artista;

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
    
    public static Artista retrieveSingle(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        return (Artista) session.get(Artista.class, id);
    }
    
    public static void addArtista(String nome, String cognome, String immagine){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Artista artista = new Artista(nome, cognome);
        
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
    
    public static void remove(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try
        {
            transaction = session.beginTransaction();

            String hql = "DELETE FROM Artista WHERE id = :artistID";
            Query query = session.createQuery(hql);
            query.setParameter("artistID", id);
            query.executeUpdate();
            
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
