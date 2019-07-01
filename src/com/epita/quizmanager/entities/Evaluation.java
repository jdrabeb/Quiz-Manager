package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.services.PDFFormatter;
import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;

public class Evaluation {
	private User user;
	private Quiz quiz;
	private List<MCQQuestion> mcqQuestions = new ArrayList<MCQQuestion>();
	private MCQAnswer answers = new MCQAnswer();
	
	private int grade;

	public Evaluation (User user, Quiz quiz)
	{
		this.user = user;
		this.quiz = quiz;
		mcqQuestions = quiz.getMcqQuestions();
	}
	
	public void startEvaluation(List<Topic> topics)
	{
		answers.setAnswers(quiz.getMcqQuestions());
		int grade = calculateGrade(quiz.getMcqQuestions(), answers.getAnswers());
		System.out.println("grade is: " + grade);
	}
	
	public int calculateGrade(List<MCQQuestion> questions, List<MCQChoice> answers)
	{
		int index = 0;
		for (MCQQuestion question : questions)
		{
			MCQChoice answer = answers.get(index);
			index++;
			int position = question.getChoices().indexOf(answer);
			if (answer.isValid())
			{
				grade++;
			}
		}
		return grade;
	}
	
	public void exportToPdf() throws DocumentException
	{
		PDFFormatter pdfFormat = new PDFFormatter();
		Document document = pdfFormat.CreatePdf("test.pdf");
		pdfFormat.addTitlePage(document, "Quiz Evaluation", user.getName());
		int index = 0;
		for (MCQQuestion question : mcqQuestions)
		{
			pdfFormat.addQuestion(document, question.toString());
			pdfFormat.addAnswer(document, answers.getAnswers().get(index).getContent());
			index++;
		}
		pdfFormat.addGrade(document, grade);
		pdfFormat.closeDocument(document);
	}
}
