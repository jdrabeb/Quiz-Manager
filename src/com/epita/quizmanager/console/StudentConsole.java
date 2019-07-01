package com.epita.quizmanager.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.entities.Evaluation;
import com.epita.quizmanager.entities.MCQAnswer;
import com.epita.quizmanager.entities.MCQQuestion;
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
	    int error = mcqDao.displayTopics();
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
		eval.startEvaluation();
		try {
			eval.exportToPdf();
		} catch (DocumentException e) {
			e.printStackTrace();
		}
		}
}
