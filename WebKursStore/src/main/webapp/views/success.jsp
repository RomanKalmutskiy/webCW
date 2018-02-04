<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ page isELIgnored="false"%>

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="/WebKurs2/views/style.css" rel="stylesheet" />
</head>
<body>





Login successful:
<br>

login:${sessionScope["user"].login}
<br>
password:${sessionScope["user"].password}
<br>
name:${sessionScope["user"].name}


<br>
<header>
			<nav>
				<a href="/WebKurs2/prod"> Dear ${sessionScope["user"].name} welcome to the shop</a>
			</nav>
		</header>


<br>
<form action='/WebKurs2/lg' method='post'>
<input type='submit' value='logout' name='logout'/>
</form>

</body>
</html>
