<html>
	<head>
		<title>Area administrativa do Painel Twenty</title>
	</head>
	<body>

		<h2>Acesso</h2>
		<% if (request.getAttribute("fail") != null) { %>
		<strong id="fail">Acesso negado!</strong>
		<% } %>
		<form id="login" name="login" action="/<%=request.getAttribute("contextName") %>/login" method="post">
			<label for="username">Login</label>
			<input id="username" name="login" type="text"/>
			<label for="password">Password</label>
			<input id="password" name="password" type="password"/>
			<input id="send" name="send" type="submit" value="Send"/>
		</form>
	</body>
</html>

<style type="text/css">

h2 {
font-family: Verdana;
color: #30a289;
}

#login {
font-family: Verdana;
color: #30a289;
}

#password {
font-family: Verdana;
color: #30a289;
}

#username {
font-family: Verdana;
color: #30a289;
}

</style>