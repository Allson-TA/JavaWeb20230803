<%@page import="lab.cart.repository.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="lab.cart.repository.model.Order"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	List<Order> orders = (List<Order>)request.getAttribute("orders");
	List<Product> products = (List<Product>)request.getAttribute("products");
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
			<% if(orders != null && orders.size() != 0) { %>
				<% for( Order order : orders )  {%>
					<li>
						商品代號:<%=order.getProductId() %> - 
						購買數量:<%=String.format("%02d", order.getOrderQty()) %>
						<a href="./cart?product_id=<%=order.getProductId() %>&action=add">
							增量
						</a>
						&nbsp;<span style="color: #ff0000">|</span>&nbsp;
						<a href="./cart?product_id=<%=order.getProductId() %>&action=reduction">
							減量
						</a>
						
					</li>
				<% } %>
			<% } else { %>
				<li>購物車無資料</li>
			<% } %>
		</ul>
		<a href="./product">返回商品列表</a>
		<% if(orders != null && orders.size() != 0) { %>
			<a href="./cart?action=empty">清空購物車</a>
			<a href="./cart?action=submit">結帳</a>
		<% } %>
	</body>
</html>