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

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;
import com.rs.fer.util.LayoutUtil;

@SuppressWarnings("serial")
@WebServlet("/login")
public class LoginServlet extends HttpServlet {

	FERService ferService = null;

	@Override
	public void init(ServletConfig config) throws ServletException {
		ferService = new FERServiceImpl();
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		String username = request.getParameter("username");
		String password = request.getParameter("password");

		User user = ferService.login(username, password);

		PrintWriter out = response.getWriter();
		if (user.getId()>0) {

			HttpSession session = request.getSession();
			session.setAttribute("username", username);
			session.setAttribute("userId", user.getId());

			// 1 header and footer
			LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);
			// 2 body
			out.println("Welcome to user : " + username + "....!");
			// 3 footer
			LayoutUtil.showFooter(request, response);

		} else {
			out.println("You have entered incorrect username or password, please try again.....!");
			out.println("<BR>");
			out.println("<BR>");
			out.println("<BR>");
			request.getRequestDispatcher("Login.html").include(request, response);
		}

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
