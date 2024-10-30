<%@page import="java.util.List"%>
<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<jsp:include page="Layout/Header_LeftFrame.jsp" />
<link rel="stylesheet" href="./CSS/FormStyle.css">

<script>
	function deleteRow(expenseId, link) {
		if (confirm("Are you sure you want to delete this expense?")) {
			var form = document.createElement("form");
			form.method = "post";
			form.action = "<%=request.getRequestURI()%>";

			var input = document.createElement("input");
			input.type = "hidden";
			input.name = "expenseId"; 
			input.value = expenseId;

			form.appendChild(input);
			document.body.appendChild(form);
			form.submit();

			var row = link.parentNode.parentNode;
			row.parentNode.removeChild(row);
		}
	}
</script>

<%
	String expenseIdParam = request.getParameter("expenseId");
	if (expenseIdParam != null) {
		int expenseId = Integer.parseInt(expenseIdParam);
		ferService.deleteExpense(expenseId); 
	}
	
	String ExpenseType = null;
	String fromDate = null;
	String toDate = null;
	if(request.getParameter("type") != null) {
		ExpenseType = request.getParameter("type");
		fromDate = request.getParameter("fromDate");
		toDate = request.getParameter("toDate");
		
		session.setAttribute("type", ExpenseType);
		session.setAttribute("fromDate", fromDate);
		session.setAttribute("toDate", toDate);
	} else {
		ExpenseType = session.getAttribute("type").toString();
		fromDate = session.getAttribute("fromDate").toString();
		toDate = session.getAttribute("toDate").toString();
	}
	
	int userId = Integer.parseInt(session.getAttribute("userId").toString());
	List<Expense> expenses = ferService.getExpenseReport(userId, ExpenseType, fromDate, toDate);
%>

<div class="form-container">
	<h2>Expense Report</h2>
	<%
		if (expenses.isEmpty()) {
			out.println("<p>Expenses not found....</p>");
		} else {
	%>
	<div class="expense-report">
		<div class="expense-report-header">
			<div class="expense-header-item">Expense Type</div>
			<div class="expense-header-item">Date</div>
			<div class="expense-header-item">Price</div>
			<div class="expense-header-item">No. of Items</div>
			<div class="expense-header-item">Total</div>
			<div class="expense-header-item">By Whom</div>
			<div class="expense-header-item">Action</div>
		</div>
		<%
			for (Expense expense : expenses) {
		%>
		<div class="expense-report-row">
			<div class="expense-item"><%= expense.getType() %></div>
			<div class="expense-item"><%= expense.getDate() %></div>
			<div class="expense-item"><%= expense.getPrice() %></div>
			<div class="expense-item"><%= expense.getNumberOfItems() %></div>
			<div class="expense-item"><%= expense.getTotal() %></div>
			<div class="expense-item"><%= expense.getByWhom() %></div>
			<div class="expense-item">
				<a href='<%=request.getContextPath()%>/EditExpense.jsp?expenseId=<%=expense.getId()%>'>Edit</a>
				<a href='javascript:void(0);' onclick='deleteRow(<%=expense.getId()%>, this);'>Delete</a>
			</div>
		</div>
		<%
			}
		%>
	</div>
	<%
		}
	%>
</div>

<jsp:include page="Layout/Footer.jsp" />
