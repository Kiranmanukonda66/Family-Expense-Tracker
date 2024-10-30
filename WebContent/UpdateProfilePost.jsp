<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
	// 1.get the user from session
	User user = (User) session.getAttribute("user");
	
	// 2.call the service method for business logic
	boolean isUserUpdate = ferService.updateUser(user);
	
	// 3.show the status
	
	// 3.2 body
	if (isUserUpdate) {
		session.setAttribute("status", "Profile Updated successfully....");
	} else {
		session.setAttribute("status", "Profile Update is failed");
	}
	
	session.removeAttribute("user");
%>

<jsp:include page="DashBoard.jsp" />