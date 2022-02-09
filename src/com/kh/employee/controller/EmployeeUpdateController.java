package com.kh.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeUpdateController
 */
@WebServlet("/update.me")
public class EmployeeUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
				// 1) POST 방식 => 인코딩 설정
				request.setCharacterEncoding("UTF-8");
				
				// 2) request 로 부터 요청시 전달한 값을 뽑기
				int empNo = Integer.parseInt(request.getParameter("empNo"));
				String empName = request.getParameter("empName");
				String phone = request.getParameter("phone");
				String email = request.getParameter("email");
				String address = request.getParameter("address");
				
				// 3) VO 객체에 담기
				Employee e = new Employee(empNo, empName, phone, email, address);
				
				// 4) Service 단으로 토스
				Employee updateEmp = new EmployeeService().updateEmployee(e);
				
				// 5) 결과물에 따라서 응답화면 지정 => 업데이트된 내용물을 같이 화면에 뿌려주기
				
				if(updateEmp == null) { // updateMem 이 null : 실패 => 에러페이지
					
					request.setAttribute("alertMsg", "회원정보 수정에 실패했습니다.");
					request.getRequestDispatcher("views/common/alertMsg.jsp").forward(request, response);
				}
				else { // 성공했을 경우 => 마이페이지 화면 그대로 보여주기 (단, 변경된 정보를 뿌려서)
					
					HttpSession session = request.getSession();
					
					session.setAttribute("loginEmployee", updateEmp); // 키값이 중복될경우 : 덮어씌워짐
					session.setAttribute("alertMsg", "성공적으로 회원정보를 수정했습니다.");

					// 마이페이지에서 정보변경 후 sendRedirect 형식으로 보여주기 (myPage.me 가 그대로 주소에 남아있어야 하므로)
					// localhost:8888/jsp/myPage.me
					response.sendRedirect(request.getContextPath() + "/myPage.me");
				}
				
			}

		
		
		
		

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
