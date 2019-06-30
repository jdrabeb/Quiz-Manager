package com.epita.quizmanager.entities;

import java.util.NoSuchElementException;

import com.epita.quizmanager.services.Configuration;

public class Session {
	
	private static final String LOGIN_PASSWORD = "login.password";
	private static final String LOGIN_USERNAME = "login.username";

	public static User login(String username, String password) {
		Configuration config = Configuration.getInstance();
		
		for(String key : properties.stringPropertyNames()) {
			  String value = properties.getProperty(key);
			  System.out.println(key + " => " + value);
			}

		
		
		
		String validUsername = config.getPropertyValue(LOGIN_USERNAME);
		String validPassword = config.getPropertyValue(LOGIN_PASSWORD);
		if (validUsername.equals(username) 
				&& validPassword.equals(password))
		{
			String id = config.getPropertyValue("login.id");
			String isAdminStr = config.getPropertyValue("login.isAdmin");
			boolean isAdmin = Boolean.parseBoolean(isAdminStr);
			User loggedUser = new User (validUsername, validPassword, id, isAdmin);
			return loggedUser;
		}
	}
	
}
