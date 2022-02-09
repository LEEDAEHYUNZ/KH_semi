<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.apv.model.vo.Apv, com.kh.attachment.model.vo.Attachment" %>
<% 
	Apv a = (Apv)request.getAttribute("a");
	Attachment at = (Attachment)request.getAttribute("at");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>

	<%@ include file="../common/menubar.jsp" %>
	
	 <!-- 상단 , 메인 -->
  	<section class="home-section">
    <!-- 상단 -->
    <div class="home-content">
      <i class='bx bx-menu' ></i>
      <div class="head"><%= loginEmployee.getEmpName() %> <%= loginEmployee.getJobCode() %>님</div>
    </div>
	<!-- 게시판 상세 -->
	<div>  
      <br>
      <div><h2 class="listTitle">결재양식</h2></div>
      <br>
      <table border="1" class="table-sm" width="90%">
	      <colgroup>
	          <col width="15%"/><col width="35%"/>
	          <col width="15%"/><col width="35%"/>
	      </colgroup>
	  
	      <!-- 게시글 정보 -->
	      <tr>
	          <th class="table-dark">번호</th> <td><%= a.getApvNo() %></td>
	          <th class="table-dark">작성자</th> <td>조창희</td>
	      </tr>
	      <tr>
	          <th class="table-dark">작성일</th> <td colspan="3"><%= a.getPostDate() %></td>
	      </tr>
	      <tr>
	          <th class="table-dark">제목</th>
	          <td colspan="3"><%= a.getApvTitle() %></td>
	      </tr>
	      <tr>
	          <th class="table-dark">내용</th>
	          <td colspan="3" height="100"><%= a.getApvContent() %></td>
	      </tr>

	      <!-- 첨부파일 -->
	      <tr>
	          <th class="table-dark">첨부파일</th>
	          <td colspan="3">
	              <!-- 첨부파일이 없을 경우 -->
	              <% if(at == null) { %>
	              	첨부파일이 없습니다.
	              <% } else { %>
	              <!-- 첨부파일이 있을 경우 -->
	              	<a download="<%= at.getOriginName() %>" href="<%= contextPath %>/<%= at.getFilePath() + at.getChangeName() %>">
	                	<%= at.getOriginName() %>
	                </a>
	              <% } %>
	          </td>
	      </tr>
      </table>
      
      <br>
      
      <table width="90%">
          <!-- 하단 메뉴 (버튼) -->
          <tr>
              <td colspan="4" align="center">
                  <!-- 로그인한 사용자이면서 작성자인 경우엔만 보여지게끔 -->
		          <% if(loginEmployee != null && loginEmployee.getEmpNo() == 1) { %>
			         <a href="<%= contextPath %>/updateForm.ap?ano=<%= a.getApvNo() %>" class="btn btn-warning btn-sm">수정</a>
			         <a href="<%= contextPath %>/delete.ap?ano=<%= a.getApvNo() %>" class="btn btn-danger btn-sm">삭제</a>
		          <% } %>
                  <a href="<%= contextPath %>/list.ap?currentPage=1" class="btn btn-secondary btn-sm">목록</a>
              </td>
          </tr>
      </table>
      
    </div> 
      <script>
    let arrow = document.querySelectorAll(".arrow");
    for (var i = 0; i < arrow.length; i++) {
      arrow[i].addEventListener("click", (e)=>{
     let arrowParent = e.target.parentElement.parentElement;//selecting main parent of arrow
     arrowParent.classList.toggle("showMenu");
      });
    }
    let sidebar = document.querySelector(".sidebar");
    let sidebarBtn = document.querySelector(".bx-menu");
    console.log(sidebarBtn);
    sidebarBtn.addEventListener("click", ()=>{
      sidebar.classList.toggle("close");
    });
    </script>
    

</body>
</html>