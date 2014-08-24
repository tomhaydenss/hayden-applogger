package hayden.applogger.annotation;

import hayden.applogger.type.StepType;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(ElementType.METHOD)
@Retention(RetentionPolicy.RUNTIME)
public @interface TransactionMethod {
	
	StepType step() default StepType.CUSTOM_STEP;
	String customStep() default "";

}
