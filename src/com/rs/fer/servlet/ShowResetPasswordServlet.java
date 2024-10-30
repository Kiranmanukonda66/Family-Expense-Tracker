package com.rs.fer.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.rs.fer.util.LayoutUtil;

@SuppressWarnings("serial")
@WebServlet("/showResetPassword")
public class ShowResetPasswordServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		HttpSession session = request.getSession();

		PrintWriter out = response.getWriter();
		// 1 header and footer
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 2 body
		request.getRequestDispatcher("ResetPassword.html").include(request, response);

		// 3 footer
		LayoutUtil.showFooter(request, response);

	}

}
