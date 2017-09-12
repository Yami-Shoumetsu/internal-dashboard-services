package com.ibm.usaa.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEWEES")
public class IntervieweeVO {
	
	@Id
	@GeneratedValue
	@Column(name="INT_VIEW_ID", nullable=false, unique=true)
	private Integer intervieweeId;
	
	@Column(name="INT_F_NAME")
	private String firstName;
	
	@Column(name="INT_M_NAME")
	private String middleName;
	
	@Column(name="INT_L_NAME")
	private String lastName;
	
	@Column(name="INT_CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name="INT_EMAIL")
	private String emailAddress;
	
	@Column(name="IS_INTERNAL", columnDefinition="INT(1)")
	private Boolean isInternal;
	
	@OneToOne
	@JoinColumn(name="ACCT_EXT_ID")
	private ExpertiseVO expertise;
	
	public Integer getIntervieweeId() {
		return intervieweeId;
	}
	
	public void setIntervieweeId(Integer intervieweeId) {
		this.intervieweeId = intervieweeId;
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

	public Boolean isInternal() {
		return isInternal;
	}

	public void setInternal(Boolean isInternal) {
		this.isInternal = isInternal;
	}

	public ExpertiseVO getExpertise() {
		return expertise;
	}

	public void setExpertise(ExpertiseVO expertise) {
		this.expertise = expertise;
	}
	
}
