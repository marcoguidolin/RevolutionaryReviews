package spring;



import javax.servlet.MultipartConfigElement;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletRegistration.Dynamic;
import org.springframework.web.WebApplicationInitializer;
import org.springframework.web.context.support.AnnotationConfigWebApplicationContext;
import org.springframework.web.servlet.DispatcherServlet;

/**
 * Classe Init
 *
 * @author Matteo Parlato
 */
public class Init implements WebApplicationInitializer
{

    @Override
    public void onStartup(ServletContext servletContext) throws ServletException
    {
        AnnotationConfigWebApplicationContext ctx = new AnnotationConfigWebApplicationContext();
        ctx.register(Config.class);
        ctx.setServletContext(servletContext);
        Dynamic servlet = servletContext.addServlet("dispatcher", new DispatcherServlet(ctx));
        servlet.addMapping("/");
        servlet.setLoadOnStartup(1);
        servlet.setMultipartConfig(new MultipartConfigElement("/tmp", 1024*1024*10, 1024*1024*10, 1024*1024*15));
    }

}
