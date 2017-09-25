package com.ibm.usaa.repository.entity;

import java.io.Serializable;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InterviewerAvailabilityPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="INT_ID")
	private Integer interviewerId;
	
	@Column(name="AVAILABLE_DAY")
	private String availableDay;

	@Column(name="AVAILABLE_TIME")
	private String availableTime;
	
	public Integer getInterviewerId() {
		return interviewerId;
	}
	
	public void setInterviewerId(Integer interviewerId) {
		this.interviewerId = interviewerId;
	}
	
	public String getAvailableDay() {
		return availableDay;
	}

	public void setAvailableDay(String availableDay) {
		this.availableDay = availableDay;
	}

	public String getAvailableTime() {
		return availableTime;
	}

	public void setAvailableTime(String availableTime) {
		this.availableTime = availableTime;
	}	
	
	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((interviewerId == null) ? 0 : interviewerId.hashCode());
		return result;
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		InterviewerAvailabilityPK other = (InterviewerAvailabilityPK) obj;
		if (interviewerId == null) {
			if (other.interviewerId != null)
				return false;
		} else if (!interviewerId.equals(other.interviewerId))
			return false;
		return true;
	}
}
