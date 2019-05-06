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
<script src="https://code.jquery.com/jquery-1.12.4.js"></script>
<script src="https://code.jquery.com/ui/1.12.1/jquery-ui.js"></script>
<script>
  $( function() {
    $( "#datepicker" ).datepicker({ dateFormat: "yy-mm-dd" });
  } );
  

  </script>

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
							page="include/guestLeftMenu.jsp" />
					</td>
					<td align="center">
						<!--Main content  --> 
						<!--Add guest form  -->
						<div class="description">Please enter the guest data:</div> 
						<form:form 	action="saveGuest" modelAttribute="guest" method="POST">
							<form:hidden path="id" />
							<table class="addGuestForm">
								<tbody >
									<tr>
										<td><label>First name:</label></td>
										<td><form:input path="firstName" /> <form:errors path="firstName" cssClass="error" /></td>
										<td />
										<td><label>Last name:</label></td>
										<td><form:input path="lastName" /> <form:errors path="lastName" cssClass="error" /></td>
									</tr>

									<tr>
										<td><label>ID number:</label></td>
										<td><form:input path="idNumber" /> <form:errors path="idNumber" cssClass="error" /></td>
										<td />
										<td><label>Phone number:</label></td>
										<td><form:input path="phoneNumber" /> <form:errors path="phoneNumber" cssClass="error" /></td>
									</tr>

									<tr>
										<td><label>Room:</label></td>
										<td><form:select path="room">
												<form:option value="${selectedRoom.getId()}" label="${selectedRoom.getStandard()} ${selectedRoom.getNumber()}" />
												<form:options items="${roomsMap}" />
												<form:hidden path="id" />
											</form:select> <form:errors path="idNumber" cssClass="error" /></td>
										<td/>
										<td><label>Checkout date:</label></td>
										<td><form:input path="checkoutDate" id="datepicker" /> <form:errors path="checkoutDate" cssClass="error" /></td>
									</tr>
									<tr>
										<br>
									<tr>
										<td />
										<td><label></label></td>
										<td><input type="submit" value="Save" class="save" /></td>
									</tr>
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