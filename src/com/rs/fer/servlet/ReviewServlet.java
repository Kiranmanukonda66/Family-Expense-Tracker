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
@WebServlet("/review")
public class ReviewServlet extends HttpServlet {

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {

		// To handle the request
		HttpSession session = request.getSession();
		PrintWriter out = response.getWriter();

		// 1.get the user and address object update address
		User user = (User) session.getAttribute("user");
		Address address = user.getAddress();
		address.setLineOne(request.getParameter("lineOne"));
		address.setLineTwo(request.getParameter("lineTwo"));
		address.setCity(request.getParameter("city"));
		address.setState(request.getParameter("state"));
		address.setCountry(request.getParameter("country"));
		address.setPincode(request.getParameter("pinCode"));

		// 3.show the status

		// 3.1 header and leftFrame
		LayoutUtil.showHeaderAndLeftFrame(request, response, out, session);

		// 3.2 body
		out.println("<table align='center' border='2'>");

		out.println("  <tr>");
		out.println("     <th align='center' colspan='2'>Confirm your details to update</th>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>First Name</td>");
		out.println("     <td><input type='text' name='firstName' value='" + user.getFirstName()
				+ "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Middle Name</td>");
		out.println("     <td><input type='text' name='middleName' value='" + user.getMiddleName()
				+ "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Last Name</td>");
		out.println(
				"     <td><input type='text' name='lastName' value='" + user.getLastName() + "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Email ID</td>");
		out.println("     <td><input type='text' name='email' value='" + user.getEmail() + "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Mobile</td>");
		out.println("     <td><input type='text' name='mobile' value='" + user.getMobile() + "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Line One</td>");
		out.println("     <td><input type='text' name='lineOne' value='" + address.getLineOne()
				+ "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Line Two</td>");
		out.println("     <td><input type='text' name='lineTwo' value='" + address.getLineTwo()
				+ "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>City</td>");
		out.println("     <td><input type='text' name='city' value='" + address.getCity() + "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>State</td>");
		out.println(
				"     <td><input type='text' name='state' value='" + address.getState() + "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Pin Code</td>");
		out.println("     <td><input type='text' name='pinCode' value='" + address.getPincode()
				+ "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("     <td>Country</td>");
		out.println("     <td><input type='text' name='country' value='" + address.getCountry()
				+ "' disabled='true'></td>");
		out.println("  </tr>");

		out.println("  <tr>");
		out.println("    <th align='center' colspan='2'>");
		out.println("      <input type='submit' value='Update' onclick=\"javascript: submitForm('updateProfile')\">");
		out.println("    </th>");
		out.println("  </tr>");

		out.println("</table>");
		// 3.3 footer
		LayoutUtil.showFooter(request, response);

	}

}
