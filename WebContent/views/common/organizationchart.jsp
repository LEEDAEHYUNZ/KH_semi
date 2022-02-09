<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.ArrayList, com.kh.employee.model.vo.Employee, com.kh.common.model.vo.PageInfo" %>
 <%
 	
 	ArrayList<Employee> list = (ArrayList<Employee>)request.getAttribute("list");
 	PageInfo pi = (PageInfo)request.getAttribute("pi");
 	
	// 페이징바 만들때 필요한 변수 미리 셋팅
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
%>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>조직도</title>
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
	    <div><h2 class="listTitle">조직도</h2></div>
	    <!-- 검색 폼 -->
	    <form action="/24works/search.em" method="post" width="90%">
	      <table width="90%">
	          <tr>
	              <td align="right">
	                  <select name="searchField" id="searchField">
	                  	  <option value="a" selected>이름</option>
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
	                <th width="10%">사번</th>
	                <th width="10%">이름</th>
	                <th width="5%">부서</th>
	                <th width="5%">직급</th>
	                <th width="25%">주소</th>
	                <th width="15%">이메일</th>
	                <th width="10%">전화번호</th>
	                <th width="10%">입사일</th>
	                
            	</tr>
	            </thead>
	            <tbody>
	         	 <% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	                	<tr>
	                		
	                	</tr>
	                	
	               <% } else { %> 	
	                <% for(Employee e : list) { %>
	            <tr align="center"> <!-- 1행 -->
	                <td> <!-- 사번 --> <%=e.getEmpNo()%></td>
	                <td> <!-- 이름 --><%=e.getEmpName()%></td>
	                <td> <!-- 부서 --> <%=e.getDeptId()%></td>
	                <td> <!-- 직급 --> <%=e.getJobCode()%> </td>
	                <td> <!-- 주소 --> <%=e.getAddress()%> </td>
	                <td><!--이메일--><%=e.getEmail()%></td>
	                <td><!--전화번호--> <%=e.getPhone()%></td>
	                <td> <!-- 입사일 --> <%=e.getHireDate()%> </td>
	               <% } %>
	             <% } %>
	          </tr>
	            </tbody>
	        </table>
	      </div>
	
	      <br><br>
	      
	     <!-- 페이징바 -->
        <div align="center" class="paging-area">
        
        	<!-- 페이징바에서 < 를 담당 : 이전페이지로 이동 -->
        	<% if(currentPage != 1) { %>
        		<button onclick="location.href='<%= contextPath %>/list.ch?currentPage=<%= currentPage - 1 %>'">&lt;</button>
        	<% } %>
        
        	<!-- 페이징바에서 숫자를 담당 -->
            <% for(int i = startPage; i <= endPage; i++) { %>
            	<!-- 버튼이 눌렸을 때 해당 페이지로 이동하게끔 -->
            	<% if(i != currentPage) { %>
            		<button onclick="location.href='<%= contextPath %>/list.ch?currentPage=<%= i %>'"><%= i %></button>
            	<% } else { %>
            		<!-- 현재 내가 보고있는 페이지일 경우에는 클릭이 안되게끔 막고싶다. -->
            		<button disabled><%= i %></button>
            	<% } %>
            <% } %>
            
            <!-- 페이징바에서 > 를 담당 : 다음페이지로 이동 -->
            <% if(currentPage != maxPage) { %>
            	<button onclick="location.href='<%= contextPath %>/list.ch?currentPage=<%= currentPage + 1 %>'">&gt;</button>
            <% } %>
            
        </div>

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
	  <script src="resources/css/vendor.js"></script>
	  <script src="resources/css/app.js"></script>

	  <!-- dragula js-->
	  <script src="resources/css/dragula.js"></script>
	  <script src="resources/css/component.js"></script>
</body>
</html>