<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Main Admin</title>

<style type="text/css">
html, body{
	font-family: Arial;
	font-size:small;
	background-color:#ACCADE;
	margin:0px;
}
.btnCanaisAdm{
	float:left; 
	padding:15px;
	 margin:5px; 
	 border:1px solid #80A7C3; 
	 cursor:pointer; 
	 color:black; 
	 text-transform: uppercase;
	 font-size: x-small;
}
</style>


</head>
<body>
	<div style="background-color:#80A7C3; padding:8px;">Bem vindo, ${usuarioLogado.login}.</div>	
	
	<div style=" height:300px; background-color:#ACCADE;">
	
		<a href="listaCanais"><div class="btnCanaisAdm">Canais ao vivo</div></a>
		<a href="listaVideosPendentes"><div class="btnCanaisAdm">Vídeos Pendentes</div></a>
		<a href="listaVideosPublicados"><div class="btnCanaisAdm">Vídeos Processados</div></a>
		<a href="logout"><div class="btnCanaisAdm">Sair</div></a>
	
	</div>
	
</body>
</html>