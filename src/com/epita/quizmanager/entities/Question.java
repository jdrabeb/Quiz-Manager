/*
 * @author Rabeb
 * This class defines other questions.
 */

package com.epita.quizmanager.entities;

import java.util.List;

public class Question
{
	private String question;
	private int difficulty;
//	private List<Topic> topics;
	private QuestionType type;
	
	public Question (String question, int difficulty, QuestionType type)
	{
		this.question = question;
		this.difficulty = difficulty;
		this.type = type;
	}
	
	public String getQuestion ()
	{
		return question;
	}
	
	public int getDifficulty ()
	{
		return difficulty;
	}
	
//	public List<Topic> getTopics ()
//	{
//		return topics;
//	}
	
	public QuestionType getQuestionType ()
	{
		return type;
	}
	
	public void setQuestion (String question)
	{
		this.question = question;
	}
	
	public void setDifficulty (int difficulty)
	{
		this.difficulty = difficulty;
	}
	
//	public void setTopics (List<Topic> topics)
//	{
//		this.topics = topics;
//	}
	
	public void setType (QuestionType type)
	{
		this.type = type;
	}

}
