package hayden.applogger;

import hayden.applogger.impl.AnnotationUtil;
import hayden.applogger.impl.JSONFormatter;
import hayden.applogger.impl.TransactionDataImpl;
import hayden.applogger.impl.XMLFormatter;
import hayden.applogger.type.StepType;
import hayden.applogger.type.TransactionFieldType;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class ApplicationLogger {

	private static final TransactionDataFormatter jsonFormatter = new JSONFormatter();
	private static final TransactionDataFormatter xmlFormatter = new XMLFormatter();
	private static final Logger logger = LoggerFactory.getLogger(ApplicationLogger.class);

	private ApplicationLogger() {

	}

	public static ApplicationLoggerBuilder step(String customStep) {
		return new ApplicationLoggerBuilder(customStep);
	}

	public static ApplicationLoggerBuilder step(TransactionData data) {
		return new ApplicationLoggerBuilder(data);
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

	public static class ApplicationLoggerBuilder {

		private final String step;
		private Map<String, Object> items = new HashMap<String, Object>();
		private TransactionDataFormatter formatter = new JSONFormatter();

		public ApplicationLoggerBuilder(String step) {
			this.step = step;
			this.formatter = jsonFormatter;
		}

		public ApplicationLoggerBuilder(TransactionData data) {
			this.step = data.step();
			this.items = data.items();
			this.formatter = jsonFormatter;
		}

		public ApplicationLoggerBuilder customer(String customer) {
			this.items.put(TransactionFieldType.CUSTOMER.name().toLowerCase(), customer);
			return this;
		}

		public ApplicationLoggerBuilder receiver(String receiver) {
			this.items.put(TransactionFieldType.RECEIVER.name().toLowerCase(), receiver);
			return this;
		}

		public ApplicationLoggerBuilder amount(Double amount) {
			this.items.put(TransactionFieldType.AMOUNT.name().toLowerCase(), amount);
			return this;
		}

		public ApplicationLoggerBuilder ccToken(String ccToken) {
			this.items.put(TransactionFieldType.CC_TOKEN.name().toLowerCase(), ccToken);
			return this;
		}

		public ApplicationLoggerBuilder ccLast4Digits(String ccLast4Digits) {
			this.items.put(TransactionFieldType.CC_LAST4DIGISTS.name().toLowerCase(), ccLast4Digits);
			return this;
		}

		public ApplicationLoggerBuilder ccBrand(String ccBrand) {
			this.items.put(TransactionFieldType.CC_BRAND.name().toLowerCase(), ccBrand);
			return this;
		}

		public ApplicationLoggerBuilder channel(String channel) {
			this.items.put(TransactionFieldType.CHANNEL.name().toLowerCase(), channel);
			return this;
		}

		public ApplicationLoggerBuilder transactionId(Long transactionId) {
			this.items.put(TransactionFieldType.TRANSACTION_ID.name().toLowerCase(), transactionId);
			return this;
		}

		public ApplicationLoggerBuilder tid(String tid) {
			this.items.put(TransactionFieldType.TID.name().toLowerCase(), tid);
			return this;
		}

		public ApplicationLoggerBuilder authCode(String authCode) {
			this.items.put(TransactionFieldType.AUTH_CODE.name().toLowerCase(), authCode);
			return this;
		}

		public ApplicationLoggerBuilder nsu(String nsu) {
			this.items.put(TransactionFieldType.NSU.name().toLowerCase(), nsu);
			return this;
		}

		public ApplicationLoggerBuilder dtLog(Date dtLog) {
			this.items.put(TransactionFieldType.DTLOG.name().toLowerCase(), dtLog);
			return this;
		}

		public ApplicationLoggerBuilder obs(String obs) {
			this.items.put(TransactionFieldType.OBS.name().toLowerCase(), obs);
			return this;
		}

		public ApplicationLoggerBuilder param(String name, Object value) {
			this.items.put(name, value);
			return this;
		}

		public ApplicationLoggerBuilder inJSONFormat() {
			this.formatter = jsonFormatter;
			return this;
		}

		public ApplicationLoggerBuilder inXMLFormat() {
			this.formatter = xmlFormatter;
			return this;
		}

		public ApplicationLoggerBuilder inCustomFormat(TransactionDataFormatter formatter) {
			this.formatter = formatter;
			return this;
		}

		public void log() {
			TransactionData data = new TransactionDataImpl(step, items);
			String info = this.formatter.format(data);
			logger.info(info);
		}

		public void log(Object obj) {
			Map<String, Object> items = AnnotationUtil.extractFrom(obj);
			TransactionData data = new TransactionDataImpl(step, items);
			String info = this.formatter.format(data);
			logger.info(info);
		}


	}

}
