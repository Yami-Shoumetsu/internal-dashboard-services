/**
 * 
 */
package com.ibm.usaa.resource;

import java.util.ArrayList;
import java.util.List;

import javax.validation.Valid;
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
import org.springframework.hateoas.Resource;
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

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@Path("/interviewees")
@Consumes(MediaType.APPLICATION_JSON)
@Produces(MediaType.APPLICATION_JSON)
public class IntervieweeResource {

    private static final Logger LOGGER = LogManager.getLogger(IntervieweeResource.class);

    @Autowired
    private IntervieweeService intervieweeService;

    @GET
    public Response getInterviewees(@QueryParam("expertise") String expertise) {
        List<IntervieweeVO> interviewees;
        if (StringUtils.isEmpty(expertise)) {
            interviewees = intervieweeService.getInterviewees();
        } else {
            interviewees = intervieweeService.getIntervieweesByExpertise(expertise);
        }
        List<IntervieweeRO> intervieweeROs = IntervieweeMapper.mapIntervieweesToRepresentationObject(interviewees);

        List<Resource<IntervieweeRO>> intervieweeResources = new ArrayList<>();
        for (IntervieweeRO intervieweeRO : intervieweeROs) {
            Resource<IntervieweeRO> intervieweeResource = new Resource<>(intervieweeRO);
            intervieweeResource.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "self", intervieweeRO.getId()));
            intervieweeResources.add(intervieweeResource);
        }

        Resources<Resource<IntervieweeRO>> resources = new Resources<>(intervieweeResources);
        resources.add(RestUtils.createHateoasLinks(IntervieweeResource.class, null, "self"));
        return Response.ok(resources)
                .build();
    }

    @POST
    public Response addInterviewee(@Valid IntervieweeRO request) {
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
    public Response getInterviewee(@PathParam("interviewee-id") int intervieweeId) {
        try {
            IntervieweeVO interviewee = intervieweeService.getInterviewee(intervieweeId);
            IntervieweeRO intervieweeRO = IntervieweeMapper.mapIntervieweeToRepresentationObject(interviewee);

            Resource<IntervieweeRO> resource = new Resource<>(intervieweeRO);
            resource.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "self", intervieweeId));
            resource.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getExpertiseOfInterviewee", "expertise", intervieweeId));
            return Response.ok(resource)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }

    }

    @PUT
    @Path("/{interviewee-id}")
    public Response updateInterviewee(@PathParam("interviewee-id") int intervieweeId, @Valid IntervieweeRO request) {
        try {
            request.setId(intervieweeId);
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
    public Response getExpertiseOfInterviewee(@PathParam("interviewee-id") int intervieweeId) {
        try {
            ExpertiseVO expertise = intervieweeService.getExpertiseOfInterviewee(intervieweeId);
            ExpertiseRO expertiseRO = ExpertiseMapper.mapExpertiseToRepresentationObject(expertise);

            Resource<ExpertiseRO> resource = new Resource<>(expertiseRO);
            resource.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getInterviewee", "parent", intervieweeId));
            resource.add(RestUtils.createHateoasLinks(IntervieweeResource.class, "getExpertiseOfInterviewee", "self", intervieweeId));
            resource.add(RestUtils.createHateoasLinks(ExpertiseResource.class, "getExpertise", "origin", expertiseRO.getId()));
            return Response.ok(resource)
                    .build();
        } catch (InvalidIdException e) {
            LOGGER.warn(e.getMessage(), e);
            throw new NotFoundException(e.getMessage(), e);
        }
    }

    @PUT
    @Path("/{interviewee-id}/expertise")
    public Response updateExpertiseOfInterviewee(@PathParam("interviewee-id") int intervieweeId, ExpertiseRO request) {
        if (request.getId() == null) {
            throw new BadRequestException();
        }
        try {
            intervieweeService.updateExpertiseOfInterviewee(intervieweeId, request.getId());
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
