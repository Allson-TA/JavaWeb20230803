<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
	<head>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
		<meta charset="UTF-8">
		<title>Index Page</title>
	</head>
	<body style="padding: 15px">
		<form class="pure-form">
			<fieldset>
				<legend style="font-size: 20px;">Basic</legend>
				<ol>
					<li><a href="./html/bmiform.html">bmiform.html</a></li>
					<li><a href="./servlet/bmi?h=170&w=60">BMIServlet</a></li>
					<li><a href="./servlet/login">LoginServlet</a></li>
					<li><a href="./servlet/lotto">LottoServlet</a></li>
					<li><a href="./servlet/many/score">ManyScoreServlet</a></li>
					<li><a href="./servlet/shoppingcar?name=coffee&coffee_price=100&coffee_price=80&name=cake&cake_price=50&cake_price=40&cake_price=30">ShoppingCarServlet</a></li>
					<li><a href="./servlet/time">TimeServlet</a></li>
					<li><a href="./html/changecalculator.html">找零錢計算機</a></li>
				</ol>
			</fieldset>
		</form>
		<form class="pure-form">
			<fieldset>
				<legend style="font-size: 20px;">MVC: User</legend>
				<ol>
					<li><a href="./servlet/login">UserAddServlet(要先登入才能新增)</a></li>
				</ol>
			</fieldset>
		</form>
		<form class="pure-form">
			<fieldset>
				<legend style="font-size: 20px;">MVC: Transfer</legend>
				<ol>
					<li><a href="./html/transferform.html">transferform.html</a></li>
					<li><a href="./servlet/transfer">TransferServlet</a></li>
				</ol>
			</fieldset>
		</form>
		<form class="pure-form">
			<fieldset>
				<legend style="font-size: 20px;">MVC: Exam</legend>
				<ol>
					<li><a href="./html/examform.html">examform.html</a></li>
					<li><a href="./servlet/exam">ExamServlet</a></li>
				</ol>
			</fieldset>
		</form>
		<form class="pure-form">
			<fieldset>
				<legend style="font-size: 20px;">MVC: Sales</legend>
				<ol>
					<li><a href="./servlet/sales/analysis">SalesAnalysisServlet 分析</a></li>
					<li><a href="./servlet/sales">SalesServlet 查詢</a></li>
					<li><a href="./servlet/sales/add">SalesAddServlet 新增</a></li>
				</ol>
			</fieldset>
		</form>
		<form class="pure-form">
			<fieldset>
				<legend style="font-size: 20px;">MVC: Product</legend>
				<ol>
					<li><a href="./servlet/shopping/product">/servlet/shopping/product 訂購商品</a></li>
					<li><a href="./servlet/shopping/shoppingcar">/servlet/shopping/shoppingcar 購物車</a></li>
				</ol>
			</fieldset>
		</form>
		<form class="pure-form">
			<fieldset>
				<legend style="font-size: 20px;">MVC + MySQL: 購物車系統</legend>
				<ol>
					<li><a href="./lab/product">/lab/product 訂購商品</a></li>
				</ol>
			</fieldset>
		</form>
	</body>
</html>