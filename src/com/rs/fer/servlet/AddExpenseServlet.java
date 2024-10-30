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
@WebServlet("/addExpense")
public class AddExpenseServlet extends HttpServlet {

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

		// 1. copy AddExpenseMain
		// 1.1 get input fields by request.getParameter();
		Expense expense = new Expense();
		expense.setType(request.getParameter("type"));
		expense.setDate(request.getParameter("date"));
		expense.setPrice(Float.parseFloat(request.getParameter("price")));
		expense.setNumberOfItems(Integer.parseInt(request.getParameter("numberOfItems")));
		expense.setTotal(Float.parseFloat(request.getParameter("total")));
		expense.setByWhom(request.getParameter("byWhom"));

		// 1.2 get userId by session object
		int userId = Integer.parseInt(session.getAttribute("userId").toString());
		expense.setUserId(userId);

		// 2.call the service method for business logic
		boolean isAddExpense = ferService.addExpense(expense);

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		if (isAddExpense) {
			out.println("Expense Added successfully....");
		} else {
			out.println("Expense Add is failed");
		}

		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
