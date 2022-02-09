package com.kh.employee.controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.kh.employee.model.service.EmployeeService;

/**
 * Servlet implementation class AjaxEmpNoCheckController
 */
@WebServlet("/empNoCheck.em")
public class AjaxEmpNoCheckController extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AjaxEmpNoCheckController() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// GET
		
		// 2) request 로 부터 값 뽑기
		int checkEmpNo = Integer.parseInt(request.getParameter("checkEmpNo"));
		
		// 3) VO 가공 => 패싱
		
		// 4) Service 단으로 토스(EmployeeService)
		int count = new EmployeeService().empNoCheck(checkEmpNo); // 중복확인 SELECT문
		
		// 5) 결과에 따른 응답뷰 지정 => 화면 깜빡 => ajax는 결과물만 내보낸다
		
		// 형식과 인코딩 먼저 지정
		response.setContentType("text/html; charset= UTF-8");
		
		// "NNNNN" 중복값이 있을 때 (count == 1)
		// "NNNNY" 중복값이 없을 때 (count == 0)
		if(count > 0) {
			response.getWriter().print("NNNNN");
		}
		else {
			response.getWriter().print("NNNNY");
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
