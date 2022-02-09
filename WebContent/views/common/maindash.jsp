<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.notice.model.vo.Notice, com.kh.notice.model.dao.NoticeDao, com.kh.calendar.model.vo.Calendar
, com.kh.board.model.vo.Board, com.kh.board.model.dao.BoardDao, com.kh.calendar.model.vo.Calendar" %>  
<%
	ArrayList<Notice> main1 = (ArrayList<Notice>)request.getAttribute("main1");
	ArrayList<Board> main2 = (ArrayList<Board>)request.getAttribute("main2");
	ArrayList<Calendar> main3 = (ArrayList<Calendar>)request.getAttribute("main3");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>main</title>
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
  
    <!-- 캘린더 -->
    <link href='resources/lib/main.css' rel='stylesheet' />
    <script src='resources/lib/main.js'></script>
  
</head>
<body>
	
	<%@ include file="../common/menubar.jsp" %>

<% if(loginEmployee != null) { %>
  <!-- 상단 , 메인 -->
  <section class="home-section">
    <!-- 상단 -->
    <div class="home-content">
      <i class='bx bx-menu' ></i>
      <div class="head"><%= loginEmployee.getEmpName() %> <%= loginEmployee.getJobCode() %>님</div>
    </div>
    
    
    
    <!-- 메인 페이지-->
    <div class="blank"></div>
    <div class="content3">
      <!--캘린더-->
      <div class="left-content" >
        <div id='calendar' class="dash-calendar" id="main-calendar"></div>
      </div>
      
      <div class="right-content">
       
 
          <div id="table-div">
            <!-- 목록 테이블 -->
            <h5>공지사항</h5>
            <table  class="table table-bordered table-hover table-sm">
              <thead class="thead-dark">
              <tr>
                  <th width="10%">번호</th>
                  <th width="40">제목</th>

                  <th width="15%">작성자</th>
                  <th width="17%">작성일</th>                  
              </tr>
              </thead>
              <tbody>
               <% if(main1.isEmpty()) { %> <!-- 조회글 없음 -->
		               	          
	           <% } else { %>
	                	<!-- list 뽑기 -->
	                <% for(Notice n : main1) { %>
	                <% if( n.getNoticeNo() > main1.size() - 5) {%>
	                		<tr align="center">
	                			<td><%= n.getNoticeNo() %></td>
	                			<td align="left"><%= n.getNoticeTitle() %></td>

	                			<td><%= n.getNoticeWriter() %></td>
	                			<td><%= n.getPostDate() %></td>
	                		</tr>
	                		<% } %>
	                	<% } %>
	                <% } %>
                  </tbody>
              </table> 
              
          </div>
          
          <div id="table-div">
            <!-- 목록 테이블 -->
            <h5>경조사</h5>
            <table  class="table table-bordered table-hover table-sm">
              <thead class="thead-dark">
              <tr>
                  <th width="10%">번호</th>
                  <th width="40">제목</th>

                  <th width="15%">작성자</th>
                  <th width="17%">작성일</th>                  
              </tr>
              </thead>
              <tbody>
               <% if(main2.isEmpty()) { %> <!-- 조회글 없음 -->
		               	          
	           <% } else { %>
	                	<!-- list 뽑기 -->
	                <% for(Board b : main2) { %>
	                <% if( b.getBoardNo() > main2.size() - 5) {%>
	                		<tr align="center">
	                			<td><%= b.getBoardNo() %></td>
	                			<td align="left"><%= b.getBoardTitle() %></td>

	                			<td><%= b.getBoardWriter() %></td>
	                			<td><%= b.getPostDate() %></td>
	                		</tr>
	                		<% } %>
	                	<% } %>
	                <% } %>
                  </tbody>
              </table> 
              
          </div>
       </div>
    </div>
  
  </section>

  <% } else {
        response.sendRedirect(request.getContextPath());
   } %>

  

<script>
   document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	
      headerToolbar: {
        left: '',
        center: 'title',
        right: ''
      },
      initialDate: '2021-12-01',
      navLinks: false, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: false,
      selectable: true,
      nowIndicator: true, // 현재 시간 
      dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)

      // 일정 추가 
      events: [
  
    	  <% if(main3.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	      
	      <% } else { %>    
	    <% for(Calendar c : main3) { %>
    	  {
    	      
          title: '<%= c.getTitle() %>',
          start: '<%= c.getStartEnd() %>',
          
    	  },
    	  <% } %>
          <% } %>
    	  {}
      
      ]
      

    
    });
    calendar.render();
  });
  </script>
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