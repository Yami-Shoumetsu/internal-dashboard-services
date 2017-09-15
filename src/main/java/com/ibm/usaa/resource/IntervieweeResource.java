/**
 * 
 */
package com.ibm.usaa.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.ws.rs.BadRequestException;
import javax.ws.rs.ClientErrorException;
import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.POST;
import javax.ws.rs.PUT;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;
import javax.ws.rs.core.Response.Status;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.util.StringUtils;

import com.ibm.usaa.exception.InvalidDependentIdException;
import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.mapper.ExpertiseMapper;
import com.ibm.usaa.mapper.IntervieweeMapper;
import com.ibm.usaa.repository.entity.ExpertiseVO;
import com.ibm.usaa.repository.entity.IntervieweeVO;
import com.ibm.usaa.resource.representation.ExpertiseRO;
import com.ibm.usaa.resource.representation.IntervieweeRO;
import com.ibm.usaa.resource.util.RestUtils;
import com.ibm.usaa.service.IntervieweeService;

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
@Api(tags = "interviewee")
@Path("/interviewees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IntervieweeResource {

    private static final Logger LOGGER = LogManager.getLogger(IntervieweeResource.class);

    @Autowired
    private IntervieweeService intervieweeService;

    @GET
    @ApiOperation(value = "Returns list of interviewees", notes = "\"content\" in the response contains list of InterVieweeRO", code = 200, response = Resources.class)
    public Response getInterviewees(
            @ApiParam(value = "name of expertise", required = false, allowMultiple = false) @QueryParam("expertise") String expertise,
            @ApiParam(value = "indicates if interviewees to be returned are internal IBMer or not", required = false, allowMultiple = false, allowableValues = "true,false") @QueryParam("internal") Boolean internal) {
        List<IntervieweeVO> interviewees = intervieweeService.getInterviewees(expertise, internal);
        List<IntervieweeRO> intervieweeROs = IntervieweeMapper.mapIntervieweesToRepresentationObject(interviewees);

        intervieweeROs.forEach((intervieweeRO) -> {
            intervieweeRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "self", null, intervieweeRO.getResourceId()));
        });

        Resources<IntervieweeRO> resources = new Resources<>(intervieweeROs);
        Map<String, Object> queryParams = null;
        if (!StringUtils.isEmpty(expertise) || internal != null) {
            queryParams = new HashMap<>();
            if (!StringUtils.isEmpty(expertise)) {
                queryParams.put("expertise", expertise);
            }
            if (internal != null) {
                queryParams.put("internal", internal);
            }
        }
        resources.add(RestUtils.createHateoasLinks(IntervieweeResource.class, null, "self", queryParams));
        return Response.ok(resources)
                .build();
    }

    @POST
    @ApiOperation(value = "Creates new interviewee")
    @ApiResponses({
            @ApiResponse(code = 201, message = "successful operation", responseHeaders = @ResponseHeader(name = "Location", description = "URI of the newly created interviewee resource", response = String.class)),
            @ApiResponse(code = 400, message = "Missing required values in the body of request"),
            @ApiResponse(code = 409, message = "Specified expertise ID in the request body does not exist") })
    public Response addInterviewee(
            @ApiParam(value = "Interviewee resource that needs to be created. \"expertiseId\" in IntervieweeRO is also required for this operation.", required = true) @Valid IntervieweeRO request) {
        if (request.getExpertiseId() == null) {
            throw new BadRequestException();
        }
        try {
            IntervieweeVO interviewee = IntervieweeMapper.mapIntervieweeFromRepresentationObject(request);
            int newIntervieweeId = intervieweeService.addInterviewee(interviewee);
            return Response.created(RestUtils.createNewResourceUri(IntervieweeResource.class, "getInterviewee", newIntervieweeId))
                    .build();
        } catch (InvalidDependentIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new ClientErrorException(e.getMessage(), Status.CONFLICT, e);
        }
    }

    @GET
    @Path("/{interviewee-id}")
    @ApiOperation(value = "Returns detail of an interviewee")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = IntervieweeRO.class),
            @ApiResponse(code = 404, message = "Interviewee not found") })
    public Response getInterviewee(@ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id") int intervieweeId) {
        try {
            IntervieweeVO interviewee = intervieweeService.getInterviewee(intervieweeId);
            IntervieweeRO intervieweeRO = IntervieweeMapper.mapIntervieweeToRepresentationObject(interviewee);
            intervieweeRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, null, "interviewees", null));
            intervieweeRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "self", null, intervieweeId));
            intervieweeRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getExpertiseOfInterviewee", "expertise", null, intervieweeId));
            return Response.ok(intervieweeRO)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{interviewee-id}")
    @ApiOperation(value = "Updates an existing interviewee")
    @ApiResponses({
            @ApiResponse(code = 204, message = "successful operation"),
            @ApiResponse(code = 400, message = "Missing required values in the body of request"),
            @ApiResponse(code = 404, message = "Interviewee to be updated does not exist") })
    public Response updateInterviewee(
            @ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id") int intervieweeId,
            @ApiParam(value = "Updated Interviewee resource. Value of \"expertiseId\" in IntervieweeRO is ignored for this operation.", required = true) @Valid IntervieweeRO request) {
        try {
            request.setResourceId(intervieweeId);
            IntervieweeVO interviewee = IntervieweeMapper.mapIntervieweeFromRepresentationObject(request);
            intervieweeService.updateInterviewee(interviewee);
            return Response.noContent()
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @GET
    @Path("/{interviewee-id}/expertise")
    @ApiOperation(value = "Returns detail of the expertise of an interviewee")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = ExpertiseRO.class),
            @ApiResponse(code = 404, message = "Interviewee does not exist") })
    public Response getExpertiseOfInterviewee(
            @ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id") int intervieweeId) {
        try {
            ExpertiseVO expertise = intervieweeService.getExpertiseOfInterviewee(intervieweeId);
            ExpertiseRO expertiseRO = ExpertiseMapper.mapExpertiseToRepresentationObject(expertise);
            expertiseRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "parent", null, intervieweeId));
            expertiseRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getExpertiseOfInterviewee", "self", null, intervieweeId));
            expertiseRO.add(RestUtils.createHateoasLinks(ExpertiseResource.class, "getExpertise", "origin", null, expertiseRO.getResourceId()));
            return Response.ok(expertiseRO)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{interviewee-id}/expertise")
    @ApiOperation(value = "Updates the expertise of an interviewee")
    @ApiResponses({
            @ApiResponse(code = 204, message = "successful operation"),
            @ApiResponse(code = 400, message = "Missing required values in the body of request"),
            @ApiResponse(code = 404, message = "Interviewee does not exist"),
            @ApiResponse(code = 409, message = "Specified expertise ID in the request body does not exist") })
    public Response updateExpertiseOfInterviewee(
            @ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id") int intervieweeId,
            @ApiParam(value = "ID of expertise", required = true) @NotNull Integer expertiseId) {
        try {
            intervieweeService.updateExpertiseOfInterviewee(intervieweeId, expertiseId);
            return Response.noContent()
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        } catch (InvalidDependentIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new ClientErrorException(e.getMessage(), Status.CONFLICT, e);
        }
    }

}
