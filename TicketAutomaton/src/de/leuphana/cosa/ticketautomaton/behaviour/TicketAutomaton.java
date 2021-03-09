package de.leuphana.cosa.ticketautomaton.behaviour;

import de.leuphana.cosa.messagingsystem.behaviour.service.DeliveryReport;
import de.leuphana.cosa.ticketautomaton.behaviour.service.event.TicketEventHandler;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class TicketAutomaton implements BundleActivator {

	private TicketEventHandler eventHandler;
	private Scanner scanner;
	private Logger logger;

	@Override
	public void start(BundleContext bundleContext) {
		System.out.println("Starting TicketAutomaton...");
		scanner = new Scanner(System.in);
		logger = LogManager.getLogger(this.getClass().getName());
		//Register TicketEventHandler
		eventHandler = new TicketEventHandler(this, bundleContext);
		String[] topics = new String[] {
				"de/leuphana/cosa/routesystem/ROUTE_SELECTED",
				"de/leuphana/cosa/pricingsystem/BILL_CREATED",
				"de/leuphana/cosa/documentsystem/TEMPLATE_CREATED",
                "de/leuphana/cosa/printingsystem/TICKET_PRINTED",
                "de/leuphana/cosa/messagingsystem/MESSAGE_SENT"
		};
		Dictionary<String, Object> properties = new Hashtable<>();
		properties.put(EventConstants.EVENT_TOPIC, topics);
		bundleContext.registerService(EventHandler.class.getName(), eventHandler, properties);
		runAutomaton();
	}

	@Override
	public void stop(BundleContext bundleContext) {
		System.out.println("...stopping TicketAutomaton!");
	}

	public void runAutomaton() {
		System.out.println("Your Action:");
		System.out.println("1 - Buy Ticket");
		System.out.println("2 - Stop automaton");
		System.out.print("Choice: ");
		switch (scanner.nextInt()) {
			case 1 -> {
				requestTicket();
				runAutomaton();
			}
			case 2 -> System.out.println("See you next time!");
		}
	}

	public boolean requestTicket() {
		return eventHandler.requestTicket();
	}

	public void createLogfile(DeliveryReport deliveryReport) {
		System.out.println("Saving Logfile...");
		logger.info(deliveryReport.getConfirmationText());
	}
}