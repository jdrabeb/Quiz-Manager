package com.epita.quizmanager.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.entities.MCQAnswer;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.Quiz;
import com.epita.quizmanager.entities.Topic;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;

public class StudentConsole {
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
		MCQAnswer answers = new MCQAnswer();
		answers.getAnswers(quiz.getMcqQuestions());
		int grade = answers.calculateGrade(quiz.getMcqQuestions());
	}
}
