package de.leuphana.cosa.messagingsystem.behaviour.service;

public interface Sendable {
	String getContent();
	String getMessageType();
	String getSender();
	String getReceiver();
}
