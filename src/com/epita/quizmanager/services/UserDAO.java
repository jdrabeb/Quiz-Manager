package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.User;

public class UserDAO extends DAO<User> {
	
	private final String INSERT_USER = "INSERT INTO USERS VALUES(NULL, ?, ?, ?, ?)";
	private final String SELECT_USER_BY_ID = "SELECT USERNAME, PASSWORD, ISADMIN FROM USER WHERE IDENTIFIANT = ?";
	private final String SELECT_BY_USERNAME_PASSWORD = "SELECT NAME, PASSWORD, IDENTIFIANT, ISADMIN FROM USERS WHERE NAME = ? AND PASSWORD = ? ";

	
	public UserDAO(Connection connection)
	{
		super(connection);
	}

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

	public void update(User user, User newUser)
	{
		
	}

	public void delete(String username)
	{
		  
	}

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