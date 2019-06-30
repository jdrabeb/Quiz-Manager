package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epita.quizmanager.entities.MCQChoice;
import com.epita.quizmanager.entities.MCQQuestion;


public class MCQQuestionDAO extends DAO<MCQQuestion>
{
	private final String SELECT_TOPIC = "SELECT TOPIC FROM TOPICS WHERE TOPIC = ? ";
	private final String SELECT_TOPIC_ID = "SELECT TOPIC_ID FROM TOPICS WHERE TOPIC = ? ";
	private final String INSERT_TOPIC = "INSERT INTO TOPICS VALUES(NULL, ?)";
	private final String INSERT_MCQQUESTION = "INSERT INTO MCQQUESTIONS VALUES(NULL, ?, ?, ?)";
	private final String SELECT_QUESTIONID = "SELECT QUESTION_ID FROM MCQQUESTIONS WHERE QUESTION = ? ";
	private final String INSERT_MCQCHOICES = "INSERT INTO MCQCHOICES VALUES(NULL, ?, ?, ?)";

	public MCQQuestionDAO(Connection connection)
	{
		super(connection);
	}

	public void create(MCQQuestion mcqQuestion)
	{
		try
		{
			int topic_id = 0;
			PreparedStatement selectTopicStatement = connection.prepareStatement(SELECT_TOPIC);
			selectTopicStatement.setString(1, mcqQuestion.getTopic().getTitle());
			ResultSet resultSet = selectTopicStatement.executeQuery();
			if (!resultSet.next())
			{
				PreparedStatement insertQuestionStatement = connection.prepareStatement(INSERT_TOPIC);
				insertQuestionStatement.setString(1, mcqQuestion.getTopic().getTitle());
				insertQuestionStatement.executeUpdate();
			}
			PreparedStatement selectTopicIdStatement = connection.prepareStatement(SELECT_TOPIC_ID);
			selectTopicIdStatement.setString(1, mcqQuestion.getTopic().getTitle());
			resultSet = selectTopicIdStatement.executeQuery();
			if (resultSet.next())
			{
				topic_id = resultSet.getInt(1);
			}
			PreparedStatement insertQuestionStatement = connection.prepareStatement(INSERT_MCQQUESTION);
			insertQuestionStatement.setString(1, mcqQuestion.getContent());
			insertQuestionStatement.setInt(2, mcqQuestion.getDifficulty());
			insertQuestionStatement.setInt(3, topic_id);
			insertQuestionStatement.executeUpdate();
			int question_id = 0;
			PreparedStatement selectIdStatement = connection.prepareStatement(SELECT_QUESTIONID);
			selectIdStatement.setString(1, mcqQuestion.getContent());
			resultSet = selectIdStatement.executeQuery();
			if (resultSet.next())
			{
				question_id = resultSet.getInt(1);
			}
	System.out.println(question_id);
			PreparedStatement insertChoicesstatement = connection.prepareStatement(INSERT_MCQCHOICES);
			for (MCQChoice choice : mcqQuestion.getChoices())
			{
				insertChoicesstatement.setString(1, choice.getContent());
				insertChoicesstatement.setBoolean(2, choice.isValid());
				insertChoicesstatement.setInt(3, question_id);
				insertChoicesstatement.execute();
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void update(MCQQuestion mcqQuestion)
	{
		
	}

	public void delete(MCQQuestion mcqQuestion)
	{
		  
	}

	public MCQQuestion find(String topic)
	{
		return null;
	}


}
