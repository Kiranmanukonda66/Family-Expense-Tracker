package com.rs.fer.service.impl;

import java.sql.Connection;


import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.rs.fer.bean.Address;
import com.rs.fer.bean.Expense;
import com.rs.fer.bean.User;
import com.rs.fer.service.FERService;
import com.rs.fer.util.DBUtil;

public class FERServiceImpl implements FERService {

	@Override
	public boolean registration(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isRegister = false;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "INSERT INTO USER(FIRSTNAME,MIDDLENAME,LASTNAME,EMAIL,USERNAME,PASSWORD,MOBILE) VALUES(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddleName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getUsername());
			preparedStatement.setString(6, user.getPassword());
			preparedStatement.setString(7, user.getMobile());
			// 4.execute the statement
			int numberOfAffectedRows = preparedStatement.executeUpdate();
			if (numberOfAffectedRows > 0) {
				isRegister = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isRegister;
	}

	@Override
	public User login(String username, String password) {
		User user=null;
		Connection connection = null;
		PreparedStatement preparedStatement = null;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "SELECT * FROM USER WHERE USERNAME=? AND PASSWORD=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, username);
			preparedStatement.setString(2, password);
			// 4.execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();
			if (resultSet.next()) {
				
				int userId = resultSet.getInt(1);
				String fileName=resultSet.getString(9);
				
				user = new User();
				user.setId(userId);
				user.setFileName(fileName);
				
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean addExpense(Expense expense) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isAddExpense = false;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "INSERT INTO EXPENSE(TYPE,DATE,PRICE,NUMBEROFITEMS,TOTAL,BYWHOM,USERID) VALUES(?,?,?,?,?,?,?)";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, expense.getType());
			preparedStatement.setString(2, expense.getDate());
			preparedStatement.setFloat(3, expense.getPrice());
			preparedStatement.setInt(4, expense.getNumberOfItems());
			preparedStatement.setFloat(5, expense.getTotal());
			preparedStatement.setString(6, expense.getByWhom());
			preparedStatement.setInt(7, expense.getUserId());
			// 4.execute the statement
			int numberOfAffectedRows = preparedStatement.executeUpdate();
			if (numberOfAffectedRows > 0) {
				isAddExpense = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isAddExpense;
	}

	@Override
	public boolean editExpense(Expense expense) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isEditExpense = false;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "UPDATE EXPENSE SET TYPE=?, DATE=?, PRICE=?, NUMBEROFITEMS=?, TOTAL=?, BYWHOM=? WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, expense.getType());
			preparedStatement.setString(2, expense.getDate());
			preparedStatement.setFloat(3, expense.getPrice());
			preparedStatement.setInt(4, expense.getNumberOfItems());
			preparedStatement.setFloat(5, expense.getTotal());
			preparedStatement.setString(6, expense.getByWhom());
			preparedStatement.setInt(7, expense.getId());

			// 4.execute the statement
			int numberOfAffectedRows = preparedStatement.executeUpdate();
			if (numberOfAffectedRows > 0) {
				isEditExpense = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isEditExpense;
	}

	@Override
	public boolean deleteExpense(int expenseId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isDeleteExpense = false;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "DELETE FROM EXPENSE WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, expenseId);
			// 4.execute the statement
			int numberOfAffectedRows = preparedStatement.executeUpdate();
			if (numberOfAffectedRows > 0) {
				isDeleteExpense = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isDeleteExpense;
	}

	@Override
	public boolean resetPassword(String newPassword, int userId, String currentPassword) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isPasswordUpdate = false;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "UPDATE USER SET PASSWORD=? WHERE ID=? AND PASSWORD=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, newPassword);
			preparedStatement.setInt(2, userId);
			preparedStatement.setString(3, currentPassword);
			// 4.execute the statement
			int numberOfAffectedRows = preparedStatement.executeUpdate();
			if (numberOfAffectedRows > 0) {
				isPasswordUpdate = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isPasswordUpdate;
	}

	@Override
	public List<Expense> getExpenseOptions(int userId) {
		List<Expense> expenses = new ArrayList<>();
		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "SELECT * FROM EXPENSE WHERE USERID=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, userId);
			// 4.execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String date = resultSet.getString(3);
				float price = resultSet.getFloat(4);
				int numberOfItems = resultSet.getInt(5);
				float total = resultSet.getFloat(6);
				String byWhom = resultSet.getString(7);
				int uId = resultSet.getInt(8);

				// 3.2 load the values to object
				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(uId);

				// add the expense object to the collection object
				expenses.add(expense);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return expenses;
	}

	@Override
	public Expense getExpense(int expenseId) {
		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "SELECT * FROM EXPENSE WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, expenseId);
			// 4.execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String date = resultSet.getString(3);
				float price = resultSet.getFloat(4);
				int numberOfItems = resultSet.getInt(5);
				float total = resultSet.getFloat(6);
				String byWhom = resultSet.getString(7);
				int uId = resultSet.getInt(8);

				// 3.2 load the values to object
				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(uId);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return expense;
	}

	@Override
	public List<Expense> getExpenseReport(int userId, String ExpenseType, String fromDate, String toDate) {
		List<Expense> expenses = new ArrayList<>();
		Expense expense = null;

		Connection connection = null;
		PreparedStatement preparedStatement = null;
		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "SELECT * FROM EXPENSE WHERE USERID=? AND TYPE=? AND DATE BETWEEN ? AND ?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, userId);
			preparedStatement.setString(2, ExpenseType);
			preparedStatement.setString(3, fromDate);
			preparedStatement.setString(4, toDate);
			// 4.execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				int id = resultSet.getInt(1);
				String type = resultSet.getString(2);
				String date = resultSet.getString(3);
				float price = resultSet.getFloat(4);
				int numberOfItems = resultSet.getInt(5);
				float total = resultSet.getFloat(6);
				String byWhom = resultSet.getString(7);
				int uId = resultSet.getInt(8);

				// 3.2 load the values to object
				expense = new Expense();

				expense.setId(id);
				expense.setType(type);
				expense.setDate(date);
				expense.setPrice(price);
				expense.setNumberOfItems(numberOfItems);
				expense.setTotal(total);
				expense.setByWhom(byWhom);
				expense.setUserId(uId);

				// add the expense object to the collection object
				expenses.add(expense);

			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}

		return expenses;
	}

	@Override
	public User getUser(int userId) {

		User user = new User();
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		List<Address> addresses = new ArrayList<Address>();

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "SELECT U.*,A.* FROM USER U LEFT JOIN ADDRESS A ON U.ID=A.USERID WHERE U.ID=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setInt(1, userId);
			// 4.execute the statement
			ResultSet resultSet = preparedStatement.executeQuery();
			while (resultSet.next()) {

				// a.user column data
				int id = resultSet.getInt(1);
				String firstName = resultSet.getString(2);
				String middleName = resultSet.getString(3);
				String lastName = resultSet.getString(4);
				String email = resultSet.getString(5);
				String username = resultSet.getString(6);
				String password = resultSet.getString(7);
				String mobile = resultSet.getString(8);

				// b.address column data
				int addrid = resultSet.getInt(10);
				String lineOne = resultSet.getString(11);
				String lineTwo = resultSet.getString(12);
				String city = resultSet.getString(13);
				String state = resultSet.getString(14);
				String pincode = resultSet.getString(15);
				String country = resultSet.getString(16);
				int uId = resultSet.getInt(17);

				// user details load into user object
				user.setId(id);
				user.setFirstName(firstName);
				user.setMiddleName(middleName);
				user.setLastName(lastName);
				user.setEmail(email);
				user.setUsername(username);
				user.setPassword(password);
				user.setMobile(mobile);

				// address details loads into address object
				Address address = new Address();
				address.setId(addrid);
				address.setLineOne(lineOne);
				address.setLineOne(lineOne);
				address.setLineTwo(lineTwo);
				address.setCity(city);
				address.setState(state);
				address.setPincode(pincode);
				address.setCountry(country);
				address.setUserId(uId);

				addresses.add(address);

			}
			user.setAddresses(addresses);

		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return user;
	}

	@Override
	public boolean updateUser(User user) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isUpdate = false;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "UPDATE USER SET FIRSTNAME=?, MIDDLENAME=?, LASTNAME=?, EMAIL=?, MOBILE=? WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, user.getFirstName());
			preparedStatement.setString(2, user.getMiddleName());
			preparedStatement.setString(3, user.getLastName());
			preparedStatement.setString(4, user.getEmail());
			preparedStatement.setString(5, user.getMobile());
			preparedStatement.setInt(6, user.getId());
			// 4.execute the statement
			int numberOfAffectedRows = preparedStatement.executeUpdate();
			if (numberOfAffectedRows > 0) {

				List<Address> addresses = user.getAddresses();

				for (Address address : addresses) {

					int addressId = address.getId();
					if (addressId == 0) {
						// insert
						query = "INSERT INTO ADDRESS(LINE1,LINE2,CITY,STATE,PINCODE,COUNTRY,USERID,TYPE) VALUES(?,?,?,?,?,?,?,?)";
						preparedStatement = connection.prepareStatement(query);

						preparedStatement.setString(1, address.getLineOne());
						preparedStatement.setString(2, address.getLineTwo());
						preparedStatement.setString(3, address.getCity());
						preparedStatement.setString(4, address.getState());
						preparedStatement.setString(5, address.getPincode());
						preparedStatement.setString(6, address.getCountry());
						preparedStatement.setInt(7, user.getId());
						preparedStatement.setString(8, address.getType());

						// 4.execute the statement
						numberOfAffectedRows = preparedStatement.executeUpdate();

					} else {
						if ("".equals(address.getType())) {
							// delete
							query = "DELETE FROM ADDRESS WHERE ID=?";
							preparedStatement = connection.prepareStatement(query);
							preparedStatement.setInt(1, addressId);

							// 4.execute the statement
							numberOfAffectedRows = preparedStatement.executeUpdate();
						} else {
							// update
							query = "UPDATE ADDRESS SET LINE1=?, LINE2=?, CITY=?, STATE=?, PINCODE=?, COUNTRY=?,TYPE=? WHERE ID=?";
							preparedStatement = connection.prepareStatement(query);
							preparedStatement.setString(1, address.getLineOne());
							preparedStatement.setString(2, address.getLineTwo());
							preparedStatement.setString(3, address.getCity());
							preparedStatement.setString(4, address.getState());
							preparedStatement.setString(5, address.getPincode());
							preparedStatement.setString(6, address.getCountry());
							preparedStatement.setString(7, address.getType());
							preparedStatement.setInt(8, addressId);

							// 4.execute the statement
							numberOfAffectedRows = preparedStatement.executeUpdate();
						}
					}

				}
				if (numberOfAffectedRows > 0) {
					isUpdate = true;
				}
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isUpdate;
	}

	@Override
	public boolean uploadImage(String fileName, int userId) {
		Connection connection = null;
		PreparedStatement preparedStatement = null;
		boolean isImageUploaded = false;

		try {
			// 1&2
			connection = DBUtil.getConnection();

			// 3.create the statement object
			String query = "UPDATE USER SET FILENAME=? WHERE ID=?";
			preparedStatement = connection.prepareStatement(query);

			preparedStatement.setString(1, fileName);
			preparedStatement.setInt(2, userId);
			// 4.execute the statement
			int numberOfAffectedRows = preparedStatement.executeUpdate();
			if (numberOfAffectedRows > 0) {
				isImageUploaded = true;
			}
		} catch (SQLException se) {
			se.printStackTrace();
		} finally {

			DBUtil.closeConnection(connection);
		}
		return isImageUploaded;

	}

}
