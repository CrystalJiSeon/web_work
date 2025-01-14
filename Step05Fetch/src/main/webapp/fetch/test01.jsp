<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
	<h3>fetch 함수 테스트</h3>
	<button id="myBtn">click</button>
	<script>
		document.querySelector("#myBtn").addEventListener("click", ()=>{
			//javascript로 서버에 요청하기
			
			
			/*
			서버 (jsp or servlet) 에서 응답한 문자열이 json 형식이면 
			return res.json();
			그 외 형식이면 html, xml, plain txt 등등
			return res.text();
			*/
			fetch("${pageContext.request.contextPath}/index.jsp") //페이지 전환 없이 javascript 로 요청을 하면 응답된 문자열이
			.then(res=>res.text())
			.then(data=>{										//두번째 then()애 전달한 함수의 매개 변수에 전달된다.
				console.log(data);
				
				//위의 then() 함수에서 res.json()을 리턴하면 data는 응답된 json 문자열을 JSON.parse()과정을 이미 거친 object나 array이다.
				//위의 then() 함수에서 res.text()를 리턴하면 data는 서버가 응답한 문자열(String)이다
			});
		});
	</script>
</body>
</html>