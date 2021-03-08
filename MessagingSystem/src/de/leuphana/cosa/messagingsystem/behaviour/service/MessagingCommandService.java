package de.leuphana.cosa.messagingsystem.behaviour.service;

public interface MessagingCommandService  {
	DeliveryReport sendMessage(Sendable sendable);
}