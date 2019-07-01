/*
 * @author Rabeb
 * This class defines other questions.
 */

package com.epita.quizmanager.entities;

/**
 * Question - Generalized type of a quiz question.
 * @author rabeb
 *
 */
public abstract class Question
{
	private String content;
	private int difficulty;
	private Topic topic;
	private QuestionType type;
	
	/**
	 * Constructor of question.
	 * @param content the content of the question
	 * @param difficulty the difficulty of the question 
	 * @param topic The topic of the question
	 * @param type the type of the question either MCQ or Open
	 */
	public Question (String content, int difficulty, Topic topic, QuestionType type)
	{
		this.content= content;
		this.difficulty = difficulty;
		this.topic = topic;
		this.type = type;
	}
	
	/**
	 * Obliges classes extending Question to override toString to display a formatted question.
	 */
	@Override
	public abstract String toString();
	
	/**
	 * Getter of the content of the question.
	 * @return the content of the question.
	 */
	public String getContent ()
	{
		return content;
	}
	
	/**
	 * Getter of the difficulty of the question.
	 * @return the difficulty of the question.
	 */
	public int getDifficulty ()
	{
		return difficulty;
	}
	
	/**
	 * Getter of the topic of the question.
	 * @return the topic of the question.
	 */
	public Topic getTopic ()
	{
		return topic;
	}

	/** Getter of the type of the question.
	 * @return the type of the question.
	 */
	public QuestionType getType()
	{
		return type;
	}
	
	/**
	 * Setter of the content of the question.
	 * @param content
	 */
	public void setContent(String content)
	{
		this.content = content;
	}
	
	/**
	 * Setter of the difficulty of the question.
	 * @param difficulty
	 */
	public void setDifficulty(int difficulty)
	{
		this.difficulty = difficulty;
	}
	
	/**
	 * Setter of the topic of the question.
	 * @param topic
	 */
	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}

	/**
	 * Setter of the type of the question.
	 * @param type
	 */
	public void setType(QuestionType type)
	{
		this.type = type;
	}
}
