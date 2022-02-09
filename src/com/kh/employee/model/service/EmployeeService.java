package com.kh.employee.model.service;

import java.sql.Connection;
import java.util.ArrayList;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.dao.EmployeeDao;
import com.kh.employee.model.vo.Employee;

public class EmployeeService {

	public Employee loginEmployee(int empNo, String empPwd) {
		
		// 1) Connection 객체생성
		Connection conn = JDBCTemplate.getConnection();
		
		// 2) Dao에 요청
		Employee e = new EmployeeDao().loginEmployee(conn, empNo, empPwd);
		
		// 3) Connection 객체 반납
		JDBCTemplate.close(conn);
		
		// 4) Controller 로 객체 넘기기
		return e;
	}

	public int insertEmployee(Employee e) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new EmployeeDao().insertEmployee(conn, e);
		
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}


	public int searchEmpNo(String empName, String phone) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new EmployeeDao().searchEmpNo(conn, empName, phone);
		
		JDBCTemplate.close(conn);
		
		return result;

	}

	public String  searchEmpPwd(int empNo, String phone) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		String result = new EmployeeDao().searchEmpPwd(conn, empNo, phone);
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	

	public int empNoCheck(int checkEmpNo) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int count = new EmployeeDao().empNoCheck(conn, checkEmpNo);
		
		JDBCTemplate.close(conn);
		
		return count;
	}

//	public ArrayList<Employee> selectEmployeeList() {
//		
//		Connection conn = JDBCTemplate.getConnection();
//		
//		ArrayList<Employee> list = new EmployeeDao().selectEmployeeList(conn);
//		
//		JDBCTemplate.close(conn);
//		
//		return list;
//	}

	public int selectListCount() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int listCount = new EmployeeDao().selectListCount(conn);
		// SELECT 문의 결과는 ResultSet 이 맞긴하지만 게시글의 총 갯수는 정수형
		
		JDBCTemplate.close(conn);
		
		return listCount;
	}

	public ArrayList<Employee> selectList(PageInfo pi) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Employee> list = new EmployeeDao().selectList(conn, pi);
		
		JDBCTemplate.close(conn);
		
		return list;
	}

	public ArrayList<Employee> searchList(String searchField, String searchWord) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Employee> list = new EmployeeDao().searchList(conn, searchField, searchWord);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	
public Employee updateEmployee(Employee e) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new EmployeeDao().updateEmployee(conn, e);
		
		// 갱신된 회원 객체를 다시 조회해오기 => 업데이트 성공한 경우에만
		Employee updateEmp = null;
		
		if(result > 0) { // 성공
			
			JDBCTemplate.commit(conn);
			
			updateEmp = new EmployeeDao().selectEmployee(conn, e.getEmpNo());
		}
		else { // 실패
			
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateEmp;
		
	}
	
	public Employee updatePwdEmployee(int empNo, String empPwd, String updatePwd) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		
		int result = new EmployeeDao().updatePwdEmployee(conn, empNo, empPwd, updatePwd);
		
		
		Employee updateEmp = null;
		
		if(result > 0) { 
			JDBCTemplate.commit(conn);
			
			
			updateEmp = new EmployeeDao().selectEmployee(conn, empNo);
		}
		else { 
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return updateEmp;
	}
	public ArrayList<Employee> selectEmployeeList() {
		
		Connection conn = JDBCTemplate.getConnection();
		
		ArrayList<Employee> list = new EmployeeDao().selectEmployeeList(conn);
		
		JDBCTemplate.close(conn);
		
		return list;
	}
	public int updateManage(Employee e) {
		
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new EmployeeDao().updateManage(conn, e);
		System.out.println(result);
		if(result > 0) {
			JDBCTemplate.commit(conn);
		}
		else {
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	}
	public int deleteManage(int empNo) {
		Connection conn = JDBCTemplate.getConnection();
		
		int result = new EmployeeDao().deleteManage(conn, empNo);
		
		if(result > 0) { // 성공
			JDBCTemplate.commit(conn);
		}
		else { // 실패
			JDBCTemplate.rollback(conn);
		}
		
		JDBCTemplate.close(conn);
		
		return result;
	
	}

	public ArrayList<Employee> searchList1(String searchField, String searchWord) {
		      
		      Connection conn = JDBCTemplate.getConnection();
		      
		      ArrayList<Employee> list = new EmployeeDao().searchList1(conn, searchField, searchWord);
		      
		      JDBCTemplate.close(conn);
		      
		      return list;
		   
	}


}

