package com.rs.fer.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.rs.fer.bean.User;
import com.rs.fer.util.LayoutUtil;

@SuppressWarnings("serial")
@WebServlet("/showContactInfo")
public class ShowContactInfoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// To handle the request
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		// 1.get the user oobject
		User user = (User) session.getAttribute("user");

		// 2.update name feilds
		user.setFirstName(request.getParameter("firstName"));
		user.setMiddleName(request.getParameter("middleName"));
		user.setLastName(request.getParameter("lastName"));

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		out.println("<table align='center' border='2'>");

		out.println("  <tr>");
		out.println("     <th align='center' colspan='2'>Contact Information</th>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Email ID</td>");
		out.println("     <td><input type='text' name='email' value='" + user.getEmail() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Mobile</td>");
		out.println("     <td><input type='text' name='mobile' value='" + user.getMobile() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("    <th align='center' colspan='2'>");
		out.println("      <input type='submit' value='Next' onclick=\"javascript: submitForm('showAddressInfo')\">");
		out.println("    </th>");
		out.println("  </tr>");

		out.println("</table>");
		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

}
