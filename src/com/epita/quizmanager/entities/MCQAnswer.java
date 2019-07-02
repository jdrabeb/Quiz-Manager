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
	public void setAnswer(MCQChoice answer)
	{
		mcqAnswer = answer;
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
