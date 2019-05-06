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
				<table class="content">
					<tr>
						<td width="100px">
							<!--Left Side Menu  --> 
							<jsp:include page="include/guestLeftMenu.jsp"/>
						</td>
						<td>
							<!--Main content  --> 
								<c:url var="checkoutLink" value="/guest/checkout">
								<c:param name="guestId" value="${guest.id}" />
								</c:url>
								<c:url var="printLink" value="/guest/bill/save?guestId=${guest.id}">
								<c:param name="guestId" value="${guest.id}" />
								</c:url>
								
							<table style="vertical-align: top;" align="center" class = "billTable">
								<tr >
									<td colspan="4"><div class="description">Details:</div></td>
								</tr>
								<tr >
									<td>First Name: </td>
									<td>${guest.firstName}</td>
								</tr>

								<tr >
									<td>Last Name: </td>
									<td>${guest.lastName}</td>
								</tr>

								<tr>
									<td>Room number: </td>
									<td>${room.number}</td>
								</tr>

								<tr>
									<td>Room standard: </td>
									<td>${room.standard}</td>
								</tr>
								<tr>
									<td>Rate: </td>
									<td>${rate} $</td>
								</tr>
								<tr>
									<td>Number of nights: </td>
									<td>${nightsNumber}</td>
								</tr>
								<tr>
									<td>Tax (24% VAT): </td>
									<td>${tax} $</td>
								</tr>
								<tr>
									<td>Total: </td>
									<td>${total} $</td>
								</tr>
								<tr>
									<td>Checkout status: </td>
									<td><c:choose>
											<c:when test="${guest.isCheckedout}">
       											Checked out
											</c:when>
											<c:otherwise>
    										<b>NOT</b> Checked out (<a href="${checkoutLink}">Checkout</a>)
											</c:otherwise>
										</c:choose></td>
								</tr>
								<tr>
								<td> 
								<form action="${pageContext.request.contextPath}/guest/bill/save" method="post">
								<input type="hidden" name="guestId" value="${guest.id}" />
							    <input type="submit" name="save" value="Save" />	    
								</form>
								</td>		
								<td> 
								<form action="${pageContext.request.contextPath}/guest/bill/mail" method="post">
								<input type="hidden" name="guestId" value="${guest.id}" />
								<input type="text" name="email" placeholder="E-mail adress"/>
							    <input type="submit" name="mail" value="Send" />	    
								</form>
								</td>
								</tr>	
							</table><td width="100px">
							<!--Right Side Menu  --> 
							<jsp:include page="include/rightMenu.jsp" />
						</td>
					</tr>
				</table>
			</td>
		</tr>
		<tr>
			<td height="25px">
				<!-- Footer  --> 
				<jsp:include page="include/footer.jsp" />
			</td>
		<tr>
	</table>


</body>
</html>