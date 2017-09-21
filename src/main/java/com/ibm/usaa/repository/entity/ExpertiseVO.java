package com.ibm.usaa.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_EXPERTISE")
public class ExpertiseVO {

	@Id
	@Column(name="ACCT_EXT_ID", nullable=false, unique=true)
	private int expertiseId;
	
	@Column(name="ACCT_EXT_NAME")
	private int expertiseName;
	
	@ManyToMany(mappedBy="expertises", fetch=FetchType.LAZY)
	private List<InterviewerVO> interviewers;
	
	public int getExpertiseId() {
		return expertiseId;
	}
	
	public void setExpertiseId(int expertiseId) {
		this.expertiseId = expertiseId;
	}
	
	public int getExpertiseName() {
		return expertiseName;
	}
	
	public void setExpertiseName(int expertiseName) {
		this.expertiseName = expertiseName;
	}

	public List<InterviewerVO> getInterviewers() {
		return interviewers;
	}

	public void setInterviewers(List<InterviewerVO> interviewers) {
		this.interviewers = interviewers;
	}
	
}
