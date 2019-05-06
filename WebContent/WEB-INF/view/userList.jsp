<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
	pageEncoding="ISO-8859-1"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="s"%>


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
		<tr>
			<td height="340px">
				<!-- Page content table -->
				<table class="content">
					<td width = "100px" >
						<!--Left Side Menu  -->
						<jsp:include page="include/adminLeftMenu.jsp"/>
					</td>
					<td style="vertical-align: baseline ;" align="center">
						<!--Main content  -->
						<!--table with rooms  -->
						<table class="roomList">
						<tr>
						<div class="description"><br>Users registered:</div><br>
						</tr>
							<tr>
								<th class =  "list">First name</th>
								<th class =  "list">Last name</th>
								<th class =  "list">Login</th>
								<th class =  "list">Action</th>
							</tr>
							<c:forEach var="tempUser" items="${userList}">
								<c:url var="deleteLink" value="/administration/delete">
									<c:param name="tempUserId" value="${tempUser.id}" />
								</c:url>
								<c:url var="updateLink" value="/administration/updateUser">
									<c:param name="tempUserId" value="${tempUser.id}" />
								</c:url>
								
								<tr class =  "list">
									<td class =  "list">${tempUser.firstName}</td>
									<td class =  "list">${tempUser.lastName}</td>
									 <td class =  "list">${tempUser.login}</td> 
									 <td class =  "list"><a href="${deleteLink}">Delete</a> |
									 <a href="${updateLink}">Update</a></td>
								</tr>
							</c:forEach>
						</table>
					</td>
					<td width = "100px">
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