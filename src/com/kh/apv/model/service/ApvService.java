package com.kh.apv.model.service;

import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.apv.model.dao.ApvDao;
import com.kh.apv.model.vo.Apv;
import com.kh.attachment.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;

public class ApvService {

	// 결재목록
	public int selectListCount() {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기 (리스트의 개수)
		int listCount = new ApvDao().selectListCount(conn);
		
		// SELECT -> 커밋, 롤백 X 
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return listCount;
		
	}
	
	
	public ArrayList<Apv> selectList(PageInfo pi) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> ArrayList 로 받기
		ArrayList<Apv> list = new ApvDao().selectList(conn, pi);
		
		// SELECT -> 커밋, 롤백 X 
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return list;
		
	}		
	
	
	// 결재 게시글 조회
	public Apv selectApv(int apvNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> Apv 로 받기
		Apv a = new ApvDao().selectApv(conn, apvNo);
		
		// SELECT -> 커밋, 롤백 X
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return a;
		
	}
	
	// 결재 첨부파일 조회
	public Attachment selectAttachment(int ApvNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> Attachment 로 받기
		Attachment at = new ApvDao().selectAttachment(conn, ApvNo);
	
		// SELECT -> 커밋, 롤백 X
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return at;
		
	}
	
	// 결재양식 작성
	public int insertApv(Apv a, Attachment at) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		// Board 테이블에 INSERT
		int result1 = new ApvDao().insertApv(conn, a); // Board INSERT 결과값
		
		int result2 = 1; // Attachment INSERT 결과값
		
		// Attachment 테이블에 INSERT
		if(at != null) {
			result2 = new ApvDao().insertAttachment(conn, at);
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
	
	// 경조사 글 수정
	public int updateApv(Apv a, Attachment at) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		// Apv 테이블에 INSERT
		int result1 = new ApvDao().updateApv(conn, a); // Apv UPDATE 결과값
		
		int result2 = 1; // Attachment UPDATE 결과값
		
		// Attachment 테이블에 UPDATE
		if(at != null) {
			
			if(at.getFileNo() != 0) { // 기존의 첨부파일이 있었을 경우
				result2 = new ApvDao().updateAttachment(conn, at);
			}
			else {	// 없었을 경우
				result2 = new ApvDao().insertNewAttachment(conn, at); 
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
	
	// 경조사 글 삭제
	public int deleteApv(int apvNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		int result = new ApvDao().deleteApv(conn, apvNo);
		
		// DELETE -> 커밋, 롤백
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	
	}
	
}
