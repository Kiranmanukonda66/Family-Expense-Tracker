package com.rs.fer.main;

import com.rs.fer.bean.Expense;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class AddExpenseMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		Expense expense = new Expense();
		expense.setType("Tea");
		expense.setDate("29-08-2024");
		expense.setPrice(20);
		expense.setNumberOfItems(1);
		expense.setTotal(20);
		expense.setByWhom("me");
		expense.setUserId(4);

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		boolean isAddExpense = ferService.addExpense(expense);

		// 3.show the status
		if (isAddExpense) {
			System.out.println("Expense Added successfully....");
		} else {
			System.out.println("Expense Add is failed");
		}

	}

}
