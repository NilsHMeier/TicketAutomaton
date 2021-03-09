package de.leuphana.cosa.printingsystem.structure;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;


public abstract class PrintJobState {

	public PrintJobState() {
		Logger logger = LogManager.getLogger(this.getClass());
		
		// System.out.println("Print job status - " + this.getClass().getSimpleName());
		logger.info("Print job status - " + this.getClass().getSimpleName());
	}
	
	protected abstract PrintJobState changePrintJobState(PrintJobAction printJobAction);
}
