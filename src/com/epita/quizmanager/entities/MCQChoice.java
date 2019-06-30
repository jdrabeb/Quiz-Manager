package com.epita.quizmanager.entities;

public class MCQChoice {
	private String content;
	private boolean isValid;
	
	public MCQChoice(String content, boolean isValid) {
		this.content = content;
		this.isValid = isValid;
	}

	public String getContent() {
		return content;
	}

	public void setContent(String content) {
		this.content = content;
	}

	public boolean isValid() {
		return isValid;
	}

	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}