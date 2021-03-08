package de.leuphana.cosa.messagingsystem.behaviour;

import de.leuphana.cosa.messagingsystem.behaviour.service.DeliveryReport;
import de.leuphana.cosa.messagingsystem.behaviour.service.MessagingCommandService;
import de.leuphana.cosa.messagingsystem.behaviour.service.Sendable;
import de.leuphana.cosa.messagingsystem.behaviour.service.event.MessagingEventHandler;
import de.leuphana.cosa.messagingsystem.structure.MessageType;
import de.leuphana.cosa.messagingsystem.structure.message.Message;
import de.leuphana.cosa.messagingsystem.structure.messagingfactory.AbstractMessagingFactory;
import de.leuphana.cosa.messagingsystem.structure.messagingprotocol.MessagingProtocol;
import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.time.LocalDate;
import java.util.Dictionary;
import java.util.Hashtable;

public class MessagingSystemImpl implements MessagingCommandService, BundleActivator {

	@Override
	public DeliveryReport sendMessage(Sendable sendable) {
		Logger logger = LogManager.getLogger(this.getClass());
		
		AbstractMessagingFactory abstractMessagingFactory = AbstractMessagingFactory.getFactory(MessageType.valueOf(sendable.getMessageType()));

		Message message = abstractMessagingFactory.createMessage(sendable.getReceiver(), sendable.getSender(), sendable.getContent());

		MessagingProtocol messageProtocol = abstractMessagingFactory.createMessagingProtocol();
		messageProtocol.open();
		messageProtocol.transfer(message);
		messageProtocol.close();

		String deliveryConfirmationText = "Message: " + sendable.getContent() + " transported via " + sendable.getMessageType();
		logger.info(deliveryConfirmationText);
		
		DeliveryReport deliveryReport = new DeliveryReport();
		deliveryReport.setConfirmationText(deliveryConfirmationText);
		deliveryReport.setDeliveryDate(LocalDate.now());
		deliveryReport.setDeliverySuccessful(true);

		return deliveryReport;
	}

	@Override
	public void start(BundleContext bundleContext) throws Exception {
		System.out.println("Starting MessagingSystem...");
		//Register MessagingEventHandler
		String[] topics = new String[] {
				"de/leuphana/cosa/ticketautomaton/DELIVERYREPORT_REQUESTED"
		};
		Dictionary properties = new Hashtable();
		properties.put(EventConstants.EVENT_TOPIC, topics);
		MessagingEventHandler eventHandler = new MessagingEventHandler(this, bundleContext);
		bundleContext.registerService(EventHandler.class.getName(), eventHandler, properties);
	}

	@Override
	public void stop(BundleContext bundleContext) throws Exception {
		System.out.println("...stopping MessagingSystem!");
	}
}
