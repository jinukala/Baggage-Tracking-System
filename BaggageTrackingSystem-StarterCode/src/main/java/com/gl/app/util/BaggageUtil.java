package com.gl.app.util;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.concurrent.atomic.AtomicInteger;

public class BaggageUtil {
	 private static final String URL = "jdbc:postgresql://localhost:5432/Capstone_Projects?current_schema=baggagetracking";
	    private static final String USER = "postgres";
	    private static final String PASSWORD = "root";
	public static Connection getConnection() throws SQLException {
		return DriverManager.getConnection(URL,USER,PASSWORD);
	}
	    static AtomicInteger counter = new AtomicInteger(101);


	    public static int generateUniqueId() {


	        return counter.getAndIncrement();
	    }
	    
	    
}
