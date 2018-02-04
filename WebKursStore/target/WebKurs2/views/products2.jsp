<%@ taglib prefix = "c" uri = "http://java.sun.com/jsp/jstl/core" %>
<%@ page isELIgnored="false" %>

<center>
<header>
<a href="?category=wine">Wine</a> --- 
<a href="?category=caviar">Caviar</a>---
<a href="?category=cigars">Cigars <br><Br>
</header>
</center>
<hr>

<c:forEach var="item" items="${sessionScope.cart}">
   Product: ${item.key.description} -
   Quantity: ${item.value}
   <br>
</c:forEach>

<hr>
<c:forEach items="${requestScope.products}" var ="item">
	<b>Name</b> : <i>${item.description}</i> , Price:${item.price}
	<form action='' method='post'>
	<input type='hidden' name='id' value="${item.id}" />
	<input type='submit' name='buy' value='buy' />
	</form>
	 <br>
</c:forEach>

