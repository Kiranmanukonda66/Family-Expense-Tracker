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

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@SuppressWarnings("serial")
@WebServlet("/deleteExpense")
public class DeleteExpenseServlet extends HttpServlet {

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

		// 1. copy DeleteExpenseMain

		// 1.1 get expenseId by session object
		int expenseId = Integer.parseInt(request.getParameter("expenseId"));

		// 2.call the service method for business logic
		boolean isDeleteExpense = ferService.deleteExpense(expenseId);

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		if (isDeleteExpense) {
			out.println("Expense deleted successfully....");
		} else {
			out.println("Expense delete is failed");
		}

		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
