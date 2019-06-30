/*
 * @author Rabeb
 * This class defines other questions.
 */

package com.epita.quizmanager.entities;

public abstract class Question
{
	private String content;
	private int difficulty;
	private Topic topic;
	
	public Question (String content, int difficulty, Topic topic)
	{
		this.content= content;
		this.difficulty = difficulty;
		this.topic = topic;
	}
	
	@Override
	public abstract String toString();
	
	public String getContent ()
	{
		return content;
	}
	
	public int getDifficulty ()
	{
		return difficulty;
	}
	
	public Topic getTopic ()
	{
		return topic;
	}
	
	public void setContent(String content)
	{
		this.content = content;
	}
	
	public void setDifficulty(int difficulty)
	{
		this.difficulty = difficulty;
	}
	
	public void setTopic(Topic topic)
	{
		this.topic = topic;
	}
}
