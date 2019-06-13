package com.epita.quizmanager.entities;

public class MCQQuestion extends Question
{
	String correctAnswer;
	String firstChoice;
	String secondChoice;
	String thirdChoice;
	String answer;

	public MCQQuestion(String question, int difficulty, QuestionType type, 
			String correctAnswer, String firstChoice, String secondChoice,
			String thirdChoice)
	{
		super(question, difficulty, type);
		this.firstChoice = firstChoice;
		this.secondChoice = secondChoice;
		this.thirdChoice = thirdChoice;
		this.correctAnswer = correctAnswer;
	}
	
	public void setCorrectAnswer (String correctAnswer)
	{
		this.correctAnswer = correctAnswer;
	}

	public void setFirstChoice (String firstChoice)
	{
		this.firstChoice = firstChoice;
	}
	
	public void setSecondChoice (String secondChoice)
	{
		this.secondChoice = secondChoice;
	}

	public void setThirdChoice (String thirdChoice)
	{
		this.thirdChoice = thirdChoice;
	}
	
	public void setAnswer (String answer)
	{
		this.answer = answer;
	}
	
	public String getCorrectAnswer ()
	{
		return correctAnswer;
	}

	public String getFirstChoice ()
	{
		return firstChoice;
	}
	
	public String getSecondChoice ()
	{
		return secondChoice;
	}

	public String getThirdChoice ()
	{
		return thirdChoice;
	}
	
	public String getAnswer ()
	{
		return answer;
	}
}
