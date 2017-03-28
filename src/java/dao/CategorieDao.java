/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import pojo.Categoria;

/**
 *
 * @author alb07y
 */
public class CategorieDao {
    
    public static List<Categoria> retrieveAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Categoria> categoriaList = session.createQuery("from Categoria").list();
        
        return categoriaList;
    }
}
