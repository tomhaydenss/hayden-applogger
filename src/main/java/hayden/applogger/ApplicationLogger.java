package hayden.applogger;

import java.util.LinkedHashMap;
import java.util.Map;

public class ApplicationLogger {
	
	private final String step;
	private Map<String, Object> params;

	private ApplicationLogger(String step) {
		this.step = step;
	}

	private ApplicationLogger withParams(Map<String, Object> params) {
		this.params = params;
		return this;
	}
	
	@Override
	public String toString() {
		return "ApplicationLogger [step=" + step + ", params=" + params + "]";
	}

	public static ApplicationLoggerBuilder startTransaction() {
		return new ApplicationLoggerBuilder(StepType.START_TRANSACTION.name());
	}

	public static ApplicationLoggerBuilder step(String customStep) {
		return new ApplicationLoggerBuilder(customStep);
	}
	
	public void log() {
		System.out.println(this.toString());
	}
	
	public static class ApplicationLoggerBuilder {
		
		private final String step;
		private Map<String, Object> params = new LinkedHashMap<String, Object>();

		public ApplicationLoggerBuilder(String step) {
			this.step = step;
		}

		public ApplicationLoggerBuilder customer(String customer) {
			this.params.put("customer", customer);
			return this;
		}

		public ApplicationLoggerBuilder receiver(String receiver) {
			this.params.put("receiver", receiver);
			return this;
		}

		public ApplicationLoggerBuilder amount(Double amount) {
			this.params.put("amount", amount);
			return this;
		}

		public ApplicationLoggerBuilder param(String name, Object value) {
			this.params.put(name, value);
			return this;
		}
		
		public ApplicationLogger build() {
			return new ApplicationLogger(step).withParams(params);
		}

	}

	public enum StepType {

		REQUEST_TRANSACTION,
		VALIDATION_TRANSACTION,
		START_TRANSACTION,
		AUTHORIZE_PAYMENT,
		CAPTURE_PAYMENT,
		CANCEL_PAYMENT,
		REFUND_PAYMENT,
		AUTHORIZE_RECHARGE,
		CONFIRM_RECHARGE,

	}

}
