package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQAnswer extends Answer {
	
	MCQChoice mcqAnswer;
	
//	List<MCQChoice> answers = new ArrayList<MCQChoice>();
//	
//	public List<MCQChoice> getAnswers()
//	{
//		return answers;
//	}
	
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
	
	public MCQChoice getAnswer()
	{
		return mcqAnswer;
	}
	
	@Override
	public String toString()
	{
		return mcqAnswer.getContent();
	}

}
