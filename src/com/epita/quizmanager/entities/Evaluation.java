package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.services.PDFFormatter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class Evaluation {
	private User user;
	private Quiz quiz;
	private List<Question> questions = new ArrayList<Question>();
	private List<Answer> answers = new ArrayList<Answer>();
	
	private int grade;

	public Evaluation (User user, Quiz quiz)
	{
		this.user = user;
		this.quiz = quiz;
		questions = quiz.getQuestions();
	}
	
	public void startEvaluation(List<Topic> topics)
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

	
	public void exportToPdf() throws DocumentException
	{
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
