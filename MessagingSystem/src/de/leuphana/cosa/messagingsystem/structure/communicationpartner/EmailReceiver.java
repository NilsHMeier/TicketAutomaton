package de.leuphana.cosa.messagingsystem.structure.communicationpartner;

public class EmailReceiver implements Receiver {

	private String name;
	private final String address;
	
	public EmailReceiver(String receiverAddress) {
		this.address = receiverAddress;
	}

	public String getAddress() {
		return address;
	}
}
