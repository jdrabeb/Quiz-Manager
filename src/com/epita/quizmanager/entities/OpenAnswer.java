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
	public void setAnswer(Question question)
	{
		Scanner input = new Scanner(System.in);
		OpenQuestion openQuestion = (OpenQuestion) question;
		System.out.print("\nEnter your answer:\n");
		openAnswer = input.nextLine();
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
