/**
 * 
 */
package com.ibm.usaa.resource.representation;

import java.util.List;

import javax.validation.Valid;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ibm.usaa.resource.validation.group.Interviewee;
import com.ibm.usaa.resource.validation.group.Update;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class IntervieweeRO extends ResourceSupport {

    @JsonIgnore
    private Integer resourceId;
    @NotNull(groups = { Interviewee.class, Update.class })
    private String firstName;
    @NotNull(groups = { Interviewee.class, Update.class })
    private String middleName;
    @NotNull(groups = { Interviewee.class, Update.class })
    private String lastName;
    private String contactNumber;
    private String emailAddress;
    @NotNull(groups = { Interviewee.class, Update.class })
    private Boolean internal;
    @NotNull(groups = Interviewee.class)
    @Size(min = 1, groups = Interviewee.class)
    @Valid
    private List<InterviewHistoryRO> interviewHistories;

    public Integer getResourceId() {
        return resourceId;
    }

    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(String middleName) {
        this.middleName = middleName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getContactNumber() {
        return contactNumber;
    }

    public void setContactNumber(String contactNumber) {
        this.contactNumber = contactNumber;
    }

    public String getEmailAddress() {
        return emailAddress;
    }

    public void setEmailAddress(String emailAddress) {
        this.emailAddress = emailAddress;
    }

    public Boolean getInternal() {
        return internal;
    }

    public void setInternal(Boolean internal) {
        this.internal = internal;
    }

    public List<InterviewHistoryRO> getInterviewHistories() {
        return interviewHistories;
    }

    public void setInterviewHistories(List<InterviewHistoryRO> interviewHistories) {
        this.interviewHistories = interviewHistories;
    }

}
