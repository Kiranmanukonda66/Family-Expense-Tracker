
<table>
<tr>
<td>
<form name='ExpenseReportForm' method='post'>
	<input type='hidden' name='expenseId' id="expenseId" value="">
	<input type='hidden' name='ExpenseType' value="<%=request.getParameter("ExpenseType") %>" >
	<input type='hidden' name='fromDate' value="<%=request.getParameter("fromDate") %>" >
	<input type='hidden' name='toDate' value="<%=request.getParameter("toDate") %>" >
</form>
</td>
</tr>
</table>