package com.rs.fer.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@SuppressWarnings("serial")
@WebServlet("/showEditExpense")
public class ShowEditExpenseServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// To handle the request
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		// 1. copy GetExpenseOptionsMain

		// 1.1 get userId by session object
		int expenseId = Integer.parseInt(request.getParameter("expenseId"));
		session.setAttribute("expenseId", expenseId);

		// 2.call the service method for business logic
		Expense expense = ferService.getExpense(expenseId);

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		out.println("<table align='center' border='2'>");

		out.println("  <tr>");
		out.println("     <th align='center' colspan='2'>Edit Expense</th>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Expense Type</td>");
		out.println("     <td><input type='text' name='type' value='" + expense.getType() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Date</td>");
		out.println("     <td><input type='text' name='date' value='" + expense.getDate() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Price</td>");
		out.println("     <td><input type='text' name='price' value='" + expense.getPrice() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Number Of Items</td>");
		out.println(
				"     <td><input type='text' name='numberOfItems' value='" + expense.getNumberOfItems() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Total</td>");
		out.println("     <td><input type='text' name='total' value='" + expense.getTotal() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>By Whom</td>");
		out.println("     <td><input type='text' name='byWhom' value='" + expense.getByWhom() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("    <th align='center' colspan='2'>");
		out.println(
				"      <input type='submit' value='Edit Expense' onclick=\"javascript: submitForm('editExpense')\">");
		out.println("    </th>");
		out.println("  </tr>");

		out.println("</table>");
		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
