package com.epita.quizmanager.entities;

import java.io.FileNotFoundException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import java.util.List;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;

public class Evaluation {
	private User user;
	private Quiz quiz;
	private List<MCQQuestion> mcqQuestions = new ArrayList<MCQQuestion>();

	public Evaluation (User user, Quiz quiz)
	{
		this.user = user;
		this.quiz = quiz;
		mcqQuestions = quiz.getMcqQuestions();
	}
	
	public void startEvaluation(List<Topic> topics)
	{
		MCQAnswer answers = new MCQAnswer();
		answers.setAnswers(quiz.getMcqQuestions());
		int grade = calculateGrade(quiz.getMcqQuestions(), answers.getAnswers());
		System.out.println("grade is: " + grade);
	}
	
	public int calculateGrade(List<MCQQuestion> questions, List<MCQChoice> answers)
	{
		int grade = 0;
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
	
	public void exportToPdf()
	{
		Document document = new Document();
	    try
	    {
	    	PdfWriter writer = PdfWriter.getInstance(document, new FileOutputStream("HelloWorld.pdf"));
	    	document.open();
	        document.add(new Paragraph("A Hello World PDF document."));
	        document.close();
	        writer.close();
	    }
	    catch (DocumentException e)
	    {
	    	e.printStackTrace();
	    }
	    catch (FileNotFoundException e)
	    {
	    	e.printStackTrace();
	    }
	}
}
