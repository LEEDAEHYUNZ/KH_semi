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
 * Servlet implementation class EmployeeUpdatePwdController
 */
@WebServlet("/updatePwd.me")
public class EmployeeUpdatePwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeUpdatePwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// 1) POST 방식이므로 인코딩
		request.setCharacterEncoding("UTF-8");
				
		// 2) request 로 부터 값 뽑기
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		String empPwd = request.getParameter("empPwd");
		String updatePwd = request.getParameter("updatePwd");
				
		// 3) VO 객체에 담아서 가공 => 부분적으로 패싱 (담을 값이 너무 적을때는 패싱 가능)
				
		// 4) Service 단으로 토스
		Employee updateEmp = new EmployeeService().updatePwdEmployee(empNo, empPwd, updatePwd);
				
		// 5) 결과값을 통해 성공 실패여부에 따른 응답화면 지정
				
		HttpSession session = request.getSession();
				
		if(updateEmp == null) { // 실패 => 마이페이지 alert
					session.setAttribute("alertMsg", "비밀번호 변경에 실패했습니다.");
		}
		
		else { // 성공 => alert, 바뀐 사용자 정보를 loginEmployee 에 덮어씌우기
		
			request.getSession().setAttribute("alertMsg", "성공적으로 비밀번호가 변경되었습니다."); // 성공
			session.setAttribute("loginEmployee", updateEmp); // 덮어씌우기
		}
				
		
		// 성공이든 실패든 myPage.me 를 요청
		
		// localhost:8888/jsp/myPage.me
		
		response.sendRedirect(request.getContextPath() + "/myPage.me");
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
