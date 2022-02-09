package com.kh.calendar.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.calendar.model.vo.Calendar;
import com.kh.common.JDBCTemplate;

public class CalendarDao {
	
	private Properties prop = new Properties();
	
	public CalendarDao() {
		
		String fileName = CalendarDao.class.getResource("/sql/calendar/calendar-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}

	public ArrayList<Calendar> selectCalendarList(Connection conn) {
		
		ArrayList<Calendar> list = new ArrayList<>();
	      
	      PreparedStatement pstmt = null;
	      
	      ResultSet rset = null;
	      
	      String sql = prop.getProperty("selectCalendarList");
	      
	      try {
	         pstmt = conn.prepareStatement(sql);
	         
	         rset = pstmt.executeQuery();
	         
	         while(rset.next()) {
	            
	            Calendar c = new Calendar(rset.getString("TITLE")
	                     , rset.getString("STARTEND"));
	 
	            list.add(c);
	         }
	         
	      } catch (SQLException e) {
	         e.printStackTrace();
	      } finally {
	         JDBCTemplate.close(rset);
	         JDBCTemplate.close(pstmt);
	      }
	      
	      return list;
	}
	
	public int insertCalendar(Connection conn, Calendar c) {
		// INSERT문 => 처리된 행의 갯수
		// 필요한 변수 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertCalendar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, c.getTitle());
			pstmt.setString(2, c.getStartEnd());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteCalendar(Connection conn, String title) {
		
		int result = 0;
		
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteCalendar");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, title);
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		return result;

	}
	
	public ArrayList<Calendar> selectCalendarMainList(Connection conn) {
		ArrayList<Calendar> main3 = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectCalendarList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Calendar c = new Calendar (rset.getString("TITLE")
						   , rset.getString("STARTEND"));
				main3.add(c);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return main3;
	}
	


}
