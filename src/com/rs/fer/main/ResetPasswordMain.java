package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class ResetPasswordMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		String newPassword = "kiran@789";
		int userId = 4;
		String currentPassword = "kiran@123";

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		boolean isPasswordUpdate = ferService.resetPassword(newPassword, userId, currentPassword);

		// 3.show the status
		if (isPasswordUpdate) {
			System.out.println("Password Updated successfully....");
		} else {
			System.out.println("Password Update is failed");
		}

	}

}
