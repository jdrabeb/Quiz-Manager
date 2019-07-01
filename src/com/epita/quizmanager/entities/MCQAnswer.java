package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * MCQAnswer - User answer to questions of type MCQ.
 * @author rabeb
 *
 */
public class MCQAnswer extends Answer {
	
	MCQChoice mcqAnswer;
	
	/**
	 * Stores user's answer to an MCQ question.
	 * @param question An MCQ question of the quiz.
	 *
	 */
	public void setAnswer(Question question)
	{
		Scanner input = new Scanner(System.in);
		MCQQuestion mcqQuestion = (MCQQuestion) question;
		int nbChoices = mcqQuestion.numberOfChoices();
		int choice;
		do
		{
			System.out.print("\nEnter your answer:\n");
			choice = input.nextInt();
			input.nextLine();
		} while (choice < 1 || choice > nbChoices);
		mcqAnswer = mcqQuestion.getChoices().get(choice - 1);
	}
	
	/**
	 * Getter of the answer given by the user to an MCQ question.
	 * @return the answer.
	 */
	public MCQChoice getAnswer()
	{
		return mcqAnswer;
	}
	
	/**
	 * Overrides toString to display the user's answer.
	 */
	@Override
	public String toString()
	{
		return mcqAnswer.getContent();
	}

}
