package com.kh.board.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.board.model.service.BoardService;
import com.kh.attachment.model.vo.Attachment;
import com.kh.board.model.vo.Board;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class BoardUpdateController
 */
@WebServlet("/update.bo")
public class BoardUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardUpdateController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST 방식
		// 1) 인코딩
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑기 -> 파일 전송 유무
		if(ServletFileUpload.isMultipartContent(request)) {
			
			// 파일 내려받기
			// 2_1) 전송파일 용량 제한 
			int maxSize = 1024 * 1024 * 10;
			
			// 2_2) 전달된 파일을 저장시킬 서버 폴더의 물리적인 경로
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			// 2_3) 전달된 파일명 수정 후 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 2_4) 값 뽑기
			int boardNo = Integer.parseInt(multiRequest.getParameter("bno"));
			String boardWriter = multiRequest.getParameter("name");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			// 3) VO 가공
			// 3_1) Board
			Board b = new Board();
			b.setBoardNo(boardNo);
			b.setBoardWriter(boardWriter);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			// 3_2) Attachment
			Attachment at = null;
			
			// 첨부파일이 있는지 확인
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				
				// 새로운 파일명이 있다면 -> 객체 생성 후 원본 이름, 수정 이름, 파일 경로로 초기화
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				at.setFilePath("resources/board_upfiles/");
				
				// 첨부파일이 있을 경우 + 원본 파일도 있을 경우
				// 원본 파일의 파일번호, 수정명 필요 -> Attachment 테이블 update 할 때 이용
				if(multiRequest.getParameter("originFileNo") != null) {
					// 기존 파일이 있다면
					at.setFileNo(Integer.parseInt(multiRequest.getParameter("originFileNo")));
				
					// 기존에 서버에 존재하던 첨부파일 삭제
					new File(savePath + multiRequest.getParameter("originFileName")).delete();
				}
				else {
					// 새로운 첨부파일이 넘어왔지만 기존 파일이 없을 경우 -> INSERT
					// + 어느 게시글의 첨부파일인지 boardNo (REF_BNO)
					at.setRefBno(boardNo);
						
				}
				
			}
			
			// 4) Service 단으로 토스
			int result = new BoardService().updateBoard(b, at);
			
			// 5) 결과에 따른 응답화면 지정
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath() + "/detail.bo?bno=" + boardNo);
			}
			else {
				request.setAttribute("errorPage", "글 수정에 실패했습니다.");
				request.getRequestDispatcher("views/common/errorPage.jsp").forward(request, response);
			}
			
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
