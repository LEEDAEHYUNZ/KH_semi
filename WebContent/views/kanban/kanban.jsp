<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
 <%@ page import="java.util.ArrayList, com.kh.kanban.model.vo.Kanban, com.kh.employee.model.vo.Employee" %>    

<%
	ArrayList<Kanban> list = (ArrayList<Kanban>)request.getAttribute("list");
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>프로젝트 현황</title>
 <!--<title> Drop Down Sidebar Menu | CodingLab </title>-->

    <!-- App css -->
    <link href="resources/css/icon.css" rel="stylesheet" type="text/css" />
    <link href="resources/css/app.css" rel="stylesheet" type="text/css" id="light-style" />  
    <style type="text/css">
    .delete{
    	float:right;
    	
    	transform: translateY(-20%);
    }
    .add{

    	float:right;
    	margin-left:3%;
    }
    </style>   
<script type="text/javascript">

	function statusChange( id ) {
		
   	var strText = id;

   	// strText 에 전체 문자열이 입력된다.
   	$("#empNo").val(strText);
   	$("#proNo").val(strText);

	}
	function statusChange2( id ) {
		
	   	var strText = id;

	   	// strText 에 전체 문자열이 입력된다.
	   	$("#empNo2").val(strText);

		}
</script>
</head>

<body>

<%@ include file="/views/common/menubar.jsp" %>

<!-- 상단 , 메인 -->
<section class="home-section">
  <!-- 상단 -->
  <div class="home-content">
    <i class='bx bx-menu' ></i>
    <div class="head"><%= loginEmployee.getEmpName() %> <%= loginEmployee.getJobCode() %>님</div>
  </div>
  
 
<div class="content-kan">   
<div class="row">
  <div class="col-12">
      <div class="page-title-box">
          
          <h4 class="page-title">프로젝트 현황 
              <a href="#" data-bs-toggle="modal" data-bs-target="#add-new-task-modal" class="btn btn-success btn-sm ms-2">추가</a></h4>
      </div>
  </div>
