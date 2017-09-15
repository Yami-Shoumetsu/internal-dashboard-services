/**
 * 
 */
package com.ibm.usaa.resource;

import java.io.IOException;
import java.io.InputStream;

import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.core.io.ResourceLoader;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Path("/api")
public class SwaggerUiResource {

    @Autowired
    private ResourceLoader resourceLoader;

    @GET
    @Produces(MediaType.TEXT_HTML)
    public InputStream getSwaggerDocumentationUi() throws IOException {
        return resourceLoader.getResource("classpath:swagger.html")
                .getInputStream();
    }

}
