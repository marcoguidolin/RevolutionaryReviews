package dao;

import hibernate.HibernateUtil;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import org.hibernate.HibernateException;
import org.hibernate.Query;
import org.hibernate.Session;
import org.hibernate.Transaction;
import pojo.Categoria;
import pojo.Membro;
import pojo.Post;
import utils.SecurityUtils;

/**
 *
 * @author FSEVERI\parlato2889
 */
public class MembriDao
{

    public static Membro checkLogin(String username, String password)
    {
        password = SecurityUtils.getSha256(password);
        
        Session session = HibernateUtil.getSessionFactory().openSession();
        
        Membro registered = (Membro) session.get(Membro.class, username);

        if (registered != null && (username.equals(registered.getUsername()) && password.equals(registered.getPassword())))
        {
            return registered;
        }
        return null;
    }

    public static Membro setInterests(List<Integer> selectedCategories, String username)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Membro membro = null;
        
        try
        {
            transaction = session.beginTransaction();

            membro = (Membro) session.get(Membro.class, username);

            List<Categoria> categoriesList = session.createQuery("from Categoria").list();
            List<Categoria> newInterestsList = new ArrayList<>();
            for (int i = 0; i < categoriesList.size(); i++)
            {
                for (int j = 0; j < selectedCategories.size(); j++)
                {
                    if (Objects.equals(categoriesList.get(i).getId(), selectedCategories.get(j)))
                    {
                        newInterestsList.add(categoriesList.get(i));
                        selectedCategories.remove(j);
                    }
                }
            }
            membro.setCategoriaList(newInterestsList);
            session.update(membro);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        
        return membro;
    }

    public static Membro register(String username, String password, String name, String surname, String mail)
    {   
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        password = SecurityUtils.getSha256(password);
        
        Membro m = null;
        
        try
        {
            transaction = session.beginTransaction();

            m = new Membro(username, password, name, surname, mail);
            
            session.save(m);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }

        return m;
    }

    public static void remove(String username)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try
        {
            transaction = session.beginTransaction();

            Membro membro = (Membro) session.get(Membro.class, username);
            session.delete(membro);
            
            List<Post> list = session.createCriteria(Post.class).list();
            for(Post p : list)
            {
                if(p.getMembro1().equals(username))
                {
                    session.delete(p);
                }
            }

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
    }
    
    public static void removePost(Integer eventID, String username)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        try
        {
            transaction = session.beginTransaction();
            
//            List<Post> list = session.createCriteria(Post.class).list();
//            for(Post p : list)
//            {
//                System.out.println(p.getMembro1().getUsername());
//                System.out.println(username);
//                System.out.println(p.getEvento1().getId());
//                System.out.println(eventID);
//                if(p.getMembro1().getUsername().equals(username) && p.getEvento1().getId().equals(eventID))
//                {
//                    session.delete(p);
//                    break;
//                }
//            }

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
    }
    
    public static Membro setAvatar(String username, String path)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Membro membro = null;
        try
        {
            transaction = session.beginTransaction();

            membro = (Membro) session.get(Membro.class, username);
            membro.setAvatar(path);
            session.update(membro);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        return membro;
    }

    public static Membro changePassword(String password, String username)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        password = SecurityUtils.getSha256(password);
        Membro membro = null;
        
        try
        {
            transaction = session.beginTransaction();

            membro = (Membro) session.get(Membro.class, username);
            membro.setPassword(password);
            session.update(membro);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        
        return membro;
    }
    
    public static Membro updateProfileInformations(String name, String surname, String mail, String username)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Membro membro = null;
        
        try
        {
            transaction = session.beginTransaction();

            membro = (Membro) session.get(Membro.class, username);
            membro.setNome(name);
            membro.setCognome(surname);
            membro.setMail(mail);
            session.update(membro);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        
        return membro;
    }
    
    public static List<Membro> retrieveAll()
    {
        return HibernateUtil.getSessionFactory().openSession().createCriteria(Membro.class).list();
    }

    public static Membro deleteInterest(Integer id, String username)
    {
        Session session = HibernateUtil.getSessionFactory().openSession();
        Transaction transaction = null;
        
        Membro membro = null;
        
        try
        {
            transaction = session.beginTransaction();

            membro = (Membro) session.get(Membro.class, username);
            
            List<Categoria> categories = membro.getCategoriaList();
            
            for(int i = 0; i < categories.size(); i++)
            {
                if(categories.get(i).getId().equals(id))
                {
                    categories.remove(i);
                    break;
                }
            }
            
            membro.setCategoriaList(categories);
            
            session.update(membro);

            transaction.commit();
        } catch (HibernateException e)
        {
            transaction.rollback();
            e.printStackTrace();
        } finally
        {
            session.close();
        }
        
        return membro;
    }
}
