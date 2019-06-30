package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;

public class DBConnection {
	static Configuration config = Configuration.getInstance();
	private static String databaseURL = config.getPropertyValue("jdbc.url");
	private static String databaseUsername = config.getPropertyValue("jdbc.username");
	private static String databasePassword = config.getPropertyValue("jdbc.password");
	private static Connection connection;

	private DBConnection()
	{
		try
	    {
	        connection = DriverManager.getConnection(databaseURL, databaseUsername, databasePassword);
	    } catch (SQLException e)
	    {
	    	e.printStackTrace();
	    }
	}
	
	public static Connection getInstance()
	{
	    if(connection == null)
	    {
	    	new DBConnection();
	    }
	    return connection;
	}
}
