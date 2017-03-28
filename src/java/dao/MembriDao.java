/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Categoria;
import pojo.Membro;

/**
 *
 * @author FSEVERI\parlato2889
 */
public class MembriDao
{
    
    public static Membro checkLogin(String username, String password)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        List<Membro> membriList = session.createQuery("from Membro").list();
        for(Membro m : membriList)
        {
            if(username.equals(m.getUsername()) && password.equals(m.getPassword()))
            {
                return m;
            }
        }
        return null;
    }
    
    public static void register(String username, String password, String name, String surname, String mail){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Membro membro = new Membro(username, password, name, surname, mail);
        session.save(membro);

        session.getTransaction().commit();
        
        
    }
}
