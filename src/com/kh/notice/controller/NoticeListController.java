package com.kh.notice.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeListController
 */
@WebServlet("/list.no")
public class NoticeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeListController() {
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
		
		// * listCount -> Notice 로부터 조회
		listCount = new NoticeService().selectListCount(); 
		
		// 2) request 로 값 뽑기
		// * currentPage -> request.getParameter("currentPage")
		currentPage = Integer.parseInt(request.getParameter("currentPage"));
		
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
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, listLimit, maxPage, startPage, endPage);
		
		// 4) Service 단으로 토스
		ArrayList<Notice> list = new NoticeService().selectList(pi);
		
		// 5) 응답화면 지정
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		// views/notice/noticeListView.jsp -> 포워딩
		request.getRequestDispatcher("views/notice/noticeListView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
