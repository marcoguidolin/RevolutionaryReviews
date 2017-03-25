package spring;

import dao.MembriDao;
import javax.servlet.http.HttpServletRequest;
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
    public String registration(ModelMap map)
    {
        return "registration";
    }
    
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap map)
    {
        return "profile";
    }
    
    @RequestMapping(value = "/doRegistration", method = RequestMethod.GET)
    public String doRegistration(ModelMap map)
    {
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
