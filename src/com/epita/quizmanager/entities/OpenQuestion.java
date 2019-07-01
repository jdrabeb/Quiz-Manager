package com.epita.quizmanager.entities;

public class OpenQuestion extends Question{
	
	public OpenQuestion(String question, int difficulty, Topic topic)
	{
		super(question, difficulty, topic);
	}
	
	@Override
	public String toString()
	{
		return "Question: " + this.getContent() + "\nDifficulty: " + Integer.toString(this.getDifficulty()) +
				"\nTopic: " + this.getTopic().getTitle() + '\n';
	}
}
