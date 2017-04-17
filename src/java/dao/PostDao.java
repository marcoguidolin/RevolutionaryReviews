/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Membro;
import pojo.Post;
import pojo.PostPK;

/**
 *
 * @author alb07y
 */
public class PostDao {
    public static List<Post> retrieveByEvent(Integer id)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Post> postList = session.createQuery("from Post").list();
        List<Post> newList = new ArrayList<>();
        for(Post p : postList)
        {
            if(p.getPostPK().getEvento() == id){
                newList.add(p);
            }
        }
        return newList;
    }
    
    public static Membro addPost(String commento, Integer voto, Integer evento, String membro){
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Membro m = null;
        
        try
        {
            transaction = session.beginTransaction();
            
            session.save(new Post(new PostPK(membro, evento), commento, voto));

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            m = (Membro) session.get(Membro.class, membro);
            session.close();
        }
        
        return m;
    }
}
