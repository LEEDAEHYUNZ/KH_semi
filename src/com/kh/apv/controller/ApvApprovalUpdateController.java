package com.kh.apv.controller;

import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.tomcat.util.http.fileupload.servlet.ServletFileUpload;

import com.kh.apv.model.service.ApvApprovalService;
import com.kh.apv.model.vo.Apv;
import com.kh.attachment.model.vo.Attachment;
import com.kh.common.MyFileRenamePolicy;
import com.oreilly.servlet.MultipartRequest;

/**
 * Servlet implementation class ApvApprovalUpdateController
 */
@WebServlet("/updateApv.ap")
public class ApvApprovalUpdateController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ApvApprovalUpdateController() {
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
			String savePath = request.getSession().getServletContext().getRealPath("/resources/apv_upfiles/");
			
			// 2_3) 전달된 파일명 수정 후 서버에 업로드
			MultipartRequest multiRequest = new MultipartRequest(request, savePath, maxSize, "UTF-8", new MyFileRenamePolicy());
			
			// 2_4) 값 뽑기
			int apvNo = Integer.parseInt(multiRequest.getParameter("ano"));
			String apvWriter = multiRequest.getParameter("name");
			String apvTitle = multiRequest.getParameter("title");
			String apvContent = multiRequest.getParameter("content");
			
			// 3) VO 가공
			// 3_1) Notice
			Apv a = new Apv();
			a.setApvNo(apvNo);
			a.setApvWriter(apvWriter);
			a.setApvTitle(apvTitle);
			a.setApvContent(apvContent);
			
			// 3_2) Attachment
			Attachment at = null;
			
			// 첨부파일이 있는지 확인
			if(multiRequest.getOriginalFileName("reUpfile") != null) {
				
				// 새로운 파일명이 있다면 -> 객체 생성 후 원본 이름, 수정 이름, 파일 경로로 초기화
				at = new Attachment();
				at.setOriginName(multiRequest.getOriginalFileName("reUpfile"));
				at.setChangeName(multiRequest.getFilesystemName("reUpfile"));
				at.setFilePath("resources/notice_upfiles/");
				
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
					// + 어느 게시글의 첨부파일인지 noticeNo (REF_NNO)
					at.setRefNno(apvNo);
						
				}
				
			}
			
			// 4) Service 단으로 토스
			int result = new ApvApprovalService().updateApv(a, at);
			
			// 5) 결과에 따른 응답화면 지정
			if(result > 0) {
				request.getSession().setAttribute("alertMsg", "성공적으로 수정되었습니다.");
				response.sendRedirect(request.getContextPath() + "/detailApv.ap?ano=" + apvNo);
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
