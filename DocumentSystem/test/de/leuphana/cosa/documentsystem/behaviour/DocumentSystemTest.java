package de.leuphana.cosa.documentsystem.behaviour;

import de.leuphana.cosa.documentsystem.behaviour.service.DocumentCommandService;
import de.leuphana.cosa.documentsystem.behaviour.service.Templateable;
import de.leuphana.cosa.documentsystem.structure.TicketTemplate;
import org.junit.jupiter.api.AfterAll;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.BeforeAll;
import org.junit.jupiter.api.Test;

import java.time.LocalDate;

class DocumentSystemTest {

    private static DocumentCommandService documentSystem;
    private static Templateable templateable;

    @BeforeAll
    static void setUpBeforeClass() {
        documentSystem = new DocumentSystemImpl();
        templateable = new Templateable() {
            @Override
            public String getStart() {
                return "Hannover";
            }

            @Override
            public String getDestination() {
                return "Lueneburg";
            }

            @Override
            public String getPriceGroup() {
                return "BARGAIN";
            }

            @Override
            public Double getMileage() {
                return 250.0;
            }

            @Override
            public Double getPrice() {
                return 10.45;
            }

            @Override
            public LocalDate getDate() {
                return LocalDate.now();
            }
        };
    }

    @AfterAll
    static void tearDownAfterClass() {
        documentSystem = null;
        templateable = null;
    }

    @Test
    void canTicketTemplateBeCreated() {
        Assertions.assertNotNull(documentSystem.createTemplate(templateable));
    }

    @Test
    void canManageableBeCreated() {
        TicketTemplate ticketTemplate = documentSystem.createTemplate(templateable);
        Assertions.assertNotNull(documentSystem.createManageable(ticketTemplate));
    }

}