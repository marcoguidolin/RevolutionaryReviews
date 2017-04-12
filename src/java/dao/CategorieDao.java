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
import pojo.Artista;
import pojo.Categoria;
import pojo.Membro;
import pojo.Post;

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
        
        if(immagine==null) 
            immagine="/WebCommunity/resources/ticket.png";
        
        Categoria categoria = new Categoria(nome, immagine);
        
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
    
    public static void remove(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try
        {
            transaction = session.beginTransaction();

            Artista artista = (Artista) session.get(Artista.class, id);
            session.delete(artista);
            
            List<Post> list = session.createCriteria(Post.class).list();
            for(Post p : list)
            {
                if(p.getId().equals(id))
                {
                    session.delete(p);
                }
            }

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
