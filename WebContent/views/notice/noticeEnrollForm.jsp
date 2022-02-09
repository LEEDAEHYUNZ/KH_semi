<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>공지사항 게시글 작성</title>
<style>
	#enroll-form input {
		width : 100%;
		height : 100%;
	}
	#enroll-form textarea {
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
      <div><h2 class="listTitle">공지사항</h2></div>
      <br>
      <form name="writeForm" id="enroll-form" action="<%= contextPath %>/insert.no" method="post" enctype="multipart/form-data" onsubmit="return vlidateForm(this);">
        
        <input type="hidden" name="empNo" value="<%= loginEmployee.getEmpNo() %>" >
        
        <table border="1" class="table-sm" width="90%">
            <tr>
                <th class="table-dark">작성자</th>
                <td>
                    <input type="text" name="name" value="<%= loginEmployee.getEmpName() %>" required>
                </td>
            </tr>
            <tr>
                <th class="table-dark">제목</th>
                <td>
                    <input type="text" name="title" required>
                </td>
            </tr>
            <tr>
                <th class="table-dark">내용</th>
                <td>
                    <textarea name="content" id="writeContent" required></textarea>
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
                    <a href="<%= contextPath %>/list.no?currentPage=1" class="btn btn-secondary btn-sm">목록</a>
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