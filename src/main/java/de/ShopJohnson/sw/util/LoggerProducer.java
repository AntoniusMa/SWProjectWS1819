package de.ShopJohnson.sw.util;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


@ApplicationScoped
public class LoggerProducer {
    public static final String LOGGER_NAME = "ShopJohnsonLogger";

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {
        Logger logger;
        try {
            logger = Logger.getLogger(injectionPoint.getBean().getBeanClass().getName() + " " + LOGGER_NAME);
        }
        catch (Exception e) {
            logger = Logger.getLogger("ShopJohnson");
        }
        return logger;
    }
}
