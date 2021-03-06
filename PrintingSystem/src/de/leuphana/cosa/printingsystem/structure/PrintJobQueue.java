package de.leuphana.cosa.printingsystem.structure;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Map;
import java.util.Queue;

public class PrintJobQueue {

	private static PrintJobQueue printJobQueue;
	
	private final Queue<PrintJob> queue;
	private final Map<PrintFormat, Printer> printersMap;

	private PrintJobQueue() {
		queue = new LinkedList<>();
		printersMap = new HashMap<>();
	}

	public static PrintJobQueue getInstance() {
		if (printJobQueue == null) {
			printJobQueue = new PrintJobQueue();
		}
		return printJobQueue;
	}

	public void addPrinter(Printer printer) {
		printersMap.put(printer.getPrintFormat(), printer);
	}
	
	public void addPrintJob(PrintJob printJob) {
		queue.add(printJob);
		printJob.changePrintJobState(PrintJobAction.QUEUE);

		PrintJob lastPrintJob = queue.poll();
		if(lastPrintJob != null) {
			PrintFormat printFormat = PrintFormat.valueOf(lastPrintJob.getConfiguration().getPrintFormat());

			Printer selectedPrinter = printersMap.get(printFormat);
			selectedPrinter.executePrintJob(lastPrintJob);
		}
	}

}
