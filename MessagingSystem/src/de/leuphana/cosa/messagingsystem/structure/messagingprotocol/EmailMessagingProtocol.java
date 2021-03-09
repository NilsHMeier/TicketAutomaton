package de.leuphana.cosa.messagingsystem.structure.messagingprotocol;

import de.leuphana.cosa.messagingsystem.structure.message.Message;

public class EmailMessagingProtocol implements MessagingProtocol {

	@Override
	public boolean open() {
		return false;
	}

	@Override
	public boolean transfer(Message message) {
		return false;
	}

	@Override
	public boolean close() {
		return false;
	}

}
