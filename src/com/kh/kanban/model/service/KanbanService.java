package com.kh.kanban.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.employee.model.dao.EmployeeDao;
import com.kh.kanban.model.dao.KanbanDao;
import com.kh.kanban.model.vo.Kanban;

public class KanbanService {

	public ArrayList<Kanban> selectKanbanList() {
Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Kanban> list = new KanbanDao().selectKanbanList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public int insertKanban(Kanban k) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new KanbanDao().insertKanban(conn, k);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
		
		
		
	}

	public int updateKanbanStatus(Kanban k) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new KanbanDao().updateKanbanStatus(conn, k);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int updateKanban(Kanban k) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new KanbanDao().updateKanban(conn, k);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

	public int deleteKanban(int proNo) {
Connection conn = JDBCTemplate.getConnection();
		
		int result = new KanbanDao().deleteKanban(conn, proNo);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}

}
