/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.Query;
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
    
    public static void register(String username, String password, String name, String surname, String mail, List<Integer> cat){
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        Membro membro = new Membro(username, password, name, surname, mail);
        List<Categoria> catList = session.createQuery("from Categoria").list();
        List<Categoria> newList;
        newList = new ArrayList<>();
        for(int i = 0; i<catList.size(); i++){
            for(int j = 0; j<cat.size(); j++){
                if(Objects.equals(catList.get(i).getId(), cat.get(j)))
                {
                    newList.add(catList.get(i));
                    cat.remove(j);
                }
            }
        }
        membro.setCategoriaList(newList);
        session.save(membro);

        session.getTransaction().commit();
        
        
    }
    
    public static void remove(Membro m){
        Session session = HibernateUtil.getSessionFactory().openSession();
        session.beginTransaction();
        
        List<Categoria> catList = session.createQuery("from Categoria c where c.membroList = '" + m + "'").list();
        
        
        
        session.getTransaction().commit();
    }
    
    
}
