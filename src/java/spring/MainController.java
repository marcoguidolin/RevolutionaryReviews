package spring;

import pojo.*;
import dao.*;
import java.io.IOException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.mail.MessagingException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.multipart.MultipartFile;
import utils.*;

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
        map.put("eventsList", EventiDao.retrieveAll());
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

    @RequestMapping(value = "/doChangePassword", method = RequestMethod.POST)
    public String doChangePassword(ModelMap map, HttpServletRequest request, @RequestParam(value = "password") String password)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        user = MembriDao.changePassword(password, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
    }

    @RequestMapping(value = "/doChangePersonalInformations", method = RequestMethod.POST)
    public String doChangePersonalInformations(ModelMap map, HttpServletRequest request, @RequestParam(value = "name") String name, @RequestParam(value = "surname") String surname, @RequestParam(value = "mail") String mail)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        user = MembriDao.updateProfileInformations(name, surname, mail, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
    }

    @RequestMapping(value = "/deleteInterest", method = RequestMethod.GET)
    public String deleteInterest(ModelMap map, HttpServletRequest request, @RequestParam(value = "id") Integer id)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        user = MembriDao.deleteInterest(id, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
    }

    @RequestMapping(value = "/deletePost", method = RequestMethod.GET)
    public String deletePost(ModelMap map, HttpServletRequest request, @RequestParam(value = "id") Integer id)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
        user = MembriDao.removePost(id, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        return "redirect:profile";
    }

    @RequestMapping(value = "/uploadFile", method = RequestMethod.POST)
    String uploadFileHandler(ModelMap map, HttpServletRequest request, @RequestParam("file") MultipartFile file)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");
                
        user = MembriDao.setAvatar(user.getUsername(), BlobUtils.createTempFile(file));

        request.getSession().setAttribute("userinfo", user);
        
        
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
    @RequestMapping(value = "/events",
            params
            =
            {
                "category",
                "name"
            }, method = RequestMethod.GET)
    public String events(ModelMap map, HttpServletRequest request, @RequestParam(value = "category") String categoryID, @RequestParam(value = "name") String categoryName)
    {
        if (!categoryID.equals("0"))
        {
            map.put("eventList", EventiDao.retrieveByCat(categoryID));
            map.put("categoryName", categoryName);
        } else
        {
            map.put("eventList", EventiDao.retrieveAll());
            map.put("categoryName", "Eventi");
        }
        return "events";
    }

    @RequestMapping(value = "/events", method = RequestMethod.GET)
    public String events(ModelMap map, HttpServletRequest request)
    {
        map.put("eventList", EventiDao.retrieveAll());
        map.put("categoryName", "Eventi");
        return "events";
    }

    @RequestMapping(value = "/eventDetail", method = RequestMethod.GET)
    public String eventDetail(ModelMap map, HttpServletRequest request, @RequestParam(value = "id") String id)
    {
        Evento evento = EventiDao.retrieveSingle(id);
        request.setAttribute("eventDetail", evento);
        Integer idInt = Integer.parseInt(id);
        List<Post> postList = PostDao.retrieveByEvent(idInt);
        request.setAttribute("postList", postList);
        return "eventDetail";
    }

    @RequestMapping(value = "/createEvent",
            method = RequestMethod.GET)
    public String createEvent(ModelMap map, HttpServletRequest request)
    {
        List<Categoria> catList = CategorieDao.retrieveAll();
        request.setAttribute("catList", catList);
        return "createEvent";
    }

    @RequestMapping(value = "/createEvent",
            params
            =
            {
                "id"
            },
            method = RequestMethod.GET)
    public String createEvent(ModelMap map, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "id") String id)
    {
        Evento evento = EventiDao.retrieveSingle(id);
        request.setAttribute("eventDetail", evento);
        Integer idInt = Integer.parseInt(id);
        List<Post> postList = PostDao.retrieveByEvent(idInt);
        request.setAttribute("postList", postList);
        return "events";
    }

    @RequestMapping(value = "/commento", params =
    {
        "comm",
        "evento",
        "voto"
    },
            method = RequestMethod.GET)
    public void commento(ModelMap map, HttpServletRequest request, HttpServletResponse response, @RequestParam(value = "comm") String comm, @RequestParam(value = "evento") String evento, @RequestParam(value = "voto") String voto)
    {
        Membro user = (Membro) request.getSession().getAttribute("userinfo");

        Integer eventoI = Integer.parseInt(evento);
        Integer votoI = Integer.parseInt(voto);

        user = PostDao.addPost(comm, votoI, eventoI, user.getUsername());
        request.getSession().setAttribute("userinfo", user);
        
        String referer = request.getHeader("Referer");

        try
        {
            response.sendRedirect(referer);
        } catch (IOException ex)
        {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    // </editor-fold>
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Back-end">
    // <editor-fold defaultstate="collapsed" desc="Amministrazione">
    @RequestMapping(value = "/administration", method = RequestMethod.GET)
    public String administration(ModelMap map)
    {
        return "administration";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Newsletter">
    @RequestMapping(value = "/administrationNewsletter", method = RequestMethod.GET)
    public String administrationNewsletter(ModelMap map, HttpServletRequest request)
    {
        map.put("usersList", MembriDao.retrieveAll());
        return "administrationNewsletter";
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
            for (Membro m : list)
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

    // <editor-fold defaultstate="collapsed" desc="Membri">
    @RequestMapping(value = "/administrationUsers", method = RequestMethod.GET)
    public String administrationUsers(ModelMap map)
    {
        List<Membro> membri = MembriDao.retrieveAll();
        map.put("usersList", membri);
        return "administrationUsers";
    }
    
    
    @RequestMapping(value = "/administrationRemoveUser", method = RequestMethod.GET)
    public String administrationRemoveUser(ModelMap map, @RequestParam("username") String username)
    {
        MembriDao.remove(username);
        map.put("eventsList", EventiDao.retrieveAll());
        return "redirect:administrationUsers";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Categorie">
    @RequestMapping(value = "/administrationCategories", method = RequestMethod.GET)
    public String administrationCategories(ModelMap map)
    {
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "administrationCategories";
    }
    
    @RequestMapping(value = "/administrationUploadCategoryImage", method = RequestMethod.POST)
    public String administrationUploadCategoryImage(ModelMap map, @RequestParam("categoryID") String id, @RequestParam("file") MultipartFile file)
    {
        if (file != null && ParseUtils.tryParseInt(id))
        {
            CategorieDao.setImmagine(Integer.parseInt(id), BlobUtils.createTempFile(file));
        }
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationCategories";
    }
    
    @RequestMapping(value = "/administrationUpdateCategory", method = RequestMethod.POST)
    public String administrationUpdateCategory(ModelMap map, @RequestParam("categoriaID") String id, @RequestParam("nome") String nome)
    {
        if (ParseUtils.tryParseInt(id))
        {
            CategorieDao.updateEvento(Integer.parseInt(id), nome);
        }
        map.put("eventsList", EventiDao.retrieveAll());
        return "redirect:administrationCategories";
    }
    
    @RequestMapping(value = "/administrationRemoveCategory", method = RequestMethod.GET)
    public String administrationRemoveCategory(ModelMap map, @RequestParam("id") String id)
    {
        if (ParseUtils.tryParseInt(id))
        {
            CategorieDao.removeCategoria(Integer.parseInt(id));
        }
        map.put("eventsList", EventiDao.retrieveAll());
        return "redirect:administrationCategories";
    }
    
    @RequestMapping(value = "/administrationAddCategory", method = RequestMethod.POST)
    public String administrationAddCategory(ModelMap map, @RequestParam("nome") String nome)
    {
        CategorieDao.addCategoria(nome);
        
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationCategories";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Eventi">
    @RequestMapping(value = "/administrationEvents", method = RequestMethod.GET)
    public String administrationEvents(ModelMap map)
    {
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "administrationEvents";
    }
    
    @RequestMapping(value = "/administrationRemoveEvent", method = RequestMethod.GET)
    public String administrationRemoveEvent(ModelMap map, @RequestParam("id") String id)
    {
        if (ParseUtils.tryParseInt(id))
        {
            EventiDao.removeEvento(Integer.parseInt(id));
        }
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationEvents";
    }
    
    @RequestMapping(value = "/administrationAddEvent", method = RequestMethod.POST)
    public String administrationAddEvent(ModelMap map, @RequestParam("titolo") String titolo, @RequestParam("luogo") String luogo, @RequestParam("data") String data, @RequestParam("categoria") String categoria, @RequestParam("descrizione") String descrizione)
    {
        if (ParseUtils.tryParseInt(categoria))
        {
            EventiDao.addEvento(titolo, luogo, data, Integer.parseInt(categoria), descrizione);
        }
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationEvents";
    }
    
    @RequestMapping(value = "/administrationUploadEventImage", method = RequestMethod.POST)
    public String administrationUploadEventImage(ModelMap map, @RequestParam("eventID") String id, @RequestParam("file") MultipartFile file)
    {
        if (file != null && ParseUtils.tryParseInt(id))
        {
            EventiDao.setImmagine(Integer.parseInt(id), BlobUtils.createTempFile(file));
        }
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationEvents";
    }
    
    @RequestMapping(value = "/administrationRemoveUserPostEvent", method = RequestMethod.GET)
    public String administrationRemoveUserPostEvent(ModelMap map, @RequestParam("eventID") String eventID, @RequestParam("userID") String userID)
    {
        if(ParseUtils.tryParseInt(eventID))
        {
            MembriDao.removePost(Integer.parseInt(eventID), userID);
        }
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationEvents";
    }
    
    @RequestMapping(value = "/administrationUpdateEvent", method = RequestMethod.POST)
    public String administrationUpdateEvent(ModelMap map, @RequestParam("eventoID") String id, @RequestParam("titolo") String titolo, @RequestParam("luogo") String luogo, @RequestParam("data") String data, @RequestParam("categoria") String categoria, @RequestParam("descrizione") String descrizione)
    {
        if (ParseUtils.tryParseInt(categoria) && ParseUtils.tryParseInt(id))
        {
            EventiDao.updateEvento(Integer.parseInt(id), titolo, luogo, data, Integer.parseInt(categoria), descrizione);
        }
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationEvents";
    }
    
    @RequestMapping(value = "/administrationRemoveArtistEvent", method = RequestMethod.GET)
    public String administrationRemoveArtistEvent(ModelMap map, @RequestParam("eventID") String eventoID, @RequestParam("artistID") String artistID)
    {
        if (ParseUtils.tryParseInt(eventoID) && ParseUtils.tryParseInt(artistID))
        {
            EventiDao.removeArtistaEvento(Integer.parseInt(eventoID), Integer.parseInt(artistID));
        }
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationEvents";
    }
    
    @RequestMapping(value = "/administrationUpdateArtists", method = RequestMethod.POST)
    public String administrationUpdateArtists(ModelMap map, @RequestParam("eventoID") String eventoID, @RequestParam(value = "artists") List<Integer> artists)
    {
        if (ParseUtils.tryParseInt(eventoID))
        {
            EventiDao.addArtistaEvento(Integer.parseInt(eventoID), artists);
        }
        map.put("eventsList", EventiDao.retrieveAll());
        map.put("artistsList", ArtistiDao.retrieveAll());
        map.put("categoriesList", CategorieDao.retrieveAll());
        return "redirect:administrationEvents";
    }
    // </editor-fold>

    // <editor-fold defaultstate="collapsed" desc="Artisti">
    @RequestMapping(value = "/administrationArtists", method = RequestMethod.GET)
    public String administrationArtists(ModelMap map, HttpServletRequest request)
    {
        List<Artista> artistiList = ArtistiDao.retrieveAll();
        request.setAttribute("artistsList", artistiList);
        return "administrationArtists";
    }
    
    @RequestMapping(value = "/doRemoveArtistAdmin", 
            params
            =
            {
                "id"
            },
        method = RequestMethod.GET)
    public String doRemoveArtistAdmin(ModelMap map,HttpServletRequest request, @RequestParam("id") String id)
    {
        Integer i = Integer.parseInt(id);
        ArtistiDao.remove(i);
        return "redirect:administrationArtists";
    }
// </editor-fold>

    // </editor-fold>
}
