<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="com.kh.employee.model.vo.Employee" %>
<%
	String contextPath = request.getContextPath();

	Employee loginEmployee = (Employee)session.getAttribute("loginEmployee");
	// 로그인 전 : login.jsp 가 로딩될때 null
	// 로그인 후 : main.jsp 가 로딩될때 로그인한 사원의 정보가 담겨있음
%>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/5.15.2/css/all.min.css"/>
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>login</title>
    <style>
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
        @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
        
        *{
        margin: 0;
        padding: 0;
        box-sizing: border-box;
        font-family:'Poppins',sans-serif;
        }

        html, body{
        display: grid;
        height: 100vh; /*100% view height*/
        width: 100%;
        place-items: center;  /* 위치 정렬 */
        background: #90EDC5;
        /* linear-gradient 투명도*/
        }
        
        .container{
        background: #fff;
        max-width: 500px;
        width: 100%;
        padding: 25px 35px;
        border-radius: 5px;
        box-shadow: 0 10px 10px rgba(0, 0, 0, 0.15);
        }

        .container form .title{
        font-size: 30px;
        font-weight: 600;
        margin: 20px 0 10px 0;
        position: relative;
        }

        .container form .title:before{ /*::befor 구분선 삽입(가상 요소 선택자/ Map 아래 초록색선)*/
        content: ''; /*특정요소/ 내용의 앞과 뒤에 콘텐츠를 추가, 이 콘텐츠는 content ="" 라는 속성으로 정의*/
        position: absolute;
        height: 5px;
        width: 33px;
        left: 0px;
        bottom: 3px;
        border-radius: 5px;
        background: #0edfa0;
        }

        .container form .input-box{
        width: 100%;
        height: 40px;
        margin-top: 70px;
        position: relative;
        }
        
        .container form .input-box input{
        width: 100%;
        height: 100%;
        outline: none;
        font-size: 16px;
        border: none;
        }

        .container form .underline::before{ /*Enter Your Email / Password / ::before로 마우스대기전에는 회색*/
        content: '';
        position: absolute;
        height: 2px;
        width: 100%;
        background: #ccc;
        left: 0;
        bottom: 0;
        }

        .container form .underline::after{ /*::after 후엔 초록색*/
        content: '';
        position: absolute;
        height: 2px;
        width: 100%;
        background: #0edfa0;
        left: 0;
        bottom: 0;
        transform: scaleX(0); /*x축(수평방향)으로 요소의 크기를 조절*/
        transform-origin: left;
        transition: all 0.3s ease; /*속성명 시간 효과*/
        }

        .container form .input-box input:focus ~ .underline::after,
        .container form .input-box input:valid ~ .underline::after{
        transform: scaleX(1); /*x축(수평방향)으로 요소의 크기를 조절*/
        transform-origin: left;
        }

        .container form .button{
        margin: 30px 0 5px 0;
        }

        .container .input-box input[type="submit"]{
        background: #0edfa0;
        font-size: 17px;
        color: white;
        font-weight: bold;
        border-radius: 5px;
        cursor: pointer;
        transition: all 0.3s ease;
        height: 50px;
        }
        .button-01{
            float: left;
            width: 48% !important;
        }
        .button-02{
            float: right;
            width: 48% !important;
        }
    </style>
</head>
<body>
	<div class="container">
		<% if(loginEmployee == null) { %>
		<%@ include file="alertMsg.jsp" %>
	        <!-- 로그인 전에 보여지는 로그인 form-->
	        <form id="login-form" action="/24works/login.em" method="post">
	            <div class="title"> 24Works</div>
	          <div class="input-box">
	            <input type="text" name="empNo" placeholder="사번 입력" required>
	            <div class="underline"></div>
	          </div>
	          <div class="input-box">
	            <input type="password" name="empPwd" placeholder="비밀번호 입력" required>
	            <div class="underline"></div>
	          </div>
	          <br>
	
	          <div class="input-box button">
	              <a href=""><input type="submit" value="로그인"></a>
	            </div>
	          <div class="input-box button">
	              <a href=""><input type="submit" name="" value="회원가입" onclick="enrollPage();"></a>
	            </div>
	          <div class="input-box button button-01">
	              <a href=""><input type="submit" name="" value="사번 찾기" onclick="searchEmpNoPage();" style="font-size: 15px;">
	            </a></div>
	          <div class="input-box button button-02">
	              <a href=""><input type="submit" name="" value="비밀번호 찾기" onclick="searchEmpPwdPage()" style="font-size: 15px;"></a>
	            </div>
	        </form>
	       <% } %>
	       
	       <script>
	       		function enrollPage(){
	       			location.href = "<%= contextPath %>/enrollForm.em"
	       		}
	       		function searchEmpNoPage(){
	       			location.href= "<%= contextPath %>/searchEmpNo.em"
	       		}
	       		function searchEmpPwdPage(){
	       			location.href = "<%= contextPath %>/serchEmpPwd.em"
	       		}
	       </script>
	       

      </div>
      
</body>
</html>