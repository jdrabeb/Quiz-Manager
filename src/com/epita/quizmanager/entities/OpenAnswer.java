package com.epita.quizmanager.entities;

import java.util.Scanner;

public class OpenAnswer extends Answer {
	
	private String openAnswer;
	
	public void setAnswer(Question question)
	{
		Scanner input = new Scanner(System.in);
		OpenQuestion openQuestion = (OpenQuestion) question;
		System.out.print("\nEnter your answer:\n");
		openAnswer = input.nextLine();
	}
	
	@Override
	public String toString()
	{
		return openAnswer;
	}
}
