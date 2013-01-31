<%@ page language="java" contentType="text/html; charset=ISO-8859-1" pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
	<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
	<title>Display Vídeo</title>

	<script type="text/javascript" charset="iso-8859-1" src="resources/plugin/swfobject/swfobject.js"></script>

	<script type="text/javascript">
		var extVideo = '.flv';
	
		carregaVideo(0);
		function carregaVideo(video){

			//alert(video+extVideo);

			var flashvars = {
				flvpFolderLocation: "upload-videos/",  
				//flvpVideoSource: "http://localhost:8080/upload-videos/publicado/83.f4v",
				flvpVideoSource: "http://localhost:8080/upload-videos/publicado/"+video+extVideo,
				flvpWidth: "514",
				flvpHeight: "290",
				flvpInitVolume: "50", 
				flvpTurnOnCorners: "true"
			};

			var params = {
				menu: "true", 
				bufferlength: "1",
				allowfullscreen: "true",
				wmode: "opaque"
			};

			swfobject.embedSWF("resources/swf/player.swf", "playerContent", "514", "290", "9.0.0", "resources/plugin/swfobject/expressInstall.swf", flashvars, params);

		}		
	</script>
	
	<style>
		html, body{
			margin: 0px;
			padding: 0px;
			height:100%;
			overflow: hidden;
		}
	</style>
	
</head>
<body>
	<div id="playerContent">
		<h1>Please Upgrade Your Flash Player</h1>
		<p>
			<a href="http://www.adobe.com/go/getflashplayer">
				<img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" />
			</a>
		</p>
	</div>
</body>
</html>