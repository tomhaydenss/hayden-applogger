package hayden.applogger.impl;

import hayden.applogger.EventFormatter;
import hayden.applogger.TransactionEvent;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONFormatter implements EventFormatter {
	
	private static Gson gson = null; 
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.enableComplexMapKeySerialization().setPrettyPrinting().create();
	}

	public String format(TransactionEvent event) {
		return gson.toJson(event);
	}

}
