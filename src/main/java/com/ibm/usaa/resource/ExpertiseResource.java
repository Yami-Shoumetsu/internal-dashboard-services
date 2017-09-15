/**
 * 
 */
package com.ibm.usaa.resource;

import java.util.List;

import javax.validation.Valid;
import javax.ws.rs.ClientErrorException;
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
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;

import com.ibm.usaa.exception.DuplicateDataException;
import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.mapper.ExpertiseMapper;
import com.ibm.usaa.repository.entity.ExpertiseVO;
import com.ibm.usaa.resource.representation.ExpertiseRO;
import com.ibm.usaa.resource.util.RestUtils;
import com.ibm.usaa.service.ExpertiseService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;
import io.swagger.annotations.ResponseHeader;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Api(tags = "expertise")
@Path("/expertises")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class ExpertiseResource {

    private static final Logger LOGGER = LogManager.getLogger(ExpertiseResource.class);

    @Autowired
    private ExpertiseService expertiseService;

    @GET
    @ApiOperation(value = "Returns list of expertises", notes = "\"content\" in the response contains list of ExpertiseRO", code = 200, response = Resources.class)
    public Response getExpertises() {
        List<ExpertiseVO> expertises = expertiseService.getExpertises();
        List<ExpertiseRO> expertiseROs = ExpertiseMapper.mapExpertisesToRepresentationObject(expertises);

        expertiseROs.forEach((expertiseRO) -> {
            expertiseRO.add(RestUtils.createHateoasLinks(ExpertiseResource.class, "getExpertise", "self", null, expertiseRO.getResourceId()));
        });

        Resources<ExpertiseRO> resources = new Resources<>(expertiseROs);
        resources.add(RestUtils.createHateoasLinks(ExpertiseResource.class, null, "self", null));
        return Response.ok(resources)
                .build();
    }

    @POST
    @ApiOperation(value = "Creates new expertise")
    @ApiResponses({
            @ApiResponse(code = 201, message = "successful operation", responseHeaders = @ResponseHeader(name = "Location", description = "URI of the newly created expertise resource", response = String.class)),
            @ApiResponse(code = 400, message = "Missing required values in the body of request"),
            @ApiResponse(code = 409, message = "Expertise name already exists") })
    public Response addExpertise(@ApiParam(value = "Expertise resource that needs to be created", required = true) @Valid ExpertiseRO expertiseRO) {
        try {
            ExpertiseVO expertise = ExpertiseMapper.mapExpertiseFromRepresentationObject(expertiseRO);
            int newExpertiseId = expertiseService.addExpertise(expertise);
            return Response.created(RestUtils.createNewResourceUri(ExpertiseResource.class, "getExpertise", newExpertiseId))
                    .build();
        } catch (DuplicateDataException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new ClientErrorException(e.getMessage(), Status.CONFLICT, e);
        }
    }

    @GET
    @Path("/{expertise-id}")
    @ApiOperation(value = "Returns detail of an expertise")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = ExpertiseRO.class),
            @ApiResponse(code = 404, message = "Expertise not found") })
    public Response getExpertise(@ApiParam(value = "ID of Expertise", required = true) @PathParam("expertise-id") int expertiseId) {
        try {
            ExpertiseVO expertise = expertiseService.getExpertise(expertiseId);
            ExpertiseRO expertiseRO = ExpertiseMapper.mapExpertiseToRepresentationObject(expertise);
            expertiseRO.add(RestUtils.createHateoasLinks(ExpertiseResource.class, null, "expertises", null));
            expertiseRO.add(RestUtils.createHateoasLinks(ExpertiseResource.class, "getExpertise", "self", null, expertiseId));
            return Response.ok(expertiseRO)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{expertise-id}")
    @ApiOperation(value = "Updates an existing expertise")
    @ApiResponses({
            @ApiResponse(code = 204, message = "successful operation"),
            @ApiResponse(code = 400, message = "Missing required values in the body of request"),
            @ApiResponse(code = 404, message = "Expertise to be updated does not exist") })
    public Response updateExpertise(
            @ApiParam(value = "ID of expertise", required = true) @PathParam("expertise-id") int expertiseId,
            @ApiParam(value = "Updated Expertise resource", required = true) @Valid ExpertiseRO request) {
        try {
            request.setResourceId(expertiseId);
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
