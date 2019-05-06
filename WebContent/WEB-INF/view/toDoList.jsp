<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Management System</title>

<!-- refrence our style sheet  -->
<link type="text/css" rel="stylesheet"
	href="${pageContext.request.contextPath}/resources/css/common.css" />
</head>


<body>
	<table class="main">
		<tr class="header">
			<td height="100px">
				<!-- HEADER  --> <jsp:include page="include/header.jsp" />
			</td>
		</tr>
		<tr class="upperMenu">
			<td height="30px">
				<!-- Upper Menu  --> <jsp:include page="include/upperMenu.jsp" />
			</td>
		</tr>
		<tr>
			<td height="340px" >
				<!-- Page content table -->
				<table class="content">
					<tr>
						<td width="100px">
							<!--Left Side Menu  -->
						</td>
						<td class="mainContent" align="center"  style="vertical-align: middle;">
							<!--Main content  --> <!--table with guests  -->
							<table class="toDoForm">
								<tr>
									<td class = "toDoShadow">
										<div class="description">
											<br>To be done...:<br>
										</div>
										<table>
											<c:forEach var="tempToDo" items="${toDo}">
												<tr class =  "list">
													<td class="list">
														<div class="todoMainList">${tempToDo.task}</div>
													</td>
													<td class="list"><form:form action="delete/?id=${tempToDo.id}" method="POST" modelAttribute="toDo">
															<input type="submit" value="&#10004" class="done" />
													</form:form></td>
												</tr>
											</c:forEach>
										</table>
									</td>
									<td><tab1/> </td>
									<td class = "toDoShadow">
										<table >
											<tr>
												<td>Add new task:</td>
											</tr>
											<tr>
												<td><form:form action="add" modelAttribute="todo"
														method="POST">
														<form:input path="task" />
														<form:errors path="task" cssClass="error" />
														<input type="submit" value="Add" class="add" />
													</form:form></td>
											</tr>
										</table>
									</td>
								</tr>
							</table>
						<td width="100px">
							<!--Right Side Menu  -->
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="25px">
				<!-- Footer  --> <jsp:include page="include/footer.jsp" />
			</td>
		<tr>
	</table>


</body>
</html>