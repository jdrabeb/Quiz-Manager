package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class MCQAnswer {
	
	List<MCQChoice> answers = new ArrayList<MCQChoice>();
	
	public List<MCQChoice> getAnswers(List<MCQQuestion> questions)
	{
		//TODO : Several answers
		for (MCQQuestion question : questions)
		{
			Scanner input = new Scanner(System.in);
			System.out.print(question.toString());
			int nbChoices = question.numberOfChoices();
			int answer;
			do
			{
				System.out.print("\nEnter your answer:\n");
				answer = input.nextInt();
				input.nextLine();
			} while (answer < 1 || answer > nbChoices);
			answers.add(question.getChoices().get(answer - 1));
		}
		return answers;
	}
	
	public int calculateGrade(List<MCQQuestion> questions)
	{
		int grade = 0;
		int index = 0;
		for (MCQQuestion question : questions)
		{
			MCQChoice answer = answers.get(index);
			int position = question.getChoices().indexOf(answer);
			if (question.getChoices().get(position + 1).isValid() == answer.isValid())
			{
				grade++;
			}
		}
		return grade;
	}
}
