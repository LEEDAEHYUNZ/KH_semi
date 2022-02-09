<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.employee.model.vo.Employee" %>

<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>My Page</title>

<style>
 	@import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
 
   *{
    margin: 0;
    padding: 20;
    box-sizing: border-box;
    font-family: 'Poppins',sans-serif;
    }

    .container{
    max-width: 700px;
    width: 100%;
    background-color: #fff;
    padding: 25px 30px;
    border-radius: 5px;
    box-shadow: 0 5px 10px rgba(0,0,0,0.15);
    }

    .container .title{
    font-size: 25px;
    font-weight: 500;
    position: relative;
    }

    .container .title::before{ /* 로그인 창에서 설명함 회원가입아래 바*/
    content: "";
    position: absolute;
    left: 0;
    bottom: 0;
    height: 3px;
    width: 30px;
    border-radius: 5px;
    background:#90EDC5;
    }
    
    .content form .user-details{
    display: flex;  
    flex-wrap: wrap; /* 요소들의 총 넓이가 부모 넓이보다 클 때 다음줄에 이어서 정렬 display:flex와 짝꿍*/
    justify-content: space-between; /* 균일한 간격을 만듬 */
    margin: 20px 0 12px 0;
    }

    form .user-details .input-box{
    margin-bottom: 15px;
    width: calc(100% / 2 - 20px); /*가로값을 100%에서 / 2(객체의 갯수) -20px를 나눈값으로 지정*/
    
                                  /* 100%으로 2로 나눈후 20px를 뺸 나머지를 가로값으로 지정*/
    }
    
    form .user-details .input-box2{
    margin-bottom: 10px;
    width: calc(100% / 2 - 20px); /*가로값을 100%에서 / 2(객체의 갯수) -20px를 나눈값으로 지정*/
                                      /* 100%으로 2로 나눈후 20px를 뺸 나머지를 가로값으로 지정*/
    }

    form .input-box span.details{
    display: block;
    font-weight: 500;
    margin-bottom: 5px;
    }
    
    form .input-box2 span.details{
    display: block;
    font-weight: 500;
    margin-bottom: 5px;
    }
         
    
    .user-details .input-box input{
    height: 45px;
    width: 100%;
    outline: none;
    font-size: 16px;
    border-radius: 5px;
    padding-left: 15px;
    border: 1px solid #ccc;
    border-bottom-width: 2px;
    transition: all 0.3s ease;
    }
    
   .user-details .input-box2 input{
    height: 45px;
    width: 213%;
    outline: none;
    font-size: 16px;
    border-radius: 5px;
    padding-left: 15px;
    border: 1px solid #ccc;
    border-bottom-width: 2px;
    transition: all 0.3s ease;
    }
    
     .user-details .input-box input:focus,
     .user-details .input-box input:valid{
     border-color: red;
     }

     .user-details .input-box2 input:focus,
     .user-details .input-box2 input:valid{
      border-color: red;
      }
    

    form .gender-details .gender-title{
    font-size: 20px;
    font-weight: 500;
    }

    form .category{
    display: flex;
    width: 80%;
    margin: 14px 0 ;
    justify-content: space-between;
    }

    form .category label{
    display: flex;
    align-items: center;
    cursor: pointer;
    }

    form .category label .dot{
    height: 18px;
    width: 18px;
    border-radius: 50%;
    margin-right: 10px;
    background: #d9d9d9;
    border: 5px solid lightgray;
    transition: all 0.3s ease; /* 빼도 상관X */
    }
    
    #dot-1:checked ~ .category label .one,
    #dot-2:checked ~ .category label .two,
    #dot-3:checked ~ .category label .three{
    background: black;
    border-color: #d9d9d9;
    }
    
    form input[type="radio"]{
    display: none;
    }
    
    form .button{
    height: 45px;
    margin: 35px 0
    }

    form .button input{
    height: 100%;
    width: 100%;
    border-radius: 5px;
    border: none;
    color: #fff;
    font-size: 18px;
    font-weight: 500;
    letter-spacing: 10px; /* 글자사이 간격임 loginlogin주석잘못함*/
    cursor: pointer;
    transition: all 0.3s ease;
    background: #0edfa0;
    }


    form .button input:hover{
    background: #0edfa0;
    }
    @media(max-width: 585px){/*미디어쿼리(수정용이)*/
    .container{
    max-width: 100%;
    }
    form .user-details .input-box{
        margin-bottom: 15px;
        width: 100%;
    }
    
    form .user-details .input-box2{
         margin-bottom: 16px;
         width: 100%;
    }

    form .category{
        width: 100%;
    }

    .content form .user-details{
        max-height: 300px;
        overflow-y: scroll;
        /*scroll : 부모요소의 범위를 넘어가는 자식요소의 
        부분은 보이지 않지만, 사용자가 확인 할 수 있도록 스크롤바를 표시한다.*/
    }

    .user-details::-webkit-scrollbar{/*스크롤바 너비변경 참고 google*/
        width: 5px;
    }

    }
    @media(max-width: 460px){ /*미디어쿼리(수정용이)*/
    .container .content .category{
        flex-direction: column;
    }
    }
