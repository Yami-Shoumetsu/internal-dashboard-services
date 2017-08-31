package com.ibm.usaa.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEWERS_AVAILABILITY")
public class InterviewerAvailabilityVO {

	@Id
	@Column(name="INT_ID")
	private int interviewerId;
	private String availableDate;
	private Date availableTime;
	
	public int getInterviewerId() {
		return interviewerId;
	}
	
	public void setInterviewerId(int interviewerId) {
		this.interviewerId = interviewerId;
	}
	
	public String getAvailableDate() {
		return availableDate;
	}
	
	public void setAvailableDate(String availableDate) {
		this.availableDate = availableDate;
	}
	
	public Date getAvailableTime() {
		return availableTime;
	}
	
	public void setAvailableTime(Date availableTime) {
		this.availableTime = availableTime;
	}
	
	
}
