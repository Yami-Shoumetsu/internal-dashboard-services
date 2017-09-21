/**
 * 
 */
package com.ibm.usaa.resource.representation;

import javax.validation.constraints.NotNull;

import org.springframework.hateoas.ResourceSupport;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.fasterxml.jackson.annotation.JsonInclude;
import com.fasterxml.jackson.annotation.JsonInclude.Include;
import com.ibm.usaa.repository.entity.EmployeeTypeVO;
import com.ibm.usaa.repository.entity.EmployeeVO;

/**
 * @author Peter Neil Cagatin (Yami)
 *
 */
@JsonInclude(value = Include.NON_NULL)
public class InterviewerRO extends ResourceSupport {

    @JsonIgnore
    private Integer resourceId;
    @NotNull
    private String interviewerStatus;
    @NotNull
    private String employeeId;
    @NotNull
    private String firstName;
    private String middleName;
    private String lastName;
    @NotNull
    private String contactNumber;
    private String email;
    @NotNull
    private Integer employeeTypeId;

    public Integer getResourceId() {
        return resourceId;
    }
    
    public void setResourceId(Integer resourceId) {
        this.resourceId = resourceId;
    }

    public String getInterviewerStatus() {
		return interviewerStatus;
	}

	public void setInterviewerStatus(String interviewerStatus) {
		this.interviewerStatus = interviewerStatus;
	}

	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
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

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public Integer getEmployeeTypeId() {
		return employeeTypeId;
	}

	public void setEmployeeTypeId(Integer employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}    

}
