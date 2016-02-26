package listener;

import org.apache.log4j.Logger;
import org.springframework.context.ApplicationContext;
import org.springframework.context.support.ClassPathXmlApplicationContext;
import org.springframework.web.context.support.WebApplicationContextUtils;

import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;

/**
 * Created by viccardo on 9/02/16.
 */
public class ApplicationContextProvider implements ServletContextListener {

    private final static Logger LOGGER = Logger.getLogger(ApplicationContextProvider.class);

    private static ApplicationContext applicationContext;


    public static ApplicationContext getApplicationContext() {
        return applicationContext;
    }

    @Override
    public void contextInitialized(ServletContextEvent servletContextEvent) {
        LOGGER.info("ApplicationContextProvider.contextInitialized.servletContextEvent:"+servletContextEvent);
        applicationContext = WebApplicationContextUtils.getRequiredWebApplicationContext(servletContextEvent.getServletContext());
        LOGGER.info("ApplicationContextProvider.contextInitialized.applicationContext:::"+applicationContext);
    }

    @Override
    public void contextDestroyed(ServletContextEvent servletContextEvent) {
        LOGGER.info("ApplicationContextProvider.contextDestroyed.servletContextEvent:"+servletContextEvent);
        applicationContext = null;
    }
}
