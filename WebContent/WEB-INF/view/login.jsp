<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page isELIgnored="false" %>
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
				<!-- HEADER  -->
				<jsp:include page="include/header.jsp"/>
			</td>
		</tr>
		<tr class="upperMenu">
			<td height="30px">
				<!-- Upper Menu  -->
				<jsp:include page="include/upperMenu.jsp"/>
			</td>
		</tr>
		<tr  align="centre">
			<td height="340px">
				<!-- Page content table -->
				<table class="content">
					<td width = "100px" >
						<!--Left Side Menu  -->
						
					</td>
					<td align="center" >
						<!--Main content  -->
						<!--table with rooms  -->
						
						<div class="error"><b>${loginError}</b></div>
						<form action = "/Hotel-Management-System/login/" method = "post">
						<table class="loginForm">
						<tr>
						<td align="left" class = "loginForm"><label >User name:</label> </td>
						<td><input type="text" id="login" name="login" placeholder="User Name" class="form-control"/></td>
						</tr>
						<tr>
						<td align="left" class = "loginForm"><label>Password:</label></td>
						<td><input type="password" id="password" name="password" placeholder="Password" class="form-control"/></td>
						</tr>
						<tr>
						<td align="center" colspan="4">
						<button id="loginButton" class="form-control">Login</button>
						</td>
						</tr>
						</table>
						</form>
						
					</td>
					<td width = "150px">
						<!--Right Side Menu  -->
						<jsp:include page="include/rightMenu.jsp"/>
					</td>
				</table>
			</td>
		</tr>
		<tr>
			<td height="25px">
				<!-- Footer  -->
				<jsp:include page="include/footer.jsp"/>
			</td>
		<tr>
	</table>
	

</body>
</html>