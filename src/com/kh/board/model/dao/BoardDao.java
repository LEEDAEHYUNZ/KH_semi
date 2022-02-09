package com.kh.board.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.attachment.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;

//JDBCTemplate 
import static com.kh.common.JDBCTemplate.*;

public class BoardDao {

	// Properties 객체 생성
	private Properties prop = new Properties();
	
	public BoardDao() {
		
		// xml 파일
		String fileName = BoardDao.class.getResource("/sql/board/board-mapper.xml").getPath();
		
		try {
			// xml 파일 불러오기
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	// 경조사 게시글 목록
	public int selectListCount(Connection conn) {
		
		// SELECT -> ResultSet -> int
		
		// 필요한 요소
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectListCount");
		
		try {
			// pstmt 객체 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 채우기 -> 패스
			
			// 실행하기
			rset = pstmt.executeQuery();
			
			// listCount 에 결과값 담기 
			if(rset.next()) {
				listCount = rset.getInt("COUNT");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(rset);
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return listCount;
		
	}

	// 경조사 검색 조회
	public ArrayList<Board> searchList(Connection conn, String searchField, String searchWord) {
		
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			if(searchField.equals("title")) {
				String sql = prop.getProperty("selectTitleSearch");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchWord+ "%");
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {

					Board b = new Board();
					b.setBoardNo(rset.getInt("BOARD_NO"));
					b.setBoardTitle(rset.getString("BOARD_TITLE"));
					b.setBoardContent(rset.getString("BOARD_CONTENT"));
					b.setBoardWriter(rset.getString("EMP_NAME"));
					b.setPostDate(rset.getDate("POST_DATE"));
					b.setVisitCount(rset.getInt("VISIT_COUNT"));
					b.setStatus(rset.getString("STATUS"));
					
					list.add(b);
				}
	
			}
			else if(searchField.equals("writer")) {
				String sql = prop.getProperty("selectWriterSearch");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchWord+ "%");
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {

					Board b = new Board();
					b.setBoardNo(rset.getInt("BOARD_NO"));
					b.setBoardTitle(rset.getString("BOARD_TITLE"));
					b.setBoardContent(rset.getString("BOARD_CONTENT"));
					b.setBoardWriter(rset.getString("EMP_NAME"));
					b.setPostDate(rset.getDate("POST_DATE"));
					b.setVisitCount(rset.getInt("VISIT_COUNT"));
					b.setStatus(rset.getString("STATUS"));
					
					list.add(b);
				}
			}		
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
	
		return list;
	}
	
	// 페이지네이션
	public ArrayList<Board> selectList(Connection conn, PageInfo pi) {
		
		// SELECT -> ResultSet -> ArrayList<Board>
		
		// 필요한 요소
		ArrayList<Board> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		String sql = prop.getProperty("selectList");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			int startRow = (pi.getCurrentPage() -1) * pi.getListLimit() + 1;
			int endRow = startRow + pi.getListLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			// 실행하기
			rset = pstmt.executeQuery();
			
			// rset 값 list 에 담기
			while(rset.next()) {
				
				list.add(new Board(rset.getInt("BOARD_NO"),
								   rset.getString("BOARD_TITLE"),
								   rset.getString("EMP_NAME"),
								   rset.getDate("POST_DATE"),
								   rset.getInt("VISIT_COUNT")));

			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(rset);
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return list;
		
	}
	
	// 조회수
	public int increaseCount(Connection conn, int boardNo) {
		
		// UPDATE -> int 
		
		// 필요한 요소
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, boardNo);
			
			// 실행하기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return result;
		
	}
	
	// 경조사 게시글 조회
	public Board selectBoard(Connection conn, int boardNo) {
		
		// SELECT -> ResultSet -> Board

		// 필요한 요소
		Board b = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectBoard");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, boardNo);
			
			// 실행하기
			rset = pstmt.executeQuery();
			
			// if문으로 값 b에 넣기
			if(rset.next()) { // 값이 있을 때까지 반복
				b = new Board(rset.getInt("BOARD_NO")
						    , rset.getString("EMP_NAME")
						    , rset.getDate("POST_DATE")
						    , rset.getInt("VISIT_COUNT")
						    , rset.getString("BOARD_TITLE")
						    , rset.getString("BOARD_CONTENT"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(rset);
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return b;
		
	}
	
	// 경조사 게시글 첨부파일 조회
	public Attachment selectAttachment(Connection conn, int boardNo) {
		
		// SELECT -> ResultSet -> Attachment

		// 필요한 요소
		Attachment at = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectAttachment");
	
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, boardNo);
			
			// 실행하기
			rset = pstmt.executeQuery();
			
			// if문으로 값 at에 넣기
			if(rset.next()) { // 값이 있을 때까지 반복
				at = new Attachment();
				
				at.setFileNo(rset.getInt("FILE_NO"));
				at.setOriginName(rset.getString("ORIGIN_NAME"));
				at.setChangeName(rset.getString("CHANGE_NAME"));
				at.setFilePath(rset.getString("FILE_PATH"));
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(rset);
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return at;
		
	}
	
	// 경조사 게시글 작성_Board
	public int insertBoard(Connection conn, Board b) {
		
		// INSERT -> int
		
		// 필요한 요소 
		int result1 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertBoard");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setString(1, b.getBoardWriter());
			pstmt.setString(2, b.getBoardTitle());
			pstmt.setString(3, b.getBoardContent());
		
			// 실행하기
			result1 = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return result1;
		
	}

	// 경조사 게시글 작성_Attachment
	public int insertAttachment(Connection conn, Attachment at) {
		
		// INSERT -> int
		
		// 필요한 요소 
		int result2 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertAttachment");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
		
			// 실행하기
			result2 = pstmt.executeUpdate();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return result2;
		
	}
	
	// 경조사 글 수정_Board
	public int updateBoard(Connection conn, Board b) {
		
		// UPDATE -> int
		
		// 필요한 요소
		int result1 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateBoard");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setString(1, b.getBoardTitle());
			pstmt.setString(2, b.getBoardContent());
			pstmt.setInt(3, b.getBoardNo());
			
			// 실행하기
			result1 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return result1;
		
	}
	
	// 경조사 글 수정_Attachment
	public int updateAttachment(Connection conn, Attachment at) {
		
		// UPDATE -> int
		
		// 필요한 요소
		int result2 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateAttachment");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setString(1, at.getOriginName());
			pstmt.setString(2, at.getChangeName());
			pstmt.setString(3, at.getFilePath());
			pstmt.setInt(4, at.getFileNo());
			
			// 실행하기
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return result2;
		
		
	}
	
	// 경조사 글 수정_NewAttachment
	public int insertNewAttachment(Connection conn, Attachment at) {
		
		// INSERT -> int
		
		// 필요한 요소
		int result2 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNewAttachment");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, at.getRefBno());
			pstmt.setString(2, at.getOriginName());
			pstmt.setString(3, at.getChangeName());
			pstmt.setString(4, at.getFilePath());
			
			// 실행하기
			result2 = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return result2;
		
	}
	
	// 경조사 글 삭제_Board
	public int deleteBoard(Connection conn, int boardNo) {
		
		// DELETE -> int
		 
		// 필요한 요소
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteBoard");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, boardNo);
			
			// 실행하기
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return result;
		
	}
	
	public ArrayList<Board> selectBoardMainList(Connection conn) {
		ArrayList<Board> main2 = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectBoardMainList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Board b = new Board (rset.getInt("BOARD_NO")
						   , rset.getString("BOARD_TITLE")
						   , rset.getString("BOARD_CONTENT")
						   , rset.getString("EMP_NAME")
						   , rset.getDate("POST_DATE")
						   , rset.getInt("VISIT_COUNT")
						   , rset.getString("STATUS"));
				main2.add(b);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return main2;
	}

}
