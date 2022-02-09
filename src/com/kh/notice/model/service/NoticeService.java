package com.kh.notice.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.attachment.model.vo.Attachment;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.dao.NoticeDao;
import com.kh.notice.model.vo.Notice;

public class NoticeService {

	// 공지사항 게시글 수
	public int selectListCount() {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스
		int listCount = new NoticeDao().selectListCount(conn);
		
		// SELECT -> 커밋, 롤백 X
		
		// Connection 자원 반납
		close(conn);
		
		// Controller 단으로 결과 반납
		return listCount;
		
	}
	
	// 공지사항 페이지네이션
	public ArrayList<Notice> selectList(PageInfo pi) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스
		ArrayList<Notice> list = new NoticeDao().selectList(conn, pi);
		
		// SELECT -> 커밋, 롤백 X
		
		// Connection 자원 반납
		close(conn);
		
		// Controller 단으로 결과 반납
		return list;
		
	}

	// 조회수
	public int increaseCount(int noticeNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		int result = new NoticeDao().increaseCount(conn, noticeNo);
		
		// UPDATE -> 커밋, 롤백
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return result;
		
	}
	
	// 공지사항 검색 조회
	public ArrayList<Notice> searchList(String searchField, String searchWord) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> Notice 로 받기
		ArrayList<Notice> list = new NoticeDao().searchList(conn, searchField, searchWord);
		
		// SELECT -> 커밋, 롤백 X
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return list;		
		
	}
	
	
	// 공지사항 게시글 조회
	public Notice selectNotice(int noticeNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> Notice 로 받기
		Notice n = new NoticeDao().selectNotice(conn, noticeNo);
		
		// SELECT -> 커밋, 롤백 X
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return n;
		
	}
	
	// 공지사항 게시글 첨부파일 조회
	public Attachment selectAttachment(int noticeNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> Attachment 로 받기
		Attachment at = new NoticeDao().selectAttachment(conn, noticeNo);
	
		// SELECT -> 커밋, 롤백 X
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return at;
		
	}
	
	// 공지사항 게시글 작성
	public int insertNotice(Notice n, Attachment at) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		// Board 테이블에 INSERT
		int result1 = new NoticeDao().insertNotice(conn, n); // Notice INSERT 결과값
		
		int result2 = 1; // Attachment INSERT 결과값
		
		// Attachment 테이블에 INSERT
		if(at != null) {
			result2 = new NoticeDao().insertAttachment(conn, at);
		}
		
		// INSERT -> 커밋, 롤백 
		if((result1 * result2) > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return (result1 * result2);
		
	}
	
	// 공지사항 글 수정
	public int updateNotice(Notice n, Attachment at) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		// Notice 테이블에 INSERT
		int result1 = new NoticeDao().updateNotice(conn, n); // Notice UPDATE 결과값
		
		int result2 = 1; // Attachment UPDATE 결과값
		
		// Attachment 테이블에 UPDATE
		if(at != null) {
			
			if(at.getFileNo() != 0) { // 기존의 첨부파일이 있었을 경우
				result2 = new NoticeDao().updateAttachment(conn, at);
			}
			else {	// 없었을 경우
				result2 = new NoticeDao().insertNewAttachment(conn, at); 
			}
		
		}
		
		// UPDATE -> 커밋, 롤백 
		if(result1 > 0 && result2 > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		// Connection 객체 반납
		close(conn);
		
		
		// Controller 단으로 결과값 반환
		return (result1 * result2);
		
	}
	
	// 공지사항 글 삭제
	public int deleteNotice(int noticeNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		int result = new NoticeDao().deleteNotice(conn, noticeNo);
		
		// DELETE -> 커밋, 롤백
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	
	}
	
	public ArrayList<Notice> selectNoticeMainList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Notice> main1 = new NoticeDao().selectNoticeMainList(conn);
		
		JDBCTemplate.close(conn);
		
		return main1;
	}
	
	
	
}
