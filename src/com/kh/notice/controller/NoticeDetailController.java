package com.kh.notice.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.attachment.model.vo.Attachment;
import com.kh.notice.model.service.NoticeService;
import com.kh.notice.model.vo.Notice;

/**
 * Servlet implementation class NoticeDetailController
 */
@WebServlet("/detail.no")
public class NoticeDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public NoticeDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// noticeNo 값 뽑기
		int noticeNo = Integer.parseInt(request.getParameter("nno"));
		
		// VO 가공 -> 패스
		
		// Service 단으로 넘기기
		// 1) 조회수 증가
		NoticeService nService  = new NoticeService();
		int result = nService.increaseCount(noticeNo);
		
		// 2) 조회수가 증가 되었다면 -> notice, attachment 테이블 조회
		if(result > 0) { // 성공
			
			// 2_1) notice 조회
			// 한 건만 조회되므로 Notice 로 받기
			Notice n = nService.selectNotice(noticeNo);
			
			// 2_2) attachment 조회
			// 한 건만 조회되므로 Attachment 로 받기
			Attachment at = nService.selectAttachment(noticeNo);
			
			// 조회했던 n, at 넘기기
			request.setAttribute("n", n);
			request.setAttribute("at", at);
			
			// 화면에 띄우기
			request.getRequestDispatcher("views/notice/noticeDetailView.jsp").forward(request, response);
		
		}
		else { // 실패 -> 에러 알림
			request.setAttribute("alertMsg", "조회에 실패했습니다.");
			request.getRequestDispatcher("views/common/alertMsg.jsp").forward(request, response);
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
