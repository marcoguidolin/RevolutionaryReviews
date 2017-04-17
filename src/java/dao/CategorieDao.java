/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Categoria;

/**
 *
 * @author alb07y
 */
public class CategorieDao {
    
    public static List<Categoria> retrieveAll()
    {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Categoria.class).list();
    }
    
    
    public static void addCategorie (String nome, String immagine){
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
    
    
    
}
