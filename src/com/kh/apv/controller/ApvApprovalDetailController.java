package com.kh.apv.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.apv.model.service.ApvApprovalService;
import com.kh.apv.model.service.ApvService;
import com.kh.apv.model.vo.Apv;
import com.kh.attachment.model.vo.Attachment;

/**
 * Servlet implementation class ApvApprovalDetailController
 */
@WebServlet("/detailApv.ap")
public class ApvApprovalDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApvApprovalDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// 선택된 해당 게시글 번호 뽑기
		int apvNo = Integer.parseInt(request.getParameter("ano"));

		// 받아올 게시글은 한 개
		// apvNo를 매개변수에 담고 서비스 단으로 넘겨서 그 결과값을 Apv 로 받기
		Apv a = new ApvApprovalService().selectApv(apvNo);

		// 첨부파일도 한 개
		// 해당 게시글에 있는 첨부파일 값을 받아와야 하니까 
		// apvNo를 매개변수에 담고 서비스 단으로 넘겨서 그 결과값을 Attachment 로 받기
		Attachment at = new ApvApprovalService().selectAttachment(apvNo);

		// --> 서비스 단에서 결과값 반환 받은 다음

		// Attribute 영역에 받은 값 저장하기
		request.setAttribute("a", a);
		request.setAttribute("at", at);

		// 응답화면 지정하기
		request.getRequestDispatcher("views/apv/apvApprovalDetailView.jsp").forward(request, response);
		
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
