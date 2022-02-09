package com.kh.notice.model.vo;

import java.sql.Date;

public class Notice {

	// [필드부]
	private int noticeNo; // NOTICE_NO NUMBER PRIMARY KEY,
	private String noticeTitle; // NOTICE_TITLE VARCHAR2(150) NOT NULL,
	private String noticeContent; // NOTICE_CONTENT VARCHAR2(1500) NOT NULL,
	private String noticeWriter; // NOTICE_WRITER NUMBER NOT NULL,
	private Date postDate; // POST_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int visitCount; // VISIT_COUNT NUMBER DEFAULT 0,
	private String status; // STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')) NOT NULL,
	
	// [생성자부]
	// 기본 생성자
	public Notice() {
		super();
	}
	
	// 추가 생성자
	public Notice(int noticeNo, String noticeTitle, String noticeWriter, Date postDate, int visitCount) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeWriter = noticeWriter;
		this.postDate = postDate;
		this.visitCount = visitCount;
	}

	public Notice(int noticeNo, String noticeWriter, Date postDate, int visitCount, String noticeTitle, String noticeContent) {
		super();
		this.noticeNo = noticeNo;
		this.noticeWriter = noticeWriter;
		this.postDate = postDate;
		this.visitCount = visitCount;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
	}

	// 모든 매개변수 생성자
	public Notice(int noticeNo, String noticeTitle, String noticeContent, String noticeWriter, Date postDate,
			int visitCount, String status) {
		super();
		this.noticeNo = noticeNo;
		this.noticeTitle = noticeTitle;
		this.noticeContent = noticeContent;
		this.noticeWriter = noticeWriter;
		this.postDate = postDate;
		this.visitCount = visitCount;
		this.status = status;
	}

	// [메소드부]
	// getter/setter
	public int getNoticeNo() {
		return noticeNo;
	}
	public void setNoticeNo(int noticeNo) {
		this.noticeNo = noticeNo;
	}
	public String getNoticeTitle() {
		return noticeTitle;
	}
	public void setNoticeTitle(String noticeTitle) {
		this.noticeTitle = noticeTitle;
	}
	public String getNoticeContent() {
		return noticeContent;
	}
	public void setNoticeContent(String noticeContent) {
		this.noticeContent = noticeContent;
	}
	public String getNoticeWriter() {
		return noticeWriter;
	}
	public void setNoticeWriter(String noticeWriter) {
		this.noticeWriter = noticeWriter;
	}
	public Date getPostDate() {
		return postDate;
	}
	public void setPostDate(Date postDate) {
		this.postDate = postDate;
	}
	public int getVisitCount() {
		return visitCount;
	}
	public void setVisitCount(int visitCount) {
		this.visitCount = visitCount;
	}
	public String getStatus() {
		return status;
	}
	public void setStatus(String status) {
		this.status = status;
	}
	
	// toString
	@Override
	public String toString() {
		return "Notice [noticeNo=" + noticeNo + ", noticeTitle=" + noticeTitle + ", noticeContent=" + noticeContent
				+ ", noticeWriter=" + noticeWriter + ", postDate=" + postDate + ", visitCount=" + visitCount
				+ ", status=" + status + "]";
	}
	
}
