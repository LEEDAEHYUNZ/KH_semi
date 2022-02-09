package com.kh.board.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;
import com.kh.common.model.vo.PageInfo;

/**
 * Servlet implementation class BoardListController
 */
@WebServlet("/list.bo")
public class BoardListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 1) 인코딩 
		request.setCharacterEncoding("UTF-8");
		
		// 페이징 처리 ========================================
		// 필요한 변수
		int listCount; 
		int currentPage;
		int pageLimit; 
		int listLimit; 
		int maxPage; 
		int startPage; 
		int endPage;
		
		// listCount
		listCount = new BoardService().selectListCount();
		
		// currentPage
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
		// pageLimit / listLimit
		pageLimit = 10;
		listLimit = 10;
		
		// maxPage
		maxPage = (int)Math.ceil((double)listCount / listLimit);
		
		// startPage / endPage
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		endPage = startPage + pageLimit - 1;
		
		// endPage를  maxPage로 변경
		if(endPage > maxPage) {
			endPage = maxPage; 
		}
		
		// 3) VO 로 가공
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
		
		// 4) Service 로 토스 -> ArrayList 로 받기
		ArrayList<Board> list = new BoardService().selectList(pi);
		
		// 5) 응답화면 지정 
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		// views/board/boardListView.jsp 화면으로 응답 -> 포워딩 방식
		request.getRequestDispatcher("views/board/boardListView.jsp").forward(request, response);
				
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
