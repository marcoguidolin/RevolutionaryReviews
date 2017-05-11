package spring;


import CRUD.CRUD;
import POJO.*;
import java.util.List;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

/**
 * Classe MainController
 *
 * @author 
 */
@Controller
public class MainController
{
    CRUD crud = new CRUD(HibernateUtil.getSessionFactory());
    
    public MainController()
    {
        //
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map)
    {        

        /*map.put("listEventiPassati", crud.leggiEventiPassati());
        map.put("listEventiScadenza", crud.leggiEventiScadenza());
        map.put("listFollowers", crud.ListaUtenti());
        map.put("listEVotati", crud.ListaEventiPiuVotati());
        


       map.put("recUt",crud.recensioniUtente(3));*/
        Recensioni r = new Recensioni();
        r.setCommento("Bellissimissimerrimo");
        Eventi e = crud.leggiDettagliEvento(13);
        Followers f = crud.leggiFollower(5);
        r.setUtente(f);
        r.setEvento(e);
        r.setVotoEvento(5);
        map.put("e",crud.aggiungiRecensione(r));

       //map.put("recUt",crud.recensioniUtente(3));


        return "index";
    }
    
    @RequestMapping(value = "/dettagliEvento", params = { "id" }, method = RequestMethod.GET)
    public String dettaglioEvento(ModelMap map, @RequestParam(value = "id") Integer id)
    {
        Eventi e = crud.leggiDettagliEvento(id);
        map.put("recensioni",crud.recensioniEvento(id));
        map.put("dettagli", e);
        
        return "dettagliEvento";
    }
    
    @RequestMapping(value="/listaFollower", method=RequestMethod.GET)
    public String ListaFollower(ModelMap map){
        List<Followers> lista=crud.ListaUtenti();
        map.put("lista",lista);
        
        return "listaFollower";
    }
    
    @RequestMapping(value = "/nuovoEvento", method = RequestMethod.GET)
    public String nuovoEvento(ModelMap map)
    {

        return "nuovoEvento";
    }
    
     
}
