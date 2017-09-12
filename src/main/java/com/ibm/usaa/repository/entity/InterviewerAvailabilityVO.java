package com.ibm.usaa.repository.entity;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.Temporal;
import javax.persistence.TemporalType;

@Entity
@Table(name = "INTERVIEWERS_AVAILABILITY")
public class InterviewerAvailabilityVO {

	@Id
	@Column(name="INT_ID")
	private Integer interviewerId;
	
	@Column(name="AVAIALBLE_DATE")
	private String availableDate;
	
	@Column(name="AVAILABLE_TIME")
	@Temporal(TemporalType.TIME)
	private Date availableTime;
	
	public Integer getInterviewerId() {
		return interviewerId;
	}
	
	public void setInterviewerId(Integer interviewerId) {
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
