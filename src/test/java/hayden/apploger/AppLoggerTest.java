package hayden.apploger;

import static org.junit.Assert.assertNotNull;
import hayden.applogger.ApplicationLogger;

import java.io.File;
import java.util.HashMap;
import java.util.Map;

import javax.ejb.embeddable.EJBContainer;

import org.junit.Test;

public class AppLoggerTest {

	@Test
	public void shouldLogStartTransactionInJSONFormat() {
		ApplicationLogger.startTransaction()
			.customer("5521983044044")
			.receiver("21981073084")
			.amount(12.35)
			.log();
	}

	@Test
	public void shouldLogCustomEventInXMLFormat() {
		ApplicationLogger.step("CHARGE_BACK")
			.customer("5521983044044")
			.amount(12.35)
			.param("service", 202)
			.param("paymentId", 12321312)
			.inXMLFormat()
			.log();
	}
	
	@Test
	public void shouldLogAnAnnotatedBusinessObject() {
		PDVRequet request = new PDVRequet("0099887766", "FFAABBCCDDEEFF", "XXXxxx", "PDV TESTE", 14.99, "21988887777");
		ApplicationLogger.authorizeRecharge().inCustomFormat(new ToStringFormatter())
		.log(request);
	}
	
	@Test
	public void shouldLogByEJBInterceptor() throws Exception {
		Map<Object, Object> properties = new HashMap<Object, Object>();
		properties.put(EJBContainer.MODULES, new File("target/classes"));
        EJBContainer container = EJBContainer.createEJBContainer(properties);
        
	    PaymentBean paymentBean = (PaymentBean)container.getContext().lookup("java:global/test-classes/PaymentBean");
	    assertNotNull(paymentBean);
	    
	    paymentBean.authorize("XFABZ-FASEFA", 17.99, "201", "EXT123", "123");
	    
	    paymentBean.capture(new CaptureRequest("201", "EXT123"), "Nao Precisa Logar");
	    
	    
	    container.close();
	}
	
}
