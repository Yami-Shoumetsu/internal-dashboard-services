/**
 * 
 */
package com.ibm.usaa.resource.representation;

import java.util.Date;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ibm.usaa.resource.validation.group.Interviewee;
import com.ibm.usaa.resource.validation.group.Interviewer;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class InterviewHistoryRO extends ResourceSupport {

    @NotNull(groups = Interviewee.class)
    private Integer interviewerId;
    @NotNull(groups = Interviewer.class)
    private Integer intervieweeId;
    @NotNull(groups = { Interviewee.class, Interviewer.class })
    private Integer expertiseId;
    @NotNull(groups = { Interviewee.class, Interviewer.class })
    private Date assignedSchedule;
    private StatusRO status;

    public Integer getInterviewerId() {
        return interviewerId;
    }

    public void setInterviewerId(Integer interviewerId) {
        this.interviewerId = interviewerId;
    }

    public Integer getIntervieweeId() {
        return intervieweeId;
    }

    public void setIntervieweeId(Integer intervieweeId) {
        this.intervieweeId = intervieweeId;
    }

    public Integer getExpertiseId() {
        return expertiseId;
    }

    public void setExpertiseId(Integer expertiseId) {
        this.expertiseId = expertiseId;
    }

    public Date getAssignedSchedule() {
        return assignedSchedule;
    }

    public void setAssignedSchedule(Date assignedSchedule) {
        this.assignedSchedule = assignedSchedule;
    }

    public StatusRO getStatus() {
        return status;
    }

    public void setStatus(StatusRO status) {
        this.status = status;
    }

}
