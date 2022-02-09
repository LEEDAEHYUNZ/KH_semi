package com.kh.employee.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeInsertController
 */
@WebServlet("/insert.em")
public class EmployeeInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// post
		request.setCharacterEncoding("UTF-8");
		
		// 2) request 객체로부터 요청시 전달값 뽑기
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		String empName = request.getParameter("empName");
		String empPwd = request.getParameter("empPwd");
		String email = request.getParameter("empidNo");
		String phone = request.getParameter("phone");
		String empidNo = request.getParameter("email");
		String address = request.getParameter("address"); 
		
		// 3) 매개변수 생성자를 이용해서 Employee 객체에 담기
		Employee e = new Employee(empNo, empName, empPwd, empidNo, phone, email, address);
		
		// 4) Service으로 토스
		int result = new EmployeeService().insertEmployee(e);
		
		// 5) 회원가입 성공/실패에 따른 사용자가 보게 될 응답화면 지정dl
		if(result>0) {
			
			request.getSession().setAttribute("alertMsg", "회원가입 성공! 로그인창으로 이동!");
			response.sendRedirect(request.getContextPath());
		}
		else {
			request.setAttribute("errorMsg", "회원가입에 실패했습니다");
			RequestDispatcher view = request.getRequestDispatcher("views/common/errorPage.jsp");
			view.forward(request, response);
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
