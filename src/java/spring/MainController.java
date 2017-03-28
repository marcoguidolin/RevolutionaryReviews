package spring;

import dao.CategorieDao;
import dao.MembriDao;
import java.util.List;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import pojo.*;

/**
 * Classe MainController
 *
 * @author Matteo Parlato
 */
@Controller
public class MainController
{

    public MainController()
    {
        //
    }

    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map)
    {
        return "index";
    }
    
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap map, HttpServletRequest request)
    {
        CategorieDao mapping = new CategorieDao();
        List<Categoria> categoriaList = CategorieDao.retrieveAll();
        request.setAttribute("categoriaList", categoriaList);
        return "registration";
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap map)
    {
        return "profile";
    }
    
    @RequestMapping(value = "/doRegistration",
            params = {
                "username", "password", "rePassword", "name", "surname", "mail", "cat"
            }, method = RequestMethod.POST)
    public String doRegistration(ModelMap map, HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "rePassword") String rePassword, @RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname, @RequestParam(value = "mail") String mail, @RequestParam(value = "cat") List<Integer> cat)
    {
        if(password.equals(rePassword)){
            MembriDao.register(username, password, name, surname, mail, cat);
        }else{
            return registration(map, request);
        }
        return "index";
    }
    
    @RequestMapping(value = "/doLogin", params =
    {
        "username", "password"
    }, method = RequestMethod.POST)
    public String doLogin(ModelMap map, HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password)
    {
        Membro user = MembriDao.checkLogin(username, password);
        if (user != null)
        {
            request.getSession().setAttribute("userinfo", user);
            return "redirect:profile";
        } else
        {
            return "redirect:registration";
        }
    }
    
    @RequestMapping(value = "/doLogout", method = RequestMethod.GET)
    public String doLogout(ModelMap map, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        session.setAttribute("username", null);
        session.invalidate();
        return "redirect:./";
    }
}
