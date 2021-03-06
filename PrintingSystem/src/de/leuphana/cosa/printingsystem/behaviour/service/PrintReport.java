package de.leuphana.cosa.printingsystem.behaviour.service;

import java.time.LocalDate;

public class PrintReport  {
	private boolean isPrintSuccessful;
	private LocalDate printDate;
	private String confirmationText;

	public boolean isPrintSuccessful() {
		return isPrintSuccessful;
	}

	public void setPrintSuccessful(boolean isPrintSuccessful) {
		this.isPrintSuccessful = isPrintSuccessful;
	}

	public LocalDate getPrintDate() {
		return printDate;
	}

	public void setPrintDate(LocalDate printDate) {
		this.printDate = printDate;
	}

	public String getConfirmationText() {
		return confirmationText;
	}

	public void setConfirmationText(String confirmationText) {
		this.confirmationText = confirmationText;
	}

}
