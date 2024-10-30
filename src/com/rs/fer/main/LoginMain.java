package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class LoginMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		String username = "kiran123";
		String password = "kiran@123";

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		User user = ferService.login(username, password);

		// 3.show the status
		if (user.getId() > 0) {
			System.out.println("Welcome to user : " + username + "....!");
		} else {
			System.out.println("You have entered incorrect username or password");
		}

	}

}
