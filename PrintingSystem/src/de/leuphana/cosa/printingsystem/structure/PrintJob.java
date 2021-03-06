package de.leuphana.cosa.printingsystem.structure;

import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;

public class PrintJob {
	private PrintConfiguration printConfiguration;
	private PrintJobState printJobState;
	private Printable printable;
	
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

}
