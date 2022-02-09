package com.kh.common;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.calendar.model.service.CalendarService;
import com.kh.calendar.model.vo.Calendar;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class Main
 */
@WebServlet("/main.co")
public class Main extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Main() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Notice> main1 = new NoticeService().selectNoticeMainList();
		ArrayList<Board> main2 = new BoardService().selectBoardMainList();
		ArrayList<Calendar> main3 = new CalendarService().selectCalendarMainList();
		// 5) 뽑아온 list 를 request 의  attribute 영역에 담아서 응답페이지로 보내버린다.
		request.setAttribute("main1", main1);
		request.setAttribute("main2", main2);
		request.setAttribute("main3", main3);
		
		// 화면 띄우기
		request.getRequestDispatcher("/views/common/maindash.jsp").forward(request, response);
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
