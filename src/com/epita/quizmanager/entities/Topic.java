package com.epita.quizmanager.entities;

/**
 * Topic - Topic of a question.
 * @author rabeb
 *
 */
public class Topic {
	private String title;
	
	/**
	 * Constructor of a topic of a question.
	 * @param title The content of the topic.
	 */
	public Topic(String title)
	{
		this.title = title;
	}

	/**
	 * Getter of the topic.
	 * @return a string with the topic content.
	 */
	public String getTitle() {
		return title;
	}

	/** Setter of a topic.
	 * @param title The content of a topic.
	 */
	public void setTitle(String title) {
		this.title = title;
	}
}
