package de.leuphana.cosa.documentsystem.behaviour;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.Manageable;
import de.leuphana.cosa.documentsystem.behaviour.service.Templateable;
import de.leuphana.cosa.documentsystem.behaviour.service.event.DocumentEventHandler;
import de.leuphana.cosa.documentsystem.structure.BargainTicketTemplate;
import de.leuphana.cosa.documentsystem.structure.CheaperTicketTemplate;
import de.leuphana.cosa.documentsystem.structure.NormalTicketTemplate;
import de.leuphana.cosa.documentsystem.structure.TicketTemplate;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;

public class DocumentSystemImpl implements DocumentCommandService, BundleActivator {

	@Override
	public TicketTemplate createTemplate(Templateable templateable) {
		return switch (templateable.getPriceGroup()) {
			case "NORMAL" -> new NormalTicketTemplate(templateable);
			case "CHEAPER" -> new CheaperTicketTemplate(templateable);
			case "BARGAIN" -> new BargainTicketTemplate(templateable);
			default -> null;
		};
	}

	@Override
	public Manageable createManageable(TicketTemplate ticketTemplate) {
		return new Manageable() {
			@Override
			public String getTitle() {
				return ticketTemplate.getClass().getSimpleName();
			}

			@Override
			public String getContent() {
				StringBuilder content = new StringBuilder();
				content.append(String.join(" ", "Ticket from", ticketTemplate.getStart(), "to", ticketTemplate.getDestination(),
						"with mileage", ticketTemplate.getMileage().toString(), "and price group"));
				if (ticketTemplate instanceof NormalTicketTemplate) content.append(" normal. ");
				else if (ticketTemplate instanceof  CheaperTicketTemplate) content.append(" cheaper. ");
				else if (ticketTemplate instanceof BargainTicketTemplate) content.append(" bargain. ");
				content.append("Price is ").append(ticketTemplate.getPrice().toString()).append(". ");
				content.append("Date: ").append(ticketTemplate.getDate());
				return content.toString();
			}
		};
	}

    @Override
    public void start(BundleContext bundleContext) {
		System.out.println("Starting DocumentSystem...");
		//Register DocumentEventHandler
		String[] topics = new String[] {
				"de/leuphana/cosa/ticketautomaton/TEMPLATE_REQUESTED"
		};
		Dictionary<String, Object> properties = new Hashtable<>();
		properties.put(EventConstants.EVENT_TOPIC, topics);
		DocumentEventHandler eventHandler = new DocumentEventHandler(this, bundleContext);
		bundleContext.registerService(EventHandler.class.getName(), eventHandler, properties);
    }

    @Override
    public void stop(BundleContext bundleContext) {
		System.out.println("...stopping DocumentSystem!");
    }

}
