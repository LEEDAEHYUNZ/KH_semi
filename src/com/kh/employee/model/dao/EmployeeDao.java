package com.kh.employee.model.dao;

import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Properties;

import com.kh.common.JDBCTemplate;
import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.vo.Employee;

public class EmployeeDao {
	
	private Properties prop = new Properties();
	
	public EmployeeDao() {
		
		String fileName = EmployeeDao.class.getResource("/sql/employee/employee-mapper.xml").getPath();
		
		try {
			prop.loadFromXML(new FileInputStream(fileName));
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	

	public Employee loginEmployee(Connection conn, int empNo, String empPwd) {
		
		// SELECT 문 -> ResultSet 객체
		// 필요한 변수
		Employee e = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("loginEmployee");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			pstmt.setString(2, empPwd);
			
			rset = pstmt.executeQuery();
			
			// rset으로부터 각각의 컬럼값을 뽑아서 Eemployee 객체에 담는다
			if(rset.next()) {
				
				// 각 컬럼값 뽑기
				e = new Employee(rset.getInt("EMP_NO")
							   , rset.getString("DEPT_ID")
							   , rset.getString("JOB_CODE")
							   , rset.getString("EMP_NAME")
							   , rset.getString("EMP_PWD")
							   , rset.getString("EMPID_NO")
							   , rset.getString("PHONE")
							   , rset.getString("EMAIL")
							   , rset.getString("ADDRESS")
							   , rset.getDate("HIRE_DATE")
							   , rset.getString("RETIRE_DATE")
							   , rset.getString("STATUS"));
			}
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return e;
		
	}

	
	public int insertEmployee(Connection conn, Employee e) {
		// INSERT문 => 처리된 행의 갯수
		// 필요한 변수 셋팅
		int result = 0;
		PreparedStatement pstmt = null;
		
		String sql = prop.getProperty("insertEmployee");
		
		try {
			pstmt = conn.prepareStatement(sql);

			pstmt.setInt(1, e.getEmpNo());
			pstmt.setString(2, e.getEmpName());
			pstmt.setString(3, e.getEmpPwd());
			pstmt.setString(4, e.getEmpidNo());
			pstmt.setString(5, e.getPhone());
			pstmt.setString(6, e.getEmail());
			pstmt.setString(7, e.getAddress());
			
			result = pstmt.executeUpdate();
			
		} catch (SQLException e1) {
			e1.printStackTrace();
		} finally {
			JDBCTemplate.close(pstmt);
		}
		
		return result;
	}
	
	public int searchEmpNo(Connection conn, String empName, String phone) {

		int searchEmpNo = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("serachEmpNo");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setString(1, empName);
			pstmt.setString(2, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				searchEmpNo = rset.getInt("EMP_NO");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		
		
		return searchEmpNo;
		
	}


	public String searchEmpPwd(Connection conn, int empNo, String phone) {
		
		String searchEmpPwd = null;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("searchEmpPwd");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, empNo);
			pstmt.setString(2, phone);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				searchEmpPwd = rset.getString("EMP_PWD");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		
		return searchEmpPwd;
	}

	public int empNoCheck(Connection conn, int checkEmpNo) {
		// SELECT => ResultSet = > COUNT 함수 이용(숫자 한개)
		
		int count = 0;
		PreparedStatement pstmt = null;
		ResultSet rset = null;
		
		String sql = prop.getProperty("empNoCheck");
		
		try {
			pstmt = conn.prepareStatement(sql);
			
			pstmt.setInt(1, checkEmpNo);
			
			rset = pstmt.executeQuery();
			
			if(rset.next()) {
				count = rset.getInt("COUNT(*)");
			}
			
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			JDBCTemplate.close(rset);
			JDBCTemplate.close(pstmt);
		}
		return count;
	}


		public int selectListCount(Connection conn) {
			
			// SELECT => ResultSet => 하지만 반환형은 int
			int listCount = 0;
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectListCount");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					listCount = rset.getInt("COUNT");
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return listCount;
		}


		public ArrayList<Employee> selectList(Connection conn, PageInfo pi) {
			
			// SELECT 문 => ResultSet => 여러 행이므로 ArrayList<Employee>
			
			ArrayList<Employee> list = new ArrayList<>();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				// TOP-N 분석 활용 : 인라인 뷰 활용(서브쿼리)
				// 1) 서브쿼리를 FROM 절에 넣음 (메인쿼리의) + ROWNUM 붙이기
				// 2) 서브쿼리의 WHERE 절에 어디서부터 어디까지만 조인
				/*
				 * boardLimit 가 10이라고 가정 하에
				 * currentPage = 1 => 시작값1, 끝값 10
				 */
				int startRow = (pi.getCurrentPage() - 1 ) * pi.getListLimit() + 1;
				int endRow = startRow + pi.getListLimit() - 1;
				
				pstmt.setInt(1, startRow);
				pstmt.setInt(2, endRow);

				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					list.add(new Employee(rset.getInt("EMP_NO")
										 ,rset.getString("EMP_NAME")
										 ,rset.getString("DEPT_ID")
										 ,rset.getString("JOB_CODE")
										 ,rset.getString("ADDRESS")
										 ,rset.getString("EMAIL")
										 ,rset.getString("PHONE")
										 ,rset.getDate("HIRE_DATE")));
				}
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			return list;
		}

		public ArrayList<Employee> searchList(Connection conn, String searchField, String searchWord) {
			// SELECT => ResultSet => 하지만 반환형은 int
			ArrayList<Employee> list = new ArrayList<>();
			PreparedStatement pstmt = null;
			ResultSet rset = null;
			System.out.println(searchWord);
			try {
				
				if(searchField.equals("a")) {
					
					String sql = prop.getProperty("select1Search");
					
					pstmt = conn.prepareStatement(sql);
					
					pstmt.setString(1, "%" + searchWord+ "%");
				}
				else if(searchField.equals("b")) {
					
					String sql = prop.getProperty("select2Search");
					pstmt = conn.prepareStatement(sql);
					System.out.println(sql);
					pstmt.setString(1, "%" + searchWord+ "%");
				}
				else {
					String sql = prop.getProperty("select3Search");
					pstmt = conn.prepareStatement(sql);
					System.out.println(sql);
					pstmt.setString(1, "%" + searchWord+ "%");
				}
				
				rset = pstmt.executeQuery();
				while(rset.next()) {

					Employee e = new Employee();
					e.setEmpNo(rset.getInt("EMP_NO"));
					e.setEmpName(rset.getString("EMP_NAME"));
					e.setDeptId(rset.getString("DEPT_ID"));
					e.setJobCode(rset.getString("JOB_CODE"));
					e.setAddress(rset.getString("ADDRESS"));
					e.setEmail(rset.getString("EMAIL"));
					e.setPhone(rset.getString("PHONE"));
					e.setHireDate(rset.getDate("HIRE_DATE"));
					
					list.add(e);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return list;
		}
		
public int updateEmployee(Connection conn, Employee e) {
			
			// UPDATE 문 => 처리된 행의 갯수
			
			// 필요한 변수
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updateEmployee");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, e.getPhone());
				pstmt.setString(2, e.getEmail());
				pstmt.setString(3, e.getAddress());
				pstmt.setInt(4, e.getEmpNo());
				pstmt.setString(5, e.getEmpName());
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
		
		
		public Employee selectEmployee(Connection conn, int empNo) {
			
			// SELECT 문 => ResultSet 형태로 결과 반환 => Member
			// 필요한 변수
			Employee e = null;
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectEmployee");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, empNo);
				
				rset = pstmt.executeQuery();
				
				if(rset.next()) {
					
					e = new Employee(rset.getInt("EMP_NO")
							   , rset.getString("DEPT_ID")
							   , rset.getString("JOB_CODE")
							   , rset.getString("EMP_NAME")
							   , rset.getString("EMP_PWD")
							   , rset.getString("EMPID_NO")
							   , rset.getString("PHONE")
							   , rset.getString("EMAIL")
							   , rset.getString("ADDRESS")
							   , rset.getDate("HIRE_DATE")
							   , rset.getString("RETIRE_DATE")
							   , rset.getString("STATUS"));
					}
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}

			return e;
		}
		
		public int updatePwdEmployee(Connection conn, int empNo, String empPwd, String updatePwd) {
			
			// UPDATE 문 => 처리된 행의 갯수
			// 필요한 변수
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updatePwdEmployee");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, updatePwd); // xml 순서 보기
				pstmt.setInt(2, empNo);
				pstmt.setString(3, empPwd);
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		}
		public int updateManage(Connection conn, Employee e) {
			
			int result = 0;
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("updateManage");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setString(1, e.getDeptId());
				pstmt.setString(2, e.getJobCode());
				pstmt.setString(3, e.getStatus());
				pstmt.setInt(4, e.getEmpNo());
				
				result = pstmt.executeUpdate();
				
				System.out.println(result);
				
			} catch (SQLException e1) {
				

				e1.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
			
		}


		public int deleteManage(Connection conn, int empNo) {
			
			int result = 0;
			
			PreparedStatement pstmt = null;
			
			String sql = prop.getProperty("deleteManage");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				pstmt.setInt(1, empNo);
				
				
				result = pstmt.executeUpdate();
				
			} catch (SQLException e1) {
				e1.printStackTrace();
			} finally {
				JDBCTemplate.close(pstmt);
			}
			
			return result;
		
		}
		
		public ArrayList<Employee> selectEmployeeList(Connection conn) {
			
			ArrayList<Employee> list = new ArrayList<>();
			
			PreparedStatement pstmt = null;
			
			ResultSet rset = null;
			
			String sql = prop.getProperty("selectEmployeeList");
			
			try {
				pstmt = conn.prepareStatement(sql);
				
				rset = pstmt.executeQuery();
				
				while(rset.next()) {
					
					Employee e = new Employee (rset.getInt("EMP_NO")
							   , rset.getString("DEPT_ID")
							   , rset.getString("JOB_CODE")
							   , rset.getString("EMP_NAME")
							   , rset.getString("EMP_PWD")
							   , rset.getString("EMPID_NO")
							   , rset.getString("PHONE")
							   , rset.getString("EMAIL")
							   , rset.getString("ADDRESS")
							   , rset.getDate("HIRE_DATE")
							   , rset.getString("RETIRE_DATE")
							   , rset.getString("STATUS"));
					
					list.add(e);
				}
				
			} catch (SQLException e) {
				e.printStackTrace();
			} finally {
				JDBCTemplate.close(rset);
				JDBCTemplate.close(pstmt);
			}
			
			return list;
			
		}


		   public ArrayList<Employee> searchList1(Connection conn, String searchField, String searchWord) {
		         // SELECT => ResultSet => 하지만 반환형은 int
		         ArrayList<Employee> list = new ArrayList<>();
		         PreparedStatement pstmt = null;
		         ResultSet rset = null;

		         try {
		            
		            if(searchField.equals("a")) {
		               
		               String sql = prop.getProperty("select4Search");
		               
		               pstmt = conn.prepareStatement(sql);
		               
		               pstmt.setString(1, "%" + searchWord+ "%");
		            }
		            else if(searchField.equals("b")) {
		               
		               String sql = prop.getProperty("select5Search");
		               pstmt = conn.prepareStatement(sql);
		               System.out.println(sql);
		               pstmt.setString(1, "%" + searchWord+ "%");
		            }
		            else {
		               String sql = prop.getProperty("select6Search");
		               pstmt = conn.prepareStatement(sql);
		               System.out.println(sql);
		               pstmt.setString(1, "%" + searchWord+ "%");
		            }
		            
		            rset = pstmt.executeQuery();
		            while(rset.next()) {

		                Employee e = new Employee();
	                     e.setEmpNo(rset.getInt("EMP_NO"));
	                     e.setEmpName(rset.getString("EMP_NAME"));
	                     e.setDeptId(rset.getString("DEPT_ID"));
	                     e.setJobCode(rset.getString("JOB_CODE"));
	                     e.setEmpPwd(rset.getString("EMP_PWD"));
	                     e.setEmpidNo(rset.getString("EMPID_NO"));
	                     e.setAddress(rset.getString("ADDRESS"));
	                     e.setEmail(rset.getString("EMAIL"));
	                     e.setPhone(rset.getString("PHONE"));
	                     e.setHireDate(rset.getDate("HIRE_DATE"));
	                     e.setRetireDate(rset.getString("RETIRE_DATE"));
	                     e.setStatus(rset.getString("STATUS"));
		               
		               list.add(e);
		            }
		            
		         } catch (SQLException e) {
		            e.printStackTrace();
		         } finally {
		            JDBCTemplate.close(rset);
		            JDBCTemplate.close(pstmt);
		         }
		         
		         return list;
		      }
		
}