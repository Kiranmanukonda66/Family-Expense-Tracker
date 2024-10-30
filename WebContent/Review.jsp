<%@page import="java.util.ArrayList"%>
<%@page import="java.util.List"%>
<%@page import="com.rs.fer.bean.Address"%>
<%@page import="com.rs.fer.bean.User"%>
<%@page import="com.rs.fer.service.FERService"%>
<%@page import="com.rs.fer.service.impl.FERServiceImpl"%>

<jsp:include page="Layout/Header_LeftFrame.jsp" />
<link rel="stylesheet" href="./CSS/FormStyle.css">

<%
    // 1.get the user and address object update address
    User user = (User) session.getAttribute("user");

    String[] lineOneArray = request.getParameterValues("lineOne");
    String[] lineTwoArray = request.getParameterValues("lineTwo");
    String[] cityArray = request.getParameterValues("city");
    String[] stateArray = request.getParameterValues("state");
    String[] pinCodeArray = request.getParameterValues("pinCode");
    String[] countryArray = request.getParameterValues("country");
    String[] typeArray = request.getParameterValues("type");
    
    List<Address> addresses = user.getAddresses();
    
    // 2. Iterate through the arrays and create Address objects
    if (lineOneArray != null) {
        Address address = null;
        
        for (int i = 0; i < lineOneArray.length; i++) {
            if(addresses.size() > i) {
                address = addresses.get(i);
            } else {
                address = new Address();
            }
            address.setLineOne(lineOneArray[i]);
            address.setType(typeArray[i]);
            address.setLineTwo(lineTwoArray[i]);
            address.setCity(cityArray[i]);
            address.setState(stateArray[i]);
            address.setPincode(pinCodeArray[i]);
            address.setCountry(countryArray[i]);
            address.setUserId(Integer.parseInt(session.getAttribute("userId").toString()));
            // Add address to the list
            if(addresses.size() <= i) {
                addresses.add(address);
            }
        }
        
        int addressLength = lineOneArray.length;
        for(int index=addressLength; index<addresses.size(); index++) {
            addresses.get(index).setType("");
        }
        
    }
    
    // 3.show the status
    
    // 3.2 body
    
%>

<div class="form-container">
    <h2 style='margin-top:50px;'>Confirm Your Details to Update</h2>
    
    <table class="user-details">
        <tr>
            <th colspan="2">User Information</th>
        </tr>
        <tr>
            <td>First Name</td>
            <td><input type='text' name='firstName' value='<%= user.getFirstName() %>' disabled></td>
        </tr>
        <tr>
            <td>Middle Name</td>
            <td><input type='text' name='middleName' value='<%= user.getMiddleName() %>' disabled></td>
        </tr>
        <tr>
            <td>Last Name</td>
            <td><input type='text' name='lastName' value='<%= user.getLastName() %>' disabled></td>
        </tr>
        <tr>
            <td>Email ID</td>
            <td><input type='text' name='email' value='<%= user.getEmail() %>' disabled></td>
        </tr>
        <tr>
            <td>Mobile</td>
            <td><input type='text' name='mobile' value='<%= user.getMobile() %>' disabled></td>
        </tr>
    </table>

    <br>

    <table class="address-information" style='margin-left:0;'>
        <tr>
            <th colspan="7">Address Information</th>
        </tr>
        <tr>
            <td>Line One</td>
            <td>Type</td>
            <td>Line Two</td>
            <td>City</td>
            <td>State</td>
            <td>Pincode</td>
            <td>Country</td>
        </tr>
        
        <%
            for(Address address : addresses) {
                if("".equals(address.getType())) {
                    continue;
                }
        %>
        <tr>
            <td><input type='text' name='lineOne' value='<%= address.getLineOne() %>' disabled size='10'></td>
            <td><input type='text' name='type' value='<%= address.getType() %>' disabled size='5'></td>
            <td><input type='text' name='lineTwo' value='<%= address.getLineTwo() %>' disabled size='10'></td>
            <td><input type='text' name='city' value='<%= address.getCity() %>' disabled size='7'></td>
            <td><input type='text' name='state' value='<%= address.getState() %>' disabled size='7'></td>
            <td><input type='text' name='pinCode' value='<%= address.getPincode() %>' disabled size='7'></td>
            <td><input type='text' name='country' value='<%= address.getCountry() %>' disabled size='7'></td>
        </tr>
        <%
            }
        %>
    </table>

    <div class="form-actions">
        <input type="button" value="Update" onclick="javascript: submitForm('UpdateProfilePost.jsp')">
    </div>
</div>

<jsp:include page="Layout/Footer.jsp" />
