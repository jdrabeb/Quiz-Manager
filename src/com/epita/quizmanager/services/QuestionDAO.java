package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epita.quizmanager.entities.Question;

public class QuestionDAO {
	
	private final String JDBC_PASSWORD = "";
	private final String JDBC_USER = "sa";
			final String JDBC_URL = "jdbc:h2:~/test";


	private Connection initConnection() throws SQLException
	{
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
	
	public void createQuestion(Question question)
	{
		try
		{
	        Class.forName("org.h2.Driver");
			Connection connection = initConnection();
			PreparedStatement statement = connection.prepareStatement("INSERT INTO QUESTION(QUESTION, DIFFICULTY, TYPE) VALUES(?,?,?)");
			statement.setString(1, question.getQuestion());
			statement.setInt(2, question.getDifficulty());
			statement.setString(3, question.getQuestionType().name());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		catch (ClassNotFoundException e) {
		    // TODO Auto-generated catch block
		    e.printStackTrace();
		   }
	}

}
