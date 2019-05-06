<%@ page language="java" contentType="text/html; charset=utf-8"
	pageEncoding="utf-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>To do list</title>
</head>
<body>
	<table class = "toDoSideMenu">
	<div class = "toDoHead">Tasks to be done:</div>	
		<c:forEach var="tempToDo" items="${toDo}">
			<tr>
				<td>
					<ul style="list-style-type: none; padding:0; margin:5px">
					<li><div class="todo">${tempToDo.task}</div></li>
					</ul>
				</td>
			</tr>
		</c:forEach>
	</table>

</body>
</html>