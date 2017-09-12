package com.ibm.usaa.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_TYPE")
public class EmployeeTypeVO {

	@Id
	@GeneratedValue
	@Column(name="EMP_TYPE_ID", nullable=false, unique=true)
	private Integer employeeTypeId;
	
	@Column(name="EMP_TYPE_NAME")
	private String employeeTypeName;

	public Integer getEmployeeTypeId() {
		return employeeTypeId;
	}

	public void setEmployeeTypeId(Integer employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}

	public String getEmployeeTypeName() {
		return employeeTypeName;
	}

	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	
}
