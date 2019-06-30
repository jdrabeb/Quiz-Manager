package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.entities.MCQChoice;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.Topic;


public class MCQQuestionDAO extends DAO<MCQQuestion>
{
	private final String SELECT_TOPIC = "SELECT TOPIC FROM TOPICS WHERE TOPIC = ? ";
	private final String SELECT_TOPIC_ID = "SELECT TOPIC_ID FROM TOPICS WHERE TOPIC = ? ";
	private final String INSERT_TOPIC = "INSERT INTO TOPICS VALUES(NULL, ?)";
	private final String INSERT_MCQQUESTION = "INSERT INTO MCQQUESTIONS VALUES(NULL, ?, ?, ?)";
	private final String SELECT_QUESTIONID = "SELECT QUESTION_ID FROM MCQQUESTIONS WHERE QUESTION = ? ";
	private final String INSERT_MCQCHOICES = "INSERT INTO MCQCHOICES VALUES(NULL, ?, ?, ?)";

	private final String SELECT_QUESTION_BY_TOPIC = "SELECT * FROM MCQQUESTIONS Q INNER JOIN TOPICS T ON Q.TOPIC_ID= T.TOPIC_ID WHERE T.TOPIC = ?";
	private final String SELECT_CHOICES_BY_QUESTION = "SELECT CHOICE, ISVALID FROM MCQCHOICES C INNER JOIN MCQQUESTIONS Q ON C.QUESTION_ID= Q.QUESTION_ID WHERE Q.QUESTION= ?";
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

	public MCQQuestion find(String searchtopic)
	{
		String question;
		int difficulty;
		Topic topic = new Topic(searchtopic);
		try
		{
			PreparedStatement selectQuestionByTopic = connection.prepareStatement(SELECT_QUESTION_BY_TOPIC);
			selectQuestionByTopic.setString(1, searchtopic);
			ResultSet resultSet = selectQuestionByTopic.executeQuery();
			if (resultSet.next())
			{
				question = resultSet.getString("question");
				difficulty = resultSet.getInt("difficulty");
			}
			else
			{
				return null;
			}
			List<MCQChoice> choices = new ArrayList<MCQChoice>();
			PreparedStatement selectChoices = connection.prepareStatement(SELECT_CHOICES_BY_QUESTION);
			selectChoices.setString(1, question);
			ResultSet rst = selectChoices.executeQuery();
			while (rst.next())
			{
				MCQChoice choice = new MCQChoice(rst.getString("choice"), rst.getBoolean("isvalid"));
				choices.add(choice);
			}
			MCQQuestion mcqQuestion = new MCQQuestion(question, difficulty, topic, choices);
			return mcqQuestion;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}


}