package com.epita.quizmanager.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.entities.Evaluation;
import com.epita.quizmanager.entities.MCQAnswer;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.OpenAnswer;
import com.epita.quizmanager.entities.Question;
import com.epita.quizmanager.entities.QuestionType;
import com.epita.quizmanager.entities.Quiz;
import com.epita.quizmanager.entities.Topic;
import com.epita.quizmanager.entities.User;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;
import com.itextpdf.text.DocumentException;

/**
 * StudentConsole - The console displayed when the user logged in is a student.
 * @author rabeb
 *
 */
public class StudentConsole {
	private User student;
	
	/**
	 * StudentConsole constructor. Stocks the information of the student logged.
	 * @param student - The student logged.
	 */
	public StudentConsole(User student)
	{
		this.student = student;
	}
	
	/**
	 * Launches the menu to choose a list of topics and start an evaluation based
	 * on theses topics.
	 */
	public void start()
	{
		List<Topic> topics = new ArrayList<Topic>();
	    MCQQuestionDAO mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
	    String topicsToDisplay = mcqDao.displayTopics();
	    if (topicsToDisplay == null)
	    {
			System.out.println("\nThere was an error displaying the topics.");
			return;
	    }
	    else if (topicsToDisplay.isEmpty())
	    {
			System.out.println("\nThere are no Topics.");
			return;
	    }
	    System.out.println("The topics available are:\n");
	    System.out.println(topicsToDisplay);
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the number of topics you want.\n");
		int topicsNumber = input.nextInt();
		input.nextLine();
		for (int i = 1; i <= topicsNumber; i++) {
			System.out.println("Topic " + i + ": ");
			String topicContent = input.nextLine();
			Topic topic = new Topic(topicContent);
			topics.add(topic);
		}
		Quiz quiz = new Quiz(topics);
		Evaluation eval = new Evaluation(student, quiz);
		
		for (Question question : quiz.getQuestions())
		{
			int error;
			System.out.print(question.toString());
			do
			{
				System.out.print("\nEnter your answer:\n");
				String answer = input.nextLine();
				error = eval.startEvaluation(question, answer);
			}
			while (error == -1);
		}
		System.out.print("Your grade is : " + eval.getGrade() + ("\n"));
		System.out.print("Do you want to export your quiz in pdf ? y/n");
		String response = input.next();
		input.nextLine();
		switch (response)
		{
			case "y":
			case "Y":
				System.out.print("Type the name you want to give to your pdf to export \n");
				String pdfTitle = input.nextLine();
				try {
					eval.exportToPdf(pdfTitle);
				} catch (DocumentException e) 
				{
					e.printStackTrace();
				}
				break;
			case"n":
			case"N":
				break;
			default:
				System.out.println("Unknown answer.");
				break;
		}
	}
}
