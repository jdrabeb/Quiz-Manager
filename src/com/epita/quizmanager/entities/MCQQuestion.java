package com.epita.quizmanager.entities;

import java.util.List;

public class MCQQuestion extends Question
{
	private List<MCQChoice> choices;
	
	public MCQQuestion(String question, int difficulty, Topic topic, List<MCQChoice> choices)
	{
		super(question, difficulty, topic);
		this.choices = choices;
	}
	
	public List<MCQChoice> getChoices()
	{
		return choices;
	}
	
	public int numberOfChoices()
	{
		return choices.size();
	}
	
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
