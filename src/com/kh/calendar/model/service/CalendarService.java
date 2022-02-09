package com.kh.calendar.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.calendar.model.dao.CalendarDao;
import com.kh.calendar.model.vo.Calendar;
import com.kh.common.JDBCTemplate;

public class CalendarService {
	
	public int insertCalendar(Calendar c) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new CalendarDao().insertCalendar(conn, c);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public ArrayList<Calendar> selectCalendarList() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Calendar> list = new CalendarDao().selectCalendarList(conn);
	      
	      JDBCTemplate.close(conn);
	      
	      return list;
		
	}

	public int deleteCalendar(String title) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new CalendarDao().deleteCalendar(conn, title);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	
	public ArrayList<Calendar> selectCalendarMainList() {
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Calendar> list = new CalendarDao().selectCalendarMainList(conn);
	      
	      JDBCTemplate.close(conn);
	      
	      return list;
	}
}
