package com.epita.quizmanager.entities;

import java.util.List;

/**
 * MCQQuestion - Question of type MCQ.
 * @author rabeb
 *
 */
public class MCQQuestion extends Question
{
	private List<MCQChoice> choices;
	
	/**
	 * Constructor of MCQQuestion
	 * @param question the string of the question.
	 * @param difficulty the difficulty of the question.
	 * @param topic the topic the question covers.
	 * @param choices the multiple choices linked to the question.
	 */
	public MCQQuestion(String question, int difficulty, Topic topic, List<MCQChoice> choices)
	{
		super(question, difficulty, topic, QuestionType.MCQ);
		this.choices = choices;
	}
	
	/**
	 *  Getter of the multiple choices linked to the MCQ question.
	 * @return a list of the choices.
	 */
	public List<MCQChoice> getChoices()
	{
		return choices;
	}
	
	/**
	 *  Returns the number of choices linked the an MCQ question.
	 * @return number of choices of the question.
	 */
	public int numberOfChoices()
	{
		return choices.size();
	}
	
	/**
	 * Overrides toString to display a formatted MCQ question.
	 */
	@Override
	public String toString()
	{
		String toString = "Question: " + this.getContent() + "\nDifficulty: " + Integer.toString(this.getDifficulty()) +
				"\nTopic: " + this.getTopic().getTitle() + '\n';
		int index = 0;
		for (MCQChoice choice : choices)
		{
			index += 1;
			toString = toString + index + ") " + choice.getContent() + "\n"; 
		}
		return toString;
	}
}
