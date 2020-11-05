<html>
	<head>
		<title>Area administrativa do Painel Twenty</title>
		<style type="text/css">

#csv {
font-family: Verdana;
color: #30a289;
}

#jpg {
font-family: Verdana;
color: #30a289;
}
		</style>
	</head>
	<body>
		<% if (request.getAttribute("fail") != null) { %>
		<strong id="fail">Falha ao carregar o arquivo</strong>
		<% } %>
		<form id="csv" name="csv" action="/<%=request.getAttribute("contextName") %>/control" method="post" enctype="multipart/form-data">
			<label for="csv">Arquivo da planilha em .CSV para upload</label>
			<input id="csv" name="csv" type="file"/>
			<input name="send" type="submit" value="Send"/>
		</form>
		
		<% if (request.getAttribute("fail") != null) { %>
		<strong id="fail">Falha ao carregar o arquivo</strong>
		<% } %>
		<form id="jpg" name="jpg" action="/<%=request.getAttribute("contextName") %>/control" method="post" enctype="multipart/form-data">
			<label for="jpg">Arquivo da infraero em .JPG para upload</label>
			<input id="jpg" name="ranking" type="file"/>
			<input name="send" type="submit" value="Send"/>
		</form>
		
		<form id="jpg" name="jpg" action="/<%=request.getAttribute("contextName") %>/control" method="post" enctype="multipart/form-data">
			<label for="APPID">Codigo APPID do site http://openweathermap.org/appid</label>
			<input id="APPID" name="APPID" type="text"/>
			<input name="send" type="submit" value="Send"/>
		</form>
	</body>
</html>