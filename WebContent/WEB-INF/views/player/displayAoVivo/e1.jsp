<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html xmlns="http://www.w3.org/1999/xhtml" lang="en" xml:lang="en">
 <head>
  <title>E1</title>
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1" />
  <script type="text/javascript" src="resources/plugin/swfobject/swfobject.js"></script>
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
  <div id="myContent">
   <h1>Please Upgrade Your Flash Player</h1>
   <p><a href="http://www.adobe.com/go/getflashplayer"><img src="http://www.adobe.com/images/shared/download_buttons/get_flash_player.gif" alt="Get Adobe Flash player" /></a></p>
  </div><!--encryptbegin-->
        
  <script type="text/javascript">
         
   var flashvars = {
     flvpFolderLocation: "flvplayer/", 
     flvpVideoSource: "rtmpt://flash-streaming-server", 
     flvpWidth: "514", 
     flvpHeight: "290"
   };
   
   var params = {
     menu: "true", 
     allowfullscreen: "true",
     wmode: "opaque"
   };
   
   swfobject.embedSWF("resources/swf/player.swf", "myContent", "514", "290", "9.0.0", "resources/plugin/swfobject/expressInstall.swf", flashvars, params);
        
        </script><!--encryptend-->
 </body>
</html>
