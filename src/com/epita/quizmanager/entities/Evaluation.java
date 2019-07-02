package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.services.PDFFormatter;
import com.itextpdf.text.DocumentException;

/**
 * Evaluation - Sets a quiz for a user, calculate their grade
 * and exports the quiz and the user answers in a pdf.
 * @author rabeb
 *
 */
public class Evaluation {
	private User user;
	private Quiz quiz;
	private List<Question> questions = new ArrayList<Question>();
	private List<Answer> answers = new ArrayList<Answer>();
	
	private int grade;

	/**
	 * Constructor of class Evaluation
	 * @param user
	 * @param quiz
	 */
	public Evaluation (User user, Quiz quiz)
	{
		this.user = user;
		this.quiz = quiz;
		questions = quiz.getQuestions();
	}
	
	/**
	 * Starts the evaluation.
	 * Goes through quiz questions and saves user answers.
	 * Calculates grade in the way.
	 * @param question - the quiz question to get an answer of.
	 * @param answer - The answer typed by the user in the console
	 */
	public int startEvaluation(Question question, String answer)
	{
		if (question.getType() == QuestionType.MCQ)
		{
			MCQAnswer mcqAnswer = new MCQAnswer();
			MCQQuestion mcqQuestion = (MCQQuestion) question;
			int nbChoices = mcqQuestion.numberOfChoices();
			int choice = Integer.valueOf(answer);
			if (choice < 1 || choice > nbChoices)
				return -1;
			mcqAnswer.setAnswer(mcqQuestion.getChoices().get(choice - 1));
			answers.add(mcqAnswer);				
			if (mcqAnswer.getAnswer().isValid())
			{
				grade += 1;
			}
		}
		else if (question.getType() == QuestionType.Open)
		{
			OpenAnswer OpenAnswer = new OpenAnswer();
			OpenAnswer.setAnswer(answer);
			answers.add(OpenAnswer);					
		}
		return 0;
	}
	
	public int getGrade()
	{
		return grade;
	}
	
	/**
	 * Exports taken quiz with the user answers and their grade to PDF.
	 * @throws DocumentException
	 */
	public void exportToPdf(String pdfTitle) throws DocumentException
	{
		//TODO : add pdf title to param and make it a configurable variable
		PDFFormatter pdfFormat = new PDFFormatter();
		pdfFormat.createPdf(pdfTitle);
		pdfFormat.addTitlePage("Quiz Evaluation", user.getName());
		int index = 0;
		for (Question question : questions)
		{
			pdfFormat.addQuestion(question.toString());
			pdfFormat.addAnswer(answers.get(index).toString());
			index++;
		}
		pdfFormat.addGrade(grade);
		pdfFormat.closeDocument();
	}
}
