<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">

h1 {
	color: #428bcf
}
</style>
<title>Login Success Page</title>
</head>
<body>
	<h4>Hi ${user} , Login successful. Your Session
		ID=${pageContext.session}</h4>
	<br>
	<h4>
		<b>Admin</b> = ${user}
	</h4>


	<br>
	<div>
		<b>${errorDataBase}</b>
	</div>
	<div>
		<b>${wrongAction}</b>
	</div>
	<br>
	<div>
		<b>${nullPage}</b>
	</div>
	<br>

	<form name="Books" action="controller" method="post">
		<input type="hidden" name="command" value="UserPage">
		<div class="col-sm-offset col-sm">
			<input type="submit" class="btn btn-primary btn-lg"
				value="see all users">
		</div>
	</form>
	<br>
	<form name="Tours" action="controller" method="post">
		<input type="hidden" name="command" value="BooksAdmin">
		<div class="col-sm-offset col-sm">
			<input type="submit" class="btn btn-primary btn-lg"
				value="see all books">
		</div>
	</form>
	<br>
	<form name="Books" action="controller" method="post">
		<input type="hidden" name="command"
			value="_____________________________________________">
		<div class="col-sm-offset col-sm">
			<input type="submit" class="btn btn-primary btn-lg"
				value="see all orders">
		</div>
	</form>
	<br>
	<a href="controller?command=logout">Logout</a>
</body>
</html>