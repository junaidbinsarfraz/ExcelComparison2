package com.excelcomparison.model;

import java.io.Serializable;
import java.util.Date;

public class Employee implements Serializable, Cloneable {

	private Integer rowId;
	private String firstName;
	private String lastName;
	private String position;
	private String designation;
	private String department;
	private Date dateJoined;
	private String contactNo;
	private String address;
	private Boolean fromDataSetA;
	private String status;
	private String remarks = "";

	public Integer getRowId() {
		return rowId;
	}

	public void setRowId(Integer rowId) {
		this.rowId = rowId;
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

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public String getDesignation() {
		return designation;
	}

	public void setDesignation(String designation) {
		this.designation = designation;
	}

	public String getDepartment() {
		return department;
	}

	public void setDepartment(String department) {
		this.department = department;
	}

	public Date getDateJoined() {
		return dateJoined;
	}

	public void setDateJoined(Date dateJoined) {
		this.dateJoined = dateJoined;
	}

	public String getContactNo() {
		return contactNo;
	}

	public void setContactNo(String contactNo) {
		this.contactNo = contactNo;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Boolean getFromDataSetA() {
		return fromDataSetA;
	}

	public void setFromDataSetA(Boolean fromDataSetA) {
		this.fromDataSetA = fromDataSetA;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	public String getRemarks() {
		return remarks;
	}

	public void setRemarks(String remarks) {
		this.remarks = remarks;
	}

	public Object clone() throws CloneNotSupportedException {
		return super.clone();
	}

	@Override
	public boolean equals(Object obj) {
		if (obj == this)
			return true;
		if (!(obj instanceof Employee)) {
			return false;
		}

		Employee employee = (Employee) obj;

		return employee.getFirstName().equals(this.getFirstName())
				&& employee.getLastName().equals(this.getLastName())
				&& employee.getPosition().equals(this.getPosition())
				&& employee.getDesignation().equals(this.getDesignation())
				&& employee.getDepartment().equals(this.getDepartment());

	}

}
