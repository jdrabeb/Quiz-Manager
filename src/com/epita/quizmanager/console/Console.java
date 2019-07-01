package com.epita.quizmanager.console;

import java.util.Scanner;

import com.epita.quizmanager.entities.Session;
import com.epita.quizmanager.entities.User;

public class Console {
	public void start()
	{
		Scanner input = new Scanner(System.in);
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
				StudentConsole studentConsole = new StudentConsole();
				studentConsole.start();
			}
				
		}
	}
}
