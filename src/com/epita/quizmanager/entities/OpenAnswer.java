package com.epita.quizmanager.entities;

import java.util.Scanner;

/**
 * MCQAnswer - User answer to Open questions.
 * @author rabeb
 *
 */
public class OpenAnswer extends Answer {
	
	private String openAnswer;
	
	/**
	 * Stores user's answer to an open question.
	 */
	public void setAnswer(String answer)
	{
		openAnswer = answer;
	}
	
	/**
	 * Overrides toString to display the user's answer.
	 */
	@Override
	public String toString()
	{
		return openAnswer;
	}
}
