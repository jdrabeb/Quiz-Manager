package com.epita.quizmanager.entities;

/**
 * MCQChoice - A choice of an MCQ questions.
 * @author rabeb
 *
 */
public class MCQChoice {
	
	private String content;
	private boolean isValid;
	
	/**
	 * Constructor of MCQchoice.
	 * @param content
	 * @param isValid
	 */
	public MCQChoice(String content, boolean isValid) {
		this.content = content;
		this.isValid = isValid;
	}

	/**
	 * Getter of the content of an MCQChoice.
	 * @return String of the content of the choice.
	 */
	public String getContent() {
		return content;
	}

	/**
	 * Setter of the content of an MCQChoice.
	 * @param content the value of choice.
	 */
	public void setContent(String content) {
		this.content = content;
	}

	/**
	 * Getter of the choice being the right answer or not.
	 * @return Boolean checking the validity of the choice.
	 */
	public boolean isValid() {
		return isValid;
	}

	/**
	 * Setter of  of the choice being the right answer or not.
	 * @param isValid True if it is a good choice, False if not.
	 */
	public void setValid(boolean isValid) {
		this.isValid = isValid;
	}
}