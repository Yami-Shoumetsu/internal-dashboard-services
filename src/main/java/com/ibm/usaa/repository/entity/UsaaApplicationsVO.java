package com.ibm.usaa.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.ManyToMany;
import javax.persistence.Table;

@Entity
@Table(name = "USAA_APPLICATIONS")
public class UsaaApplicationsVO {

	@Id
	@GeneratedValue
	@Column(name="APP_ID", nullable=false, unique=true)
	private Integer applicationId;
	
	@Column(name="APP_NAME")
	private String applicationName;
	
	@ManyToMany(mappedBy="ownedApplications")
	private List<EmployeeVO> applicationOwners;

	public Integer getApplicationId() {
		return applicationId;
	}

	public void setApplicationId(Integer applicationId) {
		this.applicationId = applicationId;
	}

	public String getApplicationName() {
		return applicationName;
	}

	public void setApplicationName(String applicationName) {
		this.applicationName = applicationName;
	}

	public List<EmployeeVO> getApplicationOwners() {
		return applicationOwners;
	}

	public void setApplicationOwners(List<EmployeeVO> applicationOwners) {
		this.applicationOwners = applicationOwners;
	}
	
}
