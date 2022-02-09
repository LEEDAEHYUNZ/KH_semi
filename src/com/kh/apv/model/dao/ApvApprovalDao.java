package com.kh.apv.model.dao;

//JDBCTemplate 
import static com.kh.common.JDBCTemplate.*;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.apv.model.vo.Apv;
import com.kh.attachment.model.vo.Attachment;
import com.kh.common.model.vo.PageInfo;

public class ApvApprovalDao {

	// Properties 객체 생성
	   private Properties prop = new Properties();
	   
	   public ApvApprovalDao() {
	      
	      // xml 파일
	      String fileName = ApvApprovalDao.class.getResource("/sql/apv/approval-mapper.xml").getPath();
	      
	      try {
	         // xml 파일 불러오기
	         prop.loadFromXML(new FileInputStream(fileName));
	      } catch (IOException e) {
	         e.printStackTrace();
	      }
	   
	   }
		
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

		// 페이지네이션
		public ArrayList<Apv> selectList(Connection conn, PageInfo pi) {
			
			// SELECT -> ResultSet -> ArrayList<Board>
			
			// 필요한 요소
			ArrayList<Apv> list = new ArrayList<>();
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
					
					list.add(new Apv(rset.getInt("APV_NO"),
									 rset.getString("APV_TITLE"),
									 rset.getString("EMP_NAME"),
									 rset.getDate("POST_DATE"),
									 rset.getInt("APV_STATUS")));

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

		//  조회
		public Apv selectApv(Connection conn, int apvNo) {
			
			// SELECT -> ResultSet -> Apv

			// 필요한 요소
			Apv a = null;
			ResultSet rset = null;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("selectApv");
			
			try {
				// pstmt 생성
				pstmt = conn.prepareStatement(sql);
				
				// 비어있는 값 넣기
				pstmt.setInt(1, apvNo);
				
				// 실행하기
				rset = pstmt.executeQuery();
				
				// if문으로 값 b에 넣기
				if(rset.next()) { // 값이 있을 때까지 반복
					a = new Apv(rset.getInt("APV_NO")
								, rset.getString("APV_TITLE")
								, rset.getString("APV_CONTENT")
							    , rset.getString("EMP_NAME")
							    , rset.getDate("POST_DATE")
							    , rset.getInt("APV_STATUS"));
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				// 자원 반납
				close(rset);
				close(pstmt);
			}
			
			// Service 단으로 결과값 반환
			return a;
			
		}
		
		// 첨부파일 조회
		public Attachment selectAttachment(Connection conn, int apvNo) {
			
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
				pstmt.setInt(1, apvNo);
				
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
		
		// 결재 글 작성_apv
		public int insertApv(Connection conn, Apv a) {
			
			// INSERT -> int
			
			// 필요한 요소 
			int result1 = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("insertApv");
			
			try {
				// pstmt 생성
				pstmt = conn.prepareStatement(sql);
				
				// 비어있는 값 넣기
				pstmt.setString(1, a.getApvWriter());
				pstmt.setString(2, a.getApvTitle());
				pstmt.setString(3, a.getApvContent());
			
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

		// 결재작성_Attachment
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
		
		// 결재 글 수정_apv
		public int updateApv(Connection conn, Apv a) {
			
			// UPDATE -> int
			
			// 필요한 요소
			int result1 = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("updateApv");
			
			try {
				// pstmt 생성
				pstmt = conn.prepareStatement(sql);
				
				// 비어있는 값 넣기
				pstmt.setString(1, a.getApvTitle());
				pstmt.setString(2, a.getApvContent());
				pstmt.setInt(3, a.getApvStatus());
				pstmt.setInt(4, a.getApvNo());
				
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
		
		// 결재 글 수정_Attachment
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
		
		// 결재 글 수정_NewAttachment
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
				pstmt.setInt(1, at.getRefAno());
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
		
		// 글 삭제_Board
		public int deleteApv(Connection conn, int apvNo) {
			
			// DELETE -> int
			 
			// 필요한 요소
			int result = 0;
			PreparedStatement pstmt = null;
			String sql = prop.getProperty("deleteApv");
			
			try {
				// pstmt 생성
				pstmt = conn.prepareStatement(sql);
				
				// 비어있는 값 넣기
				pstmt.setInt(1, apvNo);
				
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
		
}
