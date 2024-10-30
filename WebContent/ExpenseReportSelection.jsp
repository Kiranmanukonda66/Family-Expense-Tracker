<jsp:include page="Layout/Header_LeftFrame.jsp" />

<link rel="stylesheet" href="./CSS/FormStyle.css">

<script>
	function validateExpenseReportForm() {
		//1.Get the form object and instantiate the errors object
		var form = document.DashboardForm;
		var isValid = true;

		if (form.type.value.trim() === '') {
			form.type.classList.add("error");
			form.type.placeholder = "Please enter Expense Type";
			isValid = false;
		}

		if (form.fromDate.value.trim() === '') {
			form.fromDate.classList.add("error");
			form.fromDate.placeholder = "Please enter FromDate";
			isValid = false;
		}
		
		if (form.toDate.value.trim() === '') {
			form.toDate.classList.add("error");
			form.toDate.placeholder = "Please enter ToDate";
			isValid = false;
		}

		if (isValid) {
			submitForm('ExpenseReportPost.jsp');
		}
	}

	function removeError(input) {
		input.classList.remove("error");
		input.placeholder = ""; // Clear placeholder on focus
	}
</script>

<div class="form-container">
	<h2>Expense Report</h2>
	<div class="form-group">
		<label>Expense Type <font color='red'>*</font></label> <input
			type='text' name='type' placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>From Date<font color='red'>*</font></label> <input type='text'
			name='fromDate' placeholder="dd/mm/yyyy" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>To Date<font color='red'>*</font></label> <input type='text'
			name='toDate' placeholder="dd/mm/yyyy" onfocus="removeError(this)">
	</div>
	<div class="form-actions">
		<input type='button' value='Report'
			onclick="javascript: validateExpenseReportForm()">
	</div>
</div>

<jsp:include page="Layout/Footer.jsp" />