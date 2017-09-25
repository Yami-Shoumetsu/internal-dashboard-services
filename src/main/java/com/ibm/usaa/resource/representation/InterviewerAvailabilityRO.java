/**
 * 
 */
package com.ibm.usaa.resource.representation;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ibm.usaa.repository.entity.InterviewerAvailabilityVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class InterviewerAvailabilityRO extends ResourceSupport {

    @JsonIgnore
    private Integer resourceId;
    @NotNull
    private InterviewerAvailabilityVO interviewerAvailabilityVO;

	public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }
    
    public InterviewerAvailabilityVO getInterviewerAvailabilityVO() {
		return interviewerAvailabilityVO;
	}

	public void setInterviewerAvailabilityVO(InterviewerAvailabilityVO interviewerAvailabilityVO) {
		this.interviewerAvailabilityVO = interviewerAvailabilityVO;
	}

}
