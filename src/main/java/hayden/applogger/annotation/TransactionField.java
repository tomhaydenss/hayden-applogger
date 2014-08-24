package hayden.applogger.annotation;

import hayden.applogger.type.TransactionFieldType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionField {
	
	TransactionFieldType type() default TransactionFieldType.CUSTOM_TYPE;
	String customType() default "";
	boolean required();

}
