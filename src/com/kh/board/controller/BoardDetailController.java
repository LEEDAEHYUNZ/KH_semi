package com.kh.board.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.attachment.model.vo.Attachment;
import com.kh.board.model.service.BoardService;
import com.kh.board.model.vo.Board;

/**
 * Servlet implementation class BoardDetailController
 */
@WebServlet("/detail.bo")
public class BoardDetailController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardDetailController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// boardNo 값 뽑기
		int boardNo = Integer.parseInt(request.getParameter("bno"));
		
		// VO 가공 -> 패스
		
		// Service 단으로 넘기기
		// 1) 조회수 증가
		BoardService bService  = new BoardService();
		int result = bService.increaseCount(boardNo);
		
		// 2) 조회수가 증가 되었다면 -> board, attachment 테이블 조회
		if(result > 0) { // 성공
			
			// 2_1) board 조회
			// 한 건만 조회되므로 Board 로 받기
			Board b = bService.selectBoard(boardNo);
			
			// 2_2) attachment 조회
			// 한 건만 조회되므로 Attachment 로 받기
			Attachment at = bService.selectAttachment(boardNo);
			
			// 조회했던 b, at 넘기기
			request.setAttribute("b", b);
			request.setAttribute("at", at);
			
			// 화면에 띄우기
			request.getRequestDispatcher("views/board/boardDetailView.jsp").forward(request, response);
		
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
