package hayden.applogger;

import java.util.Map;

public interface TransactionData {

	String step();

	Map<String, Object> items();

}
