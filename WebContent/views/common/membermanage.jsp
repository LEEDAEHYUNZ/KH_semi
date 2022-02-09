<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.ArrayList, com.kh.employee.model.vo.Employee" %>

 
<%
	ArrayList<Employee> list = (ArrayList<Employee>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>사원관리</title>
 <!--<title> Drop Down Sidebar Menu | CodingLab </title>-->
    <link rel="stylesheet" href="resources/css/style.css">
    <!-- Boxiocns CDN Link -->
    <link href='https://unpkg.com/boxicons@2.0.7/css/boxicons.min.css' rel='stylesheet'>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    
    <!-- 부트스트랩 -->
    <!-- Latest compiled and minified CSS -->
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css">

    <!-- jQuery library -->
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>

    <!-- Popper JS -->
    <script src="https://cdnjs.cloudflare.com/ajax/libs/popper.js/1.16.0/umd/popper.min.js"></script>

    <!-- Latest compiled JavaScript -->
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.6.0/jquery.min.js"></script>
	<script type="text/javascript">

	function statusChange( statusItem ) {
   	var strText = $(statusItem).text();

   	// strText 에 전체 문자열이 입력된다.
   	$("#empno").val(strText);
   	$("#empno2").val(strText);
	}

</script>
</head>
<body>

	<%@ include file ="../common/menubar.jsp" %>
  
  <!-- 상단 , 메인 -->
	<section class="home-section">
  <!-- 상단 -->
	  <div class="home-content">
	    <i class='bx bx-menu' ></i>
	    <div class="head"><%= loginEmployee.getEmpName() %> <%= loginEmployee.getJobCode() %>님</div>
	  </div>
	    <div>
	    <!-- 게시판 목록-->
	    <br>
	    <div><h2 class="listTitle">사원관리</h2></div>
	    <!-- 검색 폼 -->
	    <form action="<%= contextPath %>/member.se" method="get" width="90%">
	      <table width="90%">
	          <tr>
	              <td align="right">
	                  <select name="searchField" id="searchField">
	                      <option value="a" >이름</option>
	                      <option value="b">직급</option>
	                      <option value="c">부서</option>
	                  </select>
	                  <input type="text" name="searchWord" id="searchWord">
	                  <input type="submit" class="btn btn-outline-secondary btn-sm" name="searchSubmit" id="searchSubmit" value="검색">
	              </td>
	          </tr>
	      </table>
	      </form>
	      
	      <div id="table-div">
	        <!-- 목록 테이블 -->
	        <table width="100%" class="table table-bordered table-hover table-sm">
	            <thead class="thead-dark">
	            <tr>
	                <th width="5%">사번</th>
	                <th width="5%">이름</th>
	                <th width="5%">부서</th>
	                <th width="5%">직급</th>
	                <th>비밀번호</th>
	                <th>주민등록번호</th>
	                <th>주소</th>
	                <th width="10%">이메일</th>
	                <th width="10%">전화번호</th>
	                <th width="10%">입사일</th>
	                <!--  
	                <th width="10%">퇴사일</th>
	                -->
	                <th>근무여부</th>
	                
	            </tr>
	            </thead>
	            <tbody>
	         	 <% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	                	<tr>
	                		
	                	</tr>
	               <% } else { %> 	
	                <% for(Employee e : list) { %>
	            <tr align="center"> <!-- 1행 -->
	                <td > <!-- 사번 --> <a href="#"onclick="statusChange(this)" id="boadListTitle"data-bs-toggle="modal" data-bs-target="#add-new-task-modal"><%=e.getEmpNo()%></a> </td>
	                <td> <!-- 이름 --><%=e.getEmpName()%></td>
	                <td> <!-- 부서 --> <%=e.getDeptId()%></td>
	                <td> <!-- 직급 --> <%=e.getJobCode()%> </td>
	                <td><!--비밀번호--> <%=e.getEmpPwd()%></td>
	                <td> <!-- 주민번호 --> <%=e.getEmpidNo()%> </td>
	                <td> <!-- 주소 --> <%=e.getAddress()%> </td>
	                <td><!--이메일--><%=e.getEmail()%></td>
	                <td><!--전화번호--> <%=e.getPhone()%></td>
	                <td> <!-- 입사일 --> <%=e.getHireDate()%></td>
	                <td><!-- 근무 여부 --><%=e.getStatus()%></td>
	                
	                <% } %>
	             <% } %>   
	            </tr>
	            
	
	            </tbody>
	        </table>
	      </div>
	
	     
	      
	    </div>
	    
	  </section>
	
	  <div class="modal fade task-modal-content" id="add-new-task-modal" tabindex="-1" role="dialog" aria-labelledby="NewTaskModalLabel" aria-hidden="true">
	    <div class="modal-dialog modal-dialog-centered modal-lg">
	        <div class="modal-content">
	            <div class="modal-header">
	                <h4 class="modal-title" id="NewTaskModalLabel">정보 변경</h4>
	                <button type="button" class="btn btn-primary" data-bs-dismiss="modal" aria-label="Close"><svg xmlns="http://www.w3.org/2000/svg" width="25" height="25" fill="currentColor" class="bi bi-x" viewBox="0 0 16 16">
	                  <path d="M4.646 4.646a.5.5 0 0 1 .708 0L8 7.293l2.646-2.647a.5.5 0 0 1 .708.708L8.707 8l2.647 2.646a.5.5 0 0 1-.708.708L8 8.707l-2.646 2.647a.5.5 0 0 1-.708-.708L7.293 8 4.646 5.354a.5.5 0 0 1 0-.708z"/>
	                </svg></button>
	            </div>
	            <div class="modal-body">
	              <form action="<%= contextPath %>/updatemanage.em" method="post">
	                <div class="user-details">
	                <input type="hidden" name="empNo" id="empno">
	                  <div class="input-box">
	                    <span class="details">직급</span>
	                    <input type="text" name="job" placeholder="직급 입력">
	                  </div>
	                 
	
	                  <div class="input-box">
	                    <span class="details">부서</span>
	                    <input type="text" name="deptId" placeholder="부서 입력">
	                  </div>
	             		<div class="input-box">
	                    <span class="details">근무 현황</span><br>
	                    <select class="Status" id="priority" name="status">
	                      <option value='Y'>Y</option>
	                      <option value='N'>N</option>
		                  </select>
		                  </div>
	                    </div>
	                    
	                    <div class="modal-end2">
	                        <button type="submit" class="btn btn-primary">입력</button>
	                        
	                    </div>
	                </form>
	                <form action="<%= contextPath %>/deletemanage.em" method="post">
	                        	<input type="hidden" name="proNo" id="empno2">
	                        <button type="submit" class="btn btn btn-primary">사원삭제</button>
	                </form>
	            </div>
	        </div><!-- /.modal-content -->
	    </div><!-- /.modal-dialog -->
	  </div><!-- /.modal -->
		
		
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