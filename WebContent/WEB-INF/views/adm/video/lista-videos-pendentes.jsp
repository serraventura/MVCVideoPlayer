<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Lista Vídeos Pendentes</title>

	<script type="text/javascript" charset="iso-8859-1" src="resources/js/lista-videos-pendentes.js"></script>


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

</head>
<body>
<input onclick="javascript: location.href='mainAdmin';" type="button" value="<--" style="border: 1px solid white; background-color: #ACCADE; width: auto;" />
	<form name="frmPublicar" action="publicarVideo" method="post"></form>

	<table border=0 width="100%" cellpadding="4" cellspacing="3" bordercolor="#ACCADE">
	<tr>
		<th>Arquivo de Vídeo</th>
		<th>Data Upload</th>
		<th>Descrição</th>
		<th>#</th>
	</tr>

	<c:forEach var="video" items="${videos}" varStatus="contador">
	
		<tr>
			<td>${video.nomeAntigoArquivo}</td>
			<td align="center">${video.dataCriacao}</td>
			<td>
				<textarea maxlength="255" rows="2" name="desc_${contador.count}" id="desc_${contador.count}" style="width:100%;"></textarea>
			</td>
			<td align="center"><a href="#" onclick="confirmar('${video.nomeAntigoArquivo}', '${video.dataCriacao}', ${contador.count});">Processar</a></td>
		</tr>
	
	</c:forEach>
	
	</table>

</body>
</html>