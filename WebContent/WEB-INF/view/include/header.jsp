<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<h1 class="header">Hotel Management System</h1>


<div class ="loginOut">
	<c:choose>
		<c:when test="${empty loggedInUser.firstName}">
			<a href="${pageContext.request.contextPath}/login" class ="loginOut">LOGIN</a>
		</c:when>
		<c:otherwise>
				WELCOME ${loggedInUser.firstName}
				<a href="${pageContext.request.contextPath}/logout" class ="loginOut">LOGOUT</a>
		</c:otherwise>
	</c:choose>
</div>