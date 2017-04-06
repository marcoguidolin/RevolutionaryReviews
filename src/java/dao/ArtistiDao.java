/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package dao;

import hibernate.HibernateUtil;
import java.util.List;
import pojo.Artista;

/**
 *
 * @author matte
 */
public class ArtistiDao
{
    public static List<Artista> retrieveAll()
    {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Artista.class).list();
    }
}
