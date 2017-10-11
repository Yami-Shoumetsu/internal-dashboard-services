/**
 * 
 */
package com.ibm.usaa.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.validation.Valid;
import javax.validation.groups.ConvertGroup;
import javax.validation.groups.Default;
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
import com.ibm.usaa.mapper.InterviewHistoryMapper;
import com.ibm.usaa.mapper.IntervieweeMapper;
import com.ibm.usaa.repository.entity.InterviewHistoryVO;
import com.ibm.usaa.repository.entity.IntervieweeVO;
import com.ibm.usaa.resource.representation.InterviewHistoryRO;
import com.ibm.usaa.resource.representation.IntervieweeRO;
import com.ibm.usaa.resource.util.RestUtils;
import com.ibm.usaa.resource.validation.group.Interviewee;
import com.ibm.usaa.resource.validation.group.Update;
import com.ibm.usaa.service.InterviewHistoryService;
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

    @Autowired
    private InterviewHistoryService interviewHistoryService;

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
        return Response.ok(resources).build();
    }

    @POST
    @ApiOperation(value = "Creates new interviewee")
    @ApiResponses({
                    @ApiResponse(code = 201, message = "successful operation", responseHeaders = @ResponseHeader(name = "Location", description = "URI of the newly created interviewee resource", response = String.class)),
                    @ApiResponse(code = 400, message = "Missing required values in the body of request"),
                    @ApiResponse(code = 409, message = "Some details on the Interview History is not valid") })

    public Response addInterviewee(
                                   @ApiParam(value = "Interviewee resource that needs to be created. \"intervieweeId\" in Interview History is not required on this operation.", required = true) @Valid @ConvertGroup(from = Default.class, to = Interviewee.class) IntervieweeRO request) {
        try {
            IntervieweeVO interviewee = IntervieweeMapper.mapIntervieweeFromRepresentationObject(request);
            List<InterviewHistoryVO> interviewHistories = InterviewHistoryMapper.mapInterviewHistoriesFromRepresentationObject(
                                                                                                                               request.getInterviewHistories());
            interviewee.setInterviewHistories(interviewHistories);
            int newIntervieweeId = intervieweeService.addInterviewee(interviewee);
            return Response.created(RestUtils.createNewResourceUri(IntervieweeResource.class, "getInterviewee", newIntervieweeId)).build();
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
    public Response getInterviewee(
                                   @ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id") int intervieweeId,
                                   @ApiParam(value = "Parameter to expand child resources", allowMultiple = true, allowableValues = "expertise") @QueryParam("expand") List<String> expand) {
        try {
            IntervieweeVO interviewee = intervieweeService.getInterviewee(intervieweeId);
            IntervieweeRO intervieweeRO = IntervieweeMapper.mapIntervieweeToRepresentationObject(interviewee);
            intervieweeRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, null, "interviewees", null));
            intervieweeRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "self", null, intervieweeId));
            intervieweeRO.add(
                              RestUtils.createHateoasLinks(
                                                           IntervieweeResource.class,
                                                           "getInterviewHistoriesOfInterviewee",
                                                           "interview-histories",
                                                           null,
                                                           intervieweeId));

            return Response.ok(intervieweeRO).build();
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
                                      @ApiParam(value = "Updated Interviewee resource. Interview histories are ignored.", required = true) @Valid @ConvertGroup(from = Default.class, to = Update.class) IntervieweeRO request) {
        try {
            request.setResourceId(intervieweeId);
            IntervieweeVO interviewee = IntervieweeMapper.mapIntervieweeFromRepresentationObject(request);
            intervieweeService.updateInterviewee(interviewee);
            return Response.noContent().build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @GET
    @Path("/{interviewee-id}/interview-histories")
    @ApiOperation(value = "Returns list of interview history of interviewee", notes = "\"content\" in the response contains list of InterviewHistoryRO")
    @ApiResponses({
                    @ApiResponse(code = 200, message = "successful operation", response = Resources.class),
                    @ApiResponse(code = 404, message = "Interviewee does not exist") })
    public Response getInterviewHistoriesOfInterviewee(
                                                       @ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id") int intervieweeId) {
        try {
            List<InterviewHistoryVO> interviewHistories = intervieweeService.getInterviewHistoriesOfInterviewee(intervieweeId);
            List<InterviewHistoryRO> interviewHistoryROs = InterviewHistoryMapper.mapInterviewHistoriesToRepresentationObject(interviewHistories);

            interviewHistoryROs.forEach((interviewHistoryRO) -> {
                interviewHistoryRO.add(
                                       RestUtils.createHateoasLinks(
                                                                    IntervieweeResource.class,
                                                                    "getInterviewHistoryOfInterviewee",
                                                                    "self",
                                                                    null,
                                                                    intervieweeId,
                                                                    interviewHistoryRO.getInterviewerId(),
                                                                    interviewHistoryRO.getIntervieweeId(),
                                                                    interviewHistoryRO.getExpertiseId()));
            });

            Resources<InterviewHistoryRO> resources = new Resources<>(interviewHistoryROs);
            resources.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "parent", null, intervieweeId));
            resources.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewHistoriesOfInterviewee", "self", null, intervieweeId));
            return Response.ok(resources).build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @GET
    @Path("/{interviewee-id}/interview-histories/{interviewer-id}-{interviewee-id-2}-{expertise-id}")
    @ApiOperation(value = "Returns detail of an interview history")
    @ApiResponses({
                    @ApiResponse(code = 200, message = "successful operation", response = InterviewHistoryRO.class),
                    @ApiResponse(code = 404, message = "Interviewee or Interviewee History does not exist") })
    public Response getInterviewHistoryOfInterviewee(
                                                     @ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id") int intervieweeId,
                                                     @ApiParam(value = "ID of interviewee", required = true) @PathParam("interviewee-id-2") int intervieweeId2,
                                                     @ApiParam(value = "ID of interviewer", required = true) @PathParam("interviewer-id") int interviewerId,
                                                     @ApiParam(value = "ID of expertise", required = true) @PathParam("expertise-id") int expertiseId) {
        try {
            if (!intervieweeService.intervieweeExists(intervieweeId)) {
                throw new InvalidIdException(intervieweeId);
            }
            if (intervieweeId != intervieweeId2) {
                throw new InvalidIdException(intervieweeId + " " + intervieweeId2);
            }
            InterviewHistoryVO interviewHistory = interviewHistoryService.getInterviewHistory(interviewerId, intervieweeId2, expertiseId);
            InterviewHistoryRO interviewHistoryRO = InterviewHistoryMapper.mapInterviewHistoryToRepresentationObject(interviewHistory);
            interviewHistoryRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "parent", null, intervieweeId));
            interviewHistoryRO.add(
                                   RestUtils.createHateoasLinks(
                                                                IntervieweeResource.class,
                                                                "getInterviewHistoriesOfInterviewee",
                                                                "interview-histories",
                                                                null,
                                                                intervieweeId));
            interviewHistoryRO.add(
                                   RestUtils.createHateoasLinks(
                                                                IntervieweeResource.class,
                                                                "getInterviewHistoryOfInterviewee",
                                                                "self",
                                                                null,
                                                                intervieweeId,
                                                                interviewerId,
                                                                intervieweeId2,
                                                                expertiseId));
            return Response.ok(interviewHistoryRO).build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }
}
