package com.rs.fer.servlet;

import java.io.IOException;

import java.io.PrintWriter;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.util.LayoutUtil;

@SuppressWarnings("serial")
@WebServlet("/showAddressInfo")
public class ShowAddressInfoServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// To handle the request
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		// 1.get the user oobject and address object to populate data
		User user = (User) session.getAttribute("user");
		Address address = user.getAddress();

		// 2.update name feilds
		user.setEmail(request.getParameter("email"));
		user.setMobile(request.getParameter("mobile"));

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		out.println("<table align='center' border='2'>");

		out.println("  <tr>");
		out.println("     <th align='center' colspan='2'>Address Information</th>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Line One</td>");
		out.println("     <td><input type='text' name='lineOne' value='" + address.getLineOne() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Line Two</td>");
		out.println("     <td><input type='text' name='lineTwo' value='" + address.getLineTwo() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>City</td>");
		out.println("     <td><input type='text' name='city' value='" + address.getCity() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>State</td>");
		out.println("     <td><input type='text' name='state' value='" + address.getState() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Pin Code</td>");
		out.println("     <td><input type='text' name='pinCode' value='" + address.getPincode() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Country</td>");
		out.println("     <td><input type='text' name='country' value='" + address.getCountry() + "'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("    <th align='center' colspan='2'>");
		out.println("      <input type='submit' value='Review' onclick=\"javascript: submitForm('review')\">");
		out.println("    </th>");
		out.println("  </tr>");

		out.println("</table>");
		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

}