</div>  
</div>
<!-- end page title --> 
<div class="content-kan">
<div class="row">
    <div class="col-12">
        <div class="board">
            <div class="tasks" data-plugin="dragula" data-containers='["task-list-one", "task-list-two", "task-list-three", "task-list-four"]'>
                <h5 class="mt-0 task-header">예정</h5>
               
		                <div id="task-list-one" class="task-list-items">
						<% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	               		<% } else { %> 	
	                	<% for(Kanban k : list) { %>
	                	<% if (k.getStatus()== 1) { %>
		                    <!-- Task Item -->
		                    <div class="card mb-0">
		                        <div class="card-body p-3">
		                            <small class="float-end text-muted"><%= k.getEndDate() %></small>
		
		                            <h5 class="mt-2 mb-2">
		                                <a href="#" data-bs-toggle="modal" data-bs-target="#<%= k.getProNm() %>" class="text-body"><%= k.getProNm() %></a>
		                            </h5>
		                            <p class="mb-0">
		                                <span class="align-middle"><%= k.getEmpNo() %></span>
		                            </p><br>
		                             <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#updateStatus"onclick="statusChange2(this.id)">상태수정</a>
		                            <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#update"onclick="statusChange(this.id)">내용수정</a>
		                        </div> <!-- end card-body -->
		                    </div>
		                    <!-- Task Item End -->
							
		                    
                    <!-- Task Item End -->
                    	 <% } %>
                    <% } %>
	             <% } %>
                		</div>
                	<!-- end company-list-1-->
            </div>

            <div class="tasks">
                <h5 class="mt-0 task-header text-uppercase">시작</h5>
                
                <div id="task-list-two" class="task-list-items">
					<% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	                	
	               		<% } else { %> 	
	                	<% for(Kanban k : list) { %>
	                	<% if (k.getStatus()== 2) { %>
                    <!-- Task Item -->
                    <div class="card mb-0">
                        <div class="card-body p-3">
                            <small class="float-end text-muted"><%= k.getEndDate() %></small>

                            <h5 class="mt-2 mb-2">
                                <a href="#" data-bs-toggle="modal" data-bs-target="#<%= k.getProNm() %>" class="text-body"><%= k.getProNm() %></a>
                            </h5>
                            <p class="mb-0">
                                <span class="align-middle"><%= k.getEmpNo() %></span>
                            </p><br>
		                    <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#updateStatus"onclick="statusChange2(this.id)">상태수정</a>
		                    <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#update"onclick="statusChange(this.id)">내용수정</a>
                        </div> <!-- end card-body -->
                    </div>
                    <!-- Task Item End -->
						<% } %>
                    <% } %>
	             <% } %>
                  
                </div> <!-- end company-list-2-->
            </div>


            <div class="tasks">
                <h5 class="mt-0 task-header text-uppercase">진행중</h5>
                <div id="task-list-three" class="task-list-items">
					<% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	                	
	               		<% } else { %> 	
	                	<% for(Kanban k : list) { %>
	                	<% if (k.getStatus()== 3) { %>
                    <!-- Task Item -->
                    <div class="card mb-0">
                        <div class="card-body p-3">
                            <small class="float-end text-muted"><%= k.getEndDate() %></small>

                            <h5 class="mt-2 mb-2">
                                <a href="#" data-bs-toggle="modal" data-bs-target="#<%= k.getProNm() %>" class="text-body"><%= k.getProNm() %></a>
                            </h5>
 
                            <p class="mb-0">
                                <span class="align-middle"><%= k.getEmpNo() %></span>
                            </p><br>
		                    <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#updateStatus"onclick="statusChange2(this.id)">상태수정</a>
		                    <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#update"onclick="statusChange(this.id)">내용수정</a>
                        </div> <!-- end card-body -->
                    </div>
                    <!-- Task Item End -->
						<% } %>
                    <% } %>
	             <% } %>
                    
                </div> <!-- end company-list-3-->
            </div>

            <div class="tasks">
                <h5 class="mt-0 task-header text-uppercase">완료</h5>
                <div id="task-list-four" class="task-list-items">
					<% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
	                	
	               	<% } else { %> 	
	                	<% for(Kanban k : list) { %>
	                	<% if (k.getStatus()== 4) { %>
                    <!-- Task Item -->
                    <div class="card mb-0">
                        <div class="card-body p-3">
                            <small class="float-end text-muted"><%= k.getEndDate() %></small>
                            <h5 class="mt-2 mb-2">
                                <a href="#" data-bs-toggle="modal" data-bs-target="#<%= k.getProNm() %>" class="text-body"><%= k.getProNm() %></a>
                            </h5>
  
                                    <!-- item-->
                            <p class="mb-0">
                                <span class="align-middle"><%= k.getEmpNo() %></span>
                                
                            </p><br>
		                    <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#updateStatus"onclick="statusChange2(this.id)">상태수정</a>
		                    <a href="#" data-bs-toggle="modal" id="<%= k.getProNo() %>" class="badge bg-secondary text-light" data-bs-target="#update"onclick="statusChange(this.id)">내용수정</a>
                        </div> <!-- end card-body -->
                    </div>
                    <!-- Task Item End -->
                    <% } %>
                    <% } %>
	             <% } %>
                </div> <!-- end company-list-4-->
            </div>

        </div> <!-- end .board-->
    </div> <!-- end col -->
</div>
</div>
<!-- end row-->

 </section>
<!--  Add new task modal -->
                	
