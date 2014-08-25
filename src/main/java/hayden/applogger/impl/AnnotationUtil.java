package hayden.applogger.impl;

import hayden.applogger.ApplicationLogger;
import hayden.applogger.TransactionData;
import hayden.applogger.annotation.TransactionField;
import hayden.applogger.annotation.TransactionMethod;
import hayden.applogger.annotation.TransactionParameter;
import hayden.applogger.type.StepType;
import hayden.applogger.type.TransactionFieldType;

import java.lang.annotation.Annotation;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.HashMap;
import java.util.Map;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class AnnotationUtil {

	private static final Logger logger = LoggerFactory.getLogger(ApplicationLogger.class);

	public static Map<String, Object> extractFrom(Object obj) {
		Map<String, Object> items = new HashMap<String, Object>();

		try {
			for (Field field : obj.getClass().getDeclaredFields()) {
				if (field.isAnnotationPresent(TransactionField.class)) {
					field.setAccessible(true);
					TransactionField ann = field.getAnnotation(TransactionField.class);
					if (!ann.type().equals(TransactionFieldType.CUSTOM_TYPE)) {
						items.put(ann.type().name().toLowerCase(), field.get(obj));
					} else {
						String customType = ann.customType();
						if (customType.trim().length() == 0) {
							customType = field.getName();
						}
						items.put(customType, field.get(obj));
					}
				}
			}
		} catch (Exception e) {
			logger.error("Error while getting annotated fields.", e);
		}

		return items;
	}

	public static TransactionData extractFrom(Method method, Object[] paramValues) {
		try {
			boolean isValidMethod = false;
			String step = method.getName().toUpperCase();
			Annotation[] methodAnnotations = method.getDeclaredAnnotations();
			for (Annotation annotation : methodAnnotations) {
				if (annotation instanceof TransactionMethod) {
					TransactionMethod ann = (TransactionMethod) annotation;
					if (!ann.step().equals(StepType.CUSTOM_STEP)) {
						step = ann.step().name();
					} else {
						if (ann.customStep().trim().length() > 0) {
							step = ann.customStep();
						}
					}
					isValidMethod = true;
					break;
				}
			}

			if (isValidMethod) {

				Map<String, Object> items = new HashMap<String, Object>();
				Annotation[][] parameterAnnotations = method.getParameterAnnotations();
				//Parameter[] parameters = method.getParameters();

				int i = 0;
				for (Annotation[] annotations : parameterAnnotations) {
					//Parameter param = parameters[i];

					if (annotations.length > 0) {
						for (Annotation annotation : annotations) {
							if (annotation instanceof TransactionParameter) {
								TransactionParameter ann = (TransactionParameter) annotation;

								if (ann.type() == TransactionFieldType.CUSTOM_TYPE) {
									String customType = ann.customType();
									if (customType.trim().length() == 0) {
										// customType = param.getName();
									}
									items.put(customType, paramValues[i]);
								} else {
									items.put(ann.type().name().toLowerCase(), paramValues[i]);
								}
								break;
							}
						}
					} else {
						// Se nao tiver anotacao no parametro, tentar extrair anotacoes do objeto
						items.putAll(extractFrom(paramValues[i]));
					}
					i++;
				}

				return new TransactionDataImpl(step, items);

			}

		} catch (Exception e) {
			logger.error("Error while getting annotated fields.", e);
		}

		return null;

	}
}
