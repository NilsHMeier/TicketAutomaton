package de.leuphana.cosa.printingsystem.structure;


public abstract class PrintJobState {

	public PrintJobState() {

	}
	
	protected abstract PrintJobState changePrintJobState(PrintJobAction printJobAction);
}
