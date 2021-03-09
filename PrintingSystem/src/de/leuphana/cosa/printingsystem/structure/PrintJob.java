package de.leuphana.cosa.printingsystem.structure;

import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;

public class PrintJob {
	private final PrintConfiguration printConfiguration;
	private PrintJobState printJobState;
	private final Printable printable;
	
	public PrintJob(Printable printable, PrintConfiguration printConfiguration) {
		this.printable = printable;
		this.printConfiguration = printConfiguration;
		printJobState = new CreatedPrintJobState();
	}

	public PrintConfiguration getConfiguration() {
		return printConfiguration;
	}
	
	public void changePrintJobState(PrintJobAction printJobAction) {
		printJobState = printJobState.changePrintJobState(printJobAction);
	}

	public Printable getPrintable() {
		return printable;
	}
}
