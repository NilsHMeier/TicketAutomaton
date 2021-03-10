# TicketAutomaton
## Setting
### Exam Task:
Implementation of a component-oriented ticket vending machine using OSGi and event delegation. 
The user should first choose a starting point for his ticket from a list and then select one from possible destinations. 
After selecting a price group, the ticket is printed on the console and a message with information about the sale is sent.
The user input is done via a command line interface. 

### Modules
- RouteSystem: Saves the existing connections and is responsible for the selection of the route.
- Pricing System: Is responsible for the selection of the price group and calculates the price for the choosen route.
- DocumentSystem: Creates a TicketTemplate with all the relevant information.
- PrintingSystem: Prints the ticket information to the console.
- MessagingSystem: Sends the confirmation message.
- TicketAutomaton: Top-level component connecting all other modules together.

### Framework & Libraries
- Apache Felix 7.0.0
- Apache Felix EventAdmin
- JUnit 5.4
- Apache Log4J2

## Getting the bundles
Option 1:
Download the entire repository and create a new project in an IDE of your choice(that supports OSGi) that includes the individual modules. 
The OSGi facets should be automatically detected and displayed based on the .iml module descriptions. Otherwise they have to be added manually. 
Build the project to generate the OSGi bundles. 

Option 2:
Download the built bundles from the repository (out/production) and add them to your framework manually. 
Observe the notes from the following section "Framework Setup" to ensure the functionality of the ticket vending machine.

## Framework Setup
### Bundles
Add the following bundles to your framework: RouteSystem, PricingSystem, DocumentSystem, PrintingSystem, MessagingSystem, TicketAutomaton, 
org.apache.felix.eventadmin, org.apache.logging.log4j.core, org.apache.logging.log4j.api.

### Configuration
To ensure functionality, pay attention to the starting order of the individual bundles.
- Start level 1: org.apache.felix.eventadmin, org.apache.logging.log4j.core, org.apache.logging.log4j.api
- Start level 2: RouteSystem, PricingSystem, DocumentSystem, PrintingSystem, MessagingSystem
- Start level 3: TicketAutomaton
