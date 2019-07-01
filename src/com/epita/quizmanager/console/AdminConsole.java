package com.epita.quizmanager.console;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

import com.epita.quizmanager.entities.MCQChoice;
import com.epita.quizmanager.entities.MCQQuestion;
import com.epita.quizmanager.entities.OpenQuestion;
import com.epita.quizmanager.entities.Topic;
import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.MCQQuestionDAO;
import com.epita.quizmanager.services.OpenQuestionDAO;

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
    			updateMcqQuestionMenu();
    			break;
    		case 4:
    			deleteMcqQuestionMenu();
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

	public void updateMcqQuestionMenu()
	{
		Scanner input = new Scanner(System.in);
	    MCQQuestionDAO mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
	    int error = mcqDao.displayQuestions();
	    if (error == 0)
	    {
			System.out.print("Enter the question_id of the question to update.\n");
			System.out.print("Enter 0 to go back.\n");
			int question_id = input.nextInt();
			if (question_id == 0)
			{
				return;
			}
			System.out.print("What do you want to update.\n");
			System.out.print("1) Question title.\n");
			System.out.print("2) difficulty.\n");
			System.out.print("3) Back.\n");
    		int choice = input.nextInt();
    		switch(choice)
    		{
    		case 1:
    			System.out.print("Enter the new question title.\n");
    			input.nextLine();
        		String newQuestion = input.nextLine();
    			mcqDao.updateQuestion(question_id, newQuestion);
    			break;
    		case 2:
    			System.out.print("Enter the new difficulty.\n");
        		int newDifficulty = input.nextInt();
    			mcqDao.updateDifficulty(question_id, newDifficulty);
    			break;
    		case 3:
    			break;
    		default:
	        	System.out.println("\nInvalid choice, try again.\n");
    			break;
    		}
	    }
	    else
	    {
			System.out.print("No question to update.\n");
	    }
	}
	
	public void deleteMcqQuestionMenu()
	{
	    MCQQuestionDAO mcqDao = new MCQQuestionDAO(DBConnection.getInstance());
		Scanner input = new Scanner(System.in);
	    int error = mcqDao.displayQuestions();
	    if (error == 0)
	    {
			System.out.print("Enter the question_id of the question to delete.\n");
			System.out.print("Enter 0 to go back.\n");
			int question_id = input.nextInt();
			if (question_id == 0)
			{
				return;
			}
			mcqDao.delete(question_id);
	    }
	    else
	    {
			System.out.print("No question to delete.\n");
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
    			createOpenQuestionMenu();
    			break;
    		case 2:
    			searchOpenQuestionMenu();
    			break;
    		case 3:
    			updateOpenQuestionMenu();
    			break;
    		case 4:
    			deleteOpenQuestionMenu();
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
	
	public void createOpenQuestionMenu()
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
		OpenQuestion openQuestion = new OpenQuestion(question, difficulty, topic);
	    DAO<OpenQuestion> openDao = new OpenQuestionDAO(DBConnection.getInstance());
		openDao.create(openQuestion);
	}
	
	public void searchOpenQuestionMenu()
	{
		Scanner input = new Scanner(System.in);
		System.out.print("Enter the topic to search.\n");
		String topic = input.nextLine();
	    DAO<OpenQuestion> openDao = new OpenQuestionDAO(DBConnection.getInstance());
		List<OpenQuestion> questions = new ArrayList<OpenQuestion>();
		questions = openDao.find(topic);
		System.out.println("\nYour search result : \n");
		for (OpenQuestion question : questions)
		{
			System.out.println(question.toString());
		}
	}

	public void updateOpenQuestionMenu()
	{
		Scanner input = new Scanner(System.in);
	    OpenQuestionDAO openDao = new OpenQuestionDAO(DBConnection.getInstance());
	    int error = openDao.displayQuestions();
	    if (error == 0)
	    {
			System.out.print("Enter the question_id of the question to update.\n");
			System.out.print("Enter 0 to go back.\n");
			int question_id = input.nextInt();
			if (question_id == 0)
			{
				return;
			}
			System.out.print("What do you want to update.\n");
			System.out.print("1) Question title.\n");
			System.out.print("2) difficulty.\n");
			System.out.print("3) Back.\n");
    		int choice = input.nextInt();
    		switch(choice)
    		{
    		case 1:
    			System.out.print("Enter the new question title.\n");
    			input.nextLine();
        		String newQuestion = input.nextLine();
    			openDao.updateQuestion(question_id, newQuestion);
    			break;
    		case 2:
    			System.out.print("Enter the new difficulty.\n");
        		int newDifficulty = input.nextInt();
    			openDao.updateDifficulty(question_id, newDifficulty);
    			break;
    		case 3:
    			break;
    		default:
	        	System.out.println("\nInvalid choice, try again.\n");
    			break;
    		}
	    }
	    else
	    {
			System.out.print("No question to update.\n");
	    }
	}
	
	public void deleteOpenQuestionMenu()
	{
	    OpenQuestionDAO openDao = new OpenQuestionDAO(DBConnection.getInstance());
		Scanner input = new Scanner(System.in);
	    int error = openDao.displayQuestions();
	    if (error == 0)
	    {
			System.out.print("Enter the question_id of the question to delete.\n");
			System.out.print("Enter 0 to go back.\n");
			int question_id = input.nextInt();
			if (question_id == 0)
			{
				return;
			}
			openDao.delete(question_id);
	    }
	    else
	    {
			System.out.print("No question to update.\n");
	    }
	}
	
}
