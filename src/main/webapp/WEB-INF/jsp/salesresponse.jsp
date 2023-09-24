<%@page import="java.util.List"%>
<%@page import="repository.model.Sales"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	String action = request.getAttribute("action") + "";
	int rowcount = Integer.parseInt(request.getAttribute("rowcount") + "");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales Response</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<form class="pure-form" method="post" action="${pageContext.request.contextPath}/index.jsp">
			<fieldset>
				<legend>Sales Response</legend>
				狀態說明: <%=action %> <%=rowcount==0?"失敗":"成功" %> <p />
				<button type="submit" class="pure-button pure-button-primary">回首頁</button>
			</fieldset>
		</form>
		
	</body>
</html>