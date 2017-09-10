package app.db;

import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Connection;


public class dbConnector {
	
	public Connection Connection() throws ClassNotFoundException, SQLException {
	Class.forName("com.mysql.jdbc.Driver");
	return DriverManager.getConnection("jdbc:mysql://localhost:3306/logindb", "root", "macbook");
	}
	
}