<div class="modal fade task-modal-content" id="add-new-task-modal" tabindex="-1" role="dialog" aria-labelledby="NewTaskModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
          <div class="modal-header">
              <h4 class="modal-title" id="NewTaskModalLabel">프로젝트 추가</h4>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form class="p-2" action="<%= contextPath %>/InsertKanban.ka">
                  <div class="row">
                  <input type="hidden" name="empNo" value="<%= loginEmployee.getEmpNo() %>">
                      <div class="col-md-8">
                          <div class="mb-3">
                              <label for="task-title" class="form-label">제목</label>
                              <input type="text" name="proNm" class="form-control form-control-light" id="task-title" placeholder="Enter title">
                              
                          </div>
                      </div>
                  </div>

                  <div class="mb-3">
                      <label for="task-description" class="form-label">내용</label>
                      <textarea  name="proContent" class="form-control form-control-light" id="task-description" rows="3"></textarea>
                  </div>

                  <div class="row">

                      <div class="col-md-6">
                          <div class="mb-3">
                              <label for="task-priority" class="form-label">종료 날짜</label>
                              <input type="text" name="endDate" class="form-control form-control-light" id="birthdatepicker" data-toggle="date-picker" data-single-date-picker="true">
                          </div>
                      </div>
                  </div>
                  
                  <div class="text-end">
                      <button type="submit" class="btn btn-primary">추가</button>
                  </div>
              </form>
          </div>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade task-modal-content" id="update" tabindex="-1" role="dialog" aria-labelledby="NewTaskModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
          <div class="modal-header">
              <h4 class="modal-title" id="NewTaskModalLabel">프로젝트 수정</h4>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form class="p-2" action="<%= contextPath %>/updatekanban.ka">
                  <div class="row">
                      <div class="col-md-8">
                          <div class="mb-3">
                              <label for="task-title" class="form-label">제목</label>
                              <input type="text" name="updateProNm" class="form-control form-control-light" id="task-title" placeholder="Enter title">
                          </div>
                      </div>
                  </div>
                  <div class="mb-3">
                      <label for="task-description" class="form-label">내용</label>
                      <textarea  name="updateProContent" class="form-control form-control-light" id="task-description" rows="3"></textarea>
                  </div>
                  <div class="row">
                      <div class="col-md-6">
                          <div class="mb-3">
                              <label for="task-priority" class="form-label">종료 날짜</label>
                              <input type="text" name="updateEndDate" class="form-control form-control-light" id="birthdatepicker" >
                          </div>
                      </div>
                  </div>     
                  
                  <input type="hidden" id="empNo" name="proNo">
                      <button type="submit" class="btn add btn-primary">수정</button>
              </form>
              <form action="<%= contextPath %>/deletekanban.ka">
		          
		          <button type="submit" class="btn delete btn btn-danger">삭제</button>
		          <input type="hidden" name="proNo" id="proNo">
	          </form>
          </div>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<div class="modal fade task-modal-content" id="updateStatus" tabindex="-1" role="dialog" aria-labelledby="NewTaskModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
          <div class="modal-header">
              <h4 class="modal-title" id="NewTaskModalLabel">상태 수정</h4>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
              <form class="p-2" action="<%= contextPath %>/updatekanbanstatus.ka">
                  <div class="row">
                      <div class="col-md-6">
                          <div class="mb-3">
	                          <label for="task-priority" class="form-label">현재 상황</label><br>
		                      <select class="Status" name="updateStatus">
			                      <option value='1'>예정</option>
			                      <option value='2'>시작</option>
			                      <option value='3'>진행중</option>
			                      <option value='4'>완료</option>
			                  </select>
		                  </div>
                      </div>
                  </div>     
                  <div class="text-end">
                  <input type="hidden" id="empNo2" name="proNo">
                      <button type="submit" class="btn btn-primary">수정</button>
                  </div>
              </form>
          </div>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->

<!--  Task details modal -->
<% if(list.isEmpty()) { %> <!-- 리스트가 비어있을 경우 -->
<% } else { %> 	
<% for(Kanban k : list) { %>	
<div class="modal fade task-modal-content" id="<%= k.getProNm() %>" tabindex="-1" role="dialog" aria-labelledby="TaskDetailModalLabel" aria-hidden="true">
  <div class="modal-dialog modal-dialog-centered modal-lg">
      <div class="modal-content">
          <div class="modal-header">
              <h4 class="modal-title" id="TaskDetailModalLabel"><%= k.getProNm() %> </h4>
              <button type="button" class="btn-close" data-bs-dismiss="modal" aria-label="Close"></button>
          </div>
          <div class="modal-body">
          
              <div class="p-2">
                  <h5 class="mt-0">내용 : </h5>

                  <p class="text-muted mb-4">
                      <%= k.getProContent() %>
                  </p>

                  <div class="row">
                      <div class="col-md-4">
                          <div class="mb-4">
                              <h5>시작 날짜</h5>
                              <p><%= k.getStartDate() %></p>
                          </div>
                      </div>
                      <div class="col-md-4">
                          <div class="mb-4">
                              <h5>종료 날짜</h5>
                              <p><%= k.getEndDate() %></p>
                          </div>
                      </div>
                      <div class="col-md-4">
                          <div class="mb-4" id="tooltip-container">
                              <p>담당자 : <%= k.getEmpNo() %></p>
                          </div>
                      </div>
                  </div>
                  <!-- end row-->

              </div> <!-- .p-2 -->
          </div>
      </div><!-- /.modal-content -->
  </div><!-- /.modal-dialog -->
</div><!-- /.modal -->
	<% } %>
<% } %>

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