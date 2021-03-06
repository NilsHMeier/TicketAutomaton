package de.leuphana.cosa.printingsystem.behaviour;

import de.leuphana.cosa.printingsystem.behaviour.service.PrintConfiguration;
import de.leuphana.cosa.printingsystem.behaviour.service.Printable;
import de.leuphana.cosa.printingsystem.behaviour.service.PrintingCommandService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

class PrintingSystemTest {

	private static PrintingCommandService printingSystem;
	private static PrintConfiguration printConfiguration;
	private static Printable printable;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		printingSystem = new PrintingSystemImpl();
		printable = new Printable() {
			@Override
			public String getTitle() {
				return "NormalTicketTemplate";
			}
			@Override
			public String getContent() {
				return "Ticket from Hannover to Bremen with mileage 120.";
			}
		};
		printConfiguration = new PrintConfiguration() {
			String printFormat;
			@Override
			public void setPrintFormat(String printFormat) {
				this.printFormat = printFormat;
			}
			@Override
			public String getPrintFormat() {
				return printFormat;
			}
		};
		printConfiguration.setPrintFormat("A6");
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		printingSystem = null;
		printConfiguration = null;
		printable = null;
	}

	@Test
	void canTicketBePrinted() {
		Assertions.assertTrue(printingSystem.printTicket(printable, printConfiguration).isPrintSuccessful());
	}

	@Test
	void canPrintFormatsBeLoaded() {
		Assertions.assertNotNull(printingSystem.getSupportedPrintFormats());
	}

}
