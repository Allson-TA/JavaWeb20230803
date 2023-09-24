<%@page import="java.util.List"%>
<%@page import="repository.model.Sales"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
	List<String> products = (List<String>)request.getAttribute("products"); // 所有 product
	List<String> cities = (List<String>)request.getAttribute("cities"); // 所有 cities
	List<String> branches = (List<String>)request.getAttribute("branches"); // 所有 branches
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales Add</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<form class="pure-form" method="post" action="${pageContext.request.contextPath}/servlet/sales/add">
			<fieldset>
				<legend>Sales Add</legend>
				日期: <input type="date" id="date" name="date" required><p />
				商品: <select id="product" name="product">
						<% for(String product : products) { %>
							<option value="<%=product %>"><%=product %></option>
						<% } %>
					 </select><p />
				價格: <input type="number" id="price" name="price" required><p />
				數量: <input type="number" id="qty" name="qty" required><p />
				城市: 
					<select id="city" name="city">
						<% for(String city : cities) { %>
							<option value="<%=city %>"><%=city %></option>
						<% } %>
					</select><p />
				分店: 
					<select id="branch" name="branch" onchange="this.value==''?branch_newname.style.display='':branch_newname.style.display='none';">
						<option value="">請選擇</option>
					    <% for(String branch : branches) { %>
							<option value="<%=branch %>"><%=branch %></option>
						<% } %>
					</select>
					<!-- 自訂新的分店名 -->
					<input type="text" id="branch_newname" name="branch_newname" title="自訂新的分店名">
				<p />
				<button type="reset" class="pure-button">重置</button>
				<button type="submit" class="pure-button pure-button-primary">新增</button>
			</fieldset>
		</form>
		
	</body>
</html>