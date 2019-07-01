package com.epita.quizmanager.entities;

import java.util.ArrayList;
import java.util.List;

import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;

public class Quiz {
	
	private List<Topic> topics = new ArrayList<Topic>();
	private List<MCQQuestion> mcqQuestions = new ArrayList<MCQQuestion>();

	public Quiz(List<Topic> topics)
	{
		this.topics = topics;
	    DAO<MCQQuestion> mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
		List<MCQQuestion> foundQuestions = new ArrayList<MCQQuestion>();
		for (Topic topic: topics)
		{
			foundQuestions = mcqDao.find(topic.getTitle());
			mcqQuestions.addAll(foundQuestions);
		}
	}
	
	public List<MCQQuestion> getMcqQuestions()
	{
		return mcqQuestions;
	}
	
	@Override
	public String toString()
	{
		String toString = "";
		int index = 0;
		for (MCQQuestion mcqQuestion : mcqQuestions)
		{
			index++;
			toString = toString + "Question " + index + ": \n\n" + mcqQuestion.toString() + "\n";
		}
		return toString;
	}
}
