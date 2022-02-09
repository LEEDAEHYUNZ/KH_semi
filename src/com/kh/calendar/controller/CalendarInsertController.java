package com.kh.calendar.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.calendar.model.service.CalendarService;
import com.kh.calendar.model.vo.Calendar;

/**
 * Servlet implementation class CalendarInsertController
 */
@WebServlet("/insert.co")
public class CalendarInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public CalendarInsertController() {
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
		String title = request.getParameter("title");
		String startEnd = request.getParameter("startEnd");
		
		
		// 3) 매개변수 생성자를 이용해서 Employee 객체에 담기
		Calendar c = new Calendar(title, startEnd);
		
		// 4) Service으로 토스
		int result = new CalendarService().insertCalendar(c);
		
		// 5) 일정추가 성공/실패에 따른 사용자가 보게 될 응답화면 지정
		if(result>0) {
			response.sendRedirect(request.getContextPath() + "/calendar.ca");
		}
		else {
			response.sendRedirect(request.getContextPath() + "/calendar.ca");
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
