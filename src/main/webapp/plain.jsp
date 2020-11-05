<%@page import="java.util.Collection"%>
<%@page import="java.util.Map"%>
<%@page import="br.com.tweent.Plain"%>
<%@page import="java.util.List"%>
<%@ taglib uri='http://java.sun.com/jstl/core' prefix='c'%>

<% String[] headers = (String[])request.getAttribute("headers"); %>
<% List<Map<String, String>> list = (List<Map<String, String>>)request.getAttribute("list"); %>

<% if (headers != null) { %>
	<table border="1">
		<thead>
			<tr>
				<% for(String header : headers) { %>
					<th align="center"><strong><%=header %></strong></th>
				<% } %>
			</tr>
		</thead>
		<tbody>
			<% for(Map<String, String> map : list) { %>
				<tr>
					<% for(String key : headers) { %>
						<td align="center"><%=map.get(key) %></td>
					<% } %>
				</tr>
			<% } %>
		</tbody>
	</table>
<% } %>