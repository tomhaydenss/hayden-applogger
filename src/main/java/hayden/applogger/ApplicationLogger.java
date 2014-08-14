package hayden.applogger;

import java.util.Date;
import java.util.LinkedHashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ApplicationLogger {

	private final Logger logger;
	private EventFormatter formatter;
	private TransactionEvent transactionEvent;

	private ApplicationLogger(String step, Map<String, Object> params, EventFormatter formatter) {
		this.logger = Logger.getLogger(ApplicationLogger.class.getName());
		this.transactionEvent = new TransactionEventImpl(step, params);
		this.formatter = formatter;
	}

	public static ApplicationLoggerBuilder step(String customStep) {
		return new ApplicationLoggerBuilder(customStep);
	}

	public static ApplicationLoggerBuilder requestTransaction() {
		return new ApplicationLoggerBuilder(StepType.REQUEST_TRANSACTION.name());
	}

	public static ApplicationLoggerBuilder validationTransaction() {
		return new ApplicationLoggerBuilder(StepType.VALIDATION_TRANSACTION.name());
	}

	public static ApplicationLoggerBuilder startTransaction() {
		return new ApplicationLoggerBuilder(StepType.START_TRANSACTION.name());
	}

	public static ApplicationLoggerBuilder authorizePayment() {
		return new ApplicationLoggerBuilder(StepType.AUTHORIZE_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder capturePayment() {
		return new ApplicationLoggerBuilder(StepType.CAPTURE_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder cancelPayment() {
		return new ApplicationLoggerBuilder(StepType.CANCEL_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder refundPayment() {
		return new ApplicationLoggerBuilder(StepType.REFUND_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder authorizeRecharge() {
		return new ApplicationLoggerBuilder(StepType.AUTHORIZE_RECHARGE.name());
	}

	public static ApplicationLoggerBuilder confirmRecharge() {
		return new ApplicationLoggerBuilder(StepType.CONFIRM_RECHARGE.name());
	}

	public static ApplicationLoggerBuilder cancelRecharge() {
		return new ApplicationLoggerBuilder(StepType.CANCEL_RECHARGE.name());
	}

	public void log() {
		logger.info(this.formatter.format(this.transactionEvent));
	}

	public static class ApplicationLoggerBuilder {

		private final String step;
		private Map<String, Object> params = new LinkedHashMap<String, Object>();
		private EventFormatter formatter = new JSONFormatter();

		public ApplicationLoggerBuilder(String step) {
			this.step = step;
		}

		public ApplicationLoggerBuilder customer(String customer) {
			this.params.put(TransactionParam.CUSTOMER.name().toLowerCase(), customer);
			return this;
		}

		public ApplicationLoggerBuilder receiver(String receiver) {
			this.params.put(TransactionParam.RECEIVER.name().toLowerCase(), receiver);
			return this;
		}

		public ApplicationLoggerBuilder amount(Double amount) {
			this.params.put(TransactionParam.AMOUNT.name().toLowerCase(), amount);
			return this;
		}

		public ApplicationLoggerBuilder ccToken(String ccToken) {
			this.params.put(TransactionParam.CC_TOKEN.name().toLowerCase(), ccToken);
			return this;
		}

		public ApplicationLoggerBuilder ccLast4Digits(String ccLast4Digits) {
			this.params.put(TransactionParam.CC_LAST4DIGISTS.name().toLowerCase(), ccLast4Digits);
			return this;
		}

		public ApplicationLoggerBuilder ccBrand(String ccBrand) {
			this.params.put(TransactionParam.CC_BRAND.name().toLowerCase(), ccBrand);
			return this;
		}

		public ApplicationLoggerBuilder channel(String channel) {
			this.params.put(TransactionParam.CHANNEL.name().toLowerCase(), channel);
			return this;
		}

		public ApplicationLoggerBuilder transactionId(Long transactionId) {
			this.params.put(TransactionParam.TRANSACTION_ID.name().toLowerCase(), transactionId);
			return this;
		}

		public ApplicationLoggerBuilder tid(String tid) {
			this.params.put(TransactionParam.TID.name().toLowerCase(), tid);
			return this;
		}

		public ApplicationLoggerBuilder authCode(String authCode) {
			this.params.put(TransactionParam.AUTH_CODE.name().toLowerCase(), authCode);
			return this;
		}

		public ApplicationLoggerBuilder nsu(String nsu) {
			this.params.put(TransactionParam.NSU.name().toLowerCase(), nsu);
			return this;
		}

		public ApplicationLoggerBuilder dtLog(Date dtLog) {
			this.params.put(TransactionParam.DTLOG.name().toLowerCase(), dtLog);
			return this;
		}

		public ApplicationLoggerBuilder obs(String obs) {
			this.params.put(TransactionParam.OBS.name().toLowerCase(), obs);
			return this;
		}

		public ApplicationLoggerBuilder param(String name, Object value) {
			this.params.put(name, value);
			return this;
		}

		public ApplicationLoggerBuilder inJSONFormat() {
			this.formatter = new JSONFormatter();
			return this;
		}

		public ApplicationLoggerBuilder inXMLFormat() {
			this.formatter = new XMLFormatter();
			return this;
		}

		public ApplicationLoggerBuilder inCustomFormat(EventFormatter formatter) {
			this.formatter = formatter;
			return this;
		}

		public ApplicationLogger build() {
			return new ApplicationLogger(step, params, formatter);
		}

	}

}
