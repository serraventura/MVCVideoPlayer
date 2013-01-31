<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Player</title>
	<script type="text/javascript" charset="iso-8859-1" src="resources/js/player-video.js"></script>
	<script charset="utf-8" src="http://widgets.twimg.com/j/2/widget.js"></script>

	<link rel="stylesheet" type="text/css" href="resources/css/player-video.css" />
	<link rel="stylesheet" type="text/css" href="resources/css/paginacao.css" />
	
	<style type="text/css">
		@import url("resources/css/player-video.css");
		@import url("resources/css/paginacao.css");
	</style>

	<script type="text/javascript">

		$(document).ready(function(){

			init('${videoParametro}', '${tipoVideoParametro}', '${idVideoVivoParametro}');

			//if(!isWindows()){
				
				$("a#canal1").easyTooltip({useElement: "itemTooltip1"});
				$("a#canal2").easyTooltip({useElement: "itemTooltip2"});
				$("a#canal3").easyTooltip({useElement: "itemTooltip3"});
				$("a#canal4").easyTooltip({useElement: "itemTooltip4"});
				$("a#canal5").easyTooltip({useElement: "itemTooltip5"});
				$("a#canal6").easyTooltip({useElement: "itemTooltip6"});				
				
			//}
			


		});

	</script>
	
	<script type="text/javascript">

	  var _gaq = _gaq || [];
	  _gaq.push(['_setAccount', 'UA-00000000-0']);
	  _gaq.push(['_trackPageview']);

	  (function() {
	    var ga = document.createElement('script'); ga.type = 'text/javascript'; ga.async = true;
	    ga.src = ('https:' == document.location.protocol ? 'https://ssl' : 'http://www') + '.google-analytics.com/ga.js';
	    var s = document.getElementsByTagName('script')[0]; s.parentNode.insertBefore(ga, s);
	  })();
	
	</script>
	
	<noscript><h1>Atenção seu navegador não suporta javascript. Infelizmente não será possível navegar normalmente pelo site.</h1></noscript>

</head>
<body>
	
	<div id="boxPlayer">
		<div id="playerContentBox">

			<div id="displayVideo"></div>

		</div>
		
		<div id="canaisAoVivo">
			<span class="titulos">&nbsp// SELECIONE OUTRO CANAL</span><br />
			<div id="boxBtnCanais" style="margin-top: 2px;">
			</div>
		</div>		
		
		<div id="displayContent">

			<span class="titulos">// VOCÊ ESTA ASSISTINDO</span><br /><br />
			<span id="descVideoCorrente">>> Selecione um vídeo ou um canal ao vivo para assistir.</span><br /><br />
			<span id="duracaoVideoCorrente"></span><br />
			<span id="dataVideoCorrente"></span><br />

		</div>

		<div class="titulos" id="tituloVideoteca">// VIDEOTECA</div>
	
		<div id="listContent">
		</div>
		
		<div id="twitterContent">
	
			<div>
				<script>carregaTwitter();</script>
			</div>

		</div>
		
		<div style="float: left; width: 100%; height: 20px;"></div>
		
	</div>
</body>
</html>