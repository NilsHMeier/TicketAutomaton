# TicketAutomaton
## Setting
### Exam Task:
Implementation of a component-oriented ticket vending machine using OSGi and event delegation. 
The user should first choose a starting point for his ticket from a list and then select one from possible destinations. 
After selecting a price group, the ticket is printed on the console, and a message with information about the sale is sent.
The user input is done via a command line interface. 

### Modules
- _RouteSystem_: Saves the existing connections and is responsible for the selection of the route.
- _PricingSystem_: Is responsible for the selection of the price group and calculates the price for the choosen route.
- _DocumentSystem_: Creates a TicketTemplate with all the relevant information.
- _PrintingSystem_: Prints the ticket information to the console.
- _MessagingSystem_: Sends the confirmation message.
- _TicketAutomaton_: Top-level component connecting all other modules together.

### Framework & Libraries
- Apache Felix 7.0.0
- Apache Felix EventAdmin 1.6.2
- JUnit 5.4
- Apache  Log4J 2.13.3 (API & Core)

## Getting the bundles
**Option 1:**
Download the entire repository and create a new project in an IDE of your choice(that supports OSGi) that includes the individual modules. 
The OSGi facets should be automatically detected and displayed based on the .iml module descriptions. Otherwise they have to be added manually. 
Build the project to generate the OSGi bundles. 

**Option 2:**
Download the built bundles from the repository (out/production) and add them to your framework manually. 
Observe the notes from the following section "Framework Setup" to ensure the functionality of the ticket vending machine.

## Framework Setup
### Bundles
Add the following bundles to your framework: RouteSystem, PricingSystem, DocumentSystem, PrintingSystem, MessagingSystem, TicketAutomaton, 
org.apache.felix.eventadmin, org.apache.logging.log4j.core, org.apache.logging.log4j.api.

### Configuration
To ensure functionality, pay attention to the starting order of the individual bundles.
- _Start level 1_: org.apache.felix.eventadmin, org.apache.logging.log4j.core, org.apache.logging.log4j.api
- _Start level 2_: RouteSystem, PricingSystem, DocumentSystem, PrintingSystem, MessagingSystem
- _Start level 3_: TicketAutomaton
