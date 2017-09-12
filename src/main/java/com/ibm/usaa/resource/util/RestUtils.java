/**
 * 
 */
package com.ibm.usaa.resource.util;

import java.net.URI;

import javax.ws.rs.core.UriBuilder;

import org.springframework.hateoas.Link;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class RestUtils {

    public static Link createHateoasLinks(Class<?> resource, String method, String rel, Object... params) {
        URI uri;
        if (StringUtils.isEmpty(method)) {
            uri = UriBuilder.fromUri(ServletUriComponentsBuilder.fromCurrentServletMapping()
                    .toUriString())
                    .path(resource)
                    .build(params);
        } else {
            uri = UriBuilder.fromUri(ServletUriComponentsBuilder.fromCurrentServletMapping()
                    .toUriString())
                    .path(resource)
                    .path(resource, method)
                    .build(params);
        }
        return new Link(uri.toString(), rel);
    }

    public static URI createNewResourceUri(Class<?> resource, String method, Object... params) {
        return UriBuilder.fromUri(ServletUriComponentsBuilder.fromCurrentServletMapping()
                .toUriString())
                .path(resource)
                .path(resource, method)
                .build(params);
    }

}
