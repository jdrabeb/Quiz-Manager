package com.epita.quizmanager.entities;

/**
 * Answer - Answer given by user to quiz questions.
 * @author rabeb
 *
 */
public abstract class Answer {

	public abstract void setAnswer(Question question);
	
	/**
	 * Overriding toString
	 * All classes extending this class have to define toString.
	 */
	@Override
	public abstract String toString();
	
}
