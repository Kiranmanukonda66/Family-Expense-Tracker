<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<%
	// 1.1 get input fields by request.getParameter();
	Expense expense = new Expense();
	expense.setType(request.getParameter("type"));
	expense.setDate(request.getParameter("date"));
	expense.setPrice(Float.parseFloat(request.getParameter("price")));
	expense.setNumberOfItems(Integer.parseInt(request.getParameter("numberOfItems")));
	expense.setTotal(Float.parseFloat(request.getParameter("total")));
	expense.setByWhom(request.getParameter("byWhom"));
	
	// 1.2 get userId by session object
	int expenseId = Integer.parseInt(session.getAttribute("expenseId").toString());
	expense.setId(expenseId);
	
	// 2.call the service method for business logic
	boolean isEditExpense = ferService.editExpense(expense);
	
	// 3.show the status
	
	// 3.2 body
	if (isEditExpense) {
		session.setAttribute("status", "Expense updated successfully....");
	} else {
		session.setAttribute("status", "Expense update is failed");
	}
	
	session.removeAttribute("expenseId");
%>

<jsp:include page="DashBoard.jsp" />