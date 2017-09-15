package com.ibm.usaa.configuration;

import javax.annotation.PostConstruct;
import javax.ws.rs.ApplicationPath;
import javax.ws.rs.Path;
import javax.ws.rs.ext.Provider;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.glassfish.jersey.server.ResourceConfig;
import org.reflections.Reflections;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import io.swagger.jaxrs.config.BeanConfig;
import io.swagger.jaxrs.listing.ApiListingResource;
import io.swagger.jaxrs.listing.SwaggerSerializers;

/**
 * Class to enable Jersey as the REST implementation instead of Spring. JAX-RS
 * annotations will be used instead of Spring provided REST annotations.
 * 
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Component
@ApplicationPath("/internal-dashboard-services")
public class JerseyConfiguration extends ResourceConfig {

    private static final Logger LOGGER = LogManager.getLogger(JerseyConfiguration.class);

    @Value("${application.name}")
    private String applicationName;

    @Value("${application.version}")
    private String applicationVersion;

    @Value("${application.description}")
    private String applicationDescription;

    public JerseyConfiguration() {
        scan("com.ibm.usaa.resource");
    }

    public void scan(String... packages) {
        for (String pack : packages) {
            Reflections reflections = new Reflections(pack);
            reflections.getTypesAnnotatedWith(Provider.class)
                    .forEach((clazz) -> {
                        LOGGER.debug("New provider registered: " + clazz.getName());
                        register(clazz);
                    });
            reflections.getTypesAnnotatedWith(Path.class)
                    .forEach((clazz) -> {
                        LOGGER.debug("New resource registered: " + clazz.getName());
                        register(clazz);
                    });
        }
    }

    @PostConstruct
    public void configureSwagger() {
        register(ApiListingResource.class);
        register(SwaggerSerializers.class);
        BeanConfig beanConfig = new BeanConfig();
        beanConfig.setTitle(applicationName);
        beanConfig.setVersion(applicationVersion);
        beanConfig.setDescription(applicationDescription);
        beanConfig.setResourcePackage("com.ibm.usaa.resource");
        beanConfig.setBasePath("/internal-dashboard-services");
        beanConfig.setPrettyPrint(true);
        beanConfig.setScan(true);
    }

}
