package com.ibm.usaa.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_EXPERTISE")
public class ExpertiseVO {

	@Id
	@GeneratedValue
	@Column(name="ACCT_EXT_ID", nullable=false, unique=true)
	private Integer expertiseId;
	
	@Column(name="ACCT_EXT_NAME")
	private String expertiseName;
	
	@ManyToMany(mappedBy="expertises", fetch=FetchType.LAZY)
	private List<InterviewerVO> interviewers;
	
	public Integer getExpertiseId() {
		return expertiseId;
	}
	
	public void setExpertiseId(Integer expertiseId) {
		this.expertiseId = expertiseId;
	}
	
	public String getExpertiseName() {
		return expertiseName;
	}
	
	public void setExpertiseName(String expertiseName) {
		this.expertiseName = expertiseName;
	}

	public List<InterviewerVO> getInterviewers() {
		return interviewers;
	}

	public void setInterviewers(List<InterviewerVO> interviewers) {
		this.interviewers = interviewers;
	}
	
}
