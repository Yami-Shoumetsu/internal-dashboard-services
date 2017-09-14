/**
 * 
 */
package com.ibm.usaa.resource.util;

import java.net.URI;
import java.util.Map;

import javax.ws.rs.core.UriBuilder;

import org.springframework.hateoas.Link;
import org.springframework.util.ObjectUtils;
import org.springframework.util.StringUtils;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

/**
 * Utility class for rest resources
 * 
 * @author Peter Neil Cagatin (Yami)
 *
 */
public class RestUtils {

    /**
     * Creates a <a href="https://en.wikipedia.org/wiki/HATEOAS">HATEOAS</a>
     * {@link Link}
     * 
     * @See <a href="https://en.wikipedia.org/wiki/HATEOAS">HATEOAS</a>
     * 
     * @param resource
     *            the resource class where the url will be derived
     * @param method
     *            the method of the resource class where additional urls will be
     *            derived. Can be null.
     * @param rel
     *            the relationship of the link
     * @param queryParams
     *            map of query parameter names and its value
     * @param params
     *            the parameters that will be used to resolve variables in the
     *            urls
     * @return a HATEOAS {@link Link}
     */
    public static Link createHateoasLinks(Class<?> resource, String method, String rel, Map<String, Object> queryParams, Object... params) {
        UriBuilder uriBuilder;
        if (StringUtils.isEmpty(method)) {
            uriBuilder = UriBuilder.fromUri(ServletUriComponentsBuilder.fromCurrentServletMapping()
                    .toUriString())
                    .path(resource);
        } else {
            uriBuilder = UriBuilder.fromUri(ServletUriComponentsBuilder.fromCurrentServletMapping()
                    .toUriString())
                    .path(resource)
                    .path(resource, method);
        }
        if (!ObjectUtils.isEmpty(queryParams)) {
            queryParams.forEach((key, value) -> {
                uriBuilder.queryParam(key, value);
            });
        }
        URI uri = uriBuilder.build(params);
        return new Link(uri.toString(), rel);
    }

    /**
     * Creates a {@link URI} that points to a url where the newly created
     * resource can be accessed.
     * 
     * @param resource
     *            the resource class where the url will be derived
     * @param method
     *            the method of the resrouce class where the newly created
     *            resource can be accessed to
     * @param params
     *            the parameters that will be used to resolve variables in the
     *            urls
     * @return a {@link URI} where the newly created resource can be accessed
     */
    public static URI createNewResourceUri(Class<?> resource, String method, Object... params) {
        return UriBuilder.fromUri(ServletUriComponentsBuilder.fromCurrentServletMapping()
                .toUriString())
                .path(resource)
                .path(resource, method)
                .build(params);
    }

}
