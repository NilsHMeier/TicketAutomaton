package de.leuphana.cosa.printingsystem.structure;

public class Printer {
	private final PrintFormat printFormat;

	public Printer(PrintFormat printFormat) {
		this.printFormat = printFormat;
	}

	public PrintFormat getPrintFormat() {
		return printFormat;
	}

	public boolean executePrintJob(PrintJob printJob) {
		// Simulation of printing
		printJob.changePrintJobState(PrintJobAction.PRINT);
		// Print content to console
		System.out.println("- - - - TICKET - - - -");
		System.out.println(printJob.getPrintable().getTitle());
		System.out.println(printJob.getPrintable().getContent());
		System.out.println("- - - - - - - - - - -");

		return true;
	}
}
