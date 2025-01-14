<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Insert title here</title>
</head>
<body>
		<div class="container">
		<h3>이미지 업로드 폼</h3>
		<form action="${pageContext.request.contextPath}/file/upload3" method="post" enctype="multipart/form-data" id="myForm">
			<input type="text" name="title" placeholder="설명 입력"/><br />
			이미지 <input type="file" name="myImg" accept="image/*"/><br />
			<button type="submit">업로드</button>
		</form>
		<img id="image" width="300"/>
	</div>
	<script>
		document.querySelector("#myForm").addEventListener("submit", (event)=>{
			//기본동작(폼제출)을 막기
			event.preventDefault();
			//event.target=>해당 이벤트가 발생한 바로 그 요소의 참조값(즉 여기서는 form의 참조값이 된다)
			//document.querySelector("#myForm")==event.target 동일 값
			const data=new FormData(event.target);
			//fetch 함수를 이용해서 FormData 전송하기
			fetch("${pageContext.request.contextPath}/file/upload3", {
				method:"post",
				body:data
			})//옵션으로 전달하는 키값은 보통 정해져 있다.
			.then(res=>res.json())
			.then(data=>{
				console.log(data);
				//data.saveFileName은 upload 폴더에 저장된 파일명이다.
				const requestPath="${pageContext.request.contextPath}/upload/"+data.saveFileName;
				document.querySelector("#image").setAttribute("src", requestPath);		
			});
		}); 
	</script>
</body>
</html>