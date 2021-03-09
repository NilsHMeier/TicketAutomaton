package de.leuphana.cosa.printingsystem.structure;

public class QueuedPrintJobState extends PrintJobState {
	
	public QueuedPrintJobState() {
		super();
	}

	@Override
	public PrintJobState changePrintJobState(PrintJobAction printJobAction) {
		PrintJobState printJobState;

		switch (printJobAction) {
			case PRINT -> printJobState = new PrintedPrintJobState();
			case STOP -> printJobState = new StoppedPrintJobState();
			default -> throw new IllegalArgumentException("Unexpected value: " + printJobAction);
		}

		return printJobState;
	}

}
