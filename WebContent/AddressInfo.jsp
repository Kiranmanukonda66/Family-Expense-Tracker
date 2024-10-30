<%@page import="com.rs.fer.bean.Address"%>
<%@page import="com.rs.fer.bean.User"%>
<%@page import="java.util.List"%>
<%@page import="java.util.ArrayList"%>

<jsp:include page="Layout/Header_LeftFrame.jsp" />



<script>

	function addRow() {
		var table = document.getElementById("addressTable");
		var newRow = table.insertRow(-1);

		var lineOne = newRow.insertCell(0);
		var type = newRow.insertCell(1);
		var lineTwo = newRow.insertCell(2);
		var city = newRow.insertCell(3);
		var state = newRow.insertCell(4);
		var pinCode = newRow.insertCell(5);
		var country = newRow.insertCell(6);
		var deleteRow = newRow.insertCell(7);

		lineOne.innerHTML = "<input type='text' name='lineOne' size='7'>";
		type.innerHTML = "<select name='type'><option value='Home' size='7'>Home</option><option value='Work' size='7'>Work</option></select>";
		lineTwo.innerHTML = "<input type='text' name='lineTwo' size='7'>";
		city.innerHTML = "<input type='text' name='city' size='7'>";
		state.innerHTML = "<input type='text' name='state' size='7'>";
		pinCode.innerHTML = "<input type='text' name='pinCode' size='7'>";
		country.innerHTML = "<input type='text' name='country' size='7'>";
		deleteRow.innerHTML = "<a href='javascript:void(0);' onclick='deleteRow(this)' size='7'>Delete</a>";
	}

	function deleteRow(link) {

		var table = document.getElementById("addressTable");
		var rowCount = table.rows.length - 3; // Subtract 3 to exclude header rows

		if (rowCount <= 1) {
			var td = document.getElementById("tdElement");
			td.innerHTML = "user must have at least one address.";
			var tr = document.getElementById("trElement");
			tr.style.display = '';
			return;
		}

		var row = link.parentNode.parentNode;

		row.parentNode.removeChild(row);
	}

	function validateAddressInfoForm() {

		var lineOneFields = document.getElementsByName('lineOne');
		var cityFields = document.getElementsByName('city');
		var stateFields = document.getElementsByName('state');
		var pinCodeFields = document.getElementsByName('pinCode');
		var countryFields = document.getElementsByName('country');

		var errors = '';

		for (var i = 0; i < lineOneFields.length; i++) {
			if (lineOneFields[i].value.trim() == '') {
				errors += "Please enter Line One for address " + (i + 1)
						+ "<br>";
			}
			if (cityFields[i].value.trim() == '') {
				errors += "Please enter City for address " + (i + 1) + "<br>";
			}
			if (stateFields[i].value.trim() == '') {
				errors += "Please enter State for address " + (i + 1) + "<br>";
			}
			if (pinCodeFields[i].value.trim() == '') {
				errors += "Please enter Pincode for address " + (i + 1)
						+ "<br>";
			}
			if (countryFields[i].value.trim() == '') {
				errors += "Please enter Country for address " + (i + 1)
						+ "<br>";
			}
		}

		if (errors != '') {
			var td = document.getElementById("tdElement");
			td.innerHTML = errors;
			var tr = document.getElementById("trElement");
			tr.style.display = '';
		} else {
			submitForm('Review.jsp');
		}
	}
</script>

<%
	// Retrieve user object and address list
	User user = (User) session.getAttribute("user");
	
	user.setEmail(request.getParameter("email"));
	user.setMobile(request.getParameter("mobile"));
	
	List<Address> addresses = user.getAddresses();
	
	if (addresses == null) {
	
		addresses = new ArrayList<Address>();
		// Add a default empty address if the list is empty
		addresses.add(new Address());
	}
	
	out.println("<table align='center' border='2' width='600px' id='addressTable'>");
	out.println("  <tr>");
	out.println("    <th align='center' colspan='8' >Address Information</th>");
	out.println("  </tr>");
	
	out.println("  <tr style='display: none; color: red' id='trElement'>");
	out.println("    <td colspan='8' id='tdElement'></td>");
	out.println("  </tr>");
	
	out.println("  <tr>");
	out.println("    <td size='6'>Line One <font color='red'>*</font></td>");
	out.println("    <td size='6'>Type</td>");
	out.println("    <td size='6'>Line Two </td>");
	out.println("    <td size='6'>City <font color='red'>*</font></td>");
	out.println("    <td size='6'>State <font color='red'>*</font></td>");
	out.println("    <td size='6'>Pincode <font color='red'>*</font></td>");
	out.println("    <td size='6'>Country <font color='red'>*</font></td>");
	out.println("    <td size='6'>Action</td>");
	out.println("  </tr>");
	
	// Loop through the list of addresses
	for (Address address : addresses) {
		out.println("<tr>");
		out.println("  <td ><input type='text' name='lineOne' value='" + address.getLineOne() + "' size='7'></td>");
		out.println("  <td >");
		out.println("    <select name='type'>");
		out.println("      <option value='Home'" + ("Home".equals(address.getType()) ? "selected" : "")+ " size='7'>Home</option>");
		out.println("      <option value='Work'" + ("Work".equals(address.getType()) ? "selected" : "")+ " size='7'>Work</option>");
		out.println("    </select>");
		out.println("  </td>");
		out.println("  <td ><input type='text' name='lineTwo' value='" + address.getLineTwo() + "' size='7'></td>");
		out.println("  <td ><input type='text' name='city' value='" + address.getCity() + "' size='7'></td>");
		out.println("  <td ><input type='text' name='state' value='" + address.getState() + "' size='7'></td>");
		out.println("  <td ><input type='text' name='pinCode' value='" + address.getPincode() + "' size='7'></td>");
		out.println("  <td ><input type='text' name='country' value='" + address.getCountry() + "' size='7'></td>");
		out.println("  <td ><a href='javascript:void(0);' onclick='deleteRow(this)' size='7'>Delete</a></td>");
		out.println("</tr>");
	}
	
	out.println("</table>");
	out.println(
			"<div align='center'><input type='button' value='Review' onclick=\"javascript: validateAddressInfoForm()\"></div>");
	out.println("<a href='javascript:addRow()' size='7'>Add</a>");
	out.println("</form>");
%>

<jsp:include page="Layout/Footer.jsp" />
