<jsp:include page="Layout/Header_LeftFrame.jsp" />

<link rel="stylesheet" href="./CSS/ExpenseOptionsStyle.css">

<script>
	function validateUploadImageForm() {
		var form = document.DashboardForm;
		var fileInput = document.getElementsByName('fname')[0];
		var errorElement = document.getElementById("error-message");
		errorElement.innerHTML = '';

		var maxFileSize = 2 * 1024 * 1024;
		var allowedTypes = ['image/jpeg', 'image/png', 'image/gif'];

		if (fileInput.value === '' || fileInput.value === null) {
			errorElement.innerHTML = "Please select an image.";
		} else if (!allowedTypes.includes(fileInput.files[0].type)) {
			errorElement.innerHTML = "Only JPEG, PNG, and GIF images are allowed.";
		} else if (fileInput.files[0].size > maxFileSize) {
			errorElement.innerHTML = "File size exceeds the maximum limit of 2MB.";
		} else {
			submitForm('UploadImagePost.jsp');
		}
	}
</script>
<div id="error-message"></div>
<div class="expense-selection" style="margin-top: 20px;">
	<label>Select File:&nbsp;</label>
	<input type="file" name="fname" />
</div>

<div class="form-actions" style="margin-top: 20px;">
	<input type="button" onclick="validateUploadImageForm()" value="Upload Image">
</div>

<jsp:include page="Layout/Footer.jsp" />
