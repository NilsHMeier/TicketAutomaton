package de.leuphana.cosa.messagingsystem.structure.message;

import de.leuphana.cosa.messagingsystem.structure.MessageType;
import de.leuphana.cosa.messagingsystem.structure.content.Content;
import de.leuphana.cosa.messagingsystem.structure.content.EmailContent;
import de.leuphana.cosa.messagingsystem.structure.communicationpartner.EmailReceiver;
import de.leuphana.cosa.messagingsystem.structure.communicationpartner.EmailSender;
import de.leuphana.cosa.messagingsystem.structure.communicationpartner.Receiver;
import de.leuphana.cosa.messagingsystem.structure.communicationpartner.Sender;

public class MessageBuilder {

	private MessageBuilder() {
	}
	
	public static Message createMessage(String receiver, String sender, String contentAsString, MessageType messageType) {
		Message message;
		
		MessageHeader messageHeader = createMessageHeader(sender, receiver, messageType);
		MessageBody messageBody = createMessageBody(contentAsString, messageType);


		switch (messageType) {
			case EMAIL -> message = new EmailMessage(messageHeader, messageBody);
			case SMS -> message = new SMSMessage(messageHeader, messageBody);
			default -> throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return message;
	}
	
	private static MessageHeader createMessageHeader(String senderAddress, String receiverAddress, MessageType messageType) {
		Receiver receiver = null;
		Sender sender = null;

		switch (messageType) {
			case EMAIL -> {
				receiver = new EmailReceiver(receiverAddress);
				sender = new EmailSender(senderAddress);
			}
			case SMS -> {
			}
			default -> throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		
		return new MessageHeader(sender, receiver);
	}
	
	private static MessageBody createMessageBody(String contentAsString, MessageType messageType) {
		Content content = null;

		switch (messageType) {
			case EMAIL -> content = new EmailContent(contentAsString);
			case SMS -> {
			}
			default -> throw new IllegalArgumentException("Unexpected value: " + messageType);
		}
		return new MessageBody(content);
	}
}