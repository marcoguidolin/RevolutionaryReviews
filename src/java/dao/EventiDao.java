package dao;

import hibernate.HibernateUtil;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Artista;
import pojo.Evento;
import pojo.Membro;

public class EventiDao {
    
    /**
     * 
     * @param categoria
     * @return 
     */
    public static List<Evento> infoPerCategoria(String categoria)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Evento> eventoList = session.createQuery("from Evento e where e.categoria = " + categoria).list();
        return eventoList;
    }
    
    /**
     * 
     * @param location
     * @return 
     */
    public static List<Evento> infoPerLocation(String location){
        Session session = HibernateUtil.getSessionFactory().openSession();
        List<Evento> eventoList = session.createSQLQuery("SELECT * FROM EVENTI WHERE Location = 'Padova'").list();
        return eventoList;
    }
    
    /**
     * 
     * @return 
     */
    public static List<Evento> ordinaPerData() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Evento E ORDER BY E.data DESC";
        Query query = session.createQuery(hql);
        return (List<Evento>) query.list();
    }
    
    /**
     * 
     * @return 
     */
    public static List<Evento> ordinaPerTitolo() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Evento E ORDER BY E.titolo ASC";
        Query query = session.createQuery(hql);
        return (List<Evento>) query.list();
    }
    
    /**
     * 
     * @return 
     */
    public static List<Evento> ordinaPerCategoria() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        String hql = "FROM Evento E ORDER BY E.categoria ASC";
        Query query = session.createQuery(hql);
        return (List<Evento>) query.list();
    }

    /**
     * 
     * @return 
     */
    public static List<Evento> infoEventi() {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Evento> eventoList = session.createQuery("from Evento").list();
        
        return eventoList;
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public static Evento infoEvento(String id) {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Evento> eventi = session.createQuery("from Evento e where e.id = " + id).list();
        
        for(Evento e : eventi){
            return e;
        }
        return null;
    }

    /**
     * 
     * @param titolo
     * @param luogo
     * @param data
     * @param categoria
     * @param descrizione
     * @param promotore 
     */
    public static void aggiungi(String titolo, String luogo, String data, Integer categoria, String descrizione, String promotore){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        Evento evento = new Evento(titolo, luogo, new Date(), descrizione, categoria, (Membro)session.get(Membro.class, promotore));
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
    
    public static void rimuovi(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            String hql = "DELETE FROM Post WHERE evento = :eventID";
            Query query = session.createQuery(hql);
            query.setParameter("eventID", id);
            query.executeUpdate();
            hql = "DELETE FROM Evento WHERE id = :eventID";
            query = session.createQuery(hql);
            query.setParameter("eventID", id);
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

    public static void aggiorna(Integer id, String titolo, String location, String data, Integer categoria, String descrizione)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;                      
        try
        {
            transaction = session.beginTransaction();            
            Evento evento = (Evento) session.get(Evento.class, id);
                        evento.setTitolo(titolo);
            evento.setLuogo(location);
            evento.setData(new Date());
            evento.setCategoria(CategorieDao.infoCategoria(categoria));
            evento.setDescrizione(descrizione);
            session.update(evento);
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

    public static void rimuoviArtistaEvento(int eventID, int artistID)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;        
        try
        {
            transaction = session.beginTransaction();
            Evento evento = (Evento) session.get(Evento.class, eventID);
            List<Artista> list = evento.getArtistaList();
            for(Artista a : list)
            {
                if(a.getId() == artistID)
                {
                    list.remove(a);
                    break;
                }
            }            
            evento.setArtistaList(list);
            session.update(evento);
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

    public static void aggiungiArtistaEvento(int eventoID, List<Integer> artists)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        try
        {
            transaction = session.beginTransaction();
            Evento evento = (Evento) session.get(Evento.class, eventoID);
            List<Artista> artistsList = new ArrayList<>();
            for(Integer a : artists)
            {
                artistsList.add(ArtistiDao.infoArtista(a));
            }
            evento.setArtistaList(artistsList);
            session.update(evento);
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
