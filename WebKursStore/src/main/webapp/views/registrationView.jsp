<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page
	import="less09.*,java.io.*,com.thoughtworks.xstream.*,com.thoughtworks.xstream.io.xml.*,java.sql.*,javax.sql.*"%>
<%@ page isELIgnored="false"%>
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
				<a href="/WebKurs2/lg">Login</a>
			</nav>
		</header>
		<center>
			<form id="registrationForm"
				action="" method="post">
				${sessionScope["error"]}<br />
				<div class="field">
					<label>Enter your login:</label>
					<div class="input">
						<input type="text" name="login" value="" id="login" />
					</div>
				</div>

				<div class="field">
					<label>Enter your password:</label>
					<div class="input">
						<input type="password" name="psw" value="" id="pass" />
					</div>
				</div>

				<div class="field">
					<label>Re-type your password:</label>
					<div class="input">
						<input type="password" name="repsw" value="" id="pass" />
					</div>
				</div>

				<div class="field">
					<label>Enter your name:</label>
					<div class="input">
						<input type="text" name="name" value="" id="login" />
					</div>
				</div>

				<div class="field">
					<label>Enter your gender:</label>
					<div class="input">
						M<input type="radio" name="gender" value="male" id="login" /> F<input
							type="radio" name="gender" value="female" id="login" />
					</div>
				</div>


				<div class="field">
					<label>Enter your area:</label>
					<div class="input">
						<select name='area'>
							<option value='0'>Donetck</option>
							<option value='1'>Luhanskk</option>
							<option value='2'>KrimNash</option>
						</select>
					</div>
				</div>
				<div class="field">
					<label>Enter your comments:</label>
					<div class="input">
						<textarea name='comment' cols='40' rows='10'></textarea>
					</div>
				</div>
				<div class="field">
					<label>Download Amigo Browser</label>
					<div class="input">
						<input type="checkbox" name="agree" value="download" id="login" />
					</div>
				</div>
				<div class="field">
					<div class="input">
						<input type="reset" value="Reset" id="login" />
					</div>
				</div>
				<div class="field">
					<div class="input">
						<input type="submit" value="SEND" id="login" />
					</div>
				</div>
				<div class="submit">
					<button type="submit">Enter</button>
					<label id="remember"><input name="" type="checkbox"
						value="" /> Remember me</label>
				</div>

			</form>
</body>
</html>