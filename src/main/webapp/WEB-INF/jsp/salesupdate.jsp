<%@page import="java.text.ParseException"%>
<%@page import="java.util.Date"%>
<%@page import="java.text.SimpleDateFormat"%>
<%@page import="java.util.List"%>
<%@page import="repository.model.Sales"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%!
	// 在 jsp 中自訂一個方法
	String getStringDate(String inputDate) {
		SimpleDateFormat inputFormat = new SimpleDateFormat("yyyy/M/d");
		SimpleDateFormat outputFormat = new SimpleDateFormat("yyyy-MM-dd");
	    try {
	        Date date = inputFormat.parse(inputDate);
	        String formattedDate = outputFormat.format(date);
	        return formattedDate;  // This will print "2023-01-01"
	    } catch (ParseException e) {
	        //e.printStackTrace();
	    }
	    return inputDate;
	}

%>
<%
	Sales sales = (Sales)request.getAttribute("sales"); // 要修改的 Sales 物件
	List<String> products = (List<String>)request.getAttribute("products"); // 所有 product
	List<String> cities = (List<String>)request.getAttribute("cities"); // 所有 cities
	List<String> branches = (List<String>)request.getAttribute("branches"); // 所有 branches
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales Update</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<form class="pure-form" method="post" action="${pageContext.request.contextPath}/servlet/sales/update">
			<fieldset>
				<legend>Sales Update</legend>
				序號: <input type="text" id="id" name="id" value="<%=sales.getId() %>" readonly><p />
				日期: <input type="date" id="date" name="date" value="<%=getStringDate(sales.getDate()) %>" required><p />
				商品: <select id="product" name="product">
						<% for(String product : products) { %>
							<option value="<%=product %>" <%=product.equals(sales.getProduct())?"selected":"" %> ><%=product %></option>
						<% } %>
					 </select><p />
				價格: <input type="number" id="price" name="price" value="<%=sales.getPrice() %>" required><p />
				數量: <input type="number" id="qty" name="qty" value="<%=sales.getQty() %>"  required><p />
				城市: 
					<select id="city" name="city">
						<% for(String city : cities) { %>
							<option value="<%=city %>" <%=city.equals(sales.getCity())?"selected":"" %> ><%=city %></option>
						<% } %>
					</select><p />
				分店: 
					<select id="branch" name="branch" onchange="this.value==''?branch_newname.style.display='':branch_newname.style.display='none';">
						<option value="">請選擇</option>
					    <% for(String branch : branches) { %>
							<option value="<%=branch %>" <%=branch.equals(sales.getBranch())?"selected":"" %> ><%=branch %></option>
						<% } %>
					</select>
					<!-- 自訂新的分店名 -->
					<input type="text" id="branch_newname" name="branch_newname" title="自訂新的分店名">
				<p />
				<button type="submit" class="pure-button pure-button-primary">修改</button>
			</fieldset>
		</form>
		
	</body>
</html>