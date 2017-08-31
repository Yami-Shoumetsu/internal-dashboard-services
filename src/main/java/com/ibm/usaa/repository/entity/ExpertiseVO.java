package com.ibm.usaa.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "ACCOUNT_EXPERTISE")
public class ExpertiseVO {

	@Id
	@Column(name="ACCT_EXT_ID", nullable=false, unique=true)
	private int expertiseId;
	
	@Column(name="ACCT_EXT_NAME")
	private int expertiseName;
	
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
	
}
