<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*, com.kh.apv.model.vo.Apv, com.kh.apv.model.dao.ApvDao, com.kh.common.model.vo.PageInfo" %>  
<%
	ArrayList<Apv> list = (ArrayList<Apv>)request.getAttribute("list");
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
<title>결재 현황 목록</title>
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
    	<div><h2 class="listTitle">결재 현황</h2></div>
    	<br>
    	
        <!-- 목록 테이블 -->
        <div id="table-div">
            <table width="100%" class="list-area table table-bordered table-hover table-sm">
                <thead class="thead-dark">
                    <tr>
                        <th width="10%">번호</th>
                        <th width="35%">결재 양식명</th>
                        <th width="15%">기안자</th>
                        <th width="15%">결재자</th>
                        <th width="15%">작성일</th>
                        <th width="10%">현황</th>
                    </tr>
                </thead>
                <tbody>
                   <!-- 게시글 출력 : 게시글이 있는지 없는지 -> isEmpty() 이용 -->
	               <% if(list.size() <= 3) { %> <!-- 조회글 없음 -->
		               	<tr>
		               		<td colspan="6">조회된 리스트가 없습니다.</td>
	      				</tr>           
	               <% } else { %>
	               		<!-- list 뽑기 -->
	               		<!-- 관리자 또는 해당 글 작성자일 경우 -->
	                	<% for(Apv a : list) { %>
	                		<% if(loginEmployee.getEmpNo() == 1 || loginEmployee.getEmpName().equals(a.getApvWriter())) { %>
		                		<% if (a.getApvNo() > 3) { %>
		                		<tr align="center">
		                			<td><%= a.getApvNo() - 3  %></td>
		                			<td align="left"><%= a.getApvTitle() %></td>
		                			<td><%= a.getApvWriter() %></td>
		                			<td>관리자</td>
		                			<td><%= a.getPostDate() %></td>
		                			<td>
		                				<% if(a.getApvStatus() == 1) { %>
		                					결재전
		                				<% } else if(a.getApvStatus() == 2) { %>
		                					반려
		                				<% } else if(a.getApvStatus() == 3) { %>
		                					결재완료
		                				<% } %>
		                			</td>
		                		</tr>
		                		<% } %>
	                		<% } %>
	                	<% } %>
	                <% } %>
                </tbody>
            </table>
        </div>
        
        <script>
        	$(function() {
        		$(".list-area>tbody>tr").click(function(){
        			var an = $(this).children().eq(0).text();
        			var ano = Number(an) + 3;
        			
        			// /24works/detail.ao?ano=X
        			location.href = "<%= contextPath %>/detailApv.ap?ano=" + ano;
        		});
        	});
        </script>

        <!-- 하단 메뉴 -->
        <!-- 로그인 값이 있으면서 관리자가 아닐 경우 -->
        <% if(loginEmployee != null) { %>
	        <table width="90%">
	            <tr align="right">
	                <td width="50">
	                    <a href="<%= contextPath %>/enrollFormApv.ap" class="btn btn-secondary btn-sm">기안</a>
	                </td>
	            </tr>
	       	</table>
       	<% } %>
        
        <!-- 페이징바 -->
        <div align="center" class="paging-area">
        	<!-- 이전 페이지 -->
        	<% if(currentPage != 1) { %>
        		<button onclick="location.href='<%= contextPath %>/listApv.ap?currentPage=<%= currentPage - 1 %>'">&lt;</button>
        	<% } %>
        	
        	<!-- 페이지 번호 -->
            <% for(int i = startPage; i <= endPage; i++) { %>
            	<!-- 버튼 클릭시 이동 -->
            	<% if(i != currentPage) { %>
            		<button onclick="location.href='<%= contextPath %>/listApv.ap?currentPage=<%= i %>'"><%= i %></button>
            	<% } else { %>
            		<!-- 현재페이지 새로고침 막기 -->
            		<button disabled><%= i %></button>
            	<% } %>
            <% } %>
            
            <!-- 다음 페이지 -->
            <% if(currentPage != maxPage) { %>
            	<button onclick="location.href='<%= contextPath %>/listApv.ap?currentPage=<%= currentPage + 1 %>'">&gt;</button>
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