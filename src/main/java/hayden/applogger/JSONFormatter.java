package hayden.applogger;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;

public class JSONFormatter implements EventFormatter {

	public String format(TransactionEvent event) {
		GsonBuilder gsonBuilder = new GsonBuilder();
		Gson gson = gsonBuilder.enableComplexMapKeySerialization().setPrettyPrinting().create();
		return gson.toJson(event);
	}

}
