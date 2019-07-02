package com.epita.quizmanager.tests;

import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.console.AdminConsole;
import com.epita.quizmanager.console.Console;
import com.epita.quizmanager.console.StudentConsole;
import com.epita.quizmanager.entities.MCQChoice;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.OpenQuestion;
import com.epita.quizmanager.entities.Question;
import com.epita.quizmanager.entities.QuestionType;
import com.epita.quizmanager.entities.Topic;
import com.epita.quizmanager.entities.User;
import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;
import com.epita.quizmanager.services.OpenQuestionDAO;
import com.epita.quizmanager.services.UserDAO;

/**
 * Tests implemented functions of the quiz manager.
 * @author rabeb
 *
 */
public class QuizTests {
	
	/**
	 * Test creating an MCQQuestion.
	 */
	public static void testCreateMCQQuestion()
	{
		MCQChoice choice1 = new MCQChoice("Encapsulation", false);
		MCQChoice choice2 = new MCQChoice("Polymorphism", false);
		MCQChoice choice3 = new MCQChoice("Abstraction", true);
		MCQChoice choice4 = new MCQChoice("Inheritance", false);
		List<MCQChoice> choices = new ArrayList<MCQChoice>();
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		choices.add(choice4);
		Topic topic = new Topic("java");
		MCQQuestion mcqQuestion= new MCQQuestion(" A process that involves recognizing and "
				+ "focusing on the important characteristics of a situation or object is known as:\n" + 
				"\n" + 
				"", 1, topic, choices);
	    DAO<MCQQuestion> mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
		mcqDao.create(mcqQuestion);
	}
	
	/**
	 * Test creating an OpenQuestion.
	 */
	public static void testCreateOpenQuestion()
	{
		Topic topic = new Topic("java");
		OpenQuestion mcqQuestion= new OpenQuestion(" Define inheritence: \n",1 , topic);
	    DAO<OpenQuestion> openDao = new OpenQuestionDAO(DBConnection.getInstance());
		openDao.create(mcqQuestion);
	}
	
	/**
	 * Test creating an admin user and a student.
	 */
	public static void testCreateUser()
	{
		User admin = new User("admin", "admin", "no id", true);
		User student = new User("toto", "tata", "12345", false);
	    DAO<User> userDao = new UserDAO(DBConnection.getInstance());
		userDao.create(admin);
		userDao.create(student);
	}
	
	/**
	 * Test launches the Admin console.
	 */
	public static void testAdminConsoleMenu()
	{
		AdminConsole console = new AdminConsole();
		console.start();
	}

	/**
	 * Test launches the student console.
	 */
	public static void testStudentConsoleMenu()
	{
		User student = new User("test", "password", "id", false);
		StudentConsole studentConsole = new StudentConsole(student);
		studentConsole.start();
	}
	
	/**
	 * Test launches the main console.
	 */
	public static void testConsoleMenu()
	{
		Console console = new Console();
		console.start();
	}
	
	public static void main(String[] args)
	{
		testCreateMCQQuestion();
		testCreateOpenQuestion();
		testCreateUser();
		testConsoleMenu();
	}
}
