package spring;


import CRUD.CRUD;
import POJO.*;

import DAO.*;

import java.util.Date;

import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Classe MainController
 *
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

        map.put("listEventiPassati", crud.leggiEventiPassati());
        map.put("listEventiScadenza", crud.leggiEventiScadenza());
        map.put("listEVotati", crud.ListaEventiPiuVotati());
        



        /**
          map.put("recUt",crud.recensioniUtente(3));
        Recensioni r = new Recensioni();
        r.setCommento("Bellissimissimerrimo");
        Eventi e = crud.leggiDettagliEvento(13);
        Followers f = crud.leggiFollower(5);
        r.setUtente(f);
        r.setEvento(e);
        r.setVotoEvento(5);
        map.put("e",crud.aggiungiRecensione(r));
        */
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
    
    @RequestMapping(value="/followers", method=RequestMethod.GET)
    public String leggiFollowers(ModelMap map){
        List<Followers> lista=crud.ListaUtenti();
        map.put("followers",lista);
        
        return "followers";
    }
   
    //SE QUALCUNO E' IN GRADO DI SISTEMARE QUESTO INSERIMENTO IO POI MI POSSO OCCUPARE DEGLI ALTRI
    /**
    public @ResponseBody
    @RequestMapping(value = "/nuovoEvento", method = RequestMethod.GET)
    public @ResponseBody
    String aggiungiEvento(ModelMap map, 
            @RequestParam(value = "Titolo") String titolo,
            @RequestParam(value = "Data") Date data,
            @RequestParam(value = "Programma") String programma,
            @RequestParam(value = "Descrizione") String descrizione,
            @RequestParam(value = "Durata") Integer durata,
            @RequestParam(value = "Sponsor") String sponsor,
            @RequestParam(value = "Social1") String social1,
            @RequestParam(value = "Social2") String social2,
            @RequestParam(value = "Biglietti") String biglietti) {

        return aggiungiEvento(titolo, data, programma, descrizione, durata, sponsor, social1, social2, biglietti);
    }

    
    @RequestMapping(value = "/registrazione", method = RequestMethod.GET)
    public String registrazione(ModelMap map)
    {

        return "registrazione";
    }
    
    @RequestMapping(value = "/faiRegistrazione",
            params
            =
            {
                "nick", "pw", "nome", "cog", "pro", "email", "icon" 
            }, method = RequestMethod.POST)
    public String faiRegistrazione(ModelMap map, HttpServletRequest request, @RequestParam(value = "nick") String nickname, @RequestParam(value = "pw") String password, @RequestParam(value = "nome") String nome, @RequestParam(value = "cog") String cognome, @RequestParam(value = "pro") String provincia, @RequestParam(value = "email") String email, @RequestParam(value = "icon") String icona)
    {
        Followers user = FollowersD.registrazione(nickname, password, nome, cognome, provincia, email, icona);
        request.getSession().setAttribute("userinfo", user);
        return "redirect:selectInterests";
    }
    
}
     


    */
     
} 

