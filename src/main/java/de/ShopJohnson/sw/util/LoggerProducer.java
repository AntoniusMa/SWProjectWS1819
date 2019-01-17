package de.ShopJohnson.sw.util;

import org.jboss.logging.Logger;

import javax.enterprise.context.ApplicationScoped;
import javax.enterprise.inject.Produces;
import javax.enterprise.inject.spi.InjectionPoint;


@ApplicationScoped
public class LoggerProducer {
    public static final String LOGGER_NAME = "ShopJohnson";

    @Produces
    public Logger createLogger(InjectionPoint injectionPoint) {

        return Logger.getLogger(injectionPoint.getBean().getBeanClass().getName() + LOGGER_NAME);
    }
}
