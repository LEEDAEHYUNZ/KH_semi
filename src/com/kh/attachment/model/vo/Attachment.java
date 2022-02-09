package com.kh.attachment.model.vo;

import java.sql.Date;

public class Attachment {

	// [필드부]
	private int fileNo; // FILE_NO NUMBER PRIMARY KEY,
	private int refNno; // REF_NNO NUMBER NOT NULL,  
	private int refBno; // REF_BNO NUMBER NOT NULL,
	private int refAno; // REF_ANO NUMBER NOT NULL,
	private String originName; // ORIGIN_NAME VARCHAR2(150) NOT NULL,
	private String changeName; // CHANGE_NAME VARCHAR2(150) NOT NULL,
	private String filePath; // FILE_PATH VARCHAR2(1000),
	private Date uploadDate; // UPLOAD_DATE DATE DEFAULT SYSDATE NOT NULL,
	private String status; // STATUS VARCHAR2(1) DEFAULT 'Y' CHECK(STATUS IN('Y', 'N')) NOT NULL,
	// [생성자부]
	// 기본 생성자
	public Attachment() {
		super();
	}
	
	// 추가 생성자
	
	
	// 모든 매개변수 생성자
	public Attachment(int fileNo, int refNno, int refBno, int refAno, String originName, String changeName,
			String filePath, Date uploadDate, String status) {
		super();
		this.fileNo = fileNo;
		this.refNno = refNno;
		this.refBno = refBno;
		this.refAno = refAno;
		this.originName = originName;
		this.changeName = changeName;
		this.filePath = filePath;
		this.uploadDate = uploadDate;
		this.status = status;
	}
	
	// [메소드부]
	// getter/setter
	public int getFileNo() {
		return fileNo;
	}
	public void setFileNo(int fileNo) {
		this.fileNo = fileNo;
	}
	public int getRefNno() {
		return refNno;
	}
	public void setRefNno(int refNno) {
		this.refNno = refNno;
	}
	public int getRefBno() {
		return refBno;
	}
	public void setRefBno(int refBno) {
		this.refBno = refBno;
	}
	public int getRefAno() {
		return refAno;
	}
	public void setRefAno(int refAno) {
		this.refAno = refAno;
	}
	public String getOriginName() {
		return originName;
	}
	public void setOriginName(String originName) {
		this.originName = originName;
	}
	public String getChangeName() {
		return changeName;
	}
	public void setChangeName(String changeName) {
		this.changeName = changeName;
	}
	public String getFilePath() {
		return filePath;
	}
	public void setFilePath(String filePath) {
		this.filePath = filePath;
	}
	public Date getUploadDate() {
		return uploadDate;
	}
	public void setUploadDate(Date uploadDate) {
		this.uploadDate = uploadDate;
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
		return "Attachment [fileNo=" + fileNo + ", refNno=" + refNno + ", refBno=" + refBno + ", refAno=" + refAno
				+ ", originName=" + originName + ", changeName=" + changeName + ", filePath=" + filePath
				+ ", uploadDate=" + uploadDate + ", status=" + status + "]";
	}
	
}
