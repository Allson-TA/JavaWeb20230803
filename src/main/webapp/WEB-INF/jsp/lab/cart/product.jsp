<%@page import="lab.cart.repository.model.Product"%>
<%@page import="java.util.List"%>
<%@page import="java.util.Map"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	List<Product> products = (List<Product>)request.getAttribute("products");
	String username = session.getAttribute("username") + "";
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>百元熱炒店-資料庫版</title>
	</head>
	<body>
		<h1>商品列表-資料庫版(登入者:<%=username %>)</h1>
		<ul>
			<% for( Product product : products )  {%>
				<li>
					商品代號:<%=product.getProductCode() %> - <%=product.getProductName() %> ($<%=product.getProductPrice() %>) -
					庫存數量:<%=product.getProductQty() %>
					<a href="<%=request.getContextPath() %>/lab/cart?product_id=<%=product.getId() %>">
						加入購物車
					</a>
				</li>
			<% } %>
		</ul>
		<a href="<%=request.getContextPath() %>/lab/cart?action=view">查看購物車</a>
	</body>
</html>