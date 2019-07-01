package com.epita.quizmanager.entities;

/**
 * OpenQuestion - Question of type Open
 * @author rabeb
 *
 */
public class OpenQuestion extends Question{
	
	/**
	 * Constructor of an open question.
	 * @param content the content of the question
	 * @param difficulty the difficulty of the question 
	 * @param topic The topic of the question
	 */
	public OpenQuestion(String question, int difficulty, Topic topic)
	{
		super(question, difficulty, topic, QuestionType.Open);
	}
	
	/**
	 * Overrides toString to display a formatted an Open question.
	 */
	@Override
	public String toString()
	{
		return "Question: " + this.getContent() + "\nDifficulty: " + Integer.toString(this.getDifficulty()) +
				"\nTopic: " + this.getTopic().getTitle() + '\n';
	}
}
