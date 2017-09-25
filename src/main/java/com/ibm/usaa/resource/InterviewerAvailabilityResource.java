/**
 * 
 */
package com.ibm.usaa.resource;

import java.util.List;

import javax.ws.rs.Consumes;
import javax.ws.rs.GET;
import javax.ws.rs.NotFoundException;
import javax.ws.rs.Path;
import javax.ws.rs.PathParam;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import javax.ws.rs.core.Response;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.beans.factory.annotation.Autowired;

import com.ibm.usaa.exception.InvalidIdException;
import com.ibm.usaa.mapper.InterviewerAvailabilityMapper;
import com.ibm.usaa.repository.entity.InterviewerAvailabilityVO;
import com.ibm.usaa.resource.representation.InterviewerAvailabilityRO;
import com.ibm.usaa.resource.representation.InterviewerRO;
import com.ibm.usaa.resource.util.RestUtils;
import com.ibm.usaa.service.InterviewerAvailabilityService;

import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiParam;
import io.swagger.annotations.ApiResponse;
import io.swagger.annotations.ApiResponses;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Api(tags = "interviewerAvailability")
@Path("/interviewers/{interviewer-id}/interviewerAvailabilities")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class InterviewerAvailabilityResource {

    private static final Logger LOGGER = LogManager.getLogger(InterviewerAvailabilityResource.class);

    @Autowired
    private InterviewerAvailabilityService interviewerAvailabilityService;
    
    @GET
    @ApiOperation(value = "Returns availabilities of the interviewer")
    @ApiResponses({
            @ApiResponse(code = 200, message = "successful operation", response = InterviewerRO.class),
            @ApiResponse(code = 404, message = "Interviewer not found") })
    public Response getInterviewerAvailabilities(@ApiParam(value = "ID of interviewer", required = true) @PathParam("interviewer-id") int interviewerId) {
        try {
            List<InterviewerAvailabilityVO> interviewerAvailabilities = interviewerAvailabilityService.getInterviewerAvailabilities(interviewerId);
            List<InterviewerAvailabilityRO> interviewerAvailabilityROs = InterviewerAvailabilityMapper.mapInterviewerAvailabilityToRepresentationObject(interviewerAvailabilities);
     
            interviewerAvailabilityROs.forEach((interviewerAvailabilityRO) -> {
            	interviewerAvailabilityRO.add(RestUtils.createHateoasLinks(InterviewerResource.class, "getInterviewer", "parent", null, interviewerId));
            	interviewerAvailabilityRO.add(RestUtils.createHateoasLinks(InterviewerAvailabilityResource.class, null, "self", null, interviewerAvailabilityRO.getInterviewerAvailabilityVO().getInterviewAvailabilityId().getInterviewerId()));
            });
            return Response.ok(interviewerAvailabilityROs)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

}
