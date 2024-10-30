package com.rs.fer.main;

import com.rs.fer.bean.Expense;

import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class EditExpenseMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		Expense expense = new Expense();
		expense.setType("coffee");
		expense.setDate("29-08-2024");
		expense.setPrice(40);
		expense.setNumberOfItems(3);
		expense.setTotal(120);
		expense.setByWhom("myself");
		expense.setId(4);

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		boolean isEditExpense = ferService.editExpense(expense);

		// 3.show the status
		if (isEditExpense) {
			System.out.println("Expense updated successfully....");
		} else {
			System.out.println("Expense update is failed");
		}

	}

}
