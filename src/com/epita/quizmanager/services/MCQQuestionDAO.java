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


/**
 * MCQQuestionDAO - DAO to manipulate MCQQuestions.
 * @author rabeb
 *
 */
/**
 * @author rabeb
 *
 */
public class MCQQuestionDAO extends DAO<MCQQuestion>
{
	// List of queries needed to create an MCQQuestion.
	private final String SELECT_TOPIC = "SELECT TOPIC FROM TOPICS WHERE TOPIC = ? ";
	private final String SELECT_TOPIC_ID = "SELECT TOPIC_ID FROM TOPICS WHERE TOPIC = ? ";
	private final String INSERT_TOPIC = "INSERT INTO TOPICS VALUES(NULL, ?)";
	private final String INSERT_MCQQUESTION = "INSERT INTO MCQQUESTIONS VALUES(NULL, ?, ?, ?)";
	private final String SELECT_QUESTIONID = "SELECT QUESTION_ID FROM MCQQUESTIONS WHERE QUESTION = ? ";
	private final String INSERT_MCQCHOICES = "INSERT INTO MCQCHOICES VALUES(NULL, ?, ?, ?)";

	// List of queries needed to find an MCQQuestion.
	private final String SELECT_QUESTION_BY_TOPIC = "SELECT * FROM MCQQUESTIONS Q INNER JOIN TOPICS T ON Q.TOPIC_ID= T.TOPIC_ID WHERE T.TOPIC = ?";
	private final String SELECT_CHOICES_BY_QUESTION = "SELECT CHOICE, ISVALID FROM MCQCHOICES C INNER JOIN MCQQUESTIONS Q ON C.QUESTION_ID= Q.QUESTION_ID WHERE Q.QUESTION= ?";
	
	// List of queries needed to display an MCQQuestion.
	private final String SELECT_MCQQUESTIONS = "SELECT QUESTION_ID, QUESTION FROM MCQQUESTIONS";
	// List of queries needed to display a Topic.
	private final String SELECT_ALLTOPICS = "SELECT TOPIC FROM TOPICS";

	// List of queries needed to update an MCQQuestion.
	private final String UPDATE_MCQQUESTION = "UPDATE MCQQUESTIONS SET QUESTION = ? WHERE QUESTION_ID = ?";
	private final String UPDATE_DIFFICULTY = "UPDATE MCQQUESTIONS SET DIFFICULTY = ? WHERE QUESTION_ID = ?";	
	
	// List of queries needed to delete an MCQQuestion.
	private final String DELETE_MCQCHOICE = "DELETE FROM MCQCHOICES WHERE QUESTION_ID= ?";
	private final String DELETE_MCQUESTION = "DELETE FROM MCQQUESTIONS WHERE QUESTION_ID= ?";
	
	/**
	 * Constructor of an MCQQuestionDao
	 * @param connection - The connection to the database.
	 */
	public MCQQuestionDAO(Connection connection)
	{
		super(connection);
	}

	/**
	 *Create a new MCQ question in the database.
	 *@param mcqQuestion - The question to add to the database.
	 */
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

	/**
	 * Update an MCQ question in the database.
	 * @param mcqQuestion_id - The question id of the question to update.
	 * @param newQuestionToUpdate - The new MCQ question to update to.
	 */
	public void update(int mcqQuestion_id, MCQQuestion newQuestionToUpdate)
	{
		//TODO
	}

	/**
	 * Update the content of an MCQ question in the database.
	 * @param mcqQuestion_id - The question id of the question to update.
	 * @param newQuestion - The new MCQ question content to update to.
	 */
	public void updateQuestion(int mcqQuestion_id, String newQuestion)
	{
		try
		{
			PreparedStatement updateQuestionStatement = connection.prepareStatement(UPDATE_MCQQUESTION);
			updateQuestionStatement.setString(1, newQuestion);
			updateQuestionStatement.setInt(2, mcqQuestion_id);
			updateQuestionStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	
	/** Update the difficulty of an MCQ question in the database.
	 * @param mcqQuestion_id - The question id of the question to update.
	 * @param newDifficulty - The new difficulty to update to.
	 */
	public void updateDifficulty(int mcqQuestion_id, int newDifficulty)
	{
		try
		{
			PreparedStatement updateQuestionStatement = connection.prepareStatement(UPDATE_DIFFICULTY);
			updateQuestionStatement.setInt(1, newDifficulty);
			updateQuestionStatement.setInt(2, mcqQuestion_id);
			updateQuestionStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	/**
	 * Display all the MCQ questions in the database.
	 * @return 0 on success, 1 if there is nothing to display.
	 */
	public int displayQuestions()
	{
		try
		{
			PreparedStatement selectQuestionsStatement = connection.prepareStatement(SELECT_MCQQUESTIONS);
			ResultSet resultSet = selectQuestionsStatement.executeQuery();
			if (!resultSet.next())
			{
				System.out.println("\nThere are no MCQQuestions.");
				return 1;
			}
			else
			{
				System.out.println("\nAll the available MCQQuestions.\n");
				do
				{
					System.out.println("Question id: " + resultSet.getInt("question_id") + ", Question: " + resultSet.getString("question") + "\n");
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
	
	/**
	 * Display all the topics in the database.
	 * @return 0 on success, 1 if there is nothing to display.
	 */
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
	
	/**
	 * Delete an MCQ question from the database.
	 * @param question_id - The question_id of the question to delete
	 */
	public void delete(int question_id)
	{
		try
		{
			PreparedStatement deleteChoiceStatement = connection.prepareStatement(DELETE_MCQCHOICE);
			deleteChoiceStatement.setInt(1, question_id);
			deleteChoiceStatement.executeUpdate();
			PreparedStatement deleteQuestionStatement = connection.prepareStatement(DELETE_MCQUESTION);
			deleteQuestionStatement.setInt(1, question_id);
			deleteQuestionStatement.executeUpdate();
			System.out.println("\nThe question was deleted succesfully.\n");

		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}


	/**
	 * Find all MCQ questions covering a certain topic.
	 * @param searchtopic - the topic to search questions based on.
	 * @return all the MCQ questions covering searchtopic.
	 */
	public List<MCQQuestion> find(String searchtopic)
	{
		String question;
		int difficulty;
		Topic topic = new Topic(searchtopic);
		try
		{
			List<MCQQuestion> questions = new ArrayList<MCQQuestion>();
			PreparedStatement selectQuestionByTopic = connection.prepareStatement(SELECT_QUESTION_BY_TOPIC);
			selectQuestionByTopic.setString(1, searchtopic);
			ResultSet resultSet = selectQuestionByTopic.executeQuery();
			while (resultSet.next())
			{
				question = resultSet.getString("question");
				difficulty = resultSet.getInt("difficulty");
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
				questions.add(mcqQuestion);
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
