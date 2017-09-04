package com.ibm.usaa.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEWERS")
public class InterviewerVO {

	@Id
	@Column(name="INT_ID", nullable=false, unique=true)
	private int interviewerId;
	
	@Column(name="STATUS")
	private String interviewerStatus;
	
	@OneToOne
	@JoinColumn(name="EMP_ID")
	private EmployeeVO employee;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="INT_ID")
	private List<InterviewHistoryVO> interviewHistory;
	
	@OneToMany(fetch = FetchType.LAZY)
	@JoinColumn(name="INT_ID")
	private List<InterviewerAvailabilityVO> interviewerAvailability;
	
	@ManyToMany(fetch = FetchType.LAZY)
	@JoinTable(name="INTERVIEWER_EXPERTISE", joinColumns=@JoinColumn(name="INT_ID"), inverseJoinColumns=@JoinColumn(name="ACCT_EXT_ID"))
	private List<ExpertiseVO> expertises;
	
	public int getInterviewerId() {
		return interviewerId;
	}

	public void setInterviewerId(int interviewerId) {
		this.interviewerId = interviewerId;
	}

	public String getInterviewerStatus() {
		return interviewerStatus;
	}

	public void setInterviewerStatus(String interviewerStatus) {
		this.interviewerStatus = interviewerStatus;
	}

	public List<InterviewHistoryVO> getInterviewHistory() {
		return interviewHistory;
	}

	public void setInterviewHistory(List<InterviewHistoryVO> interviewHistory) {
		this.interviewHistory = interviewHistory;
	}

	public List<InterviewerAvailabilityVO> getInterviewerAvailability() {
		return interviewerAvailability;
	}

	public void setInterviewerAvailability(List<InterviewerAvailabilityVO> interviewerAvailability) {
		this.interviewerAvailability = interviewerAvailability;
	}

	public List<ExpertiseVO> getExpertises() {
		return expertises;
	}

	public void setExpertises(List<ExpertiseVO> expertises) {
		this.expertises = expertises;
	}
	
}
