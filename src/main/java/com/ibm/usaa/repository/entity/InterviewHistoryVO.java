package com.ibm.usaa.repository.entity;

import javax.persistence.Column;
import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEW_HISTORY")
public class InterviewHistoryVO {

	@EmbeddedId
	private InterviewHistoryPK interviewHistoryId;
	
	@Column(name="STATUS")
	private String status;
	
	public InterviewHistoryPK getInterviewHistoryId() {
		return interviewHistoryId;
	}

	public void setInterviewHistoryId(InterviewHistoryPK interviewHistoryId) {
		this.interviewHistoryId = interviewHistoryId;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
