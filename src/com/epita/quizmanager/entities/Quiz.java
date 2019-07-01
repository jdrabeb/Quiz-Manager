package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;
import com.epita.quizmanager.services.OpenQuestionDAO;

public class Quiz {
	
	private List<Topic> topics = new ArrayList<Topic>();
	//TODO : make one list List<Question>
	private List<Question> questions = new ArrayList<Question>();

	public Quiz(List<Topic> topics)
	{
		this.topics = topics;
	    DAO<MCQQuestion> mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
		List<MCQQuestion> foundMcqQuestions = new ArrayList<MCQQuestion>();
	    DAO<OpenQuestion> openDao = new OpenQuestionDAO(DBConnection.getInstance());
		List<OpenQuestion> foundOpenQuestions = new ArrayList<OpenQuestion>();

		for (Topic topic: topics)
		{
			foundMcqQuestions = mcqDao.find(topic.getTitle());
			questions.addAll(foundMcqQuestions);
			foundOpenQuestions = openDao.find(topic.getTitle());
			questions.addAll(foundOpenQuestions);
		}
	}
	
	public List<Question> getQuestions()
	{
		return questions;
	}
	
	@Override
	public String toString()
	{
		String toString = "";
		int index = 0;
		for (Question question : questions)
		{
			index++;
			toString = toString + "Question " + index + ": \n\n" + question.toString() + "\n";
		}
		return toString;
	}
}
