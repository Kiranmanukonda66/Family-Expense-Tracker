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
@WebServlet("/showDeleteExpenseOptions")
public class ShowDeleteExpenseOptionsServlet extends HttpServlet {

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
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		// 2.call the service method for business logic
		List<Expense> expenses = ferService.getExpenseOptions(userId);

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		if (expenses.isEmpty()) {
			out.println("Expenses not found....");
		} else {

			out.println("Expense Id:");// static
			out.println("&nbsp;&nbsp;&nbsp;");
			out.println(" <select name='expenseId'>");
			out.println(" <option value=''>Please select the Expesne Id</option>");
			int value = 0;
			String text = null;
			for (Expense expense : expenses) {
				value = expense.getId();
				text = value + "--" + expense.getType() + "--" + expense.getDate() + "--" + expense.getTotal() + "--"
						+ expense.getByWhom();
				out.println(" <option value='" + value + "'>" + text + "</option>");
			}
			out.println(" </select>");
			out.println(" &nbsp;&nbsp;&nbsp;");
			out.println("<input type='submit' value='Delete' onclick=\"javascript: submitForm('deleteExpense')\">");// static
		}

		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
