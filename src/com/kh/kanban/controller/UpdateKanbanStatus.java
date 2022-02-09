package com.kh.kanban.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;
import com.kh.kanban.model.service.KanbanService;
import com.kh.kanban.model.vo.Kanban;

/**
 * Servlet implementation class UpdateKanbanStatus
 */
@WebServlet("/updatekanbanstatus.ka")
public class UpdateKanbanStatus extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateKanbanStatus() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		int status = Integer.parseInt(request.getParameter("updateStatus"));
		 
		Kanban k = new Kanban(proNo, status);
		int result = new KanbanService().updateKanbanStatus(k);
		

		if(result>0) {
			response.sendRedirect(request.getContextPath() + "/kanbanform.ka");
		}
		else {
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
