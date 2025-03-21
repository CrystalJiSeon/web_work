<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>

<body>
	<div class="container">
		<h1>회원가입 폼</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				<input class="form-control" type="text" name="id" id="id" />
				<small class="form-text">영문자 소문자로 시작하고 5~10글자 이내로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디입니다.</div>
				<div class="invalid-feedback">사용할 수 없는 아이디입니다.</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd2">비밀번호 확인</label>
				<input class="form-control" type="password"  id="pwd2"/>
			</div>
			<div class="mb-2">
				<label class="form-label" for="email">이메일 주소</label>
				<input class="form-control" type="email" name="email" id="email" />
				<div class="invalid-feedback">이메일 주소의 형식을 갖추세요</div>
			</div>
			<button class="btn btn-success" type="submit" disabled="disabled">가입</button>
		</form>
	</div>
	<script>
		//아이디 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isIdValid=false;
		//비밀번호 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isPwdValid=false;
		//이메일 유효성 여부를 관리할 변수를 만들고 초기값 부여
		let isEmailValid=false;
	
		const checkForm=()=>{
			//폼 전체의 유효성 여부에 따라 분기(이 예제에서는 id 유효성 여부만, 다음 예제에서는 isIdValid&&isPwdValid&&isEmailValid 이런식으로 들어갈 거임. 
			if(isIdValid && isPwdValid && isEmailValid){
				//type 속성이 submit 인 요소를 찾아서 disabled 속성을 제거
				document.querySelector("[type=submit]").removeAttribute("disabled");
			}else{
				//type 속성이 submit 인 요소를 찾아서 disabled="disabled" 속성을 추가
				document.querySelector("[type=submit]").setAttribute("disabled", "disabled");
			}
		}
		
		/*
			폼에 submit 이벤트가 발생하면 입력한 내용을 검증해서 조건을 만족하지 못하면 폼 제출을 막기
		*/
		const reg_id = /^[a-z].{4,9}$/;
	
		//id를 입력할 때마다 실행할 함수 등록하기
		document.querySelector("#id").addEventListener("input", (event)=>{
			//일단 is-valid, is-invalid 클래스를 모두 지우고(id가 입력될 때마다 계속 실행됨)
			event.target.classList.remove("is-valid", "is-invalid");
			//현재까지 입력한 아이디를 읽어오기
			let inputId=event.target.value;//event.target=document.querySelector("#id")
			//만일 정규표현식을 통과하지 못했다면
			if(!reg_id.test(inputId)){
				//어떤 요소에 클래스를 추가하는 방법 : .classList.add("클래스명");
				event.target.classList.add("is-invalid");
				//아이디의 상태값을 여기서 변경
				isIdValid=false;
			}else{
				event.target.classList.add("is-valid");
				//아이디의 상태값을 여기서 변경
				isIdValid=true;
			}
			//상태값을 이용해서 UI를 변경하는 함수 호출
			checkForm();
		});
		
		const reg_pwd=/[\W]/;
		//함수를 미리 만들어서
		const checkPwd = ()=>{
			//양쪽에 입력한 비밀번호를 읽어와서 (2줄 코딩)
			let pwd=document.querySelector("#pwd").value;
			let pwd2=document.querySelector("#pwd2").value;		
			//일단 is-valid 와 is-invalid 클래스를 제거를 먼저하고 (1줄 코딩)
			document.querySelector("#pwd").classList.remove("is-valid", "is-invalid");		
			//일단 정규표현식을 만족하는지 확인해서 만족하지 않으면 함수를 여기서 종료(return) 해야 한다.
			//만일 첫번째 비밀번호가 정규표현식을 통과하지 못하거나 또는 
			//두번째 비밀번호가 정규표현식을 통과하지 못한다면 isPwdValid 를 false 로 변경하고 checkForm() 호출
			if(!reg_pwd.test(pwd) || !reg_pwd.test(pwd2)){
				document.querySelector("#pwd").classList.add("is-invalid");
				isPwdValid=false;
				checkForm();
				return;
			}
			//양쪽에 입력한 비밀번호가 같은지 확인해서 같으면 isPwdValid 를 true 
			// 다르면 isPwdValid 를 false 로 변경하고 checkForm() 호출 
			if(pwd == pwd2){
				document.querySelector("#pwd").classList.add("is-valid");
				//비밀번호가 유효 하다는 의미에서 true 를 넣어준다.
				isPwdValid=true;
			}else{
				document.querySelector("#pwd").classList.add("is-invalid");
				//비밀번호가 유효 하지 않다는 의미에서 false 를 넣어준다.
				isPwdValid=false;
			}
			checkForm();
		};
		
		document.querySelector("#pwd").addEventListener("input", checkPwd);
		document.querySelector("#pwd2").addEventListener("input",checkPwd);
		
		document.querySelector("#email").addEventListener("input", ()=>{
			document.querySelector("#email").classList.remove("is-valid", "is-invalid");
			let inputEmail= document.querySelector("#email").value;
			const reg_email=/[\w]+@[.\w]+/;
			if(!reg_email.test(inputEmail)){
				document.querySelector("#email").classList.add("is-invalid");
				isEmailValid=false;
			}else{
				document.querySelector("#email").classList.add("is-valid");
				isEmailValid=true;
			}
			checkForm();
		});
		
	</script>
</body>
</html>