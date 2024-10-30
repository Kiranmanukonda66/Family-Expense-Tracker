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
@WebServlet("/updateProfile")
public class UpdateProfileServlet extends HttpServlet {

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

		// 1.get the user from session
		User user = (User) session.getAttribute("user");

		// 2.call the service method for business logic
		boolean isUserUpdate = ferService.updateUser(user);

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		if (isUserUpdate) {
			out.println("Profile Updated successfully....");
		} else {
			out.println("Profile Update is failed");
		}

		// 3.3 footer
		LayoutUtil.showFooter(request, response);

		session.removeAttribute("user");

	}

	@Override
	public void destroy() {
		ferService = null;
	}

}
