package com.ibm.usaa.repository.entity;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "INTERVIEWER_EXPERTISE")
public class InterviewerExpertise {

	@OneToMany
	@JoinColumn(name="INT_ID")
	private InterviewerVO interviewerVO;
	
	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="ACCT_EXT_ID")
	private ExpertiseVO expertiseVO;

	public InterviewerVO getInterviewerVO() {
		return interviewerVO;
	}

	public void setInterviewerVO(InterviewerVO interviewerVO) {
		this.interviewerVO = interviewerVO;
	}

	public ExpertiseVO getExpertiseVO() {
		return expertiseVO;
	}

	public void setExpertiseVO(ExpertiseVO expertiseVO) {
		this.expertiseVO = expertiseVO;
	}
	
}
