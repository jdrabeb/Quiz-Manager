package com.epita.quizmanager.entities;

public class User
{
	private String name;
	private String password;
	private String id;
	private boolean isAdmin;
	
	public User(String name, String password, String id, Boolean isAdmin)
	{
		this.name = name;
		this.password = password;
		this.id = id;
		this.isAdmin = isAdmin;
	}
	
    public void saveUser() {
    	//TODO : Save user in database
    }

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public String getId() {
		return id;
	}

	public void setId(String id) {
		this.id = id;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean isAdmin) {
		this.isAdmin = isAdmin;
	}
}