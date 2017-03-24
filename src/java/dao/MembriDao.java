/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import pojo.Membri;

/**
 *
 * @author FSEVERI\parlato2889
 */
public class MembriDao
{
    
    public static List<Membri> retrieveAll()
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Membri> membriList = session.createQuery("from Membri").list();
        
        return membriList;
    }
    
    
}
