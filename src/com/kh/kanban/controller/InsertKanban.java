package com.kh.kanban.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.kanban.model.service.KanbanService;
import com.kh.kanban.model.vo.Kanban;



/**
 * Servlet implementation class InsertKanban
 */
@WebServlet("/InsertKanban.ka")
public class InsertKanban extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public InsertKanban() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		request.setCharacterEncoding("UTF-8");
		
		String proNm = request.getParameter("proNm");
		String empNo = request.getParameter("empNo");
		String proContent = request.getParameter("proContent");
		String endDate = request.getParameter("endDate");
		

		Kanban k = new Kanban(empNo, proNm, proContent, endDate); 
		
		int result = new KanbanService().insertKanban(k);
		
		System.out.println(result);
		if(result>0) {
			request.getSession().setAttribute("alertMsg", "변경에 성공했습니다.");
			response.sendRedirect(request.getContextPath() + "/kanbanform.ka");
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
