package hayden.applogger;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.FIELD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionParam {
	
	TransactionParamType type() default TransactionParamType.CUSTOM_TYPE;
	String customType() default "";
	boolean required();

}
