package de.leuphana.cosa.messagingsystem.structure.message;

import de.leuphana.cosa.messagingsystem.structure.content.Content;

public class MessageBody {
	private final Content content;
	
	public MessageBody(Content content) {
		this.content = content;
	}

	public Content getContent() {
		return content;
	}
}
