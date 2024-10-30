<%@page import="com.rs.fer.bean.Expense"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<%!FERService ferService = new FERServiceImpl();%>

<jsp:include page="Layout/Header_LeftFrame.jsp" />

<link rel="stylesheet" href="./CSS/FormStyle.css">

<script>
	function validateEditExpenseForm() {
		//1.Get the form object and instantiate the errors object
		var form = document.DashboardForm;
		var isValid = true;

		if (form.type.value.trim() === '') {
			form.type.classList.add("error");
			form.type.placeholder = "Please enter Expense Type";
			isValid = false;
		}

		if (form.date.value.trim() === '') {
			form.date.classList.add("error");
			form.date.placeholder = "Please enter Date";
			isValid = false;
		}

		if (form.price.value.trim() === '') {
			form.price.classList.add("error");
			form.price.placeholder = "Please enter Price";
			isValid = false;
		}

		if (form.numberOfItems.value.trim() === '') {
			form.numberOfItems.classList.add("error");
			form.numberOfItems.placeholder = "Please enter Number Of Items";
			isValid = false;
		}

		if (form.total.value.trim() === '') {
			form.total.classList.add("error");
			form.total.placeholder = "Please enter total";
			isValid = false;
		}

		if (form.byWhom.value.trim() === '') {
			form.byWhom.classList.add("error");
			form.byWhom.placeholder = "Please enter By Whom";
			isValid = false;
		}

		if (isValid) {
			submitForm('EditExpensePost.jsp');
		}
	}

	function removeError(input) {
		input.classList.remove("error");
		input.placeholder = ""; // Clear placeholder on focus
	}
</script>

<%
	// 1.1 get userId by session object
int expenseId = Integer.parseInt(request.getParameter("expenseId"));
session.setAttribute("expenseId", expenseId);

// 2.call the service method for business logic
Expense expense = ferService.getExpense(expenseId);
%>
<div class="form-container">
	<h2>Edit Expense</h2>
	<div class="form-group">
		<label>Expense Type <font color='red'>*</font></label> <input
			type='text' name='type' value='<%=expense.getType()%>'
			placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Date <font color='red'>*</font></label> <input type='text'
			name='date' value='<%=expense.getDate()%>' placeholder="dd/mm/yyyy"
			onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Price <font color='red'>*</font></label> <input type='text'
			name='price' value='<%=expense.getPrice()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Number Of Items <font color='red'>*</font></label> <input
			type='text' name='numberOfItems'
			value='<%=expense.getNumberOfItems()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Total <font color='red'>*</font></label> <input type='text'
			name='total' value='<%=expense.getTotal()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>By Whom <font color='red'>*</font></label> <input type='text'
			name='byWhom' value='<%=expense.getByWhom()%>' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-actions">
		<input type='button' value='Edit Expense'
			onclick="javascript: validateEditExpenseForm()">
	</div>
</div>

<jsp:include page="Layout/Footer.jsp" />