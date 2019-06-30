package com.epita.quizmanager.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.entities.MCQChoice;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.Topic;
import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;

public class AdminConsole {
		
	public void start()
	{
		Scanner input = new Scanner(System.in);
	    boolean mainLoop = true;

	    int choice;
	    while(mainLoop)
	    {
	    	System.out.println("What kind of questions do you want to manipulate?\n");
	        System.out.print("1.) MCQ Questions. \n");
	        System.out.print("2.) Open Questions.\n");
	        System.out.print("3.) Exit\n");
	        System.out.print("\nEnter Your Menu Choice: ");
	        choice = input.nextInt();
	        switch(choice)
	        {
	        case 1:
	        	mcqMenu();
	        	break;
	        case 2:
	        	openMenu();
	        	break;
	        case 3:
	        	System.out.println("\nGoodbye.\n");
	        	mainLoop = false;
	        	break;
	        default:
	        	System.out.println("\nInvalid choice, try again.\n");
	        	break;
	        }
	    }
	    input.close();
	}
	
	public void mcqMenu()
	{
		Scanner input = new Scanner(System.in);
		int choice;
		boolean mcqMenuLoop = true;
    	while(mcqMenuLoop)
    	{
    		System.out.println("What do you want to do?\n");
    		System.out.print("1.) Create an MCQ Question.\n");
    		System.out.print("2.) Search an MCQ Question.\n");
    		System.out.print("3.) Update an MCQ Question.\n");
    		System.out.print("4.) Delete an MCQ Question.\n");
    		System.out.print("5.) Exit\n");
    		System.out.print("\nEnter Your Choice: ");
    		choice = input.nextInt();
    		switch(choice)
    		{
    		case 1:
    			createMcqQuestionMenu();
    			break;
    		case 2:
    			searchMcqQuestionMenu();
    			break;
    		case 3:
    			break;
    		case 4:
    			break;
    		case 5:
	        	mcqMenuLoop = false;
    			break;
    		default:
	        	System.out.println("\nInvalid choice, try again.\n");
    			break;
    		}
    	}
	}

	public void createMcqQuestionMenu()
	{
		//TODO : Add exceptions to manage type mismatch.
		List<MCQChoice> choices = new ArrayList<MCQChoice>();
		Scanner input = new Scanner(System.in);
		System.out.print("Enter your question.\n");
		String question = input.nextLine();
		System.out.print("Enter the difficulty of your question.\n");
		int difficulty = input.nextInt();
	    input.nextLine();
		System.out.print("Enter the topic of your question.\n");
		String topicContent = input.nextLine().toLowerCase();
		Topic topic = new Topic(topicContent);
		System.out.print("Enter the number of choices.\n");
		int nbChoices = input.nextInt();
	    input.nextLine();
		for (int i = 1; i <= nbChoices; i++) {
    		System.out.print("Enter the choice number " + i + ". \n" );
    		String content = input.nextLine();
    		System.out.print("Is this the right choice ? true/false");
    		boolean isValid = input.nextBoolean();
    	    input.nextLine();
    		MCQChoice choice = new MCQChoice(content, isValid);
    		choices.add(choice);
		}
		MCQQuestion mcqQuestion = new MCQQuestion(question, difficulty, topic, choices);
	    DAO<MCQQuestion> mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
		mcqDao.create(mcqQuestion);
	}
	
	public void searchMcqQuestionMenu()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the topic to search.\n");
		String topic = input.nextLine();
	    DAO<MCQQuestion> mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
		List<MCQQuestion> questions = new ArrayList<MCQQuestion>();
		questions = mcqDao.find(topic);
		System.out.println("\nYour search result : \n");
		for (MCQQuestion question : questions)
		{
			System.out.println(question.toString());
		}
	}

	public void openMenu()
	{
		Scanner input = new Scanner(System.in);
		int choice;
		boolean openMenuLoop = true;
    	while(openMenuLoop)
    	{
    		System.out.println("What do you want to do?\n");
    		System.out.print("1.) Create an Open Question.\n");
    		System.out.print("2.) Search an Open Question.\n");
    		System.out.print("3.) Update an Open Question.\n");
    		System.out.print("4.) Delete an Open Question.\n");
    		System.out.print("5.) Exit\n");
    		System.out.print("\nEnter Your Choice: ");
    		choice = input.nextInt();
    		switch(choice)
    		{
    		case 1:
    			break;
    		case 2:
    			break;
    		case 3:
    			break;
    		case 4:
    			break;
    		case 5:
    			openMenuLoop = false;
    			break;
    		default:
	        	System.out.println("\nInvalid choice, try again.\n");
    			break;
    		}
    	}
	}
}
