package com.kh.board.model.service;

// JDBCTemplate 
import static com.kh.common.JDBCTemplate.*;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.attachment.model.vo.Attachment;
import com.kh.board.model.dao.BoardDao;
import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;

public class BoardService {

	// 경조사 게시글 목록
	public int selectListCount() {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기 (리스트의 개수)
		int listCount = new BoardDao().selectListCount(conn);
		
		// SELECT -> 커밋, 롤백 X 
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return listCount;
		
	}
	
	// 경조사 검색 조회
	public ArrayList<Board> searchList(String searchField, String searchWord) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> ArrayList 로 받기
		ArrayList<Board> list = new BoardDao().searchList(conn, searchField, searchWord);
		
		// SELECT -> 커밋, 롤백 X 
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return list;
		
	}
	
	public ArrayList<Board> selectList(PageInfo pi) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> ArrayList 로 받기
		ArrayList<Board> list = new BoardDao().selectList(conn, pi);
		
		// SELECT -> 커밋, 롤백 X 
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return list;
		
	}	
	
	// 조회수
	public int increaseCount(int boardNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		int result = new BoardDao().increaseCount(conn, boardNo);
		
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
	
	// 경조사 게시글 조회
	public Board selectBoard(int boardNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> Board 로 받기
		Board b = new BoardDao().selectBoard(conn, boardNo);
		
		// SELECT -> 커밋, 롤백 X
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return b;
		
	}
	
	// 경조사 게시글 첨부파일 조회
	public Attachment selectAttachment(int boardNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> Attachment 로 받기
		Attachment at = new BoardDao().selectAttachment(conn, boardNo);
	
		// SELECT -> 커밋, 롤백 X
		
		// Connection 객체 반납
		close(conn);
		
		// Controller 단으로 결과값 반환
		return at;
		
	}
	
	// 경조사 게시글 작성
	public int insertBoard(Board b, Attachment at) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		// Board 테이블에 INSERT
		int result1 = new BoardDao().insertBoard(conn, b); // Board INSERT 결과값
		
		int result2 = 1; // Attachment INSERT 결과값
		
		// Attachment 테이블에 INSERT
		if(at != null) {
			result2 = new BoardDao().insertAttachment(conn, at);
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
	public int updateBoard(Board b, Attachment at) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		// Board 테이블에 INSERT
		int result1 = new BoardDao().updateBoard(conn, b); // Board UPDATE 결과값
		
		int result2 = 1; // Attachment UPDATE 결과값
		
		// Attachment 테이블에 UPDATE
		if(at != null) {
			
			if(at.getFileNo() != 0) { // 기존의 첨부파일이 있었을 경우
				result2 = new BoardDao().updateAttachment(conn, at);
			}
			else {	// 없었을 경우
				result2 = new BoardDao().insertNewAttachment(conn, at); 
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
	public int deleteBoard(int boardNo) {
		
		// Connection 객체
		Connection conn = getConnection();
		
		// DAO 단으로 토스 -> int 로 받기
		int result = new BoardDao().deleteBoard(conn, boardNo);
		
		// DELETE -> 커밋, 롤백
		if(result > 0) {
			commit(conn);
		}
		else {
			rollback(conn);
		}
		
		return result;
	
	}
	
	public ArrayList<Board> selectBoardMainList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Board> main2 = new BoardDao().selectBoardMainList(conn);
		
		JDBCTemplate.close(conn);
		
		return main2;
	}
	
	
}
