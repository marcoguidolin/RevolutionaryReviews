/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.Session;
import pojo.Post;

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
}
