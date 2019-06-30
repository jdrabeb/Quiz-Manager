package com.epita.quizmanager.tests;

import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.entities.MCQChoice;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.Question;
import com.epita.quizmanager.entities.QuestionType;
import com.epita.quizmanager.entities.Topic;
import com.epita.quizmanager.entities.User;
import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.UserDAO;

public class DAOQuestionMain {
	public static void main(String[] args)
	{
//		QuestionDAO dao = new QuestionDAO();
//		Question question = new Question ("test", 1, QuestionType.MCQ);
//		MCQQuestion mcqQuestion = new MCQQuestion("mcqtest6", 1, QuestionType.MCQ, "Answer 1", "Answer 1", "Answer 2", "Answer 3");
//		dao.createQuestion(question);
//		dao.createMCQQuestion(mcqQuestion);
//	    DAO<User> userDao = new UserDAO(DBConnection.getInstance());
//		User student = new User("francis", "test2", "x13", true);
//		userDao.create(student);
		MCQChoice choice1 = new MCQChoice("choice1", false);
		MCQChoice choice2 = new MCQChoice("choice2", true);
		MCQChoice choice3 = new MCQChoice("choice3", false);
		List<MCQChoice> choices = new ArrayList<MCQChoice>();
		choices.add(choice1);
		choices.add(choice2);
		choices.add(choice3);
		Topic test = new Topic("test");
		MCQQuestion mcqQuestion= new MCQQuestion("This is a question?", 0, test, choices);
		System.out.println(mcqQuestion.toString());
	}
}
