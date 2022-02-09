package com.kh.notice.model.dao;

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
import com.kh.notice.model.vo.Notice;

import static com.kh.common.JDBCTemplate.*;

public class NoticeDao {
	
	// Properties 객체 생성
	private Properties prop = new Properties();
	
	public NoticeDao() {
		
		// xml 파일
		String fileName = NoticeDao.class.getResource("/sql/notice/notice-mapper.xml").getPath();
		
		try {
			// xml 파일 불러오기
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	
	}
	
	// 공지사항 목록 개수
	public int selectListCount(Connection conn) {
		
		// SELECT -> ResultSet -> int
		
		// 필요한 요소
		int listCount = 0;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectListCount");
		
		try {
			// pstmt 객체
			pstmt = conn.prepareStatement(sql);
			
			// 빈값 채우기 -> 패스
			
			// 실행하기
			rset = pstmt.executeQuery();
			
			// listCount 에 값 담기
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

	// 공지사항 검색 결과
	public ArrayList<Notice> searchList(Connection conn, String searchField, String searchWord) {
		
		ArrayList<Notice> list = new ArrayList<>();
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		try {
			
			if(searchField.equals("title")) {
				String sql = prop.getProperty("selectTitleSearch");
				pstmt = conn.prepareStatement(sql);
				pstmt.setString(1, "%" + searchWord+ "%");
			}
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {

				Notice n = new Notice();
				n.setNoticeNo(rset.getInt("NOTICE_NO"));
				n.setNoticeTitle(rset.getString("NOTICE_TITLE"));
				n.setNoticeContent(rset.getString("NOTICE_CONTENT"));
				n.setNoticeWriter(rset.getString("EMP_NAME"));
				n.setPostDate(rset.getDate("POST_DATE"));
				n.setVisitCount(rset.getInt("VISIT_COUNT"));
				n.setStatus(rset.getString("STATUS"));
				
				list.add(n);
			}			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			close(rset);
			close(pstmt);
		}
		
		return list;
	}
	
	
	// 공지사항 페이지네이션
	public ArrayList<Notice> selectList(Connection conn, PageInfo pi) {
		
		// SELECT -> ResultSet -> ArrayList<Notice>
		
		// 필요한 요소
		ArrayList<Notice> list = new ArrayList<>();
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectList");
		
		try {
			// pstmt 객체
			pstmt = conn.prepareStatement(sql);
			
			// 빈값 채우기 -> 패스
			// 추가 변수
			int startRow = (pi.getCurrentPage() - 1) * pi.getListLimit() + 1;
			int endRow = startRow + pi.getListLimit() - 1;
			
			pstmt.setInt(1, startRow);
			pstmt.setInt(2, endRow);
			
			// 실행하기
			rset = pstmt.executeQuery();

			// list에 값 담기
			while(rset.next()) {
				list.add(new Notice(rset.getInt("NOTICE_NO"),
								    rset.getString("NOTICE_TITLE"),
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
	public int increaseCount(Connection conn, int noticeNo) {
		
		// UPDATE -> int 
		
		// 필요한 요소
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("increaseCount");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, noticeNo);
			
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
	
	// 공지사항 게시글 조회
	public Notice selectNotice(Connection conn, int noticeNo) {
		
		// SELECT -> ResultSet -> Board

		// 필요한 요소
		Notice n = null;
		ResultSet rset = null;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("selectNotice");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, noticeNo);
			
			// 실행하기
			rset = pstmt.executeQuery();
			
			// if문으로 값 b에 넣기
			if(rset.next()) { // 값이 있을 때까지 반복
				n = new Notice(rset.getInt("NOTICE_NO")
						    , rset.getString("EMP_NAME")
						    , rset.getDate("POST_DATE")
						    , rset.getInt("VISIT_COUNT")
						    , rset.getString("NOTICE_TITLE")
						    , rset.getString("NOTICE_CONTENT"));
			}
			
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			// 자원 반납
			close(rset);
			close(pstmt);
		}
		
		// Service 단으로 결과값 반환
		return n;
		
	}
	
	// 공지사항 게시글 첨부파일 조회
	public Attachment selectAttachment(Connection conn, int noticeNo) {
		
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
			pstmt.setInt(1, noticeNo);
			
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
	
	// 공지사항 게시글 작성_Notice
	public int insertNotice(Connection conn, Notice n) {
		
		// INSERT -> int
		
		// 필요한 요소 
		int result1 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("insertNotice");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setString(1, n.getNoticeWriter());
			pstmt.setString(2, n.getNoticeTitle());
			pstmt.setString(3, n.getNoticeContent());
		
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

	// 공지사항 게시글 작성_Attachment
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
	
	// 공지사항 글 수정_Notice
	public int updateNotice(Connection conn, Notice n) {
		
		// UPDATE -> int
		
		// 필요한 요소
		int result1 = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("updateNotice");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setString(1, n.getNoticeTitle());
			pstmt.setString(2, n.getNoticeContent());
			pstmt.setInt(3, n.getNoticeNo());
			
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
	
	// 공지사항 글 수정_Attachment
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
	
	// 공지사항 글 수정_NewAttachment
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
			pstmt.setInt(1, at.getRefNno());
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
	
	// 공지사항 글 삭제_Notice
	public int deleteNotice(Connection conn, int noticeNo) {
		
		// DELETE -> int
		 
		// 필요한 요소
		int result = 0;
		PreparedStatement pstmt = null;
		String sql = prop.getProperty("deleteNotice");
		
		try {
			// pstmt 생성
			pstmt = conn.prepareStatement(sql);
			
			// 비어있는 값 넣기
			pstmt.setInt(1, noticeNo);
			
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
	
	public ArrayList<Notice> selectNoticeMainList(Connection conn) {
		ArrayList<Notice> main1 = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectNoticeMainList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Notice n = new Notice (rset.getInt("NOTICE_NO")
						   , rset.getString("NOTICE_TITLE")
						   , rset.getString("NOTICE_CONTENT")
						   , rset.getString("EMP_NAME")
						   , rset.getDate("POST_DATE")
						   , rset.getInt("VISIT_COUNT")
						   , rset.getString("STATUS"));
				main1.add(n);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return main1;
	}
	
}
