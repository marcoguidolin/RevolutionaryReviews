package spring;


import CRUD.CRUD;
import POJO.*;
import DAO.*;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import org.springframework.stereotype.Controller;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.method;
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
     

