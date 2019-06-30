package com.epita.quizmanager.console;

import java.util.Scanner;

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
    			break;
    		case 2:
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
