package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.Question;
import com.epita.quizmanager.entities.QuestionType;

package com.epita.quizmanager.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.SQLException;

import com.epita.quizmanager.entities.User;

public class MCQQuestionDAO extends DAO<MCQQuestion>
{
	private final String INSERT_USER = "INSERT INTO USERS VALUES(NULL, ?, ?, ?, ?)";

	public MCQQuestionDAO(Connection connection)
	{
		super(connection);
	}

	public void create(MCQQuestion mcqQuestion)
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

	public void update(MCQQuestion mcqQuestion)
	{
		
	}

	public void delete(MCQQuestion mcqQuestion)
	{
		  
	}

	public MCQQuestion find(int id)
	{
		return null;
	}


}

//public class MCQQuestionDAO {
//	
//	private final String JDBC_PASSWORD = "";
//	private final String JDBC_USER = "sa";
//			final String JDBC_URL = "jdbc:h2:~/test";
//
//
//	private Connection initConnection() throws SQLException
//	{
//		return DriverManager.getConnection(JDBC_URL, JDBC_USER, JDBC_PASSWORD);
//	}
//	
//	public void createQuestion(Question question)
//	{
//		try
//		{
//	        Class.forName("org.h2.Driver");
//			Connection connection = initConnection();
//			PreparedStatement statement = connection.prepareStatement("INSERT INTO QUESTIONS (QUESTION, DIFFICULTY, TYPE) VALUES(?,?,?)");
//			statement.setString(1, question.getQuestion());
//			statement.setInt(2, question.getDifficulty());
//			statement.setString(3, question.getQuestionType().name());
//			statement.execute();
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		catch (ClassNotFoundException e) {
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//		   }
//	}
//	
//	public void createMCQQuestion(MCQQuestion mcqQuestion)
//	{
//		int questionId = 0;
//		Question question = new Question (mcqQuestion.getQuestion(), mcqQuestion.getDifficulty(), mcqQuestion.getQuestionType());
//		createQuestion(question);
//		try
//		{
//	        Class.forName("org.h2.Driver");
//			Connection connection = initConnection();
////			PreparedStatement selectStatement = connection.prepareStatement("SELECT QUESTION_ID FROM QUESTIONS WHERE QUESTION = ? AND DIFFICULTY = ? AND TYPE = ?");
////			selectStatement.setString(1, question.getQuestion());
////			selectStatement.setInt(2, question.getDifficulty());
////			selectStatement.setString(3, question.getQuestionType().name());
////			ResultSet rs = selectStatement.executeQuery();
////			while (rs.next())
////			{
////				questionId = rs.getInt(1);
////				System.out.println(questionId);
////			}
////			System.out.println(questionId);
//			PreparedStatement insertStatement = connection.prepareStatement("INSERT INTO MCQ_QUESTION (QUESTION_ID, CHOICE_1, CHOICE_2, CHOICE_3, ANSWER) VALUES(?,?,?,?,?)");
//			insertStatement.setInt(1, questionId);
//			insertStatement.setString(2, mcqQuestion.getAnswer());
//			insertStatement.setString(3, mcqQuestion.getFirstChoice());
//			insertStatement.setString(4, mcqQuestion.getSecondChoice());
//			insertStatement.setString(5, mcqQuestion.getThirdChoice());
//			insertStatement.execute();
//			
//		} catch (SQLException e) {
//			e.printStackTrace();
//		}
//		catch (ClassNotFoundException e) {
//		    // TODO Auto-generated catch block
//		    e.printStackTrace();
//		   }
//	}
//}
