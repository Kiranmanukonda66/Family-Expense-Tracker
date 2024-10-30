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
@WebServlet("/resetPassword")
public class ResetPasswordServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// To hanlde the request
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		// 1. copy ResetPasswordMain
		// 1.1 get input fields by request.getParameter();
		String newPassword = request.getParameter("newPassword");
		String currentPassword = request.getParameter("currentPassword");

		// 1.2 get userId by session object
		int userId = Integer.parseInt(session.getAttribute("userId").toString());

		// 2.call the service method for business logic
		boolean isPasswordUpdate = ferService.resetPassword(newPassword, userId, currentPassword);

		// 3.show the status in body
		// 3.1 header and footer
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		if (isPasswordUpdate) {
			out.println("Password Updated successfully....");
		} else {
			out.println("Password Update is failed");
		}

		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
