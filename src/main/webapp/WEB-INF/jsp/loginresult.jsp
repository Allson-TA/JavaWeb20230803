<%@page import="java.util.List"%>
<%@page import="repository.model.User"%>
<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<%
String username = (String)request.getAttribute("username");
Boolean check = (Boolean)request.getAttribute("check");
%>
<html>
	<head>
		<meta charset="UTF-8">
		<title>登入</title>
		<link rel="stylesheet" href="https://cdn.jsdelivr.net/npm/purecss@3.0.0/build/pure-min.css">
	</head>
	<body style="padding: 15px">
		
		<table>
			<td valign="top">
				<!-- 登入結果 -->
				<form method="get" action="/JavaWeb20230803/servlet/login" class="pure-form">
					<fieldset>
						<legend>登入-結果</legend>
						帳號: <%=username %><p />
						結果: <%=check %><p />
						<button type="submit" class="pure-button pure-button-primary">回上一頁</button>	 
					</fieldset>
				</form>
			</td>
			
			<td valign="top">
				<!-- User 表單 -->
				<form method="post" action="/JavaWeb20230803/servlet/user/add" class="pure-form">
					<fieldset>
						<legend>User 表單</legend>
						帳號: <input type="text" id="username" name="username"><p />
						密碼: <input type="text" id="password" name="password"><p />
						生日: <input type="date" id="birth" name="birth"><p />
						<button type="submit" class="pure-button pure-button-primary">新增</button>	 
					</fieldset>
				</form>
			</td>
			
			<td valign="top">
				<!-- User 列表 -->
				<form class="pure-form">
					<fieldset>
						<legend>User 列表</legend>
						<table class="pure-table pure-table-bordered">
							<thead>
								<th>序號</th><th>帳號</th><th>生日</th><th>年齡</th>
							</thead>
							<tbody>
								<% if(check) { %>
									<% List<User> users = (List<User>)request.getAttribute("users"); %>
									<% for(User user : users) { %>
										<tr>
											<td><%=user.getId() %></td>
											<td><%=user.getUsername() %></td>
											<td><%=user.getBirthFormat() %></td>
											<td>
												<%
													if(user.getUsername().equals(username)) {
														out.print(user.getAge());
													} else {
														out.print("");
													}
												%>
											</td>
										</tr>
									<% } %>
								<% } %>
							</tbody>
						</table>
					</fieldset>
				</form>
			</td>
		</table>
		
		
		
		
		
	</body>
</html>