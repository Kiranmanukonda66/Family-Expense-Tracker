<%@page import="java.util.List"%>
<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<jsp:include page="Layout/Header_LeftFrame.jsp" />

<link rel="stylesheet" href="./CSS/ExpenseOptionsStyle.css">

<script>
	function validateEditExpenseOptionsForm() {
		// Get the form object and instantiate the errors object
		var form = document.DashboardForm;
		var error = '';

		// Add the error to the errors object if input field is empty
		if (form.expenseId.value.trim() == '') {
			error = "Please Select An Expense to Edit";
			document.getElementById("error-message").innerText = error; // Show error message
		} else {
			document.getElementById("error-message").innerText = ""; // Clear error
			submitForm('EditExpense.jsp');
		}
	}
</script>

<%
	// 1.1 get userId by session object
int userId = Integer.parseInt(session.getAttribute("userId").toString());

// 2.call the service method for business logic
List<Expense> expenses = ferService.getExpenseOptions(userId);

// 3.show the status

// 3.1 header and leftFrame

// 3.2 body
if (expenses.isEmpty()) {
	out.println("Expenses not found....");
} else {
	out.println("<div id='error-message'></div>");
	out.println("<div class='expense-selection'>"); // New flex container
	out.println("Expense Id&nbsp; <font color='red'>*</font>&nbsp; :"); // static
	out.println("&nbsp;&nbsp;&nbsp;");

	out.println("<select name='expenseId' id='select'>");
	out.println("<option value='' id='option'>Please select the Expense Id</option>");

	int value = 0;
	String text = null;
	for (Expense expense : expenses) {
		value = expense.getId();
		text = value + "--" + expense.getType() + "--" + expense.getDate() + "--" + expense.getTotal() + "--"
		+ expense.getByWhom();
		out.println("<option value='" + value + "'>" + text + "</option>");
	}
	out.println("</select>");
	out.println("&nbsp;&nbsp;&nbsp;");

	out.println("<input type='button' value='Next' onclick=\"javascript: validateEditExpenseOptionsForm()\">"); // static
	out.println("</div>");
}
%>

<jsp:include page="Layout/Footer.jsp" />
