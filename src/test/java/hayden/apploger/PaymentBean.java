package hayden.apploger;

import hayden.applogger.ApplicationLoggerInterceptor;
import hayden.applogger.annotation.TransactionMethod;
import hayden.applogger.annotation.TransactionParameter;
import hayden.applogger.type.StepType;
import hayden.applogger.type.TransactionFieldType;

import javax.ejb.Stateless;
import javax.interceptor.Interceptors;

@Stateless
@Interceptors(ApplicationLoggerInterceptor.class)
public class PaymentBean {

	@TransactionMethod(step = StepType.AUTHORIZE_PAYMENT)
	public void authorize(@TransactionParameter(type = TransactionFieldType.CC_TOKEN) String tokenCard,
			@TransactionParameter(type = TransactionFieldType.AMOUNT) Double amount,
			@TransactionParameter(customType = "eldorado_service_id") String serviceId, 
			@TransactionParameter String externalID, 
			String cvv) {

	}

	@TransactionMethod
	public void capture(CaptureRequest request, String naoPrecisaLogar) {

	}

}
