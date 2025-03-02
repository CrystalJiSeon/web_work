<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>/user/signup_form5.jsp</title>
<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/bootstrap@5.3.3/dist/css/bootstrap.min.css" />
</head>
<body>
	<div class="container" id="app">
		<h1>회원가입 폼 입니다 (vuejs 이용)</h1>
		<form action="signup.jsp" method="post" id="signupForm" novalidate>
			<div class="mb-2">
				<label class="form-label" for="id">아이디</label>
				
				<input :class="{'is-valid':isIdValid ,'is-invalid':!isIdValid && isIdTyped}"
					@input="onIdInput" class="form-control" type="text" name="id" id="id"/>
				<small class="form-text">영문자 소문자로 시작하고 5~10 글자 이네로 입력하세요</small>
				<div class="valid-feedback">사용 가능한 아이디 입니다</div>
				<div class="invalid-feedback">사용할수 없는 아이디 입니다</div>
			</div>
			<div class="mb-2">
				<label class="form-label" for="pwd">비밀번호</label>
				<input @input="onPwdInput" 
						:class="{'is-valid':isPwdValid, 'is-invalid':!isPwdValid&&isPwdTyped}" 
						v-model="pwd" 
						class="form-control" type="password" name="pwd" id="pwd"/>
				<small class="form-text">특수 문자를 하나 이상 조합하세요.</small>
				<div class="invalid-feedback">비밀 번호를 확인 하세요</div>
			</div>
			<div class="mb-2">
				<label  class="form-label" for="pwd2">비밀번호 확인</label>
				<input @input="onPwdMatch" 
						:class="{'is-valid':isPwdValid,'is-invalid':!isPwdValid&&isPwdTyped}"
						v-model="pwd2" class="form-control" type="password"  id="pwd2"/>
			</div>		
			<div class="mb-2">
				<label class="form-label" for="email">이메일</label>
				<input @input="onEmailInput" :class="{'is-valid':isEmailValid ,'is-invalid':!isEmailValid && isEmailTyped}"
					class="form-control" type="email" name="email" id="email"/>
				<div class="invalid-feedback">이메일 형식에 맞게 입력하세요.</div>
			</div>				
			<button class="btn btn-success" type="submit" v-bind:disabled="!isIdValid || !isPwdValid || !isEmailValid">가입</button>
		</form>
	</div>
	<script src="https://cdn.jsdelivr.net/npm/vue/dist/vue.js"></script>
	<script>
		new Vue({
			el:"#app",
			data:{
				isIdValid:false,
				isIdTyped:false,
				isPwdValid:false,
				isPwdTyped:false,
				isEmailValid:false,
				isEmailTyped:false,
				pwd:"",
				pwd2:""
			},
			methods:{
				onIdInput(e){
						this.isIdTyped=true;
						const reg_id=/^[a-z].{4,9}$/;
						let inputId=e.target.value;
						if(!reg_id.test(inputId)){
							this.isIdValid=false;
							return;
						}
						fetch("${pageContext.request.contextPath }/user/checkid.jsp?id="+inputId)
						.then(res=>res.json())
						.then(data=>{		
							if(data.canUse){
								this.isIdValid=true;
							}else{
								this.isIdValid=false;
							}
						});
				},
				onEmailInput(e){
						this.isEmailTyped=true;
						const reg_email=/@/;
						const email=e.target.value;
						if(reg_email.test(email)){
							this.isEmailValid=true;
							return;
						}else{//제대로 입력하지 않았다면
							this.isEmailValid=false;
						}
				},
				onPwdInput(e){
						
						this.isPwdTyped=true;
						const reg_pwd=/[\W]/;	
						//정규표현식을 만족하는지 확인해서 만족하지 않으면 여기서 함수를 종료.
						//만일 첫번째 비밀번호가 정규표현식을 통과하지 못하거나 두번째 비밀번호가 정규표현식을 통과하지 못한다면~
						if(!reg_pwd.test(this.pwd)||!reg_pwd.test(this.pwd2)){
								this.isPwdValid=false;
								return;
						}
						//위를 통과했다면 여기서는 비밀번호가 같은지 여부를 알아내서 유효성 여부에 반영
						if(this.pwd == this.pwd2){
							//비밀번호가 유효하다
							this.isPwdValid=true;
						}else{
							//비밀번호가 유효하지 않다
							this.isPwdValid=false;
						}
				}	
			}
		});
		
	</script>
</body>
</html>

