<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h1>폼에 입력한 내용을 페이지 전환 없이 전송하기</h1>
	<form action="${pageContext.request.contextPath}/user/login.jsp" method="post" id="myForm">
		<input type="text" name="id" placeholder="아이디 입력..." />
		<input type="password" name="pwd" placeholder="비밀번호 입력..." />
		<button type="submit">로그인</button>
	</form>
	<script>
		//폼에 submit 이벤트가 일어날 때 실행할 함수 등록
		document.querySelector("#myForm").addEventListener("submit", (e)=>{
			//event 객체의 .preventDefault()함수를 호출하면 기본 동작을 막는다(폼 제출이 막아진다)
			e.preventDefault();
			console.log("submit!");
			//폼에 입력한 내용을 FormData 타입으로 얻어내기
			const data= new FormData(e.target);//e.target은 form 의 참조값이다
			//FormData를 이용해서 query string 얻어내기
			const queryString =new URLSearchParams(data).toString();
			//query string을 콘솔에 출력해보기
			console.log(queryString);
			//action 속성의 value 읽어오기
			const url=e.target.action;
			//fetch()함수를 이용해서 post 방식 요청하면서 queryString 을 요청의 body에 전달하기
			fetch(url,{
				method:"POST",
				headers:{"Content-Type:"application/x-www-form-urlencoded; charset=utf-8"},
				body:queryString //queryString에는 id=admin&pwd=1234 같은 형식으로 id와 pwd가 전달됨.
			})
			.then(res=>res.json())
			.then(data=>{
				//data는 object 이고 id와 pwd의 유효성 여부가 담겨 있음
				console.log(data);
				
			})
			.catch(error=>{
				console.log("에러정보:" + error);
			});
			
		});
	</script>
</body>
</html>