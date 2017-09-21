/**
 * 
 */
package com.ibm.usaa.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resource;
import org.springframework.hateoas.Resources;

import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.mapper.ExpertiseMapper;
import com.ibm.usaa.repository.entity.ExpertiseVO;
import com.ibm.usaa.resource.representation.ExpertiseRO;
import com.ibm.usaa.resource.util.RestUtils;
import com.ibm.usaa.service.ExpertiseService;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Path("/expertises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpertiseResource {

    private static final Logger LOGGER = LogManager.getLogger(ExpertiseResource.class);

    @Autowired
    private ExpertiseService expertiseService;

    @GET
    public Response getExpertises() {
        List<ExpertiseVO> expertises = expertiseService.getExpertises();
        List<ExpertiseRO> expertiseROs = ExpertiseMapper.mapExpertisesToRepresentationObject(expertises);

        List<Resource<ExpertiseRO>> expertiseResources = new ArrayList<>();
        for (ExpertiseRO expertiseRO : expertiseROs) {
            Resource<ExpertiseRO> expertiseResource = new Resource<>(expertiseRO);
            expertiseResource.add(RestUtils.createHateoasLinks(ExpertiseResource.class, "getExpertise", "self", expertiseRO.getId()));
            expertiseResources.add(expertiseResource);
        }

        Resources<Resource<ExpertiseRO>> resources = new Resources<>(expertiseResources);
        resources.add(RestUtils.createHateoasLinks(ExpertiseResource.class, null, "self"));
        return Response.ok(resources)
                .build();
    }

    @POST
    public Response addExpertise(@Valid ExpertiseRO expertiseRO) {
        ExpertiseVO expertise = ExpertiseMapper.mapExpertiseFromRepresentationObject(expertiseRO);
        int newExpertiseId = expertiseService.addExpertise(expertise);
        return Response.created(RestUtils.createNewResourceUri(ExpertiseResource.class, "getExpertise", newExpertiseId))
                .build();
    }

    @GET
    @Path("/{expertise-id}")
    public Response getExpertise(@PathParam("expertise-id") int expertiseId) {
        try {
            ExpertiseVO expertise;
            expertise = expertiseService.getExpertise(expertiseId);
            ExpertiseRO expertiseRO = ExpertiseMapper.mapExpertiseToRepresentationObject(expertise);

            Resource<ExpertiseRO> resource = new Resource<>(expertiseRO);
            resource.add(RestUtils.createHateoasLinks(ExpertiseResource.class, "getExpertise", "self", expertiseId));
            return Response.ok(resource)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{expertise-id}")
    public Response updateExpertise(@PathParam("expertise-id") int expertiseId, @Valid ExpertiseRO request) {
        try {
            request.setId(expertiseId);
            ExpertiseVO expertise = ExpertiseMapper.mapExpertiseFromRepresentationObject(request);
            expertiseService.updateExpertise(expertise);
            return Response.noContent()
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

}
