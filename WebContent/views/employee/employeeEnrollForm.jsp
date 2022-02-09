<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%
	String contextPath = request.getContextPath();
%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>

<style>
    @import url('https://fonts.googleapis.com/css2?family=Poppins:wght@200;300;400;500;600;700&display=swap');
    
    *{
    margin: 0;
    padding: 0;
    box-sizing: border-box;
    font-family: 'Poppins',sans-serif;
    }

    body{
    height: 100vh; /*vh = viewport Height */
    display: flex;
    justify-content: center; /* flex 좌우정렬*/
    align-items: center; /* flex 좌우정렬*/
    padding: 10px;
    background: #90EDC5;
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

    form .input-box span.details{
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

    .user-details .input-box input:focus,
    .user-details .input-box input:valid{
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
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="//t1.daumcdn.net/mapjsapi/bundle/postcode/prod/postcode.v2.js"></script>

    <div class="container">
        <div class="title">회원가입</div>
        <div class="content">
          <form id="enroll-form" action="<%= contextPath %>/insert.em" method="post">
            <div class="user-details">
              <div class="input-box">
                <span class="details">사번 * </span>
                <input type="text" name="empNo" id="empNo" placeholder="사번 입력" required>
              </div>
              <div class="input-box">
                <span class="details">이름 *</span>
                <input type="text" name="empName" id="empName" placeholder="이름 입력" required>
              </div>
              <div class="input-box">
                <span class="details">비밀번호 *</span>
                <input type="password" name="empPwd" id="empPwd1" placeholder="비밀번호 입력" required>
              </div>
              <div class="input-box">
                <span class="details">비밀번호 확인 *</span>
                <input type="password" id="empPwd2" placeholder="비밀번호 확인" required>
              </div>
              <div class="input-box">
                <span class="details">이메일</span>
                <input type="email" name="email" placeholder="이메일 입력" required>
              </div>
              <div class="input-box">
                <span class="details">전화번호</span>
                <input type="text" name="phone" placeholder="전화번호 입력 - 포함" required>
              </div>
              <div class="input-box">
                <span class="details">주민번호</span>
                <input type="text" name="empidNo" placeholder="주민번호 입력  - 포함" required>
              </div>
              <div class="input-box">
                <span class="details">주소</span>
                <input type="text" id="address" name="address" placeholder="주소 입력" required>
              </div>
            </div>
            
              <div class="button">
                <input type="button" onclick="empNoCheck();" value="사번확인">
              </div>
              <div class="button">
                <input type="button" onclick="serchPostClicked();" value="우편번호">
              </div>
              <div class="button">
                <input type="submit" value="회원가입" onclick="return validate();">
              </div>
              <div class="button">
                <input type="reset" value="초기화">
              </div>
          </form>
        </div>
      </div>
      
      <script>
      	function validate(){
      		var empNo = document.getElementById('empNo').value;
      		var empName = document.getElementById('empName').value;
      		var empPwd1 = document.getElementById('empPwd1').value;
      		var empPwd2 = document.getElementById('empPwd2').value;
      		

            regExp = /^[가-힣]{2,}$/;
            if(!regExp.test(empName)){

                alert('유효한 이름을 입력해 주세요');

                document.getElementById('empName').select();
                return false;
            }
            
            regExp = /^[a-z\d!@#%^*]{8,15}$/i;
            if(!regExp.test(empPwd1)){

                alert('유효한 비밀번호를 입력해주세요');

                // 재입력유도
                document.getElementById('empPwd1').value = '';
                document.getElementById('empPwd1').focus(); // 깜빡깜빡
                return false;
            }

            // 3) 비밀번호 일치
            if(empPwd1 != empPwd2){

                alert('동일한 비밀번호를 입력하세요')

                // 재입력유도
                document.getElementById('empPwd2').value = '';
                document.getElementById('empPwd2').focus();
                return false;
            }
            return true;
      		
      	}
    	function empNoCheck(){
         
   	
          	
      		var $empNo = $("#enroll-form input[name=empNo]");
      			
      		$.ajax({
      			url : "empNoCheck.em",
      			data : {checkEmpNo : $empNo.val() },
      			success : function(result) {
      				
      				// result 경우의 수 : "NNNNN", "NNNNY"
      				if(result == "NNNNN"){
      					alert("이미존재하거나 퇴사한 사원의 사번입니다");
      					
      					// 다시 입력 유도
      					 $empNo.focus();
      				}
      				else{
      					var regExp = /^[2][4][0-9]{4,11}$/;
      					var a=$empNo.val()
			          	 if(!regExp.test(a)){
     			              alert('24works에서 부여받은 사번을 입력하세요');
     			               return 0;
     			           }

      					if(confirm("24works에서 부여한 사원입니까?")){

     						$empNo.attr("readonly", true);
      					}
      					else{
      						$empNo.focus();
      					}      						
      				}
      			},
      			error : function(){
      				console.log("아이디 중복체크용 ajax 실패");
      			}
      			
      		});
      	}
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