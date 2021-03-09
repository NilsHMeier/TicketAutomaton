package de.leuphana.cosa.messagingsystem.structure.content;

public class EmailContent implements Content {
	private final String text;
	private String attachment;
	
	public EmailContent(String content) {
		this.text = content;
	}

	public String getText() {
		return text;
	}
}
