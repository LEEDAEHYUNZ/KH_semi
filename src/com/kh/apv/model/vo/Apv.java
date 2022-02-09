package com.kh.apv.model.vo;

import java.util.Date;

public class Apv {
   
	private int apvNo;//	  APV_NO NUMBER PRIMARY KEY,
	private String apvTitle;//	  APV_TITLE VARCHAR2(150),
	private String apvManager;//	  APV_MANAGER NUMBER NOT NULL,
	private String apvContent;//	  APV_CONTENT VARCHAR2(1500) NOT NULL,
	private String apvWriter;//	  APV_WRITER NUMBER NOT NULL,
	private String apvReason;//	  APV_REASON VARCHAR2(1500),
	private int apvStatus;//	  APV_STATUS NUMBER DEFAULT 1 NOT NULL,
	private Date postDate;//	  POST_DATE DATE DEFAULT SYSDATE NOT NULL,
	private String status;//	  STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')) NOT NULL,  
	
	// 기본 생성자
	public Apv() {
		super();
	}
	
	// 추가 생성자
	public Apv(int apvNo, String apvTitle, String apvWriter, Date postDate) {
		super();
		this.apvNo = apvNo;
		this.apvTitle = apvTitle;
		this.apvWriter = apvWriter;
		this.postDate = postDate;
	}


	public Apv(int apvNo, String apvManager, Date postDate, String apvTitle, String apvContent) {
		super();
		this.apvNo = apvNo;
		this.apvManager = apvManager;
		this.postDate = postDate;
		this.apvTitle = apvTitle;
		this.apvContent = apvContent;
	}
	
	

	public Apv(int apvNo, String apvTitle, String apvWriter, Date postDate, int apvStatus) {
		super();
		this.apvNo = apvNo;
		this.apvTitle = apvTitle;
		this.apvWriter = apvWriter;
		this.postDate = postDate;
		this.apvStatus = apvStatus;
	}
	

	public Apv(int apvNo, String apvTitle, String apvContent, String apvWriter, Date postDate, int apvStatus) {
		super();
		this.apvNo = apvNo;
		this.apvTitle = apvTitle;
		this.apvContent = apvContent;
		this.apvWriter = apvWriter;
		this.postDate = postDate;
		this.apvStatus = apvStatus;
	}
	

	public Apv(int apvNo, String apvTitle, String apvManager, String apvContent, String apvWriter, String apvReason,
			int apvStatus, Date postDate, String status) {
		super();
		this.apvNo = apvNo;
		this.apvTitle = apvTitle;
		this.apvManager = apvManager;
		this.apvContent = apvContent;
		this.apvWriter = apvWriter;
		this.apvReason = apvReason;
		this.apvStatus = apvStatus;
		this.postDate = postDate;
		this.status = status;
	}


	public int getApvNo() {
		return apvNo;
	}

	public void setApvNo(int apvNo) {
		this.apvNo = apvNo;
	}

	public String getApvTitle() {
		return apvTitle;
	}

	public void setApvTitle(String apvTitle) {
		this.apvTitle = apvTitle;
	}

	public String getApvManager() {
		return apvManager;
	}

	public void setApvManager(String apvManager) {
		this.apvManager = apvManager;
	}

	public String getApvContent() {
		return apvContent;
	}

	public void setApvContent(String apvContent) {
		this.apvContent = apvContent;
	}

	public String getApvWriter() {
		return apvWriter;
	}

	public void setApvWriter(String apvWriter) {
		this.apvWriter = apvWriter;
	}

	public String getApvReason() {
		return apvReason;
	}

	public void setApvReason(String apvReason) {
		this.apvReason = apvReason;
	}

	public int getApvStatus() {
		return apvStatus;
	}

	public void setApvStatus(int apvStatus) {
		this.apvStatus = apvStatus;
	}

	public Date getPostDate() {
		return postDate;
	}

	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}

	public String getStatus() {
		return status;
	}

	public void setStatus(String status) {
		this.status = status;
	}

	@Override
	public String toString() {
		return "Apv [apvNo=" + apvNo + ", apvTitle=" + apvTitle + ", apvManager=" + apvManager + ", apvContent="
				+ apvContent + ", apvWriter=" + apvWriter + ", apvReason=" + apvReason + ", apvStatus=" + apvStatus
				+ ", postDate=" + postDate + ", status=" + status + "]";
	}

	
	
}