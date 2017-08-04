package com.ibm.usaa.configuration;

import javax.ws.rs.ApplicationPath;

import org.glassfish.jersey.server.ResourceConfig;
import org.springframework.stereotype.Component;

/**
 * Class to enable Jersey as the REST implementation instead of Spring. JAX-RS
 * annotations will be used instead of Spring provided REST annotations.
 * 
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Component
@ApplicationPath("/services")
public class JerseyConfiguration extends ResourceConfig {

    public JerseyConfiguration() {
        packages("com.ibm.usaa.services.resources");
    }

}
