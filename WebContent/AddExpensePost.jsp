<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
		Expense expense = new Expense();
	expense.setType(request.getParameter("type"));
	expense.setDate(request.getParameter("date"));
	expense.setPrice(Float.parseFloat(request.getParameter("price")));
	expense.setNumberOfItems(Integer.parseInt(request.getParameter("numberOfItems")));
	expense.setTotal(Float.parseFloat(request.getParameter("total")));
	expense.setByWhom(request.getParameter("byWhom"));
	
	// 1.2 get userId by session object
	int userId = Integer.parseInt(session.getAttribute("userId").toString());
	expense.setUserId(userId);
	
	// 2.call the service method for business logic
	boolean isAddExpense = ferService.addExpense(expense);
	
	// 3.show the status
	
	// 3.2 body
	if (isAddExpense) {
		session.setAttribute("status", "Expense Added successfully....");
	} else {
		session.setAttribute("status", "Expense Add is failed");
	}
%>

<jsp:include page="DashBoard.jsp" />