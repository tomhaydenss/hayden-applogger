package hayden.applogger;

import hayden.applogger.impl.AnnotationUtil;

import java.lang.reflect.Method;

import javax.interceptor.AroundInvoke;
import javax.interceptor.InvocationContext;

public class ApplicationLoggerInterceptor {

	@AroundInvoke
	public Object annotateThread(InvocationContext invocationContext) throws Exception {

		Method method = invocationContext.getMethod();
		Object[] parameters = invocationContext.getParameters();
		TransactionData transactionData = AnnotationUtil.extractFrom(method, parameters);
		if (transactionData != null) {
			ApplicationLogger.step(transactionData).log();
		}

		return invocationContext.proceed();

	}

}
