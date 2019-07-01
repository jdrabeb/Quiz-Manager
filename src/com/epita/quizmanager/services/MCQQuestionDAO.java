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
	
	private final String UPDATE_MCQQUESTION = "UPDATE MCQQUESTIONS SET QUESTION = ? WHERE QUESTION = ?";
	private final String UPDATE_DIFFICULTY = "UPDATE MCQQUESTIONS SET DIFFICULTY = ? WHERE QUESTION = ?";	
	
	private final String DELETE_MCQCHOICE = "DELETE FROM MCQCHOICES WHERE QUESTION_ID= ?";
	private final String DELETE_MCQUESTION = "DELETE FROM MCQQUESTIONS WHERE QUESTION= ?";
	
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

	public void update(MCQQuestion mcqQuestionToUpdate, MCQQuestion newQuestionToUpdate)
	{
		if (newQuestionToUpdate.getContent() != null)
			updateQuestion(mcqQuestionToUpdate, newQuestionToUpdate.getContent());
		if (newQuestionToUpdate.getDifficulty() != -1)
			updateDifficulty(mcqQuestionToUpdate, newQuestionToUpdate.getDifficulty());
	}

	public void updateQuestion(MCQQuestion mcqQuestion, String newQuestion)
	{
		try
		{
			PreparedStatement updateQuestionStatement = connection.prepareStatement(UPDATE_MCQQUESTION);
			updateQuestionStatement.setString(1, newQuestion);
			updateQuestionStatement.setString(2, mcqQuestion.getContent());
			updateQuestionStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void updateDifficulty(MCQQuestion mcqQuestion, int newDifficulty)
	{
		try
		{
			PreparedStatement updateQuestionStatement = connection.prepareStatement(UPDATE_DIFFICULTY);
			updateQuestionStatement.setInt(1, newDifficulty);
			updateQuestionStatement.setString(2, mcqQuestion.getContent());
			updateQuestionStatement.executeUpdate();
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}
	
	public void delete(String question)
	{
		try
		{
			int question_id = 0;
			PreparedStatement selectIdStatement = connection.prepareStatement(SELECT_QUESTIONID);
			selectIdStatement.setString(1, question);
			ResultSet resultSet = selectIdStatement.executeQuery();
			if (resultSet.next())
			{
				question_id = resultSet.getInt(1);
				System.out.println(question_id);
				PreparedStatement deleteChoiceStatement = connection.prepareStatement(DELETE_MCQCHOICE);
				deleteChoiceStatement.setInt(1, question_id);
				deleteChoiceStatement.executeUpdate();
				PreparedStatement deleteQuestionStatement = connection.prepareStatement(DELETE_MCQUESTION);
				deleteQuestionStatement.setString(1, question);
				deleteQuestionStatement.executeUpdate();
				System.out.println("\n" + question + " was deleted succesfully.\n");
			}
			else
			{
				System.out.println("\nThe question you want to delete doesn't exist\n");
			}
		}
		catch (SQLException e)
		{
			e.printStackTrace();
		}
	}

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
