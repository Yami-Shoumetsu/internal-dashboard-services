package com.ibm.usaa.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEW_HISTORY")
public class InterviewHistoryVO {

	@Column(name="INT_ID")
	private int interviewerId;
	
	@Column(name="INT_VIEW_ID")
	private int intervieweeId;
	
	@Column(name="DATE")
	private Date interviewDate;
	
	@Column(name="STATUS")
	private String status;
	
	public int getInterviewerId() {
		return interviewerId;
	}
	
	public void setInterviewerId(int interviewerId) {
		this.interviewerId = interviewerId;
	}
	
	public int getIntervieweeId() {
		return intervieweeId;
	}
	
	public void setIntervieweeId(int intervieweeId) {
		this.intervieweeId = intervieweeId;
	}
	
	public Date getInterviewDate() {
		return interviewDate;
	}
	
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}
	
	public String getStatus() {
		return status;
	}
	
	public void setStatus(String status) {
		this.status = status;
	}
	
}
