/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import java.util.List;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import pojo.Membri;

/**
 *
 * @author FSEVERI\parlato2889
 */
public class CRUDMembri
{
    private static SessionFactory factory;
    
    public CRUDMembri(SessionFactory factory)
    {
        CRUDMembri.factory = factory;
    }
    
    public static List<Membri> retrieveAll()
    {
        Session session = factory.openSession();
        
        List<Membri> membriList = session.createQuery("from Membri").list();
        
        return membriList;
    }
    
}
