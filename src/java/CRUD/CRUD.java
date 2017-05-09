/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import POJO.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;

/**
 *
 * @author FSEVERI\guidolin3172
 */
public class CRUD {
    
    //Varibili di istanza
    private static SessionFactory factory;
    
    /**
     * Costruttore parametrico completo
     * @param factory 
     */
    public CRUD(SessionFactory factory) {
        CRUD.factory=factory;
    }
    
    /**
     * Metodo per leggere gli eventi passati
     * @return la lista degli eventi passati
     */
    public List leggiEventiPassati(){
        Session sessione=factory.openSession();
        Transaction transazione=null;
        try{
            transazione=sessione.beginTransaction();
            List eventiPassati=sessione.createQuery("FROM Eventi WHERE data < CURRENT_DATE").list();
            transazione.commit();
            
            return eventiPassati;
        }catch(HibernateException e){
            if(transazione!=null) transazione.rollback();
        }finally{
            sessione.close();
        }
        return null;
    }   

    /**
     * Metodo per leggere gli eventi in scadenza
     * @return la lista degli eventi in scadenza
     */
    public List<Eventi> leggiEventiScadenza(){
        Session sessione=factory.openSession();
        Transaction transazione=null;
        try{
            transazione=sessione.beginTransaction();
            
            List<Eventi> eventiScadenza=sessione.createCriteria(Eventi.class).list();
            
            DateTimeFormatter dtf = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            LocalDate localDate = LocalDate.now();
            
            
            List<Eventi> eventi = new ArrayList<>();
            for(Eventi e : eventiScadenza)
            {
                if(e.getData().toString().equals(dtf.format(localDate)))
                {
                    eventi.add(e);
                }
            }
            
            transazione.commit();
            
            return eventi;
        }catch(HibernateException e){
            if(transazione!=null) transazione.rollback();
        }finally{
            sessione.close();
        }
        return null;
    }   
    
    /**
     * Metodo per visualizzare i dettagli di un evento
     * @param id id dell'evento
     * @return le informozioni dell'evento
     */
    public Eventi leggiDettagliEvento(Integer id) {
        Session sessione=factory.openSession();
        Transaction transazione=null;
        try{
            transazione=sessione.beginTransaction();
            Eventi e = (Eventi) sessione.get(Eventi.class, id);
            transazione.commit();
            
            return e;
        }catch(HibernateException e){
            if(transazione!=null) transazione.rollback();
        }finally{
            sessione.close();
        }
        return null;
    }
    
    /**
     * Metodo per visualizzare tutti i followers
     * @return la lista dei followers
     */
    public List ListaUtenti() {
        Session sessione=factory.openSession();
        Transaction transazione=null;
        try{
            transazione=sessione.beginTransaction();
            List f=sessione.createQuery("FROM Followers").list();
            transazione.commit();
            
            return f;
        }catch(HibernateException e){
            if(transazione!=null) transazione.rollback();
        }finally{
            sessione.close();
        }
        return null;
    }
    
    /**
     * Metodo che cerca e stampa gli eventi più votati
     * @param id Identificativo dell'evento
     * @return la lista degli eventi più votati
     */
    public List ListaEventiPiuVotati() {
        Session sessione=factory.openSession();
        Transaction transazione=null;
        try{
            transazione=sessione.beginTransaction();
            List e=sessione.createSQLQuery("SELECT E.Id, V.Media FROM EVENTI E, VISTAVOTI V WHERE E.Id=V.Id AND V.Media > (SELECT AVG(Media) FROM VISTAVOTI)").list();
            transazione.commit();
            
            return e;
        }catch(HibernateException e){
            if(transazione!=null) transazione.rollback();
        }finally{
            sessione.close();
        }
        return null;
    }    
}
