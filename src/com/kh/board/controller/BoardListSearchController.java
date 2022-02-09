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
import com.kh.notice.model.service.NoticeService;

/**
 * Servlet implementation class BoardListSearchController
 */
@WebServlet("/search.bo")
public class BoardListSearchController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardListSearchController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 검색어 처리 ========================================
		String searchField = request.getParameter("searchField");
		String searchWord = request.getParameter("searchWord");
		
		// Service 로 토스 -> ArrayList 로 받기
		ArrayList<Board> searchList = new ArrayList<>();
	
		// 페이징 처리 ========================================
		// 필요한 변수
		int listCount; 
		int currentPage;
		int pageLimit; 
		int listLimit; 
		int maxPage; 
		int startPage; 
		int endPage;
		
		// * listCount -> Notice 로부터 조회
		listCount = searchList.size();
		
		// 2) request 로 값 뽑기
		// * currentPage -> request.getParameter("currentPage")
		currentPage = 1;
		
		// * pageLimit, listLimit -> 10개
		pageLimit = 10;
		listLimit = 10;
		
		// test : 정상
		// System.out.println(listCount); 
		// System.out.println(currentPage);
		
		// * maxPage <- listCount, noticeLimit
		maxPage = (int)Math.ceil( (double)listCount / listLimit ); 
		
		// test : 정상
		// System.out.println(maxPage);
		
		// * startPage <- pageLimit, currentPage
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1;
		
		// * endPage <- startPage, pageLimit
		endPage = startPage + pageLimit - 1;
		
		// maxPage 가 endPage 보다 작을 경우 -> endPage 값을 maxPage 로 변경
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 3) VO 가공
		// 게시글 10개씩 select
		PageInfo searchedPi = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
		
		searchList = new BoardService().searchList(searchField, searchWord);
		
		// 응답화면 지정 
		request.setAttribute("searchList", searchList);
		request.setAttribute("searchedPi", searchedPi);
		
		// views/board/boardListView.jsp 화면으로 응답 -> 포워딩 방식
		request.getRequestDispatcher("views/board/boardSearchedList.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
