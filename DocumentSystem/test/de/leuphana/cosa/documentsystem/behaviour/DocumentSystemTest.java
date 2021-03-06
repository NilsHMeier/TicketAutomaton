package de.leuphana.cosa.documentsystem.behaviour;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.BeforeAll;

class DocumentSystemTest {

	private static DocumentCommandService documentSystem;
	
	@BeforeAll
	static void setUpBeforeClass() throws Exception {
		documentSystem = new DocumentSystemImpl();
	}

	@AfterAll
	static void tearDownAfterClass() throws Exception {
		documentSystem = null;
	}



}
