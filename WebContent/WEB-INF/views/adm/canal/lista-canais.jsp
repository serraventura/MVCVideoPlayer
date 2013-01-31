<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista canais ao vivo</title>

	<script type="text/javascript" charset="iso-8859-1" src="resources/js/lista-canais.js"></script>

<style type="text/css">
	html, body{
		font-family: Arial;
		font-size:small;
		background-color:#80A7C3;
		margin:0px;
	}
	
	td{
		border:1px solid white; background-color:#ACCADE;
	}
	input{
		width:98%;
	}
</style>

</head>
<body>

	<input onclick="javascript: location.href='mainAdmin';" type="button" value="<--" style="border: 1px solid white; background-color: #ACCADE; width: auto;" />

	<form name="frmSalvar" action="salvarCanal" method="post"></form>

	<table border=0 width="100%" cellpadding="4" cellspacing="3" bordercolor="#ACCADE">
	<tr>
		<th>#</th>
		<th>Canal</th>
		<th>Link</th>
		<th>Texto Tooltip</th>
		<th>Publicado</th>
		<th>#</th>
	</tr>

	<c:forEach var="canal" items="${canais}" varStatus="contador">
	
		<tr>
			<td align="center">
				${canal.id}
			</td>			
			<td align="center" bordercolor="red">
				<input type="text" name="nome_${contador.count}" id="nome_${contador.count}" value="${canal.nome}" maxlength="45" />
			</td>

			<td>
				<input type="text" name="link_${contador.count}" id="link_${contador.count}" value="${canal.link}" maxlength="255" />
			</td>

			<td>
				<input type="text" name="textoTooltip_${contador.count}" id="textoTooltip_${contador.count}" value="${canal.textoTooltip}" maxlength="140" />
			</td>
			
			<td align="center">
				<c:if test="${canal.publicado eq true}">
					SIM
				</c:if>
				<c:if test="${canal.publicado eq false}">
					NÃO
				</c:if>
			</td>
						
			<td align="center">
				<a href="#" onclick="confirmar(${canal.id}, ${contador.count});">Salvar</a> 
			</td>
		</tr>
	
	</c:forEach>
	
	</table>

</body>
</html>