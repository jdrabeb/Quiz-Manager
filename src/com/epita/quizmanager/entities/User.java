package com.epita.quizmanager.entities;

/**
 * Represents a user which can be an Admin or a student.
 */
public class User
{
	private String name;
	private String password;
	private String id;
	private boolean isAdmin;
	
	/**
	 * Constructor of a user.
	 * @param name The name of the user
	 * @param password The password of the user
	 * @param id The id of the user, is the user is admin the id is not important and can be set to anything.
	 * @param isAdmin A boolean true if the user is an admin or false if the user is a student.
	 */
	public User(String name, String password, String id, Boolean isAdmin)
	{
		this.name = name;
		this.password = password;
		this.id = id;
		this.isAdmin = isAdmin;
	}

	/**
	 * Getter of the name of a user.
	 * @return the name of a user.
	 */
	public String getName() {
		return name;
	}

	/**
	 * Setter of the name of a user.
	 * @param name - The name of a user.
	 */
	public void setName(String name) {
		this.name = name;
	}

	/**
	 * Getter of the password of a user.
	 * @return the password of a user.
	 */
	public String getPassword() {
		return password;
	}

	/**
	 * Setter of the password of a user.
	 * @param password - The password of a user.
	 */
	public void setPassword(String password) {
		this.password = password;
	}

	/**
	 * Getter of the id of a user.
	 * @return the id of a user.
	 */
	public String getId() {
		return id;
	}

	/**
	 * Setter of the id of a user.
	 * @param id - The id of a user.
	 */
	public void setId(String id) {
		this.id = id;
	}

	/**
	 * Getter of the rights of the user.
	 * @return true if the user is an admin, false if the user is a student.
	 */
	public boolean isAdmin() {
		return isAdmin;
	}

	/**
	 * Setter of the rights of the user.
	 * @param isAdmin - true if the user is an admin, false if the user is a student.
	 */
	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
	
	/**
	 * Overrides toString to display the user's info.
	 */
	@Override
	public String toString()
	{
		return "Username: " + this.getName() + "\nPassword: " + this.getPassword() +
				"\nIdentifiant: " + this.getId() + '\n';
	}
}