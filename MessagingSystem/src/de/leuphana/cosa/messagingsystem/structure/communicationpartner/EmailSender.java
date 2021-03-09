package de.leuphana.cosa.messagingsystem.structure.communicationpartner;

public class EmailSender implements Sender {

	private String name;
	private final String address;
	
	public EmailSender(String senderAddress) {
		this.address = senderAddress;
	}

	public String getAddress() {
		return address;
	}
}
