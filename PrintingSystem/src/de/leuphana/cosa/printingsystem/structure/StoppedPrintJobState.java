package de.leuphana.cosa.printingsystem.structure;

public class StoppedPrintJobState extends PrintJobState {
	
	public StoppedPrintJobState() {
		super();
	}

	@Override
	public PrintJobState changePrintJobState(PrintJobAction printJobAction) {
		return null;
	}

}
