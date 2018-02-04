<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="/WebKurs2/views/style.css" rel="stylesheet" />
</head>
<body>

<center>

		<header>
			<nav>
				<a href="/WebKurs2/regView">Registration</a>
			</nav>
		</header>
		<center>
	

<form id="loginForm" action="" method="post">

	<div class="field">
		<label>Enter your login:</label>
		<div class="input">
		<input type="text" name="login" />
		</div>
	</div>

	<div class="field">
		<label>Enter your password:</label>
		<div class="input"><input type="password" name="psw"  /></div>
	</div>

	
	<div class="field">
		<div class="input">
		<input type="submit" value="SEND" id="login" />
		</div>
	</div>
	

</form>
</body>
</html>