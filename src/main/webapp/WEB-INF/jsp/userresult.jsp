<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<meta charset="UTF-8">
		<title>User Result</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<form class="pure-form">
			<fieldset>
				<legend>User Result</legend>
				新增結果: <%=request.getAttribute("result") %><p />
				<button type="button" 
						onclick="history.back();"
						class="pure-button pure-button-primary">回上一頁</button>	 
			</fieldset>
		</form>
		
	</body>
</html>