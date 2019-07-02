package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.User;

/**
 * UserDAO - DAO to manipulate Users.
 * @author rabeb
 *
 */
public class UserDAO extends DAO<User> {
	
	private final String INSERT_USER = "INSERT INTO USERS VALUES(NULL, ?, ?, ?, ?)";
	private final String SELECT_USER_BY_ID = "SELECT USERNAME, PASSWORD, ISADMIN FROM USER WHERE IDENTIFIANT = ?";
	private final String SELECT_BY_USERNAME_PASSWORD = "SELECT NAME, PASSWORD, IDENTIFIANT, ISADMIN FROM USERS WHERE NAME = ? AND PASSWORD = ? ";

	
	/**
	 * Constructor of a UserDAO.
	 * @param connection - The connection to the database.
	 */
	public UserDAO(Connection connection)
	{
		super(connection);
	}

	/**
	 *Create a new user in the database.
	 *@param user - The user to add to the database.
	 */
	public void create(User user)
	{
		try {
			PreparedStatement statement = connection.prepareStatement(INSERT_USER);
			statement.setString(1, user.getName());
			statement.setString(2, user.getPassword());
			statement.setString(3, user.getId());
			statement.setBoolean(4, user.isAdmin());
			statement.execute();
		} catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	/**
	 * Update a user from the database.
	 * @param user_id - The user_id of the user to update.
	 * @param newUser - The new user to update to.
	 */
	public void update(int user_id, User newUser)
	{
		
	}

	/**
	 * Delete a user from the database.
	 * @param user_id - The user_id of the user to delete.
	 */
	public int delete(int user_id)
	{
		  return 0;
	}

	/**
	 * Find all users having a given id.
	 * @param id - the user id to search users based on.
	 * @return all the users having the given id.
	 */
	public List<User> find(String id)
	{
		try
		{
			List<User> users = new ArrayList<User>();
			PreparedStatement selectUserById = connection.prepareStatement(SELECT_USER_BY_ID);
			selectUserById.setString(1, id);
			ResultSet resultSet = selectUserById.executeQuery();
			if (resultSet.next())
			{
				User user = new User(resultSet.getString("username"), resultSet.getString("password"), id, resultSet.getBoolean("isadmin"));
				users.add(user);
				return users;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	/**
	 * Find and return a user matching a given username and password.
	 * @param username - The username to search in the database.
	 * @param password - The password to search in the database.
	 * @return a user matching the info searched.
	 */
	public User findLogin(String username, String password)
	{
		try
		{
			PreparedStatement selectUserByNamePassword = connection.prepareStatement(SELECT_BY_USERNAME_PASSWORD);
			selectUserByNamePassword.setString(1, username);
			selectUserByNamePassword.setString(2, password);
			ResultSet resultSet = selectUserByNamePassword.executeQuery();
			if (resultSet.next())
			{
				User user = new User(username, password, resultSet.getString("identifiant"), resultSet.getBoolean("isadmin"));
				return user;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

	
}