package de.leuphana.cosa.printingsystem.behaviour.service;

import java.util.Set;

import de.leuphana.cosa.printingsystem.behaviour.service.exceptions.UnsupportedPrintFormatException;

public interface PrintingCommandService  {
	PrintReport printTicket(Printable printable, PrintConfiguration printConfiguration) throws UnsupportedPrintFormatException;
	Set<String> getSupportedPrintFormats();
}