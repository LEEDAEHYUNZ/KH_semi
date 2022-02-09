package com.kh.employee.controller;

import java.io.IOException;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeSearchIdController
 */
@WebServlet("/empNoSearch.em")
public class EmployeeSearchEmpNoController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeSearchEmpNoController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String empName = request.getParameter("empName");
		String phone = request.getParameter("phone");

		int result = new EmployeeService().searchEmpNo(empName, phone);
		
		response.setContentType("text/html; charset=UTF-8");
		
		
		if(result>0) {
			//request.getSession().setAttribute("alertMsg" , "사번 찾기 성공!");
			//request.getRequestDispatcher("views/employee/searchNo.jsp").forward(request, response);
			response.getWriter().print(result);
		}
		else {
			response.getWriter().print("N");
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
