package com.ibm.usaa.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEWEES")
public class IntervieweeVO {
	
	@Id
	@Column(name="INT_VIEW_ID", nullable=false, unique=true)
	private int intervieweeId;
	
	@Column(name="INT_FIRST_NAME")
	private String firstName;
	
	@Column(name="INT_LAST_NAME")
	private String lastName;
	
	@Column(name="INT_CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name="INT_EMAIL")
	private String emailAddress;
	
	@OneToMany
	@JoinColumn(name="ACCT_EXT_ID")
	private List<ExpertiseVO> expertiseVO;
	
	public int getIntervieweeId() {
		return intervieweeId;
	}
	
	public void setIntervieweeId(int intervieweeId) {
		this.intervieweeId = intervieweeId;
	}
	
	public String getFirstName() {
		return firstName;
	}
	
	public void setFirstName(String firstName) {
		this.firstName = firstName;
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
	
}
