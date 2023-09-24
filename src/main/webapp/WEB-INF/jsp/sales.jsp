<%@page import="java.util.List"%>
<%@page import="repository.model.Sales"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	List<Sales> salesList = (List<Sales>)request.getAttribute("salesList");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<form class="pure-form">
			<fieldset>
				<legend>Sales List</legend>
				<table class="pure-table pure-table-bordered">
					<thead>
						<th>序號</th><th>日期</th><th>商品</th><th>價格</th><th>數量</th><th>城市</th><th>分店</th><th>刪除</th>
					</thead>
					<tbody>
						<% for(Sales sales : salesList) { %>
							<tr>
								<td>
									<a href="${pageContext.request.contextPath}/servlet/sales/update?id=<%=sales.getId() %>">
										<%=sales.getId() %>
									</a>
								</td>
								<td><%=sales.getDate() %></td>
								<td><%=sales.getProduct() %></td>
								<td><%=sales.getPrice() %></td>
								<td><%=sales.getQty() %></td>
								<td><%=sales.getCity() %></td>
								<td><%=sales.getBranch() %></td>
								<td>
									<a href="${pageContext.request.contextPath}/servlet/sales/delete?id=<%=sales.getId() %>">
										刪除
									</a>
								</td>
							</tr>
						<% } %>		
					</tbody>
				</table>
					 
			</fieldset>
		</form>
		
	</body>
</html>