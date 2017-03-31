package spring;

import utils.FTPUtil;
import dao.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
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
    
    @RequestMapping(value = "/selectInterests", method = RequestMethod.GET)
    public String selectInterests(ModelMap map, HttpServletRequest request)
    {
        request.setAttribute("categoriesList", CategorieDao.retrieveAll());
        return "selectInterests";
    }
    
    @RequestMapping(value = "/doInterests",
            params
            =
            {
                "categories"
            }, method = RequestMethod.POST)
    public String doInterests(ModelMap map, HttpServletRequest request, @RequestParam(value = "categories") List<Integer> categories)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        MembriDao.setInterests(categories, user.getUsername());
        request.setAttribute("categoriaList", CategorieDao.retrieveAll());
        return "redirect:profile";
    }

    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap map)
    {
        return "profile";
    }

    @RequestMapping(value = "/doRegistration",
            params
            =
            {
                "username", "password", "passwordCheck", "name", "surname", "mail"
            }, method = RequestMethod.POST)
    public String doRegistration(ModelMap map, HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "passwordCheck") String passwordCheck, @RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname, @RequestParam(value = "mail") String mail)
    {
        if (password.equals(passwordCheck))
        {
            Membro user = MembriDao.register(username, password, name, surname, mail);
            request.getSession().setAttribute("userinfo", user);
        } else
        {
            request.setAttribute("error", true);
            request.setAttribute("messageError", "Le password che hai inserito non coincidono. Verifica i dati inseriti e riprova.");
            return registration(map);
        }
        return "redirect:selectInterests";
    }

    @RequestMapping(value = "/doLogin", params
            =
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

    @RequestMapping(value = "/doRemove", method = RequestMethod.GET)
    public String doRemove(ModelMap map, HttpServletRequest request)
    {
        HttpSession session = request.getSession();
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        MembriDao.remove(user.getUsername());
        session.setAttribute("username", null);
        session.invalidate();
        return "redirect:./";
    }

    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categories(ModelMap map, HttpServletRequest request)
    {
        List<Categoria> categoriaList = CategorieDao.retrieveAll();
        request.setAttribute("catList", categoriaList);
        return "categories";
    }

    @RequestMapping(value = "/events", params =
    {
        "category"
    },
            method = RequestMethod.GET)
    public String events(ModelMap map, HttpServletRequest request, @RequestParam(value = "category") String category)
    {
        if ((category != null) && (!category.equals("0")))
        {
            List<Evento> eventoList = EventiDao.retrieveByCat(category);
            request.setAttribute("eventList", eventoList);
        } else
        {
            List<Evento> eventoList = EventiDao.retrieveAll();
            request.setAttribute("eventList", eventoList);
        }
        return "events";
    }

    @RequestMapping(value = "/eventDetail",
            params
            =
            {
                "id"
            },
            method = RequestMethod.GET)
    public String eventDetail(ModelMap map, HttpServletRequest request, @RequestParam(value = "id") String id)
    {
        Evento evento = EventiDao.retrieveSingle(id);
        request.setAttribute("eventDetail", evento);
        Integer idInt = Integer.parseInt(id);
        List<Post> postList = PostDao.retrieveByEvent(idInt);
        request.setAttribute("postList", postList);
        return "eventDetail";
    }
    
    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    String uploadFileHandler(ModelMap map, HttpServletRequest request, @RequestParam("file") MultipartFile file)
    {
        if (!file.isEmpty())
        {
            try
            {
                byte[] bytes = file.getBytes();

                // Creating the directory to store file
                String rootPath = System.getProperty("user.home");
                File dir = new File(rootPath + File.separator + "temp");
                if (!dir.exists())
                {
                    dir.mkdirs();
                }

                // Create the file on server
                File tempFile = new File(dir.getAbsolutePath() + File.separator + file.getOriginalFilename().hashCode());
                BufferedOutputStream stream = new BufferedOutputStream(new FileOutputStream(tempFile));
                stream.write(bytes);
                stream.close();

                System.out.print(tempFile.getAbsolutePath());

                FTPUtil.upload(tempFile.getAbsolutePath(), tempFile.getName());
            } catch (IOException ex)
            {
                Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
            }
        } else
        {
            //
        }
        return "redirect:profile";
    }
}
