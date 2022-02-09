<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 
 <%@ page import="java.util.ArrayList, com.kh.calendar.model.vo.Calendar" %>    

<%
   ArrayList<Calendar> list = (ArrayList<Calendar>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>calendar</title>

	<!-- 캘린더 -->
	<link href='resources/lib/main.css' rel='stylesheet'/>
	<script src='resources/lib/main.js'></script>
			
	<script>
   document.addEventListener('DOMContentLoaded', function() {
    var calendarEl = document.getElementById('calendar');

    var calendar = new FullCalendar.Calendar(calendarEl, {
    	
      headerToolbar: {
        left: 'prev,next today',
        center: 'title',
        right: 'dayGridMonth,listMonth'
      },
      initialDate: '2021-12-01',
      navLinks: true, // can click day/week names to navigate views
      businessHours: true, // display business hours
      editable: true,
      selectable: true,
      nowIndicator: true, // 현재 시간 
      dayMaxEvents: true, // 이벤트가 오버되면 높이 제한 (+ 몇 개식으로 표현)

      // 일정 추가 
      events: [
  
    	  <% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	      
	      <% } else { %>    
	    <% for(Calendar c : list) { %>
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
	  var del =$("#deleteca option:checked").text();
	  $("del").val(del);
  </script>
  
<style>

	 body {
	    font-family: Arial, Helvetica Neue, Helvetica, sans-serif;
	    font-size: 14px;  /* 마진, 패딩 값 0 */
	  }
	
	  #calendar {
	   
	     max-width: 950px;
	    margin: auto;
	    margin-top: 30px;
	
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
    <div class="head" ><%= loginEmployee.getEmpName() %> <%= loginEmployee.getJobCode() %>님</div>
  </div>
  
    <div class ="button" style="margin-top:10px;" >
    <input type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#insertCaForm" value="일정 추가" 
    style="margin-right:7px; margin-left:1225px;"> 
    <input type="button" class="btn btn-success btn-sm" data-toggle="modal" data-target="#deleteCaForm" value="일정 삭제"> 
  	</div>
  	
  <div id='calendar'></div>


        <!-- The Modal : 일정 추가 -->
      <div class="modal" id="insertCaForm">
        <div class="modal-dialog">
          <div class="modal-content">
      
            <!-- Modal Header -->
            <div class="modal-header">
              <h4 class="modal-title">일정 추가</h4>
              <button type="button" class="btn btn btn-success btn-sm" data-dismiss="modal">&times;</button>
            </div>
      
            <!-- Modal body -->
            <div class="modal-body" align="center">
      
              <form action="<%= contextPath %>/insert.co" method="post">
           
          
              <table>
                <tr>
                  <td>title</td>
                  <td><input type="text" name="title" required></td>
                </tr>
                <tr>
                  <td>start</td>
                  <td><input type="text" name="startEnd"  placeholder="YYYY-MM-DD" required></td>
                </tr>
              </table>
      
              <br>
      
              <button type="submit" class="btn btn btn-success btn-sm">일정 추가</button>
              </form>
      </div>
     </div>
   </div>
  </div>
  
      <!-- The Modal : 일정 삭제 -->
      <div class="modal" id="deleteCaForm">
        <div class="modal-dialog">
          <div class="modal-content">
      
            <!-- Modal Header -->
            <div class="modal-header">
              <h4 class="modal-title">일정 삭제</h4>
              <button type="button" class="btn btn btn-success btn-sm" data-dismiss="modal">&times;</button>
            </div>
      
            <!-- Modal body -->
            <div class="modal-body" align="center">
      
              <form action="<%= contextPath %>/delete.ca" method="post">
              
              <select id="deleteca" name="deleteca">
              <% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	      
	      		<% } else { %>    
	    		<% for(Calendar c : list) { %>
              <option  value="<%= c.getTitle() %>"><%= c.getTitle() %></option>
              		<% } %>
          		<% } %>
          		
              </select>
              
              <input type="hidden" name="deleteca" id="delteca">
              
              <button type="submit" class="btn btn btn-success btn-sm" style="margin-left:20px">일정 삭제</button>
              
              </form>
      </div>
     </div>
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

</body>
</html>

