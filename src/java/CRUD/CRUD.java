/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package CRUD;

import POJO.*;
import java.util.List;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.hibernate.Transaction;
import spring.HibernateUtil;

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
     * @return gli eventi passati
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

    * Ritorna una lista contenente le recensioni scritte da un dato Follower
    * @param id id dell'utente di cui si vigliono selezionare le recensioni
    * @return una lista contenente le recensioni scritte da un dato Follower
    */
   public List<Recensioni> recensioniUtente(int id) {
     * Metodo per visualizzare tutte le recensioni fatte da un utente
     * @param id identificativo del follower
     * @return tutte le recensioni fatte dall'utente
     */
    public Recensioni ListRecensioniUtente(Integer id) {
        Session sessione=factory.openSession();
        Transaction transazione=null;
        try{
            transazione=sessione.beginTransaction();
            

            List<Recensioni> f=sessione.createQuery("FROM Recensioni where utente ="+id).list();
        
            transazione.commit();
            return f;

            Recensioni r = (Recensioni) sessione.get(Recensioni.class, id);
                        
            transazione.commit();
            return r;


        }catch(HibernateException e){
            if(transazione!=null) transazione.rollback();
        }finally{
            sessione.close();
        }
        return null;
    }
    
    /**
     * Metodo che cerca e stampa gli eventi più votati
     * @return 
     */
    public List ListaEventiPiuVotati() {
        Session sessione=factory.openSession();
        Transaction transazione=null;
        try{
            transazione=sessione.beginTransaction();
            
            List e=sessione.createQuery("SELECT EVENTI.Id, VISTAVOTI.Media FROM EVENTI, VISTAVOTI WHERE EVENTI.Id=VISTAVOTI.Id AND VISTAVOTI.Media > (SELECT AVG(Media) FROM VISTAVOTI)").list();
            
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
    
    

