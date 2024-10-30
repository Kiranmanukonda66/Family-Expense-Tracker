<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
	String username = request.getParameter("username");
String password = request.getParameter("password");

User user = ferService.login(username, password);

String path = null;
if (user != null) {
	if (user.getId() > 0) {

		session.setAttribute("username", username);
		session.setAttribute("userId", user.getId());
		session.setAttribute("uploadedImage", user.getFileName());

		session.setAttribute("status", "Welcome to user : " + username + "....!");

		path = "DashBoard.jsp";

	}
} else {
	request.setAttribute("message", "You have entered incorrect username or password, please try again.....!");
	out.println("<BR>");
	out.println("<BR>");
	out.println("<BR>");

	path = "Login.jsp";
}
%>

<jsp:include page="<%=path%>" />