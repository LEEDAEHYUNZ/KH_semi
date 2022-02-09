package com.kh.apv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.apv.model.service.ApvService;
import com.kh.apv.model.vo.Apv;
import com.kh.attachment.model.vo.Attachment;


/**
 * Servlet implementation class ApvUpdateFormController
 */
@WebServlet("/updateForm.ap")
public class ApvUpdateFormController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApvUpdateFormController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// GET 방식 
		// 인코딩 패스
		
		// 해당 글번호 
		int apvNo = Integer.parseInt(request.getParameter("ano"));
		
		// 글번호에 해당하는 Apv 테이블로부터 행을  조회
		Apv a = new ApvService().selectApv(apvNo);
		
		// 글번호에 해당하는 첨부파일 Attachment 테이블로부터 행을 조회
		Attachment at = new ApvService().selectAttachment(apvNo);
		
		// 값 넘기기
		request.setAttribute("a", a);
		request.setAttribute("at", at);
		
		// 화면 먼저 띄워보기 -> 포워딩
		request.getRequestDispatcher("views/apv/apvUpdateForm.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
