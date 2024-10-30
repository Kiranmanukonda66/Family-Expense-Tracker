<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
	// 1. copy ResetPasswordMain
	// 1.1 get input fields by request.getParameter();
	String newPassword = request.getParameter("newPassword");
	String currentPassword = request.getParameter("currentPassword");
	
	// 1.2 get userId by session object
	int userId = Integer.parseInt(session.getAttribute("userId").toString());
	
	// 2.call the service method for business logic
	boolean isPasswordUpdate = ferService.resetPassword(newPassword, userId, currentPassword);
	
	// 3.show the status in body
	// 3.1 header and footer
	
	// 3.2 body
	if (isPasswordUpdate) {
		session.setAttribute("status", "Password Updated successfully....");
	} else {
		session.setAttribute("status", "Password Update is failed");
	}
%>

<jsp:include page="DashBoard.jsp" />