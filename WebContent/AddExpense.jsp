<jsp:include page="Layout/Header_LeftFrame.jsp" />

<link rel="stylesheet" href="./CSS/FormStyle.css">

<script>
	function validateAddExpenseForm() {
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
			submitForm('AddExpensePost.jsp');
		}
	}

	function removeError(input) {
		input.classList.remove("error");
		input.placeholder = ""; // Clear placeholder on focus
	}
</script>

<div class="form-container">
	<h2>Add Expense</h2>
	<div class="form-group">
		<label>Expense Type <font color='red'>*</font></label> <input
			type='text' name='type' placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Date <font color='red'>*</font></label> <input type='text'
			name='date' placeholder="dd/mm/yyyy" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Price <font color='red'>*</font></label> <input type='text'
			name='price' placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Number Of Items <font color='red'>*</font></label> <input
			type='text' name='numberOfItems' placeholder=""
			onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>Total <font color='red'>*</font></label> <input type='text'
			name='total' placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-group">
		<label>By Whom <font color='red'>*</font></label> <input type='text'
			name='byWhom' placeholder="" onfocus="removeError(this)">
	</div>
	<div class="form-actions">
		<input type='button' value='Save Expense'
			onclick="javascript: validateAddExpenseForm()">
	</div>
</div>

<jsp:include page="Layout/Footer.jsp" />