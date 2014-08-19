package hayden.applogger;

import hayden.applogger.impl.JSONFormatter;
import hayden.applogger.impl.TransactionEventImpl;
import hayden.applogger.impl.XMLFormatter;

import java.lang.reflect.Field;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

import org.apache.log4j.Logger;

public class ApplicationLogger {

	private static final EventFormatter jsonFormatter = new JSONFormatter();
	private static final EventFormatter xmlFormatter = new XMLFormatter();
	private static final Logger defaultLogger = Logger.getLogger(ApplicationLogger.class.getName());

	private ApplicationLogger() {

	}

	public static ApplicationLoggerBuilder step(String customStep) {
		return new ApplicationLoggerBuilder(customStep);
	}

	public static ApplicationLoggerBuilder step(String customStep, Logger logger) {
		return new ApplicationLoggerBuilder(customStep, logger);
	}

	public static ApplicationLoggerBuilder requestTransaction() {
		return new ApplicationLoggerBuilder(StepType.REQUEST_TRANSACTION.name());
	}

	public static ApplicationLoggerBuilder requestTransaction(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.REQUEST_TRANSACTION.name(), logger);
	}

	public static ApplicationLoggerBuilder validationTransaction() {
		return new ApplicationLoggerBuilder(StepType.VALIDATION_TRANSACTION.name());
	}

	public static ApplicationLoggerBuilder validationTransaction(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.VALIDATION_TRANSACTION.name(), logger);
	}

	public static ApplicationLoggerBuilder startTransaction() {
		return new ApplicationLoggerBuilder(StepType.START_TRANSACTION.name());
	}

	public static ApplicationLoggerBuilder startTransaction(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.START_TRANSACTION.name(), logger);
	}

	public static ApplicationLoggerBuilder authorizePayment() {
		return new ApplicationLoggerBuilder(StepType.AUTHORIZE_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder authorizePayment(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.AUTHORIZE_PAYMENT.name(), logger);
	}

	public static ApplicationLoggerBuilder capturePayment() {
		return new ApplicationLoggerBuilder(StepType.CAPTURE_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder capturePayment(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.CAPTURE_PAYMENT.name(), logger);
	}

	public static ApplicationLoggerBuilder cancelPayment() {
		return new ApplicationLoggerBuilder(StepType.CANCEL_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder cancelPayment(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.CANCEL_PAYMENT.name(), logger);
	}

	public static ApplicationLoggerBuilder refundPayment() {
		return new ApplicationLoggerBuilder(StepType.REFUND_PAYMENT.name());
	}

	public static ApplicationLoggerBuilder refundPayment(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.REFUND_PAYMENT.name(), logger);
	}

	public static ApplicationLoggerBuilder authorizeRecharge() {
		return new ApplicationLoggerBuilder(StepType.AUTHORIZE_RECHARGE.name());
	}

	public static ApplicationLoggerBuilder authorizeRecharge(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.AUTHORIZE_RECHARGE.name(), logger);
	}

	public static ApplicationLoggerBuilder confirmRecharge() {
		return new ApplicationLoggerBuilder(StepType.CONFIRM_RECHARGE.name());
	}

	public static ApplicationLoggerBuilder confirmRecharge(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.CONFIRM_RECHARGE.name(), logger);
	}

	public static ApplicationLoggerBuilder cancelRecharge() {
		return new ApplicationLoggerBuilder(StepType.CANCEL_RECHARGE.name());
	}

	public static ApplicationLoggerBuilder cancelRecharge(Logger logger) {
		return new ApplicationLoggerBuilder(StepType.CANCEL_RECHARGE.name(), logger);
	}

	public static class ApplicationLoggerBuilder {

		private final String step;
		private Map<String, Object> params = new HashMap<String, Object>();
		private EventFormatter formatter = new JSONFormatter();
		private Logger logger;

		public ApplicationLoggerBuilder(String step) {
			this.step = step;
			this.formatter = jsonFormatter;
			this.logger = defaultLogger;
		}

		public ApplicationLoggerBuilder(String customStep, Logger logger) {
			this.step = customStep;
			this.logger = logger;
		}

		public ApplicationLoggerBuilder customer(String customer) {
			this.params.put(TransactionParamType.CUSTOMER.name().toLowerCase(), customer);
			return this;
		}

		public ApplicationLoggerBuilder receiver(String receiver) {
			this.params.put(TransactionParamType.RECEIVER.name().toLowerCase(), receiver);
			return this;
		}

		public ApplicationLoggerBuilder amount(Double amount) {
			this.params.put(TransactionParamType.AMOUNT.name().toLowerCase(), amount);
			return this;
		}

		public ApplicationLoggerBuilder ccToken(String ccToken) {
			this.params.put(TransactionParamType.CC_TOKEN.name().toLowerCase(), ccToken);
			return this;
		}

		public ApplicationLoggerBuilder ccLast4Digits(String ccLast4Digits) {
			this.params.put(TransactionParamType.CC_LAST4DIGISTS.name().toLowerCase(), ccLast4Digits);
			return this;
		}

		public ApplicationLoggerBuilder ccBrand(String ccBrand) {
			this.params.put(TransactionParamType.CC_BRAND.name().toLowerCase(), ccBrand);
			return this;
		}

		public ApplicationLoggerBuilder channel(String channel) {
			this.params.put(TransactionParamType.CHANNEL.name().toLowerCase(), channel);
			return this;
		}

		public ApplicationLoggerBuilder transactionId(Long transactionId) {
			this.params.put(TransactionParamType.TRANSACTION_ID.name().toLowerCase(), transactionId);
			return this;
		}

		public ApplicationLoggerBuilder tid(String tid) {
			this.params.put(TransactionParamType.TID.name().toLowerCase(), tid);
			return this;
		}

		public ApplicationLoggerBuilder authCode(String authCode) {
			this.params.put(TransactionParamType.AUTH_CODE.name().toLowerCase(), authCode);
			return this;
		}

		public ApplicationLoggerBuilder nsu(String nsu) {
			this.params.put(TransactionParamType.NSU.name().toLowerCase(), nsu);
			return this;
		}

		public ApplicationLoggerBuilder dtLog(Date dtLog) {
			this.params.put(TransactionParamType.DTLOG.name().toLowerCase(), dtLog);
			return this;
		}

		public ApplicationLoggerBuilder obs(String obs) {
			this.params.put(TransactionParamType.OBS.name().toLowerCase(), obs);
			return this;
		}

		public ApplicationLoggerBuilder param(String name, Object value) {
			this.params.put(name, value);
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

		public ApplicationLoggerBuilder inCustomFormat(EventFormatter formatter) {
			this.formatter = formatter;
			return this;
		}

		public void log() {
			TransactionEvent event = new TransactionEventImpl(step, params);
			String info = this.formatter.format(event);
			this.logger.info(info);
		}

		public void log(Object obj) {
			Map<String, Object> params = getAnnotatedFields(obj);
			TransactionEvent event = new TransactionEventImpl(step, params);
			String info = this.formatter.format(event);
			this.logger.info(info);
		}

		private Map<String, Object> getAnnotatedFields(Object obj) {
			Map<String, Object> params = new HashMap<String, Object>();

			try {
				for (Field field : obj.getClass().getDeclaredFields()) {
					if (field.isAnnotationPresent(TransactionParam.class)) {
						field.setAccessible(true);
						TransactionParam param = field.getAnnotation(TransactionParam.class);
						if (param.type() == TransactionParamType.CUSTOM_TYPE) {
							String customType = param.customType();
							if (customType.trim().length() == 0) {
								customType = field.getName();
							}
							params.put(customType, field.get(obj));
						} else
							params.put(param.type().name().toLowerCase(), field.get(obj));
					}
				}
			} catch (Exception e) {
				defaultLogger.error("Error while getting annotated fields.", e);
			}

			return params;
		}
	}

}
