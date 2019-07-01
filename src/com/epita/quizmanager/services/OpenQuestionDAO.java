package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.entities.OpenQuestion;
import com.epita.quizmanager.entities.Topic;

public class OpenQuestionDAO extends DAO<OpenQuestion>{

	private final String SELECT_TOPIC = "SELECT TOPIC FROM TOPICS WHERE TOPIC = ? ";
	private final String SELECT_TOPIC_ID = "SELECT TOPIC_ID FROM TOPICS WHERE TOPIC = ? ";
	private final String INSERT_TOPIC = "INSERT INTO TOPICS VALUES(NULL, ?)";
	private final String INSERT_OPENQUESTION = "INSERT INTO OPENQUESTIONS VALUES(NULL, ?, ?, ?)";
	private final String SELECT_QUESTIONID = "SELECT OPENQUESTION_ID FROM OPENQUESTIONS WHERE QUESTION = ? ";

	private final String SELECT_QUESTION_BY_TOPIC = "SELECT * FROM OPENQUESTIONS Q INNER JOIN TOPICS T ON Q.TOPIC_ID= T.TOPIC_ID WHERE T.TOPIC = ?";
	
	private final String SELECT_OPENQUESTIONS = "SELECT OPENQUESTION_ID, QUESTION FROM OPENQUESTIONS";
	private final String SELECT_ALLTOPICS = "SELECT TOPIC FROM TOPICS";

	
	private final String UPDATE_OPENQUESTION = "UPDATE OPENQUESTIONS SET QUESTION = ? WHERE OPENQUESTION_ID = ?";
	private final String UPDATE_DIFFICULTY = "UPDATE OPENQUESTIONS SET DIFFICULTY = ? WHERE OPENQUESTION_ID = ?";	
	
	private final String DELETE_OPENQUESTION = "DELETE FROM OPENQUESTIONS WHERE OPENQUESTION_ID= ?";
	
	public OpenQuestionDAO(Connection connection)
	{
		super(connection);
	}

	public void create(OpenQuestion openQuestion)
	{
		try
		{
			int topic_id = 0;
			PreparedStatement selectTopicStatement = connection.prepareStatement(SELECT_TOPIC);
			selectTopicStatement.setString(1, openQuestion.getTopic().getTitle());
			ResultSet resultSet = selectTopicStatement.executeQuery();
			if (!resultSet.next())
			{
				PreparedStatement insertQuestionStatement = connection.prepareStatement(INSERT_TOPIC);
				insertQuestionStatement.setString(1, openQuestion.getTopic().getTitle());
				insertQuestionStatement.executeUpdate();
			}
			PreparedStatement selectTopicIdStatement = connection.prepareStatement(SELECT_TOPIC_ID);
			selectTopicIdStatement.setString(1, openQuestion.getTopic().getTitle());
			resultSet = selectTopicIdStatement.executeQuery();
			if (resultSet.next())
			{
				topic_id = resultSet.getInt(1);
			}
			PreparedStatement insertQuestionStatement = connection.prepareStatement(INSERT_OPENQUESTION);
			insertQuestionStatement.setString(1, openQuestion.getContent());
			insertQuestionStatement.setInt(2, openQuestion.getDifficulty());
			insertQuestionStatement.setInt(3, topic_id);
			insertQuestionStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

	public void update(int mcqQuestion_id, OpenQuestion newQuestionToUpdate)
	{
		//TODO
	}

	public void updateQuestion(int openQuestion_id, String newQuestion)
	{
		try
		{
			PreparedStatement updateQuestionStatement = connection.prepareStatement(UPDATE_OPENQUESTION);
			updateQuestionStatement.setString(1, newQuestion);
			updateQuestionStatement.setInt(2, openQuestion_id);
			updateQuestionStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateDifficulty(int openQuestion_id, int newDifficulty)
	{
		try
		{
			PreparedStatement updateQuestionStatement = connection.prepareStatement(UPDATE_DIFFICULTY);
			updateQuestionStatement.setInt(1, newDifficulty);
			updateQuestionStatement.setInt(2, openQuestion_id);
			updateQuestionStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public int displayQuestions()
	{
		try
		{
			PreparedStatement selectQuestionsStatement = connection.prepareStatement(SELECT_OPENQUESTIONS);
			ResultSet resultSet = selectQuestionsStatement.executeQuery();
			if (!resultSet.next())
			{
				System.out.println("\nThere are no Open Questions.");
				return 1;
			}
			else
			{
				System.out.println("\nAll the available Open Questions.\n");
				do
				{
					System.out.println("Question id: " + resultSet.getInt("openquestion_id") + ", Question: " + resultSet.getString("question") + "\n");
				}
				while (resultSet.next());
				return 0;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 1;
	}
	
	public int displayTopics()
	{
		try
		{
			PreparedStatement selectTopicsStatement = connection.prepareStatement(SELECT_ALLTOPICS);
			ResultSet resultSet = selectTopicsStatement.executeQuery();	
			if (!resultSet.next())
			{
				System.out.println("\nThere are no Topics.");
				return 1;
			}
			else
			{
				System.out.println("\nAll the available Topics:\n");
				do
				{
					System.out.println(resultSet.getString("topic"));
				}
				while (resultSet.next());
				return 0;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return 1;
	}
	
	
	public void delete(int question_id)
	{
		try
		{
			PreparedStatement deleteQuestionStatement = connection.prepareStatement(DELETE_OPENQUESTION);
			deleteQuestionStatement.setInt(1, question_id);
			deleteQuestionStatement.executeUpdate();
			System.out.println("The question was deleted succesfully.\n");
		}
		catch (SQLException e)
		{
			System.out.println("\nThe question you want to delete doesn't exist\n");
			e.printStackTrace();
		}
	}

	public List<OpenQuestion> find(String searchtopic)
	{
		String question;
		int difficulty;
		Topic topic = new Topic(searchtopic);
		try
		{
			List<OpenQuestion> questions = new ArrayList<OpenQuestion>();
			PreparedStatement selectQuestionByTopic = connection.prepareStatement(SELECT_QUESTION_BY_TOPIC);
			selectQuestionByTopic.setString(1, searchtopic);
			ResultSet resultSet = selectQuestionByTopic.executeQuery();
			while (resultSet.next())
			{
				question = resultSet.getString("question");
				difficulty = resultSet.getInt("difficulty");
				OpenQuestion openQuestion = new OpenQuestion(question, difficulty, topic);
				questions.add(openQuestion);
			}
			return questions;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
		return null;
	}

}
