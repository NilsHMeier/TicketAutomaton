package de.leuphana.cosa.printingsystem.structure;

public class CreatedPrintJobState extends PrintJobState {
	
	public CreatedPrintJobState() {
		super();
	}

	@Override
	public PrintJobState changePrintJobState(PrintJobAction printJobAction) {
		PrintJobState printJobState;

		if (printJobAction == PrintJobAction.QUEUE) {
			printJobState = new QueuedPrintJobState();
		} else {
			throw new IllegalArgumentException("Unexpected value: " + printJobAction);
		}
		
		return printJobState;
	}

}
