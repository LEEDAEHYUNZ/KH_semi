package com.kh.kanban.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.kanban.model.service.KanbanService;
import com.kh.kanban.model.vo.Kanban;

/**
 * Servlet implementation class UpdateKanban
 */
@WebServlet("/updatekanban.ka")
public class UpdateKanban extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UpdateKanban() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		request.setCharacterEncoding("UTF-8");
		
		int proNo = Integer.parseInt(request.getParameter("proNo"));
		String proNm =request.getParameter("updateProNm");
		String proContent =request.getParameter("updateProContent");
		String endDate =request.getParameter("updateEndDate");
		 
		Kanban k = new Kanban(proNo, proNm,proContent,endDate);
		int result = new KanbanService().updateKanban(k);
		
		System.out.println(result);
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
