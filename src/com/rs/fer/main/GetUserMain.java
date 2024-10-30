package com.rs.fer.main;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class GetUserMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		int userId = 4;

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		User user = ferService.getUser(userId);

		// 3.show the status
		if (user == null) {
			System.out.println("user not found....");
		} else {
			Address address = user.getAddress();
			System.out.println(".............Name Info.................");
			System.out.println("First Name : " + user.getFirstName());
			System.out.println("Middle Name : " + user.getMiddleName());
			System.out.println("last Name : " + user.getLastName());
			System.out.println(".............Contact Info.................");
			System.out.println("Email : " + user.getEmail());
			System.out.println("Mobile : " + user.getMobile());
			System.out.println(".............Address Info.................");
			System.out.println("Line 1 : " + address.getLineOne());
			System.out.println("Line 2 : " + address.getLineTwo());
			System.out.println("City : " + address.getCity());
			System.out.println("State : " + address.getState());
			System.out.println("Pincode : " + address.getPincode());
			System.out.println("Country : " + address.getCountry());
		}

	}

}
