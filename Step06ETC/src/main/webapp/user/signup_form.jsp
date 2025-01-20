<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<style>
	.invalid-feedback{
		display:none;
		color: red;
	}
</style>
</head>

<body>
	<div class="container">
		<h1>회원가입 폼</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div>
				<label for="id">아이디</label>
				<input type="text" name="id" id="id" />
				<small>영문자 소문자로 시작하고 5~10글자 이내로 입력하세요</small>
				<div class="invalid-feedback">사용할 수 없는 아이디입니다.</div>
			</div>
			<button type="submit">가입</button>
		</form>
	</div>
	<script>
		/*
			폼에 submit 이벤트가 발생하면 입력한 내용을 검증해서 조건을 만족하지 못하면 폼 제출을 막기
		*/
		const reg_id = /^[a-z].{4,9}$/;
		
		//폼 안의 서브밋 버튼을 누르면 form이 제출되는 기본 동작을 한다.
		document.querySelector("#signupForm").addEventListener("submit", (event)=>{
			//서브밋 버튼의 기본 동작을 막기(폼 제출을 막기)
			
			const inputId = document.querySelector("#id").value;
			
			if(!reg_id.test(inputId)){
				event.preventDefault();
				document.querySelector(".invalid-feedback").style.display="block";
			}
		})
	</script>
</body>
</html>