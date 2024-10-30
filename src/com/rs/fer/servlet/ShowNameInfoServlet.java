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
@WebServlet("/showNameInfo")
public class ShowNameInfoServlet extends HttpServlet {

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
		User user = ferService.getUser(userId);
		session.setAttribute("user", user);

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		out.println("<table align='center' border='2'>");

		out.println("  <tr>");
		out.println("     <th align='center' colspan='2'>Personal Information</th>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>First Name</td>");
		out.println("     <td><input type='text' name='firstName' value='" + user.getFirstName() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Middle Name</td>");
		out.println("     <td><input type='text' name='middleName' value='" + user.getMiddleName() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Last Name</td>");
		out.println("     <td><input type='text' name='lastName' value='" + user.getLastName() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("    <th align='center' colspan='2'>");
		out.println("      <input type='submit' value='Next' onclick=\"javascript: submitForm('showContactInfo')\">");
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
