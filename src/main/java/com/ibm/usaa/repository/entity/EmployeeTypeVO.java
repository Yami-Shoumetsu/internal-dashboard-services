package com.ibm.usaa.repository.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

@Entity
@Table(name = "EMPLOYEE_TYPE")
public class EmployeeTypeVO {

	@Id
	@Column(name="EMP_TYPE_ID", nullable=false, unique=true)
	private int employeeTypeId;
	
	@Column(name="EMP_TYPE_NAME")
	private String employeeTypeName;

	public int getEmployeeTypeId() {
		return employeeTypeId;
	}

	public void setEmployeeTypeId(int employeeTypeId) {
		this.employeeTypeId = employeeTypeId;
	}

	public String getEmployeeTypeName() {
		return employeeTypeName;
	}

	public void setEmployeeTypeName(String employeeTypeName) {
		this.employeeTypeName = employeeTypeName;
	}
	
}
