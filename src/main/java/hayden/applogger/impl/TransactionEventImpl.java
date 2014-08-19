package hayden.applogger.impl;

import hayden.applogger.TransactionEvent;

import java.util.Map;

public class TransactionEventImpl implements TransactionEvent {

	private String step;
	private Map<String, Object> params;

	public TransactionEventImpl(String step, Map<String, Object> params) {
		this.step = step;
		this.params = params;
	}

	public String getStep() {
		return step;
	}

	public Map<String, Object> getParams() {
		return params;
	}

}
