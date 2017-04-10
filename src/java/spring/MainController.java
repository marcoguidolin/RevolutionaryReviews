package spring;

import pojo.Artista;
import pojo.Post;
import pojo.Evento;
import pojo.Categoria;
import pojo.Membro;
import utils.FTPUtils;
import dao.*;
import java.io.*;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import utils.MailUtils;

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

    // <editor-fold defaultstate="collapsed" desc="/">
    @RequestMapping(value = "/", method = RequestMethod.GET)
    public String index(ModelMap map)
    {
        return "index";
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Front-end">
    // <editor-fold defaultstate="collapsed" desc="Registrazione">
    @RequestMapping(value = "/registration", method = RequestMethod.GET)
    public String registration(ModelMap map)
    {
        return "registration";
    }

    @RequestMapping(value = "/doRegistration",
            params
            =
            {
                "username", "password", "name", "surname", "mail"
            }, method = RequestMethod.POST)
    public String doRegistration(ModelMap map, HttpServletRequest request, @RequestParam(value = "username") String username, @RequestParam(value = "password") String password, @RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname, @RequestParam(value = "mail") String mail)
    {
        Membro user = MembriDao.register(username, password, name, surname, mail);
        request.getSession().setAttribute("userinfo", user);
        return "redirect:selectInterests";
    }

    @RequestMapping(value = "/selectInterests", method = RequestMethod.GET)
    public String selectInterests(ModelMap map, HttpServletRequest request)
    {
        map.put("categoriesList", CategorieDao.retrieveAll());
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
        user = MembriDao.setInterests(categories, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Login e Logout">
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
            System.out.println(user);
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Profilo">
    @RequestMapping(value = "/profile", method = RequestMethod.GET)
    public String profile(ModelMap map, HttpServletRequest request)
    {
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "profile";
    }

    @RequestMapping(value = "/doRemove", method = RequestMethod.GET)
    public String doRemove(ModelMap map, HttpServletRequest request)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        MembriDao.remove(user.getUsername());
        HttpSession session = request.getSession();
        session.setAttribute("username", null);
        session.invalidate();
        return "redirect:./";
    }

    @RequestMapping(value = "/doChangePassword",
            params
            =
            {
                "password"
            }, method = RequestMethod.POST)
    public String doChangePassword(ModelMap map, HttpServletRequest request, @RequestParam(value = "password") String password)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        user = MembriDao.changePassword(password, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
    }

    @RequestMapping(value = "/doChangePersonalInformations",
            params
            =
            {
                "name",
                "surname",
                "mail"
            }, method = RequestMethod.POST)
    public String doChangePersonalInformations(ModelMap map, HttpServletRequest request, @RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname, @RequestParam(value = "mail") String mail)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        user = MembriDao.updateProfileInformations(name, surname, mail, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
    }
    
    @RequestMapping(value = "/deleteInterest",
            params
            =
            {
                "id"
            }, method = RequestMethod.GET)
    public String deleteInterest(ModelMap map, HttpServletRequest request, @RequestParam(value = "id") Integer id)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        user = MembriDao.deleteInterest(id, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
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

                FTPUtils.upload(tempFile.getAbsolutePath(), tempFile.getName());

                Membro user = (Membro) request.getSession().getAttribute("userinfo");

                user = MembriDao.setAvatar(user.getUsername(), "http://webcommunityproject.altervista.org/" + tempFile.getName());

                request.getSession().setAttribute("userinfo", user);
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

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Categorie">
    @RequestMapping(value = "/categories", method = RequestMethod.GET)
    public String categories(ModelMap map, HttpServletRequest request)
    {
        List<Categoria> categoriaList = CategorieDao.retrieveAll();
        request.setAttribute("catList", categoriaList);
        return "categories";
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Artisti">
    @RequestMapping(value = "/artists", method = RequestMethod.GET)
    public String artists(ModelMap map, HttpServletRequest request)
    {
        List<Artista> artistiList = ArtistiDao.retrieveAll();
        request.setAttribute("artistsList", artistiList);
        return "artists";
    }

    // </editor-fold>
    
    // <editor-fold defaultstate="collapsed" desc="Eventi">
    @RequestMapping(value = "/events", params =
    {
        "category"
    },
            method = RequestMethod.GET)
    public String events(ModelMap map, HttpServletRequest request, @RequestParam(value = "category") String category)
    {
        if (!category.equals("0"))
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

    @RequestMapping(value = "/events",
            method = RequestMethod.GET)
    public String events(ModelMap map, HttpServletRequest request)
    {
        List<Evento> eventoList = EventiDao.retrieveAll();
        request.setAttribute("eventList", eventoList);

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
        System.out.println("Provaasdf");
        Evento evento = EventiDao.retrieveSingle(id);
        request.setAttribute("eventDetail", evento);
        Integer idInt = Integer.parseInt(id);
        List<Post> postList = PostDao.retrieveByEvent(idInt);
        request.setAttribute("postList", postList);
        return "eventDetail";
    }

    @RequestMapping(value = "/commento", params =
    {
        "comm",
        "evento",
        "voto"
    },
            method = RequestMethod.GET)
    public String commento(ModelMap map, HttpServletRequest request, @RequestParam(value = "comm") String comm, @RequestParam(value = "evento") String evento, @RequestParam(value = "voto") String voto)
    {

        System.out.println("Prova");

        Membro user = (Membro) request.getSession().getAttribute("userinfo");

        Integer eventoI = Integer.parseInt(evento);
        Integer votoI = Integer.parseInt(voto);

        PostDao.addPost(comm, votoI, eventoI, user.getUsername());

        return "redirect:events";
    }
    // </editor-fold>
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Back-end">
    @RequestMapping(value = "/administration", method = RequestMethod.GET)
    public String administration(ModelMap map)
    {
        return "administration";
    }

    @RequestMapping(value = "/administrationNewsletter", method = RequestMethod.GET)
    public String administrationNewsletter(ModelMap map, HttpServletRequest request)
    {
        map.put("usersList", MembriDao.retrieveAll());
        return "administrationNewsletter";
    }
    
    @RequestMapping(value = "/administrationUsers", method = RequestMethod.GET)
    public String administrationUsers(ModelMap map)
    {
        return "administrationUsers";
    }
    
    @RequestMapping(value = "/administrationCategories", method = RequestMethod.GET)
    public String administrationCategories(ModelMap map)
    {
        return "administrationCategories";
    }
    
    @RequestMapping(value = "/administrationEvents", method = RequestMethod.GET)
    public String administrationEvents(ModelMap map)
    {
        return "administrationEvents";
    }
    
    @RequestMapping(value = "/administrationArtists", method = RequestMethod.GET)
    public String administrationArtists(ModelMap map)
    {
        return "administrationArtists";
    }

    @RequestMapping(value = "/sendNewsletters",
            params =
            {
                "messageObject",
                "messageBody"
            }, method = RequestMethod.POST)
    public String sendNewsletters(ModelMap map, @RequestParam("messageObject") String messageObject, @RequestParam("messageBody") String messageBody)
    {
        List<Membro> list = MembriDao.retrieveAll();
        try
        {
            for(Membro m : list)
            {
                MailUtils.Send(m.getMail(), messageObject, messageBody);
            }
        } catch (MessagingException ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        return "redirect:administrationNewsletter";
    }
    // </editor-fold>

}
