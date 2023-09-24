<%@page import="java.util.Map.Entry"%>
<%@page import="java.util.Map"%>
<%@page import="java.util.List"%>
<%@page import="repository.model.Sales"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<% 
	Map<String, Integer> salesMap = (Map<String, Integer>)request.getAttribute("salesMap");
	String chartName = request.getAttribute("chartName") + "";
	String itemName = (request.getAttribute("itemName") + "").intern();
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>Sales Analysis</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		
		<script type="text/javascript" src="https://www.gstatic.com/charts/loader.js"></script>
	    <script type="text/javascript">
	      google.charts.load('current', {'packages':['corechart']});
	      google.charts.setOnLoadCallback(drawChart);
	
	      function drawChart() {
	
	        var data = google.visualization.arrayToDataTable([
	          ['Item', 'Amount'],
	          <% for(Entry<String, Integer> map : salesMap.entrySet()) { %>
	          	['<%=map.getKey() %>', <%=map.getValue() %>],
	          <% } %>
	        ]);
	
	        var options = {
	          title: 'Sales Analysis', is3D: true
	        };
	
	        var chart = new google.visualization.<%=chartName.trim() %>Chart(document.getElementById('piechart'));
	        
	        chart.draw(data, options);
	      }
	    </script>
		
	</head>
	<body style="padding: 15px">
		
		<table>
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>Sales Analysis</legend>
						分析項目: <br>
							<input type="radio" id="itemName" name="itemName" value="1" <%=itemName.equals("1")?"checked":"" %>> 根據地區印出每區的銷售金額<br>
							<input type="radio" id="itemName" name="itemName" value="2" <%=itemName.equals("2")?"checked":"" %>> 根據產品印出每一種產品的銷售金額<br>
							<input type="radio" id="itemName" name="itemName" value="3" <%=itemName.equals("3")?"checked":"" %>> 根據分店印出每一家的銷售金額<p>
						選擇圖型: <br>
							<input type="radio" id="chartName" name="chartName" value="Column" <%=chartName.equals("Column")?"checked":"" %>> 直方圖
							<input type="radio" id="chartName" name="chartName" value="Bar" <%=chartName.equals("Bar")?"checked":"" %>> 長條圖
							<input type="radio" id="chartName" name="chartName" value="Pie" <%=chartName.equals("Pie")?"checked":"" %>> 圓餅圖
							<input type="radio" id="chartName" name="chartName" value="Line" <%=chartName.equals("Line")?"checked":"" %>> 折線圖
						<br>
						<button type="submit" class="pure-button pure-button-primary">傳送</button>
						<br>
						<table class="pure-table pure-table-bordered">
							<thead>
								<th>項目</th><th>銷售額</th>
							</thead>
							<tbody>
								<% for(Entry<String, Integer> map : salesMap.entrySet()) { %>
									<tr>
										<td><%=map.getKey() %></td>
										<td><%=map.getValue() %></td>
									</tr>
								<% } %>		
							</tbody>
						</table>
					</fieldset>
				</form>
			</td>
			<td valign="top">
				<form class="pure-form">
					<fieldset>
						<legend>Sales Chart</legend>
						<div id="piechart" style="width: 900px; height: 500px;"></div>
					</fieldset>
				</form>	
			
			</td>
		</table>
		
	</body>
</html>