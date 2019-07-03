package com.epita.quizmanager.console;

import java.util.Scanner;

import com.epita.quizmanager.console.Session;
import com.epita.quizmanager.entities.User;
import com.epita.quizmanager.services.DAO;
import com.epita.quizmanager.services.DBConnection;
import com.epita.quizmanager.services.UserDAO;
import com.itextpdf.text.DocumentException;

/**
 * The main console launches when the quiz manager is first launched
 * @author rabeb
 *
 */
public class Console {
	/**
	 * Starts the console. Adds user to database on register. 
	 * Verifies the info of the user on login.
	 * Launches AdminConsole if the user logged in is an admin.
	 * Launches StudentConsole if the user logged in is a student.
	 * Else exists
	 * @see ConsoleAdmin
	 * @see StudentAdmin
	 */
	public void start()
	{
		int choice;
		Scanner input = new Scanner(System.in);
		boolean mainLoop = true;
		while (mainLoop)
		{
			System.out.println("Welcome to the Quiz Manager..\n\n");
			System.out.println("1) Register \n2) Login\n3) Exit\n");
	        choice = input.nextInt();
	        input.nextLine();
	        switch(choice)
	        {
	        	case 1:
	        	{
					System.out.println("Enter a username");
					String username = input.nextLine();
					System.out.println("Enter a password");
					String password = input.nextLine();
					System.out.println("Enter your id");
					String id = input.nextLine();
					System.out.println("Are you an admin ? y/n");
					String response;
					boolean isAdmin = false;
					do
					{ 
						response = input.nextLine(); 
					} while ( !"n".equals(response.toLowerCase()) && !"y".equals(response.toLowerCase()));
					if ("y".equals(response.toLowerCase()))
					{
						isAdmin = true;
					}
					User newUser = new User(username, password, id, isAdmin);
				    DAO<User> userDao = new UserDAO(DBConnection.getInstance());
					userDao.create(newUser);
					System.out.println("\nUser registered successfully !\n");
	        		break;
	        	}
	        	case 2:
					System.out.println("Please enter your username");
				    String username = input.nextLine();
				    System.out.println("Please enter your password");
				    String password = input.nextLine();
					User user = Session.login(username, password);
					if (user != null)
					{
						System.out.println("\nHello " + user.getName() + " !\n");
						if (user.isAdmin())
						{
							AdminConsole adminConsole = new AdminConsole();
							adminConsole.start();
						}
						else
						{
							StudentConsole studentConsole = new StudentConsole(user);
							studentConsole.start();
						}	
					}
	        		break;
	        	case 3:
	        		mainLoop = false;
	        		System.out.println("\nGoodbye !");
	        		break;
	        	default:
	        	System.out.println("\nInvalid choice, try again.\n");
	        		break;
			}
		}
	}
}
