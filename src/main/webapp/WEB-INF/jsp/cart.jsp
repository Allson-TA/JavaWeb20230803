<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	Map<String, String> productNames = (Map<String, String>)application.getAttribute("productNames");
	Map<String, Integer> cart = (Map<String, Integer>)session.getAttribute("cart");
	String username = session.getAttribute("username") + "";
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>百元熱炒店-購物車</title>
	</head>
	<body>
		<h1>購物車列表(登入者:<%=username %>)</h1>
		<ul>
			<% if(cart != null && cart.size() != 0) { %>
				<% for( Map.Entry<String, Integer> entry : cart.entrySet() )  {%>
					<li>
						商品代號:<%=entry.getKey() %> - 購買數量:<%=entry.getValue() %>
						<a href="./cart?product=<%=entry.getKey() %>&&action=reduction">
							減量
						</a>
					</li>
				<% } %>
			<% } else { %>
				<li>購物車無資料</li>
			<% } %>
		</ul>
		<a href="./product">返回商品列表</a>
		<a href="./cart?action=empty">清空購物車</a>
		<a href="./cart?action=submit">結帳</a>
	</body>
</html>