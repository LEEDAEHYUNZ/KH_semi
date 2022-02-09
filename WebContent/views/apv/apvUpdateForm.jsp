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
<title>결재</title>
<style>
	#enroll-area input {
		width : 100%;
		height : 100%;
	}
	#enroll-area textarea {
		width : 100%;
		height : 300px;
		resize : none;
	}
</style>
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
	
	<!-- 결재 수정 -->
	<div>  
      <br>
      <div><h2 class="listTitle">결재</h2></div>
      <br>
      <form action="<%= contextPath %>/update.ap" method="post" name="writeForm" enctype="multipart/form-data">
        
		<!-- 게시글 번호를 hidden 으로 넘길 것 -->
		<input type="hidden" name="ano" value="<%= a.getApvNo() %>">
    
        <table border="1" id="enroll-area" class="table-sm" width="90%">
            <tr>
                <th class="table-dark">작성자</th>
                <td>
                    <input type="text" name="name" value="<%= loginEmployee.getEmpName() %>" readonly>
                </td>
            </tr>
            <tr>
                <th class="table-dark">제목</th>
                <td>
                    <input type="text" name="title">
                </td>
            </tr>
            <tr>
                <th class="table-dark">내용</th>
                <td>
                    <textarea name="content" id="updateContent"></textarea>
                </td>
            </tr>
            <tr>
                <th class="table-dark">첨부파일</th>
                <td>
                    <!-- 파일이 있다면 원본명 -->
                   	<% if(at != null) { %>
                   		<%= at.getOriginName() %>
                   		<!-- 기존 파일이 있으면 수정 파일명, 파일번호 넘기기 -->
                   		<input type="hidden" name="originFileNo" value="<%= at.getFileNo() %>">
                   		<input type="hidden" name="originFileName" value="<%= at.getChangeName() %>">
                   	<% } %>
                   	<!-- 기존 파일이 없다면 -->
                   	<input type="file" name="reUpfile">
                </td>
            </tr>
        </table>
        <br>
        <table width="90%">
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" class="btn btn-secondary btn-sm">작성</button>
                    <button type="reset" class="btn btn-secondary btn-sm">초기화</button>
                    <a href="<%= contextPath %>/list.ap?currentPage=1" class="btn btn-secondary btn-sm">목록</a>
                </td>
            </tr>
        </table>
        </form>
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