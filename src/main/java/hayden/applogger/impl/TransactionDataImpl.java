package hayden.applogger.impl;

import hayden.applogger.TransactionData;

import java.util.Map;

public class TransactionDataImpl implements TransactionData {

	private String step;
	private Map<String, Object> items;

	public TransactionDataImpl(String step, Map<String, Object> items) {
		this.step = step;
		this.items = items;
	}

	public String step() {
		return step;
	}

	public Map<String, Object> items() {
		return items;
	}

}
