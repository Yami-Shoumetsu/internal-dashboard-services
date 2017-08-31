package com.ibm.usaa.repository.entity;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table(name = "OWNED_APPLICATIONS")
public class OwnedApplicationsVO {

	@ManyToOne(fetch = FetchType.LAZY)
	@JoinColumn(name="EMP_ID")
	private EmployeeVO employeeVO;
	
	@OneToMany
	@JoinColumn(name="APP_ID")
	private List<UsaaApplicationsVO> usaaAppList;

	public EmployeeVO getEmployeeVO() {
		return employeeVO;
	}

	public void setEmployeeVO(EmployeeVO employeeVO) {
		this.employeeVO = employeeVO;
	}

	public List<UsaaApplicationsVO> getUsaaAppList() {
		return usaaAppList;
	}

	public void setUsaaAppVO(List<UsaaApplicationsVO> usaaAppList) {
		this.usaaAppList = usaaAppList;
	}

	
}
