package com.kh.board.controller;

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
 * Servlet implementation class BoardInsertController
 */
@WebServlet("/insert.bo")
public class BoardInsertController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public BoardInsertController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// POST 방식
		// 1) 인코딩 설정
		request.setCharacterEncoding("UTF-8");
		
		// 2) 값 뽑아오기 : multipart 객체 이용
		// 2_1) enctype 이 multipart/form-data 로 잘 전송되었을 경우 
		if(ServletFileUpload.isMultipartContent(request)) {
			// 2_1_1) 전송 파일 용량 제한 
			int maxSize = 1024 * 1024 * 10;
			
			// 2_1_2) 전달된 파일을 저장할 서버의 폴더 경로 알아내기
			String savePath = request.getSession().getServletContext().getRealPath("/resources/board_upfiles/");
			
			// 2_2_1) 전달된 파일명 수정
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 3) 값 뽑기
			String boardWriter = multiRequest.getParameter("empNo");
			String boardTitle = multiRequest.getParameter("title");
			String boardContent = multiRequest.getParameter("content");
			
			// 4) VO 객체로 가공 
			// 4_1) 첫번째 INSERT 문
			Board b = new Board();
			b.setBoardWriter(boardWriter);
			b.setBoardTitle(boardTitle);
			b.setBoardContent(boardContent);
			
			// 4_2) 두번째 INSERT 문 -> 첨부파일이 있을 경우 
			Attachment at = null;  
			
			// 4) Service 단으로 전달 
			if(multiRequest.getOriginalFileName("upfile") != null) {
				// 첨부파일이 있다면 VO 객체로 가공
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("upfile")); 
			
				// 수정 파일명
				at.setChangeName(multiRequest.getFilesystemName("upfile")); 
				
				// 파일 경로 
				at.setFilePath("resources/board_upfiles/");
				
			}

			int result = new BoardService().insertBoard(b, at);
			
			// 응답화면 지정
			if(result > 0) { // 성공
				request.getSession().setAttribute("alertMsg", "성공적으로 게시글을 작성했습니다.");
				response.sendRedirect(request.getContextPath() + "/list.bo?currentPage=1");
			}
			else { // 실패
				request.setAttribute("alertMsg", "게시글 작성에 실패했습니다.");
				request.getRequestDispatcher("views/board/boardEnrollForm.jsp").forward(request, response);
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
