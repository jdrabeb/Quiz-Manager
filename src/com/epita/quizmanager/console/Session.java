package com.epita.quizmanager.console;

import com.epita.quizmanager.entities.User;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.UserDAO;

/**
 * Session - Authentificates a user.
 * @author rabeb
 *
 */
public class Session {
	
	/**
	 * Checks if the user trying to login is in the database.
	 * @param username The username entered 
	 * @param password The password entered
	 * @return the user if he is autherized else returns null.
	 */
	public static User login(String username, String password) {
	    UserDAO userDao = new UserDAO(DBConnection.getInstance());
	    User user = userDao.findLogin(username, password);
	    if (user == null)
	    {
	    	System.out.println("Authentification failed. Please try again!\n");
	    }
	    else
	    {
			return user;
		}
		return null;
	}
	
}
