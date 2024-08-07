package com.dit.chatapp.dao;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import static com.dit.chatapp.utils.ConfigReader.getValue;

public interface CommonDao {
	public static Connection createConnection() throws ClassNotFoundException, SQLException {
		Class.forName(getValue("DRIVER"));
		final String CONNECTION_String=getValue("CONNECTION_URL");
		final String USER_ID=getValue("USERID");
		final String PASSWORD=getValue("PASSWORD");
		Connection con= DriverManager.getConnection(CONNECTION_String,USER_ID,PASSWORD);
		if(con!=null) {
			System.out.println("Connection Created");
		}
		return con;
	}
}