</style>
</head>
<body>	
	<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>
	<%@ include file="../common/menubar.jsp" %>
	<%
		int empNo = loginEmployee.getEmpNo();
		String empName = loginEmployee.getEmpName();
		
		// 필수 X => "XXX" / "", 배열의 경우에는 선택 안할경우 null
		String phone = (loginEmployee.getPhone() == null) ? "" : loginEmployee.getPhone(); // 삼항연산자
		String email = (loginEmployee.getEmail() == null) ? "" : loginEmployee.getEmail();
		String address = (loginEmployee.getAddress() == null) ? "" : loginEmployee.getAddress();
	%>
	
<section class="home-section">
  <!-- 상단 -->
  <div class="home-content">
    <i class='bx bx-menu' ></i>
    <div class="head" ><%= loginEmployee.getEmpName() %> <%= loginEmployee.getJobCode() %>님</div>
  </div>
  
  <div style ="margin-top:90px;">
<div class="container" style="width:700px; height:650px;">
<div class="title"> MY PAGE </div>
<div class="content">
    <form id="mypage-form" action ="<%= contextPath %>/update.me" method="post">
    <div class="user-details">
      <div class="input-box">
        <span class="details">사번</span>
        <input type="text" name="empNo"  value="<%= empNo %>" readonly> <!-- 수정 필요 : value X -->
      </div>
      <div class="input-box">
        <span class="details">이름</span>
        <input type="text"  name="empName"  value="<%= empName %>" readonly>
      </div>
      <div class="input-box">
        <span class="details">전화번호</span>
        <input type="text" name="phone"  value="<%= phone %>" required>
      </div>
      <div class="input-box">
        <span class="details">이메일</span>
        <input type="text" name="email"  value="<%= email %>" required>
      </div>
      <div class="input-box2">
        <span class="details">주소</span>
        <input type="text" id="address" name="address" value="<%= address %>" required>
      </div>
    </div>

	<div class ="button">
        <input type="button" value="우편번호" onclick="serchPostClicked();">
    </div>
    <div class ="button">
        <input type="button"  data-toggle="modal" data-target="#updatePwdForm" value="비밀번호 변경">
   </div>
    <div class ="button">
        <input type="submit"  value="정보변경">    
    </div>

  </form>
</div>
</div>
</div>

      <!-- The Modal : 비밀번호 변경 -->
      <div class="modal" id="updatePwdForm">
        <div class="modal-dialog">
          <div class="modal-content">
      
            <!-- Modal Header -->
            <div class="modal-header">
              <h4 class="modal-title">비밀번호 변경</h4>
              <button type="button" class="close" data-dismiss="modal">&times;</button>
            </div>
      
            <!-- Modal body -->
            <div class="modal-body" align = "center">
      
              <form action="<%= contextPath %>/updatePwd.me" method="post">
              
              <!-- 현재 비밀번호, 변경할 비밀번호, 변경할 비밀번호 확인 -->
              
              <!-- 확실하게 주인을 판별할 수 있는 id 도 같이 넘겨야 한다. -->
              <input type="hidden" name="empNo" value="<%= empNo %>">
      
              <table>
                <tr>
                  <td>현재 비밀번호</td>
                  <td><input type="password" name="empPwd" required></td>
                </tr>
                <tr>
                  <td>변경할 비밀번호</td>
                  <td><input type="password" name="updatePwd" required></td>
                </tr>
                <tr>
                  <td>변경할 비밀번호 재입력</td>
                  <td><input type="password" name="checkPwd" required></td>
                </tr>
              </table>
      
              <br>
      
              <button type="submit" class="btn btn btn-success btn-sm" onclick="return validatePwd();">비밀번호 변경</button>
              </form>
              
              <script>
                  function validatePwd() {

                     if($("input[name=updatePwd]").val() != $("input[name=checkPwd]").val()) {
                        alert("비밀번호가 일치하지 않습니다.");

                           return false;
                        }
                        else {
                           return true;
                        }

                        }
                 </script>  
                             
              
            </div>  
      
          </div>
        </div>
      </div>
</section>
     <script>
   //우편번호 찾기 눌렀을때 실행
      function serchPostClicked(){
          new daum.Postcode({
              oncomplete: function(data) {
                 
                   var addr='';
                   
                   if(data.userSelectedType ==='R'){
                      addr=data.roadAddress;
                   }
                   else{
                      addr=data.jibunAddress;
                   }
                   document.getElementById('address').value=addr;
                   document.getElementById('address').focus();
              }
           }).open();
          $("#enroll-form button[type=submit]").removeAttr("disabled");
          }
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

<script>
   //우편번호 찾기 눌렀을때 실행
      function serchPostClicked(){
          new daum.Postcode({
              oncomplete: function(data) {
                 
                   var addr='';
                   
                   if(data.userSelectedType ==='R'){
                      addr=data.roadAddress;
                   }
                   else{
                      addr=data.jibunAddress;
                   }
                   document.getElementById('address').value=addr;
                   document.getElementById('address').focus();
              }
           }).open();
          $("#enroll-form button[type=submit]").removeAttr("disabled");
          }
   </script>

</body>
</html>

