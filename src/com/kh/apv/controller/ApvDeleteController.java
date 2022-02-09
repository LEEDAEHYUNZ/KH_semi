package com.kh.apv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.apv.model.service.ApvService;


/**
 * Servlet implementation class ApvDeleteController
 */
@WebServlet("/delete.ap")
public class ApvDeleteController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApvDeleteController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET 방식 -> 인코딩 X 
		
		// 값 뽑기
		int apvNo = Integer.parseInt(request.getParameter("ano")); // getParameter() 는 문자열형을 반환하므로 형변환 해줘야 한다
		
		// Vo 객체로 가공 -> 패스
		
		// Service 로 토스
		int result = new ApvService().deleteApv(apvNo);
		
		// 결과값에 따라 응답화면 지정
		if(result > 0) { 
			request.getSession().setAttribute("alertMsg", "성공적으로 경조사 게시글이 삭제되었습니다.");
			response.sendRedirect(request.getContextPath() + "/list.ap?currentPage=1"); 
		}
		else {
			request.setAttribute("errorPage", "경조사 게시글 삭제에 실패했습니다.");
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
