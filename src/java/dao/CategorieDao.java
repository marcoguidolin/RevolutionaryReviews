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


public class CategorieDao{
    
    /**
     * 
     * @return 
     */
    public static List<Categoria> infoCategorie()
    {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Categoria.class).list();
    }
    
    /**
     * 
     * @param id
     * @return 
     */
    public static Categoria infoCategoria(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        return (Categoria) session.get(Categoria.class, id);
    }
    
    /**
     * 
     * @param nome 
     */
    public static void  aggiungi(String nome){
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

    /**
     * 
     * @param id
     * @param nome 
     */
    public static void aggiorna(int id, String nome)
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
}
