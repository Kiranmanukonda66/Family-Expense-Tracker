package com.rs.fer.main;

import java.util.List;

import com.rs.fer.bean.Expense;
import com.rs.fer.service.FERService;
import com.rs.fer.service.impl.FERServiceImpl;

public class ExpenseReportMain {

	public static void main(String[] args) {

		// to handle the request

		// 1. get the input - create the object for bean class and set the inputs
		int userId = 3;
		String ExpenseType="Tea";
		String fromDate="21-08-2024";
		String toDate="23-08-2024";

		// 2. call the service for business logic execution
		// 2.1 create the object for FERServiceImpl taking parent as reference
		// 2.2 call the method it will return boolean
		FERService ferService = new FERServiceImpl();
		List<Expense> expenses = ferService.getExpenseReport(userId, ExpenseType, fromDate, toDate);

		// 3.show the status
		if (expenses.isEmpty()) {
			System.out.println("Expenses not found....");
		} else {
			for (Expense expense : expenses) {

				System.out.println("Id :" + expense.getId());
				System.out.println("Expense Type :" + expense.getType());
				System.out.println("Date :" + expense.getDate());
				System.out.println("Price :" + expense.getPrice());
				System.out.println("Number of Items :" + expense.getNumberOfItems());
				System.out.println("Total :" + expense.getTotal());
				System.out.println("By WHom:" + expense.getByWhom());
				System.out.println("User Id:" + expense.getUserId());

				System.out.println("............................................");
			}
		}

	}

}
