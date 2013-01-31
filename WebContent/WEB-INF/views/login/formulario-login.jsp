<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Login</title>

<style type="text/css">
html, body{
	margin:0px;
	font-family: Arial;
	font-size:small;
}

</style>

</head>
<body>


	<div style=" margin:0px; margin-top:20%; background-color:#ACCADE; padding:40px;  border: 3px solid #80A7C3; border-left:0px; border-right:0px;">

		
			<form method="post" action="efetuaLogin" style="margin-left:30%;">
				Login: <input type="text" name="login" />
				Senha: <input type="password" name="senha" />
				<input type="submit" value="ok" style="border: 1px solid #80A7C3; background-color:#ACCADE;" />
			</form>
		

	</div>

</body>
</html>