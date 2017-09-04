package com.ibm.usaa.repository.entity;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToOne;
import javax.persistence.Table;

@Entity
@Table(name = "USAA_EMPLOYEES")
public class EmployeeVO {

	@Id
	@Column(name="EMP_ID", nullable=false, unique=true)
	private String employeeId;
	
	@Column(name="EMP_FIRST_NAME")
	private String firstName;
	
	@Column(name="EMP_LAST_NAME")
	private String lastName;
	
	@Column(name="EMP_CONTACT_NUMBER")
	private String contactNumber;
	
	@Column(name="EMP_EMAIL")
	private String email;

	@OneToOne
	@JoinColumn(name="EMP_TYPE_ID")
	private EmployeeTypeVO employeeTypeVO;
	
	@ManyToMany
	@JoinTable(name="OWNED_APPLICATIONS", joinColumns=@JoinColumn(name="EMP_ID"), inverseJoinColumns=@JoinColumn(name="APP_ID"))
	private List<UsaaApplicationsVO> ownedApplications;
	
	public String getEmployeeId() {
		return employeeId;
	}

	public void setEmployeeId(String employeeId) {
		this.employeeId = employeeId;
	}

	public String getFirstName() {
		return firstName;
	}

	public void setFirstName(String firstName) {
		this.firstName = firstName;
	}

	public String getLastName() {
		return lastName;
	}

	public void setLastName(String lastName) {
		this.lastName = lastName;
	}

	public String getContactNumber() {
		return contactNumber;
	}

	public void setContactNumber(String contactNumber) {
		this.contactNumber = contactNumber;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public EmployeeTypeVO getEmployeeTypeVO() {
		return employeeTypeVO;
	}

	public void setEmployeeTypeVO(EmployeeTypeVO employeeTypeVO) {
		this.employeeTypeVO = employeeTypeVO;
	}

	public List<UsaaApplicationsVO> getOwnedApplications() {
		return ownedApplications;
	}

	public void setOwnedApplications(List<UsaaApplicationsVO> ownedApplications) {
		this.ownedApplications = ownedApplications;
	}

}
