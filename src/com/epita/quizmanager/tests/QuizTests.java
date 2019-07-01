package com.epita.quizmanager.tests;

import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.console.AdminConsole;
import com.epita.quizmanager.console.Console;
import com.epita.quizmanager.console.StudentConsole;
import com.epita.quizmanager.entities.MCQChoice;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.Question;
import com.epita.quizmanager.entities.QuestionType;
import com.epita.quizmanager.entities.Topic;
import com.epita.quizmanager.entities.User;
import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;
import com.epita.quizmanager.services.UserDAO;

public class QuizTests {
	
	public static void testCreateMCQQuestion()
	{
		MCQChoice choice1 = new MCQChoice("choice1", false);
		MCQChoice choice2 = new MCQChoice("choice2", true);
		MCQChoice choice3 = new MCQChoice("choice3", false);
		List<MCQChoice> choices = new ArrayList<MCQChoice>();
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		Topic test = new Topic("topic");
		MCQQuestion mcqQuestion= new MCQQuestion("This is a question?", 0, test, choices);
	    DAO<MCQQuestion> mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
		mcqDao.create(mcqQuestion);
	}
	
	public static void testAdminConsoleMenu()
	{
		AdminConsole console = new AdminConsole();
		console.start();
	}

	public static void testStudentConsoleMenu()
	{
		User student = new User("test", "password", "id", false);
		StudentConsole studentConsole = new StudentConsole(student);
		studentConsole.start();
	}
	
	public static void testConsoleMenu()
	{
		Console console = new Console();
		console.start();
	}
	
	public static void main(String[] args)
	{
		testAdminConsoleMenu();
	}
}
