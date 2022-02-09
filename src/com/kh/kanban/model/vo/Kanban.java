package com.kh.kanban.model.vo;

import java.util.Date;

public class Kanban {
	
	
	private int proNo;
	private String empNo;
	private String proNm;
	private String proContent;
	private int status;
	private Date startDate;
	private String endDate;
	
	
	public Kanban(int proNo, int status) {
		super();
		this.proNo = proNo;
		this.status = status;
	}



	public Kanban(String empNo, String proNm, String proContent, String endDate) {
		super();
		this.empNo = empNo;
		this.proNm = proNm;
		this.proContent = proContent;
		this.endDate = endDate;
	}





	public Kanban(int proNo, String empNo, String proNm, String proContent, int status, Date startDate,
			String endDate) {
		super();
		this.proNo = proNo;
		this.empNo = empNo;
		this.proNm = proNm;
		this.proContent = proContent;
		this.status = status;
		this.startDate = startDate;
		this.endDate = endDate;
	}


	public Kanban(int proNo, String proNm, String proContent, String endDate) {
		super();
		this.proNo = proNo;
		this.proNm = proNm;
		this.proContent = proContent;
		this.endDate = endDate;
	}



	public int getProNo() {
		return proNo;
	}



	public void setProNo(int proNo) {
		this.proNo = proNo;
	}



	public String getEmpNo() {
		return empNo;
	}



	public void setEmpNo(String empNo) {
		this.empNo = empNo;
	}



	public String getProNm() {
		return proNm;
	}



	public void setProNm(String proNm) {
		this.proNm = proNm;
	}



	public String getProContent() {
		return proContent;
	}



	public void setProContent(String proContent) {
		this.proContent = proContent;
	}



	public int getStatus() {
		return status;
	}



	public void setStatus(int status) {
		this.status = status;
	}



	public Date getStartDate() {
		return startDate;
	}



	public void setStartDate(Date startDate) {
		this.startDate = startDate;
	}



	public String getEndDate() {
		return endDate;
	}



	public void setEndDate(String endDate) {
		this.endDate = endDate;
	}



	@Override
	public String toString() {
		return "Kanban [proNo=" + proNo + ", empNo=" + empNo + ", proNm=" + proNm + ", proContent=" + proContent
				+ ", status=" + status + ", startDate=" + startDate + ", endDate=" + endDate + "]";
	}



	
	
	
	
	
}
