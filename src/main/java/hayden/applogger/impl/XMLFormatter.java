package hayden.applogger.impl;

import hayden.applogger.TransactionDataFormatter;
import hayden.applogger.TransactionData;

import com.thoughtworks.xstream.XStream;

public class XMLFormatter implements TransactionDataFormatter {
	
	private static XStream xstream = new XStream();

	public String format(TransactionData data) {
		return xstream.toXML(data);
	}

}
