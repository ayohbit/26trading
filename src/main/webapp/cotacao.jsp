<%@page import="br.com.tweent.Cotacao.Valor"%>
<%@page import="java.util.List"%>
<%@page import="br.com.tweent.Cotacao"%>

<% Cotacao cotacao = (Cotacao)request.getAttribute("cotacao"); %>

<table border="1">
	<thead>
		<section class="cabecalho">
			<strong><font color="white">{{titulo}}</font></strong>
		</section>
	</thead>
	<% if (cotacao != null) { %>
	<tbody>
		<% if (cotacao.hasParidade()) { %>
		<tr>
			<td rowspan="2" align="center"><strong>{{moeda}}</strong></td>
			<td colspan="2" align="center"><strong>Taxa</strong></td>
			<td colspan="2" align="center"><strong>Paridade</strong></td>
		</tr>
		<tr>
			<td align="center"><strong>Compra</strong></td>
			<td align="center"><strong>Venda</strong></td>
			<td align="center"><strong>Compra</strong></td>
			<td align="center"><strong>Venda</strong></td>
		</tr>
		<% } else { %>
		<tr>
			<td align="center"><strong>{{moeda}}</strong></td>
			<td align="center"><strong>Compra</strong></td>
			<td align="center"><strong>Venda</strong></td>
		</tr>
		<% } %>
		<% List<Valor> list = cotacao.getList(); %>
		<% for(Valor valor : list) { %>
		<tr>
			<td align="center"><%=valor.getDate() %></td>
			<td align="center"><%=valor.getValorCompraTaxa() %></td>
			<td align="center"><%=valor.getValorVendaTaxa() %></td>
			<% if (cotacao.hasParidade()) { %>
			<td align="center"><%=valor.getValorCompraParidade() %></td>
			<td align="center"><%=valor.getValorVendaParidade() %></td>
			<% } %>
		</tr>
		<% } %>
	</tbody>
	<% } %>
</table>