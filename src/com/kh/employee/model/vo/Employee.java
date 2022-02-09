package com.kh.employee.model.vo;

import java.util.Date;

public class Employee {
	
	private int empNo;//  EMP_NO NUMBER PRIMARY KEY,
	private String deptId;//  DEPT_ID VARCHAR2(10) NOT NULL,
	private String jobCode;//  JOB_CODE VARCHAR2(10) NOT NULL, 
	private String empName;//  EMP_NAME VARCHAR2(30) NOT NULL, 
	private String empPwd;//  EMP_PWD VARCHAR2(20) NOT NULL, 
	private String empidNo;//  EMPID_NO VARCHAR2(15), 
	private String phone;//  PHONE VARCHAR2(15), 
	private String email;//  EMAIL VARCHAR2(30), 
	private String address;//  ADDRESS VARCHAR2(300),
	private Date hireDate;//  HIRE_DATE DATE DEFAULT SYSDATE NOT NULL,
	private String retireDate;//  RETIRE_DATE DATE, 
	private String status;//  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')) NOT NULL,
	
	public Employee() {
		super();
	}

	public Employee(int empNo, String deptId, String jobCode, String status) {
		super();
		this.empNo = empNo;
		this.deptId = deptId;
		this.jobCode = jobCode;
		this.status = status;
	}

	public Employee(int empNo, String deptId, String jobCode, String empName, String empPwd, String empidNo, String phone,
			String email, String address, Date hireDate, String retireDate, String status) {
		super();
		this.empNo = empNo;
		this.deptId = deptId;
		this.jobCode = jobCode;
		this.empName = empName;
		this.empPwd = empPwd;
		this.empidNo = empidNo;
		this.phone = phone;
		this.email = email;
		this.address = address;
		this.hireDate = hireDate;
		this.retireDate = retireDate;
		this.status = status;
	}
	
	
	public Employee(int empNo, String empName, String deptId, String jobCode, String address, String email, String phone,
			Date hireDate) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.deptId = deptId;
		this.jobCode = jobCode;
		this.address = address;
		this.email = email;
		this.phone = phone;
		this.hireDate = hireDate;
	}

	public Employee(int empNo, String empName, String empPwd, String empidNo, String phone, String email,
			String address) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.empPwd = empPwd;
		this.empidNo = empidNo;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	
	public Employee(int empNo, String empName, String phone, String email,
			String address) {
		super();
		this.empNo = empNo;
		this.empName = empName;
		this.phone = phone;
		this.email = email;
		this.address = address;
	}
	
	public Employee(String empName, String phone) {
		super();
		this.empName = empName;
		this.phone = phone;
	}

	
	public int getEmpNo() {
		return empNo;
	}

	public void setEmpNo(int empNo) {
		this.empNo = empNo;
	}

	public String getDeptId() {
		return deptId;
	}

	public void setDeptId(String deptId) {
		this.deptId = deptId;
	}

	public String getJobCode() {
		return jobCode;
	}

	public void setJobCode(String jobCode) {
		this.jobCode = jobCode;
	}

	public String getEmpName() {
		return empName;
	}

	public void setEmpName(String empName) {
		this.empName = empName;
	}

	public String getEmpPwd() {
		return empPwd;
	}

	public void setEmpPwd(String empPwd) {
		this.empPwd = empPwd;
	}

	public String getEmpidNo() {
		return empidNo;
	}

	public void setEmpidNo(String empidNo) {
		this.empidNo = empidNo;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getEmail() {
		return email;
	}

	public void setEmail(String email) {
		this.email = email;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public Date getHireDate() {
		return hireDate;
	}

	public void setHireDate(Date hireDate) {
		this.hireDate = hireDate;
	}

	public String getRetireDate() {
		return retireDate;
	}

	public void setRetireDate(String retireDate) {
		this.retireDate = retireDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Employee [empNo=" + empNo + ", deptId=" + deptId + ", jobCode=" + jobCode + ", empName=" + empName + ", empPwd="
				+ empPwd + ", empidNo=" + empidNo + ", phone=" + phone + ", email=" + email + ", address=" + address
				+ ", hireDate=" + hireDate + ", retireDate=" + retireDate + ", status=" + status + "]";
	}
	
	
	
	
	
}
