package de.leuphana.cosa.pricingsystem.behaviour;

import de.leuphana.cosa.pricingsystem.behaviour.service.PricingCommandService;
import de.leuphana.cosa.pricingsystem.behaviour.service.event.PricingEventHandler;
import de.leuphana.cosa.pricingsystem.structure.Bill;
import de.leuphana.cosa.pricingsystem.structure.PriceGroup;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.service.event.EventConstants;
import org.osgi.service.event.EventHandler;

import java.util.Dictionary;
import java.util.Hashtable;
import java.util.Scanner;

public class PricingSystemImpl implements PricingCommandService, BundleActivator {

    private final Scanner scanner;

    public PricingSystemImpl() {
        scanner = new Scanner(System.in);
    }

    @Override
    public PriceGroup selectPriceGroup() {
        System.out.println("Please select your price group!");
        int counter = 1;
        for (PriceGroup priceGroup : PriceGroup.values()) {
            System.out.println(counter + " - " + priceGroup);
            counter++;
        }
        System.out.print("Your choice (enter the number): ");
        int choice = scanner.nextInt();
        return switch (choice) {
            case 1 -> PriceGroup.NORMAL;
            case 2 -> PriceGroup.CHEAPER;
            case 3 -> PriceGroup.BARGAIN;
            default -> null;
        };
    }

    @Override
    public Bill createBill(PriceGroup priceGroup, Double mileage) {
        return new Bill(priceGroup, mileage);
    }

    @Override
    public void start(BundleContext bundleContext) throws Exception {
        System.out.println("Starting PricingSystem...");
        //Register PricingEventHandler
        String[] topics = new String[] {
                "de/leuphana/cosa/ticketautomaton/PRICEGROUP_REQUESTED"
        };
        Dictionary properties = new Hashtable();
        properties.put(EventConstants.EVENT_TOPIC, topics);
        PricingEventHandler eventHandler = new PricingEventHandler(this, bundleContext);
        bundleContext.registerService(EventHandler.class.getName(), eventHandler, properties);
    }

    @Override
    public void stop(BundleContext bundleContext) throws Exception {
        System.out.println("...stopping PricingSystem!");
    }
}
