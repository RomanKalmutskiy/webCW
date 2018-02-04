<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>	

<!DOCTYPE html>
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
	<title></title>
	<meta charset="UTF-8" />
	<link href="/WebKurs2/views/style.css" rel="stylesheet" />
</head>
<body>

<form action='/WebKurs2/lg' method='post'>
<input type='submit' value='logout' name='logout'/>
</form>


<center>
<header>
<nav>
				<a href="/WebKurs2/prod">All</a>
			</nav>
<a href="?category=wine">Wine</a> --- 
<a href="?category=caviar">Caviar</a>---
<a href="?category=cigars">Cigars <br><Br>
</header>
</center>
<hr>


<c:forEach var="item" items="${sessionScope.cart}">
    Product: ${item.key.description} -
   Quantity: ${item.value} - Price: ${item.key.price} UAH; Total: ${item.value*item.key.price} UAH
   <br>
   <form action='' method='post'>
   <input type='hidden' name='id' value="${item.key.id}" />
	<input type='submit' name='buy' value='Increase' />
	</form>
   <form action='' method='post'>
   <input type='hidden' name='id' value="${item.key.id}" />
	<input type='submit' name='DEL' value='Decrease' />
	</form>
	
</c:forEach>

<hr>
<c:forEach items="${requestScope.products}" var ="item">
	<b>Category</b> : <i>${item.category}</i>
	<b>Name</b> : <i>${item.description}</i> , Price:${item.price}
	<br>
	<img title=" " alt=" " src="/WebKurs2/views/FOTO/${item.id}.jpg" />
	<form action='' method='post'>
	<input type='hidden' name='id' value="${item.id}" />
	<input type='submit' name='buy' value='CART' />
	</form>
	<form action='' method='post'>
   <input type='hidden' name='id' value="${item.id}" />
	<input type='submit' name='DEL' value='Decrease' />
	</form>
	 <br>
</c:forEach>


</body>
</html>

