package com.rs.fer.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

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
@WebServlet("/expenseReport")
public class ExpenseReportServlet extends HttpServlet {

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

		// 1. copy ExpenseReportMain

		// 1.1 set the values by request.getparameter()
		String ExpenseType = request.getParameter("type");
		String fromDate = request.getParameter("fromDate");
		String toDate = request.getParameter("toDate");

		// 1.2 get userId by session object
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		// 2.call the service method for business logic
		List<Expense> expenses = ferService.getExpenseReport(userId, ExpenseType, fromDate, toDate);

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		if (expenses.isEmpty()) {
			out.println("Expenses not found....");
		} else {

			out.println("<table align='center' border='2'>");
			out.println("	<tr align='center'>");
			out.println("	  <th colspan='7'> Expense Report</th>");
			out.println("	</tr>");

			out.println("	<tr>");
			out.println("	  <th>Expense Type</th>");
			out.println("     <th>Date</th>");
			out.println("     <th>Price</th>");
			out.println("	  <th>No.of Items</th>");
			out.println("	  <th>Total</th>");
			out.println("	  <th>By Whom</th>");
			out.println("	</tr>");

			for (Expense expense : expenses) {
				out.println("<tr>");
				out.println("  <td>" + expense.getType() + "</td>");
				out.println("  <td>" + expense.getDate() + "</td>");
				out.println("  <td>" + expense.getPrice() + "</td>");
				out.println("  <td>" + expense.getNumberOfItems() + "</td>");
				out.println("  <td>" + expense.getTotal() + "</td>");
				out.println("  <td>" + expense.getByWhom() + "</td>");
				out.println("</tr>");
			}
			out.println("</table>");
		}

		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
