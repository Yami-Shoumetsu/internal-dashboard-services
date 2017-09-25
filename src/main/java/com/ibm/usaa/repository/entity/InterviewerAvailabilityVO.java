package com.ibm.usaa.repository.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEWERS_AVAILABILITY")
public class InterviewerAvailabilityVO {

	@EmbeddedId
	private InterviewerAvailabilityPK interviewerAvailabilityId;

	public InterviewerAvailabilityPK getInterviewAvailabilityId() {
		return interviewerAvailabilityId;
	}

	public void setInterviewAvailabilityId(InterviewerAvailabilityPK interviewAvailabilityId) {
		this.interviewerAvailabilityId = interviewAvailabilityId;
	}
	
}
