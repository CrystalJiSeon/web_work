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
		<h1>업로드 결과 페이지</h1>
		<p>파일을 업로드했습니다.</p>
		<p>title : <strong> ${requestScope.title }</strong></p>
		<p>origFileName: <strong>${origFileName}</strong></p>
		<p>saveFileName: <strong>${saveFileName}</strong></p>
		<p>fileSize : <strong>${fileSize}</strong></p>
		<p>uploadPath: <strong>${uploadPath}</strong></p>
		<p><a href="${pageContext.request.contextPath}/file/download?origFileName=${origFileName}&saveFileName=${saveFileName}&fileSize=${fileSize}">다운로드</a></p>
		<img src="${pageContext.request.contextPath}/upload/${saveFileName}" alt="업로드된 이미지" />
	</div>
</body>
</html>