package com.rs.fer.main;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class DeleteExpenseMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		int expenseId = 5;

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		boolean isDeleteExpense = ferService.deleteExpense(expenseId);

		// 3.show the status
		if (isDeleteExpense) {
			System.out.println("Expense Deleted successfully....");
		} else {
			System.out.println("Expense Delete is failed");
		}

	}

}
