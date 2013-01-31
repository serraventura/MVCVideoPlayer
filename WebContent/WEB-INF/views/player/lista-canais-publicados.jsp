<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<c:forEach var="canal" items="${canais}" varStatus="contador">

	<div class="itemTooltip" id="itemTooltip${contador.count}">${canal.textoTooltip}</div>
	
	<a class="linkTooltip" id="canal${contador.count}">
		<div class="btnSala" align="center" onclick="javascript:playAoVivo('${canal.link}', ${canal.id});">${canal.nome}</div>
	</a>
	
	<input type="hidden" name="textoTooltip_${canal.id}" id="textoTooltip_${canal.id}" value="${canal.textoTooltip}" />
	
</c:forEach>


