package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;

public class Evaluation {
	private User user;
	private Quiz quiz;
	private List<MCQQuestion> mcqQuestions = new ArrayList<MCQQuestion>();

	public Evaluation (User user, Quiz quiz)
	{
		this.user = user;
		this.quiz = quiz;
		mcqQuestions = quiz.getMcqQuestions();
	}
}
