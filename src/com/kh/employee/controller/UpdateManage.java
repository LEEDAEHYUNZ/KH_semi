package com.kh.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class UpdateManage
 */
@WebServlet("/updatemanage.em")
public class UpdateManage extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateManage() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		
		String deptId = request.getParameter("deptId");
		String jobCode = request.getParameter("job");
		String status = request.getParameter("status");
		int empNo = Integer.parseInt(request.getParameter("empNo"));
		

		Employee e = new Employee(empNo, jobCode, deptId,status); 
		
		int result = new EmployeeService().updateManage(e);
		
		System.out.println(result);
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "변경에 성공했습니다.");
			response.sendRedirect(request.getContextPath() + "/manage.em");
		}
		else {
			request.setAttribute("errorPage", "변경에 실패했습니다");
			request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
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
