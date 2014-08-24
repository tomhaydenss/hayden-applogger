package hayden.applogger.impl;

import hayden.applogger.TransactionDataFormatter;
import hayden.applogger.TransactionData;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONFormatter implements TransactionDataFormatter {
	
	private static Gson gson = null; 
	{
		GsonBuilder gsonBuilder = new GsonBuilder();
		gson = gsonBuilder.enableComplexMapKeySerialization().setPrettyPrinting().create();
	}

	public String format(TransactionData data) {
		return gson.toJson(data);
	}

}
