<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	Map<String, String> productNames = (Map<String, String>)application.getAttribute("productNames");
	Map<String, Integer> products = (Map<String, Integer>)application.getAttribute("products");
	String username = session.getAttribute("username") + "";
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>百元熱炒店</title>
	</head>
	<body>
		<h1>商品列表(登入者:<%=username %>)</h1>
		<ul>
			<% for( Map.Entry<String, Integer> entry : products.entrySet() )  {%>
				<li>
					商品代號:<%=entry.getKey() %> - 
					<%=productNames.get(entry.getKey()) %> -
					庫存數量:<%=entry.getValue() %>
					<a href="./cart?product=<%=entry.getKey()%>&action=add">
						加入購物車
					</a>
				</li>
			<% } %>
		</ul>
		<a href="./cart?action=view">查看購物車</a>
	</body>
</html>