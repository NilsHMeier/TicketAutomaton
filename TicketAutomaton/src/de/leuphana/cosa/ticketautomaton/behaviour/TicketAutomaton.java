package de.leuphana.cosa.ticketautomaton.behaviour;

import de.leuphana.cosa.ticketautomaton.behaviour.event.TicketEventHandler;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;

import java.util.Dictionary;
import java.util.Hashtable;

public class TicketAutomaton implements BundleActivator {

	private TicketEventHandler eventHandler;

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting TicketAutomaton...");
		//Register TicketEventHandler
		eventHandler = new TicketEventHandler(this, bundleContext);
		String[] topics = new String[] {
				"de/leuphana/cosa/routesystem/ROUTE_SELECTED",
				"de/leuphana/cosa/pricingsystem/BILL_CREATED",
				"de/leuphana/cosa/documentsystem/TEMPLATE_CREATED",
                "de/leuphana/cosa/printingsystem/TICKET_PRINTED",
                "de/leuphana/cosa/messagingsystem/MESSAGE_SENT"
		};
		Dictionary properties = new Hashtable();
		properties.put(EventConstants.EVENT_TOPIC, topics);
		bundleContext.registerService(TicketEventHandler.class.getName(), eventHandler, properties);
		requestTicket();
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("...stopping TicketAutomaton!");
	}

	public boolean requestTicket() {
		return eventHandler.requestTicket();
	}

}