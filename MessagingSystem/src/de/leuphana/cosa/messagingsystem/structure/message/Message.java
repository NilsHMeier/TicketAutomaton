package de.leuphana.cosa.messagingsystem.structure.message;

public abstract class Message {
	private final MessageHeader messageHeader;
	private final MessageBody messageBody;
	
	public Message(MessageHeader messageHeader, MessageBody messageBody) {
		this.messageBody = messageBody;
		this.messageHeader = messageHeader;
	}

	public MessageBody getMessageBody() {
		return messageBody;
	}

	public MessageHeader getMessageHeader() {
		return messageHeader;
	}
}
