package de.leuphana.cosa.documentsystem.behaviour.service;

import de.leuphana.cosa.documentsystem.structure.TicketTemplate;

public interface DocumentCommandService  {
	TicketTemplate createTemplate(Templateable templateable);
	Manageable createManageable(TicketTemplate ticketTemplate);
}