package com.rs.fer.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBUtil {
	// 1.get the connection

	public static Connection getConnection() {
		Connection connection = null;

		try {
			Class.forName("com.mysql.cj.jdbc.Driver");

			// 2.Get the connection object - connection is a predefined interface
			connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/fer_jdbc", "root", "root");
		} catch (ClassNotFoundException cnfe) {
			cnfe.printStackTrace();
		} catch (SQLException se) {
			se.printStackTrace();
		}

		return connection;
	}

	public static void closeConnection(Connection connection) {
		try {
			// 5.close the connection
			connection.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
