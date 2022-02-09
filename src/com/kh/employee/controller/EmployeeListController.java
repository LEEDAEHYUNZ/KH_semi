package com.kh.employee.controller;

import java.io.IOException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.common.model.vo.PageInfo;
import com.kh.employee.model.service.EmployeeService;
import com.kh.employee.model.vo.Employee;

/**
 * Servlet implementation class EmployeeListController
 */
@WebServlet("/list.ch")
public class EmployeeListController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public EmployeeListController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// 페이징 처리
		int listCount; // 현재 조직도의 게시글? 사원들의 총 수 => EMPLOYEE 로 부터 조회COUNT(*) STATUS = Y
		int currentPage; // 현재 페이지(즉, 사용자가 요청한 페이지) => request.getParameter("currentPage")
		int pageLimit; // 페이지 하단에 보여질 페이징바의 페이지 최대 갯수 => 10개
		int boardLimit; // 한 페이지에 보여질 게시글의 최대 갯수
		
		int maxPage; // 가장 마지막 페이지가 몇번 페이지인지(== 총 페이지 갯수)
		int startPage; // 페이지 하단에 보여질 페이징바의 시작수
		int endPage; // 페이지 하단에 보여질 페이징바의 끝수
		
		// * listCount : 총 게시글 갯수
		listCount = new EmployeeService().selectListCount(); // 총 게시글 갯수
		currentPage = Integer.parseInt(request.getParameter("currentPage")); // 현재페이지
		pageLimit = 10; // 페이징바의 페이지 최대 갯수
		boardLimit = 10; // 한 페이지에 보여질 게시글의 최대 갯수
		
		 //System.out.println(listCount);
		 //System.out.println(currentPage);
		
		// * maxPage : 가장 마지막 페이지가 몇번 페이지 인지(총 페이지 갯수)
		/*
		 * listCount, boardLimit에 영향을 받음
		 * 
		 * - 공식 구하기
		 * 단, boardLimit 이 10이라가는 가정 하에 규칙을 구해보자
		 * (listCount / boardLimit) 를 올림처리 할 경우 maxPage 가 된다.
		 * 
		 * 스텝
		 * 1) listCount 를 double 로 형변환
		 * 2) listCount / boardLimit
		 * 3) 결과에 올림 처리 => Math.ceil()
		 * 4) 결과값을 int 로 형변환
		 */
		maxPage = (int)Math.ceil((double)listCount / boardLimit);
		
		//System.out.println(maxPage);
		
		/*
		 * - 공식 구하기
		 * startPage => pageLimit, currentPage에 영향을 받음 -> 페이지 하단에 보여질 페이징바의 시작수 
		 */
		
		startPage = (currentPage - 1) / pageLimit * pageLimit + 1 ;
		
		/*
		 * endPage : 페이징 하단에 보여질 페이지바의 끝수
		 * 
		 * startPage, pageLimit 에 영향을 받음 (단, maxPage도 마지막 페이징 바에 대해서 영향을 준다)
		 */
		endPage = startPage + pageLimit - 1;
		if(endPage > maxPage) {
			endPage = maxPage;
		}
		
		// 여기까지 총 7개의 변수들을 만들었음
		// 7개의 변수를 가지고 해당되는 범위에 따른 게시글 10개씩만 SELCT
		// Service 단으로 토스 VO 클래스에 만들어서 가공해서 남길것
		PageInfo pi = new PageInfo(listCount, currentPage, pageLimit, boardLimit,
								   maxPage, startPage, endPage);
		
		ArrayList<Employee> list = new EmployeeService().selectList(pi);
		
		request.setAttribute("list", list);
		request.setAttribute("pi", pi);
		
		request.getRequestDispatcher("views/common/organizationchart.jsp").forward(request,response);
		
		
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
