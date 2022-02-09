package com.kh.kanban.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.kanban.model.vo.Kanban;



public class KanbanDao {

	private Properties prop = new Properties();
	
	public KanbanDao() {
		
		String fileName = KanbanDao.class.getResource("/sql/kanban/kanban-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public ArrayList<Kanban> selectKanbanList(Connection conn) {
		ArrayList<Kanban> list = new ArrayList<>();
		
		PreparedStatement pstmt = null;
		
		ResultSet rset = null;
		
		String sql = prop.getProperty("selectKanbanList");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			rset = pstmt.executeQuery();
			
			while(rset.next()) {
				
				Kanban k = new Kanban (rset.getInt("PRO_NO")
						   , rset.getString("EMP_NAME")
						   , rset.getString("PRO_NM")
						   , rset.getString("PRO_CONTENT")
						   , rset.getInt("STATUS")
						   , rset.getDate("START_DATE")
						   , rset.getString("END_DATE"));
				list.add(k);
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return list;
	}

	public int insertKanban(Connection conn, Kanban k) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertKanban");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, k.getEmpNo());
			pstmt.setString(2, k.getProNm());
			pstmt.setString(3, k.getProContent());
			pstmt.setString(4, k.getEndDate());
			
			
			result = pstmt.executeUpdate();
			
			System.out.println(result);
			
		} catch (SQLException e1) {
			
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateKanbanStatus(Connection conn, Kanban k) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateKanbanStatus");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, k.getStatus());
			pstmt.setInt(2, k.getProNo());

			result = pstmt.executeUpdate();

			
		} catch (SQLException e1) {
			
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int updateKanban(Connection conn, Kanban k) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("updateKanban");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, k.getProNm());
			pstmt.setString(2, k.getProContent());
			pstmt.setString(3, k.getEndDate());
			pstmt.setInt(4, k.getProNo());

			result = pstmt.executeUpdate();
			

			
		} catch (SQLException e1) {
			
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

	public int deleteKanban(Connection conn, int proNo) {
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("deleteKanban");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, proNo);

			result = pstmt.executeUpdate();

		} catch (SQLException e1) {
			
			
			// TODO Auto-generated catch block
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}

}
