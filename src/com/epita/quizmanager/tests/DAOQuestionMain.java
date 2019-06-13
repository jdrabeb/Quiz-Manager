package com.epita.quizmanager.tests;

import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.Question;
import com.epita.quizmanager.entities.QuestionType;
import com.epita.quizmanager.services.QuestionDAO;

public class DAOQuestionMain {
	public static void main(String[] args)
	{
		QuestionDAO dao = new QuestionDAO();
		Question question = new Question ("test", 1, QuestionType.MCQ);
		MCQQuestion mcqQuestion = new MCQQuestion("mcqtest6", 1, QuestionType.MCQ, "Answer 1", "Answer 1", "Answer 2", "Answer 3");
		dao.createQuestion(question);
		dao.createMCQQuestion(mcqQuestion);
	}
}
