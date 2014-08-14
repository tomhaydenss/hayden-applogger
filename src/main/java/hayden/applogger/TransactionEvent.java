package hayden.applogger;

import java.util.Map;

public interface TransactionEvent {
	
	String getStep();
	Map<String, Object> getParams();

}
