package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epita.quizmanager.entities.User;

public class UserDAO extends DAO<User> {
	
	private final String INSERT_USER = "INSERT INTO USERS VALUES(NULL, ?, ?, ?, ?)";
	
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

	public void update(User user)
	{
		
	}

	public void delete(User user)
	{
		  
	}

	public User find(int id)
	{
		return null;
	}

}