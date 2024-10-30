<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
	// 1. copy DeleteExpenseMain
	
	// 1.1 get expenseId by session object
	int expenseId = Integer.parseInt(request.getParameter("expenseId"));
	
	// 2.call the service method for business logic
	boolean isDeleteExpense = ferService.deleteExpense(expenseId);
	
	// 3.show the status
	
	// 3.2 body
	if (isDeleteExpense) {
		session.setAttribute("status", "Expense deleted successfully....");
	} else {
		session.setAttribute("status", "Expense delete is failed");
	}
%>

<jsp:include page="DashBoard.jsp" />