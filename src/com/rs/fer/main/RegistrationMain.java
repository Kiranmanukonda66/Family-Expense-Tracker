package com.rs.fer.main;

import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class RegistrationMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		User user = new User();
		user.setFirstName("Kiran");
		user.setMiddleName("kumar");
		user.setLastName("Manukonda");
		user.setEmail("kiran66@gmail.com");
		user.setUsername("kiran123");
		user.setPassword("kiran@123");
		user.setMobile("7893146223");

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		boolean isRegister = ferService.registration(user);

		// 3.show the status
		if (isRegister) {
			System.out.println("user registered successfully....");
		} else {
			System.out.println("user registration is failed");
		}

	}

}
