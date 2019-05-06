<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Hotel Management System</title>
<!--Data picker code  -->
<link rel="stylesheet"
	href="//code.jquery.com/ui/1.12.1/themes/base/jquery-ui.css">

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
			<td height="340px">
				<!-- Page content table -->
				<table class="content">
					<td width="100px">
						<!--Left Side Menu  --> 
						<jsp:include
							page="include/adminLeftMenu.jsp" />
					</td>
					<td align="center">
						<!--Main content  --> 
						<div class="description">Please enter the user data:</div> 
						<form:form 	action="registerUser" modelAttribute="user" method="POST">
							<form:hidden path="id" />

							<table class="addGuestForm">
								<tbody >
									<tr>
										<td><label>First name: </label></td>
										<td><form:input path="firstName" /> <form:errors
												path="firstName" cssClass="error" /></td>
										<td />
										<td><label>Last name: </label></td>
										<td><form:input path="lastName" /> <form:errors
												path="lastName" cssClass="error" /></td>
									</tr>

									<tr>
										<td><label>Login: </label></td>
										<td><form:input path="login" /> <form:errors
												path="login" cssClass="error" /></td>
										<td />
										<td><label>Password: </label></td>
										<td><form:input path="password" /> <form:errors
												path="password" cssClass="error" /></td>
									</tr>
									<tr>
										<td />
										<td><label></label></td>
										<td><input type="submit" value="Register" class="save" /></td>

									</tr>		
								</tbody>
							</table>
						</form:form>
					</td>
					<td width="100px">
						<!--Right Side Menu  -->
						<jsp:include page="include/rightMenu.jsp"/>
					</td>
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