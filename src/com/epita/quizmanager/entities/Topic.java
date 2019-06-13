package com.epita.quizmanager.entities;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.SQLException;

public class Topic {
	private Question question; // FOREIGN KEY
	
	private String topicName;
	//TODO put DB configuration into config file 
	private final String JDBC_PASSWORD = "";
	private final String JDBC_USER = "sa";
			final String JDBC_URL = "jdbc:h2:~/test";

	private Connection initConnection() throws SQLException {
		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
	}
	
	public void createTopic (Topic topic) {
		try {
			Connection connection = initConnection();

			PreparedStatement statement = connection.prepareStatement("INSERT INTO TOPIC(TOPIC, QUESTION_ID) VALUES(?,?)");
			statement.setString(1, topic.topicName);
			statement.setString(2, topic.question.getQuestion());
			statement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}
}
