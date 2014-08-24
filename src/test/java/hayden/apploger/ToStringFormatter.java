package hayden.apploger;

import hayden.applogger.TransactionData;
import hayden.applogger.TransactionDataFormatter;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;

public class ToStringFormatter implements TransactionDataFormatter {

	public String format(TransactionData data) {

		return data.step() + " [" + formatMap(data.items()) + "]";
		
	}

	private String formatMap(Map<String, Object> items) {
		String result = "";
		List<String> keys = new ArrayList<String>(items.keySet());
		for (int i = 0; i < keys.size(); i++) {
			String key = keys.get(i);
			Object obj = items.get(key);
			result += key + "=" + obj.toString() + (i < keys.size() - 1 ? ", " : "");
		}
		return result;
	}
}
