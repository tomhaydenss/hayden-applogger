package hayden.applogger.impl;

import hayden.applogger.EventFormatter;
import hayden.applogger.TransactionEvent;

import com.thoughtworks.xstream.XStream;

public class XMLFormatter implements EventFormatter {
	
	private static XStream xstream = new XStream();

	public String format(TransactionEvent event) {
		return xstream.toXML(event);
	}

}
