package spring;


import CRUD.CRUD;
import POJO.Eventi;
import POJO.Followers;
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
        map.put("listEventiPassati", crud.leggiEventiPassati());
        map.put("listEventiScadenza", crud.leggiEventiScadenza());
        map.put("listFollowers", crud.ListaUtenti());
        map.put("listEVotati", crud.ListaEventiPiuVotati());
        
        return "index";
    }
    
    @RequestMapping(value = "/dettagliEvento", params = { "id" }, method = RequestMethod.GET)
    public String dettaglioEvento(ModelMap map, @RequestParam(value = "id") Integer id)
    {
        Eventi e = crud.leggiDettagliEvento(id);
        map.put("dettagli", e);
        
        return "dettagliEvento";
    }
    
    @RequestMapping(value="/listaFollower", method=RequestMethod.GET)
    public String ListaFollower(ModelMap map){
        List<Followers> lista=crud.ListaUtenti();
        map.put("lista",lista);
        
        return "listaFollower";
    }
    
    @RequestMapping(value="/listEventiScandenza", method=RequestMethod.GET)
    public String ListEventiScadenza(ModelMap map){
        List<Eventi> listaEScadenza=crud.leggiEventiScadenza();
        
        map.put("listEventiScandeza",listaEScadenza);
        
        return "lista";
    }
    

    
}
