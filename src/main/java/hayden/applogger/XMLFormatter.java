package hayden.applogger;

import com.thoughtworks.xstream.XStream;

public class XMLFormatter implements EventFormatter {

	public String format(TransactionEvent event) {
		XStream xstream = new XStream();
		return xstream.toXML(event);
	}

}
