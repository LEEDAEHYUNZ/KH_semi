<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>결재 현황 작성</title>
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
	
	<!-- 게시판 글작성 -->
	<div>  
      <br>
      <div><h2 class="listTitle">결재 기안</h2></div>
      <br>
      <form name="writeForm" action="/24works/insertApv.ap" method="post" enctype="multipart/form-data">
        
        <input type="hidden" name="empNo" value="<%= loginEmployee.getEmpNo() %>">
        
        <table border="1" id="enroll-area" class="table-sm" width="90%">
            
            <tr>
                <th class="table-dark">제목</th>
                <td>
                    <input type="text" name="title" placeholder="사원명_양식명" required>
                </td>
            </tr>
            <tr>
                <th class="table-dark">내용</th>
                <td>
                    <textarea name="content" id="writeContent" placeholder="결재 사유를 입력하세요" required></textarea>
                </td>
            </tr>
            <tr>
                <th class="table-dark">첨부파일</th>
                <td>
                    <input type="file" name="upfile">
                </td>
            </tr>
        </table>
        <br>
        <table width="90%">
            <tr>
                <td colspan="2" align="center">
                    <button type="submit" class="btn btn-secondary btn-sm">작성</button>
                    <button type="reset" class="btn btn-secondary btn-sm">초기화</button>
                    <a href="<%= contextPath %>/listApv.ap?currentPage=1" class="btn btn-secondary btn-sm">목록</a>
                </td>
            </tr>
        </table>
        </form>
    </div>
    </section>
    
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