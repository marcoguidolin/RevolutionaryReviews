
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Artista;


public class ArtistiDao
{
    /**
     * 
     * @return 
     */
    public static List<Artista> infoAristi()
    {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Artista.class).list();
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public static Artista infoArtista(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Artista) session.get(Artista.class, id);
    }
    
    /**
     * 
     * @param nome
     * @param cognome 
     */
    public static void aggiungi(String nome, String cognome){
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
    
    /**
     * 
     * @param id 
     */
    public static void cancella(Integer id)
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