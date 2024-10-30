package com.rs.fer.main;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class UpdateProfileMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		int userId = 4;
		FERService ferService = new FERServiceImpl();
		User user = ferService.getUser(userId);
		
		//personal Info
		user.setFirstName("Kiran");
		user.setMiddleName("");
		user.setLastName("Manukonda");
		
		//contact Info
		user.setEmail("kiranmanu66@gmail.com");
		user.setMobile("9876543210");
		
		//address info
		Address address=user.getAddress();
		address.setLineOne("rythupeta");
		address.setLineTwo("near geetamandir");
		address.setCity("Nandigama");
		address.setState("Andhra");
		address.setPincode("521185");
		address.setCountry("INDIA");
		
		
		//2.business logic
		boolean isUpdate=ferService.updateUser(user);

		// 3.show the status
		if (isUpdate) {
			System.out.println("user Profile Updated successfully....");
		} else {
			System.out.println("user Profile update is failed");
		}
	}

}