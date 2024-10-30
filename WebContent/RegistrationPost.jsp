<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
	User user = new User();
	user.setFirstName(request.getParameter("firstName"));
	user.setMiddleName(request.getParameter("middleName"));
	user.setLastName(request.getParameter("lastName"));
	user.setEmail(request.getParameter("email"));
	user.setUsername(request.getParameter("username"));
	user.setPassword(request.getParameter("password"));
	user.setMobile(request.getParameter("mobile"));
	
	boolean isRegister = ferService.registration(user);
	
	String path = null;
	if (isRegister) {
		request.setAttribute("registrationSuccess", true);
		path = "Login.jsp";
	} else {
		out.println("user registration is failed");
		path = "Registration.jsp";
	}
%>

<jsp:include page="<%=path%>" />