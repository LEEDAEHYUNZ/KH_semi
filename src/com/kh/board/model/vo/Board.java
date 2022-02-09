package com.kh.board.model.vo;

import java.sql.Date;

public class Board {

	// [필드부]
	private int boardNo; // BOARD_NO NUMBER PRIMARY KEY,
	private String boardTitle; // BOARD_TITLE VARCHAR2(150) NOT NULL,
	private String boardContent; // BOARD_CONTENT VARCHAR2(1500) NOT NULL,
	private String boardWriter; // BOARD_WRITER NUMBER NOT NULL,
	private Date postDate; // POST_DATE DATE DEFAULT SYSDATE NOT NULL,
	private int visitCount; // VISIT_COUNT NUMBER DEFAULT 0,
	private String status; // STATUS VARCHAR2(1) DEFAULT 'Y' CHECK (STATUS IN('Y', 'N')) NOT NULL,
	
	// [생성자부]
	// 기본 생성자
	public Board() {
		super();
	}
	
	// 추가 생성자
	public Board(int boardNo, String boardTitle, String boardWriter, Date postDate, int visitCount) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardWriter = boardWriter;
		this.postDate = postDate;
		this.visitCount = visitCount;
	}
	public Board(int boardNo, String boardWriter, Date postDate, int visitCount, String boardTitle, String boardContent) {
		super();
		this.boardNo = boardNo;
		this.boardWriter = boardWriter;
		this.postDate = postDate;
		this.visitCount = visitCount;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
	}
	
	// 모든 매개변수 생성자
	public Board(int boardNo, String boardTitle, String boardContent, String boardWriter, Date postDate, int visitCount,
			String status) {
		super();
		this.boardNo = boardNo;
		this.boardTitle = boardTitle;
		this.boardContent = boardContent;
		this.boardWriter = boardWriter;
		this.postDate = postDate;
		this.visitCount = visitCount;
		this.status = status;
	}

	// [메소드부]
	// getter/setter
	public int getBoardNo() {
		return boardNo;
	}
	public void setBoardNo(int boardNo) {
		this.boardNo = boardNo;
	}
	public String getBoardTitle() {
		return boardTitle;
	}
	public void setBoardTitle(String boardTitle) {
		this.boardTitle = boardTitle;
	}
	public String getBoardContent() {
		return boardContent;
	}
	public void setBoardContent(String boardContent) {
		this.boardContent = boardContent;
	}
	public String getBoardWriter() {
		return boardWriter;
	}
	public void setBoardWriter(String boardWriter) {
		this.boardWriter = boardWriter;
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
		return "Board [boardNo=" + boardNo + ", boardTitle=" + boardTitle + ", boardContent=" + boardContent
				+ ", boardWriter=" + boardWriter + ", postDate=" + postDate + ", visitCount=" + visitCount + ", status="
				+ status + "]";
	}
	
}
