package com.epita.quizmanager.entities;

import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.UserDAO;

public class Session {
	
	public static User login(String username, String password) {
	    UserDAO userDao = new UserDAO(DBConnection.getInstance());
	    User user = userDao.findLogin(username, password);
	    if (user == null)
	    {
	    	System.out.println("Authentification failed. Please try again!");
	    }
	    else
	    {
			return user;
		}
		return null;
	}
	
}
