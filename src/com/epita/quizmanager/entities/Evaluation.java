package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;

public class Evaluation {
	private User user;
	private Quiz quiz;
	private int grade;
	private List<MCQQuestion> mcqQuestions = new ArrayList<MCQQuestion>();
	MCQQuestion current;

	public Evaluation (User user, Quiz quiz)
	{
		this.user = user;
		this.quiz = quiz;
		mcqQuestions = quiz.getMcqQuestions();
	}
	
	public getNextQuestion()
	{
		
	}
	
	public void getAnswer(MCQAnswer answer)
	{
		
	}

	public void calculateGrade ()
	{
		
	}
}
