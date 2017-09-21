/**
 * 
 */
package com.ibm.usaa.resource;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.QueryParam;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.hateoas.Resources;
import org.springframework.util.StringUtils;

import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.mapper.InterviewerMapper;
import com.ibm.usaa.repository.entity.InterviewerVO;
import com.ibm.usaa.resource.representation.InterviewerRO;
import com.ibm.usaa.resource.util.RestUtils;
import com.ibm.usaa.service.InterviewerService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Api(tags = "interviewer")
@Path("/interviewers")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewerResource {

    private static final Logger LOGGER = LogManager.getLogger(InterviewerResource.class);

    @Autowired
    private InterviewerService interviewerService;

    @GET
    @ApiOperation(value = "Returns list of interviewers", notes = "\"content\" in the response contains list of InterViewerRO", code = 200, response = Resources.class)
    public Response getInterviewers(
            @ApiParam(value = "name of expertise", required = true, allowMultiple = false) @QueryParam("expertise") String expertise) {
        List<InterviewerVO> interviewers = interviewerService.getInterviewers(expertise);
        List<InterviewerRO> interviewerROs = InterviewerMapper.mapInterviewersToRepresentationObject(interviewers);

        interviewerROs.forEach((interviewerRO) -> {
            interviewerRO.add(RestUtils.createHateoasLinks(InterviewerResource.class, "getInterviewer", "self", null, interviewerRO.getResourceId()));
        });

        Resources<InterviewerRO> resources = new Resources<>(interviewerROs);
        Map<String, Object> queryParams = null;
        if (!StringUtils.isEmpty(expertise)) {
            queryParams = new HashMap<>();
            if (!StringUtils.isEmpty(expertise)) {
                queryParams.put("expertise", expertise);
            }
        }
        resources.add(RestUtils.createHateoasLinks(InterviewerResource.class, null, "self", queryParams));
        return Response.ok(resources)
                .build();
    }
    
    @GET
    @Path("/{interviewer-id}")
    @ApiOperation(value = "Returns detail of an interviewer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = InterviewerRO.class),
            @ApiResponse(code = 404, message = "Interviewer not found") })
    public Response getInterviewer(@ApiParam(value = "ID of interviewer", required = true) @PathParam("interviewer-id") int interviewerId) {
        try {
            InterviewerVO interviewer = interviewerService.getInterviewer(interviewerId);
            InterviewerRO interviewerRO = InterviewerMapper.mapInterviewerToRepresentationObject(interviewer);
            interviewerRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, null, "interviewers", null));
            interviewerRO.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewer", "self", null, interviewerId));
            return Response.ok(interviewerRO)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

}
