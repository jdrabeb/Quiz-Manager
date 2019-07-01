package com.epita.quizmanager.entities;

public abstract class Answer {

	public abstract void setAnswer(Question question);
	
	@Override
	public abstract String toString();
	
}
