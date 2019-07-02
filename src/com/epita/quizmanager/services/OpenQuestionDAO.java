package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.entities.OpenQuestion;
import com.epita.quizmanager.entities.Topic;

/**
 * OpenQuestionDAO - DAO to manipulate OpenQuestions.
 * @author rabeb
 *
 */
public class OpenQuestionDAO extends DAO<OpenQuestion>{

	// List of queries needed to create an OpenQuestion.
	private final String SELECT_TOPIC = "SELECT TOPIC FROM TOPICS WHERE TOPIC = ? ";
	private final String SELECT_TOPIC_ID = "SELECT TOPIC_ID FROM TOPICS WHERE TOPIC = ? ";
	private final String INSERT_TOPIC = "INSERT INTO TOPICS VALUES(NULL, ?)";
	private final String INSERT_OPENQUESTION = "INSERT INTO OPENQUESTIONS VALUES(NULL, ?, ?, ?)";
	private final String SELECT_QUESTIONID = "SELECT OPENQUESTION_ID FROM OPENQUESTIONS WHERE QUESTION = ? ";

	// List of queries needed to find an Question by topic.
	private final String SELECT_QUESTION_BY_TOPIC = "SELECT * FROM OPENQUESTIONS Q INNER JOIN TOPICS T ON Q.TOPIC_ID= T.TOPIC_ID WHERE T.TOPIC = ?";
	
	// List of queries needed to display all OpenQuestion.
	private final String SELECT_OPENQUESTIONS = "SELECT OPENQUESTION_ID, QUESTION FROM OPENQUESTIONS";
	// List of queries needed to display all topics.
	private final String SELECT_ALLTOPICS = "SELECT TOPIC FROM TOPICS";

	// List of queries needed to update an OpenQuestion.
	private final String UPDATE_OPENQUESTION = "UPDATE OPENQUESTIONS SET QUESTION = ? WHERE OPENQUESTION_ID = ?";
	private final String UPDATE_DIFFICULTY = "UPDATE OPENQUESTIONS SET DIFFICULTY = ? WHERE OPENQUESTION_ID = ?";	
	
	// List of queries needed to delete an OpenQuestion.
	private final String DELETE_OPENQUESTION = "DELETE FROM OPENQUESTIONS WHERE OPENQUESTION_ID= ?";
	
	/**
	 * Constructor of an OpenQuestionDao
	 * @param connection - The connection to the database
	 */
	public OpenQuestionDAO(Connection connection)
	{
		super(connection);
	}

	/**
	 *Create a new Open question in the database.
	 *@param openQuestion - The question to add to the database.
	 */
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
	
	/**
	 * Update an Open question in the database.
	 * @param openQuestion_id - The question id of the question to update.
	 * @param newQuestionToUpdate - The new Open question to update to.
	 */
	public void update(int openQuestion_id, OpenQuestion newQuestionToUpdate)
	{
		//TODO
	}

	/**
	 * Update the content of an Open question in the database.
	 * @param openQuestion_id - The question id of the question to update.
	 * @param newQuestion - The new Open question content to update to.
	 */
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
	
	/** Update the difficulty of an OPen question in the database.
	 * @param openQuestion_id - The question id of the question to update.
	 * @param newDifficulty - The new difficulty to update to.
	 */
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
	
	/**
	 * Display all the Open questions in the database.
	 * @return a string with all the topics available in the database. returns null on error.
	 */
	public String displayQuestions()
	{
		try
		{
			String questionsToDisplay = "";
			PreparedStatement selectQuestionsStatement = connection.prepareStatement(SELECT_OPENQUESTIONS);
			ResultSet resultSet = selectQuestionsStatement.executeQuery();
			if (!resultSet.next())
			{
				return questionsToDisplay;
			}
			else
			{
				do
				{
					questionsToDisplay = questionsToDisplay + "Question id: " + resultSet.getInt("openquestion_id") + ", Question: " + resultSet.getString("question") + "\n";
				}
				while (resultSet.next());
				return questionsToDisplay;
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return null;
		}
	}
	
	/**
	 * Delete an Open question from the database.
	 * @param question_id - The question_id of the question to delete
	 */
	public int delete(int question_id)
	{
		try
		{
			PreparedStatement deleteQuestionStatement = connection.prepareStatement(DELETE_OPENQUESTION);
			deleteQuestionStatement.setInt(1, question_id);
			deleteQuestionStatement.executeUpdate();
			return 0;
		}
		catch (SQLException e)
		{
			e.printStackTrace();
			return -1;
		}
	}

	/**
	 * Find all Open questions covering a certain topic.
	 * @param searchtopic - the topic to search questions based on.
	 * @return all the Open questions covering searchtopic.
	 */
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
