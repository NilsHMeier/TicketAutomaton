package de.leuphana.cosa.printingsystem.behaviour;

import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintReport;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;
import de.leuphana.cosa.printingsystem.behaviour.service.event.PrintingEventHandler;
import de.leuphana.cosa.printingsystem.structure.PrintFormat;
import de.leuphana.cosa.printingsystem.structure.PrintJob;
import de.leuphana.cosa.printingsystem.structure.PrintJobQueue;
import de.leuphana.cosa.printingsystem.structure.Printer;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.time.LocalDate;
import java.util.Arrays;
import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Set;
import java.util.stream.Collectors;

public class PrintingSystemImpl implements PrintingCommandService, BundleActivator {

	private final PrintJobQueue printJobQueue;

	public PrintingSystemImpl() {
		printJobQueue = PrintJobQueue.getInstance();
		printJobQueue.addPrinter(new Printer(PrintFormat.A6));
		printJobQueue.addPrinter(new Printer(PrintFormat.A4));
		printJobQueue.addPrinter(new Printer(PrintFormat.A3));
	}

	@Override
	public PrintReport printTicket(Printable printable, PrintConfiguration printConfiguration) {
		PrintJob printJob = new PrintJob(printable, printConfiguration);
		printJobQueue.addPrintJob(printJob);
		// Create PrintReport
		PrintReport printReport = new PrintReport();
		printReport.setConfirmationText(printable.getContent());
		printReport.setPrintDate(LocalDate.now());
		printReport.setPrintSuccessful(true);
		return printReport;
	}

	@Override
	public Set<String> getSupportedPrintFormats() {
		return Arrays.stream(PrintFormat.values()).map(Enum::name).collect(Collectors.toSet());
	}

	@Override
	public void start(BundleContext bundleContext) {
		System.out.println("Starting PrintingSystem...");
		//Register PrintEventHandler
		String[] topics = new String[] {
				"de/leuphana/cosa/ticketautomaton/PRINTREPORT_REQUESTED"
		};
		Dictionary<String, Object> properties = new Hashtable<>();
		properties.put(EventConstants.EVENT_TOPIC, topics);
		PrintingEventHandler eventHandler = new PrintingEventHandler(this, bundleContext);
		bundleContext.registerService(EventHandler.class.getName(), eventHandler, properties);
	}

	@Override
	public void stop(BundleContext bundleContext) {
		System.out.println("...stopping PrintingSystem!");
	}
}
