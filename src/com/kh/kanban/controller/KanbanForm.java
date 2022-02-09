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
 * Servlet implementation class KanbanForm
 */
@WebServlet("/kanbanform.ka")
public class KanbanForm extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public KanbanForm() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		
		
		ArrayList<Kanban> list = new KanbanService().selectKanbanList();
		
		// 5) 뽑아온 list 를 request 의  attribute 영역에 담아서 응답페이지로 보내버린다.
		request.setAttribute("list", list);
		
		// 화면 띄우기
		
		request.getRequestDispatcher("/views/kanban/kanban.jsp").forward(request, response);
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
