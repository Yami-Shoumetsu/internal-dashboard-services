package com.ibm.usaa.repository.entity;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

@Embeddable
public class InterviewHistoryPK implements Serializable{

	private static final long serialVersionUID = 1L;

	@Column(name="INT_ID")
	private Integer interviewerId;
	
	@Column(name="INT_VIEW_ID")
	private Integer intervieweeId;
	
	@Column(name="DATE")
	private Date interviewDate;
	
	public Integer getInterviewerId() {
		return interviewerId;
	}
	
	public void setInterviewerId(Integer interviewerId) {
		this.interviewerId = interviewerId;
	}
	
	public Integer getIntervieweeId() {
		return intervieweeId;
	}
	
	public void setIntervieweeId(Integer intervieweeId) {
		this.intervieweeId = intervieweeId;
	}
	
	public Date getInterviewDate() {
		return interviewDate;
	}
	
	public void setInterviewDate(Date interviewDate) {
		this.interviewDate = interviewDate;
	}

	@Override
	public int hashCode() {
		final int prime = 31;
		int result = 1;
		result = prime * result + ((interviewDate == null) ? 0 : interviewDate.hashCode());
		result = prime * result + intervieweeId;
		result = prime * result + interviewerId;
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
		InterviewHistoryPK other = (InterviewHistoryPK) obj;
		if (interviewDate == null) {
			if (other.interviewDate != null)
				return false;
		} else if (!interviewDate.equals(other.interviewDate))
			return false;
		if (intervieweeId != other.intervieweeId)
			return false;
		if (interviewerId != other.interviewerId)
			return false;
		return true;
	}
	
}
