package com.kh.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.service.EmployeeService;

/**
 * Servlet implementation class EmployeeSearchEmpPwdController
 */
@WebServlet("/empPwdSearch.em")
public class EmployeeSearchEmpPwdController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeSearchEmpPwdController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		String phone = request.getParameter("phone");
		
		String result = new EmployeeService().searchEmpPwd(empNo, phone);
		
		response.setContentType("text/html; charset=UTF-8");
		
		if(result.isEmpty()) {
			//request.getSession().setAttribute("alertMsg", "비밀번호 찾기 실패!");
			response.sendRedirect(request.getContextPath());
		}
		else {
			request.getSession().setAttribute("alertMsg", "비밀번호 찾기 성공!");
			//request.getRequestDispatcher("views/employee/searchPwd.jsp").forward(request, response);
			
			response.getWriter().print(result);
			
			
			//response.sendRedirect(request.getContextPath() +"/serchEmpPwd.em");
			
			// HttpSession session = request.getSession();
			
			// session.setAttribute("pwd", result);

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
