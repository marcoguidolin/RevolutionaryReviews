/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
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
}
