<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/functions" prefix="fn"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista Vídeos Processados</title>

	<script type="text/javascript" charset="iso-8859-1" src="resources/js/lista-videos-publicados.js"></script>

	<script type="text/javascript">
	
		$(document).ready(function() {  
		  
		    $('textarea[maxlength]').keyup(function(){  
		        //get the limit from maxlength attribute  
		        var limit = parseInt($(this).attr('maxlength'));  
		        //get the current text inside the textarea  
		        var text = $(this).val();  
		        //count the number of characters in the text  
		        var chars = text.length;  
		  
		        //check if there are more characters then allowed  
		        if(chars > limit){  
		            //and if there are use substr to get the text before the limit  
		            var new_text = text.substr(0, limit);  
		  
		            //and change the current text with the new text  
		            $(this).val(new_text);  
		        }  
		    });  
		  
		});
	
	</script>


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
	input, textarea{
		width:97%;
	}
</style>

</head>
<body>

	<input onclick="javascript: location.href='mainAdmin';" type="button" value="<--" style="border: 1px solid white; background-color: #ACCADE; width: auto;" />
	<input onclick="javascript: location.href='listaVideosPublicados';" type="button" value="*" style="border: 1px solid white; background-color: #ACCADE; width: auto;" />

	<form name="frmSalvar" action="salvarDescricaoVideo" method="post"></form>

	<table border=0 width="100%" cellpadding="4" cellspacing="3" bordercolor="#ACCADE">
	<tr>
		<th>Processado</th>
		<th>Dados</th>
		<th>Data</th>
		<th>Descrição</th>
		<th>Chamada</th>
		<th>#</th>
	</tr>

	<c:forEach var="video" items="${videos}" varStatus="contador">
	
		<tr>
			<td align="center">
				<c:if test="${video.publicado eq true}">
					SIM
				</c:if>
				<c:if test="${video.publicado eq false}">
					<span style="text-decoration: blink;">NÃO</span>
				</c:if>
			</td>

			<td>

				<strong>Arquivo:</strong> ${video.nome}<br />
				<strong>Tamanho Arquivo:</strong> ${video.tamanhoArquivo}<br />
				<strong>Duração: </strong>${video.duracao}<br />
	
				<strong>Nome Antigo Arquivo: </strong>
				<!-- c:out value="${fn:length(video.nomeAntigoArquivo)}" /-->
				<c:if test="${fn:length(video.nomeAntigoArquivo) > 30}">
					${fn:substring(video.nomeAntigoArquivo,0,27)}... 
				</c:if>
				
				<c:if test="${fn:length(video.nomeAntigoArquivo) < 30}">
					${video.nomeAntigoArquivo}
				</c:if>
				<br />
				<strong>WidthxHeight:</strong> ${video.width}x${video.height}<br />
				<strong>Framerate:</strong> ${video.framerate}<br />

			</td>

			<td align="center">
				<input type="text" name="dataCriacao_${contador.count}" id="dataCriacao_${contador.count}" value="${video.dataCriacao}" maxlength="21" />
			</td>

			<td>
				<textarea maxlength="255" name="desc_${contador.count}" id="desc_${contador.count}" style="width:100%; height: 90px;" onblur="copiarChamada(${contador.count});" >${video.descricao}</textarea>
			</td>

			<td>
				<input style="width: 99%;" type="text" name="chamada_${contador.count}" id="chamada_${contador.count}" value="${video.chamada}" maxlength="140" />
			</td>
			<td align="center">

				<c:if test="${video.publicado eq false}">
					<span style="text-decoration: blink;">Processando...</span> 
				</c:if>

				<c:if test="${video.publicado eq true}">
					<a href="#" onclick="confirmar(${video.id}, ${contador.count});">Publicar</a> 
				</c:if>

			</td>
		</tr>
	
	</c:forEach>
	
	</table>

</body>
</html>