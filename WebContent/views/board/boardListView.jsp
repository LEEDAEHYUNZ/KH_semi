<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.board.model.vo.Board, com.kh.board.model.dao.BoardDao, com.kh.common.model.vo.PageInfo" %>  
<%
	ArrayList<Board> list = (ArrayList<Board>)request.getAttribute("list");
	PageInfo pi = (PageInfo)request.getAttribute("pi");
	
	// 페이징바 변수
	int currentPage = pi.getCurrentPage();
	int startPage = pi.getStartPage();
	int endPage = pi.getEndPage();
	int maxPage = pi.getMaxPage();
	
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>경조사 게시글 목록</title>   
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
    
  	<!-- 게시판 목록-->
  	<div>
        <br>
    	<div><h2 class="listTitle">경조사</h2></div>
    	<br>
    	
    	<!-- 검색 폼 -->
    	<!-- 키워드로 검색할 수 있게 구현하기 -->
    	<div class="list-top-nav">
            <form action="search.bo" method="post">
                <table>
                    <tr align="center">
                        <th class="table-dark"><a href="<%= contextPath %>/list.no?currentPage=1" style="color : white;">공지사항</a></th>
                        <th class="table-dark" style="background: #707070;"><a href="<%= contextPath %>/list.bo?currentPage=1" style="color : white;">경조사</a></th>
                        <td align="right">
                            <select name="searchField" id="searchField">
                                <option value="title">제목</option>
                                <option value="writer">작성자</option>
                            </select>
                            <input type="text" name="searchWord" id="searchWord">
                            <input type="submit" class="btn btn-outline-secondary btn-sm" name="searchSubmit" id="searchSubmit" value="검색">
                        </td>
                    </tr>
                </table>
            </form> 
        </div>

        <!-- 목록 테이블 -->
        <div id="table-div">
            <table width="100%" class="list-area table table-bordered table-hover table-sm">
                <thead class="thead-dark">
                    <tr>
                        <th width="10%">번호</th>
                        <th width="40">제목</th>
                        <th width="15%">작성자</th>
                        <th width="15%">작성일</th>
                        <th width="10%">조회수</th>
                    </tr>
                </thead>
                <tbody>
                   <!-- 게시글 출력 : 게시글이 있는지 없는지 -> isEmpty() 이용 -->
	               <% if(list.isEmpty()) { %> <!-- 조회글 없음 -->
		               	<tr>
		               		<td colspan="5">조회된 리스트가 없습니다.</td>
	      				</tr>           
	               <% } else { %>
	               		<!-- list 뽑기 -->
	                	<% for(Board b : list) { %>
	                		<tr align="center">
	                			<td><%= b.getBoardNo() %></td>
	                			<td align="left"><%= b.getBoardTitle() %></td>
	                			<td><%= b.getBoardWriter() %></td>
	                			<td><%= b.getPostDate() %></td>
	                			<td><%= b.getVisitCount() %></td>
	                		</tr>
	                	<% } %>
	                <% } %>
                </tbody>
            </table>
        </div>
        
        <script>
        	$(function() {
        		$(".list-area>tbody>tr").click(function(){
        			var bno = $(this).children().eq(0).text();
        			
        			// /24works/detail.bo?bno=X
        			location.href = "<%= contextPath %>/detail.bo?bno=" + bno;
        		});
        	});
        </script>

        <!-- 하단 메뉴 -->
        <% if(loginEmployee != null) { %>
	        <table width="90%">
	            <tr align="right">
	                <td width="50">
	                    <a href="<%= contextPath %>/enrollForm.bo" class="btn btn-secondary btn-sm">글작성</a>
	                </td>
	            </tr>
	       	</table>
       	<% } %>
        
        <!-- 페이징바 -->
        <div align="center" class="paging-area">
        	<!-- 이전 페이지 -->
        	<% if(currentPage != 1) { %>
        		<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= currentPage - 1 %>'">&lt;</button>
        	<% } %>
        	
        	<!-- 페이지 번호 -->
            <% for(int i = startPage; i <= endPage; i++) { %>
            	<!-- 버튼 클릭시 이동 -->
            	<% if(i != currentPage) { %>
            		<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= i %>'"><%= i %></button>
            	<% } else { %>
            		<!-- 현재페이지 새로고침 막기 -->
            		<button disabled><%= i %></button>
            	<% } %>
            <% } %>
            
            <!-- 다음 페이지 -->
            <% if(currentPage != maxPage) { %>
            	<button onclick="location.href='<%= contextPath %>/list.bo?currentPage=<%= currentPage + 1 %>'">&gt;</button>
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
</body>
</html>