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
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Categoria;
import pojo.Evento;

/**
 *
 * @author alb07y
 */
public class CategorieDao {
    
    public static List<Categoria> retrieveAll()
    {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Categoria.class).list();
    }
    
    public static Categoria retrieveSingle(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        return (Categoria) session.get(Categoria.class, id);
    }
    
    
    public static void addCategoria (String nome){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Categoria categoria = new Categoria(nome);
        
        try{
            transaction = session.beginTransaction();
            
            session.save(categoria);
            
            transaction.commit();
        }catch(HibernateException e){
            transaction.rollback();
            e.printStackTrace();
        }finally{
            session.close();
        }
    }

    public static void setImmagine(int id, String path)
    {
        try
        {
            Connection conn = DriverManager.getConnection(HibernateUtil.HOST, HibernateUtil.USERNAME, HibernateUtil.PASSWORD);
            conn.setAutoCommit(false);
            
            File file = new File(path);
            
            PreparedStatement statement = conn.prepareStatement("UPDATE Categorie SET Immagine = ? WHERE Id = ?");
            statement.setBinaryStream(1, new FileInputStream(file), (int) file.length());
            statement.setInt(2, id);
            statement.executeUpdate();
            
            conn.commit();
        }
        catch(FileNotFoundException | SQLException ex)
        {
            ex.printStackTrace();
        }
    }

    public static void updateEvento(int id, String nome)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
                       
        try
        {
            transaction = session.beginTransaction();
            
            Categoria categoria = (Categoria) session.get(Categoria.class, id);
            
            categoria.setNome(nome);
            
            session.update(categoria);

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
    
    public static void removeCategoria(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        String hql;
        Query query;
        
        try
        {
            transaction = session.beginTransaction();
            
            Categoria categoria = (Categoria) session.get(Categoria.class, id);
            List<Evento> eventi = categoria.getEventoList();
            for(Evento evento : eventi)
            {
                hql = "DELETE FROM Post WHERE evento = :eventID";
                query = session.createQuery(hql);
                query.setInteger("eventID", evento.getId());
                query.executeUpdate();
            }
            
            hql = "DELETE FROM Evento WHERE categoria = :categoryID";
            query = session.createQuery(hql);
            query.setInteger("categoryID", id);
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
