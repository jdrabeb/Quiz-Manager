package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.services.PDFFormatter;
import com.itextpdf.text.Document;
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
	 * Calculates grade in the way and displays it at the end.
	 */
	public void startEvaluation()
	{
//		//TODO : Several answers
		for (Question question : questions)
		{
			Scanner input = new Scanner(System.in);
			System.out.print(question.toString());
			if (question.getType() == QuestionType.MCQ)
			{
				MCQAnswer mcqAnswer = new MCQAnswer();
				mcqAnswer.setAnswer(question);
				answers.add(mcqAnswer);
				System.out.println();
				if (mcqAnswer.getAnswer().isValid())
				{
					grade += 1;
				}
			}
			else if (question.getType() == QuestionType.Open)
			{
				OpenAnswer OpenAnswer = new OpenAnswer();
				OpenAnswer.setAnswer(question);
				answers.add(OpenAnswer);					
			}
			else
			{
				System.out.println("Unkown question type.");
			}
		}
		System.out.println("grade is: " + grade);
	}

	
	/**
	 * Exports taken quiz with the user answers and their grade to PDF.
	 * @throws DocumentException
	 */
	public void exportToPdf() throws DocumentException
	{
		//TODO : add pdf title to param and make it a conigurable variable
		PDFFormatter pdfFormat = new PDFFormatter();
		Document document = pdfFormat.CreatePdf("test.pdf");
		pdfFormat.addTitlePage(document, "Quiz Evaluation", user.getName());
		int index = 0;
		for (Question question : questions)
		{
			pdfFormat.addQuestion(document, question.toString());
			pdfFormat.addAnswer(document, answers.get(index).toString());
			index++;
		}
		pdfFormat.addGrade(document, grade);
		pdfFormat.closeDocument(document);
	}
}
