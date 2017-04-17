/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
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
import java.util.Objects;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Artista;
import pojo.Categoria;
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
    
    public static void addEvento(String titolo, String luogo, String data, Integer categoria, String descrizione){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Evento evento = new Evento(titolo, luogo, new Date(), descrizione, categoria);
        
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
    
    public static void removeEvento(Integer id)
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

    public static void setImmagine(Integer id, String path)
    {
        try
        {
            Connection conn = DriverManager.getConnection(HibernateUtil.HOST, HibernateUtil.USERNAME, HibernateUtil.PASSWORD);
            conn.setAutoCommit(false);
            
            File file = new File(path);
            
            PreparedStatement statement = conn.prepareStatement("UPDATE Eventi SET Immagine = ? WHERE Id = ?");
            statement.setBinaryStream(1, new FileInputStream(file), (int) file.length());
            statement.setInt(2, id);
            statement.executeUpdate();
            
            conn.commit();
        }
        catch(FileNotFoundException | SQLException ex)
        {
           // 
        }
    }

    public static void updateEvento(Integer id, String titolo, String luogo, String data, Integer categoria, String descrizione)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
                       
        try
        {
            transaction = session.beginTransaction();
            
            Evento evento = (Evento) session.get(Evento.class, id);
            
            evento.setTitolo(titolo);
            evento.setLuogo(luogo);
            evento.setData(new Date());
            evento.setCategoria(CategorieDao.retrieveSingle(id));
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

    public static void removeArtistaEvento(int eventID, int artistID)
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

    public static void addArtistaEvento(int eventoID, List<Integer> artists)
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
                artistsList.add(ArtistiDao.retrieveSingle(a));
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
