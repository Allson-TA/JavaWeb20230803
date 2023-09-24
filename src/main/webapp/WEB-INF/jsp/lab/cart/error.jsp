<%@page import="lab.cart.repository.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	String username = session.getAttribute("username") + "";
	String errorMsg = request.getAttribute("errorMsg") + "";
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>錯誤訊息</title>
	</head>
	<body>
		<h1>錯誤訊息(登入者:<%=username %>)</h1>
		<ul>
			<%=errorMsg %>
		</ul>
		<a href="<%=request.getContextPath() %>/lab/product">商品列表</a>
		|
		<a href="<%=request.getContextPath() %>/lab/cart?action=view">查看購物車</a>
	</body>
</html>