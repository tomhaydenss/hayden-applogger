package hayden.apploger;

import org.junit.Test;

import hayden.applogger.ApplicationLogger;

public class AppLoggerTest {

	@Test
	public void deveLogarInicioTransacaoEmJSON() {
		ApplicationLogger.startTransaction()
			.customer("5521983044044")
			.receiver("21981073084")
			.amount(12.35)
			.log();
	}

	@Test
	public void deveLogarEventoEmXML() {
		ApplicationLogger.step("CHARGE_BACK")
			.customer("5521983044044")
			.amount(12.35)
			.param("service", 202)
			.param("paymentId", 12321312)
			.inXMLFormat()
			.log();
	}
	
	@Test
	public void deveLogarEventoAnotado() {
		PDVRequet request = new PDVRequet("0099887766", "FFAABBCCDDEEFF", "XXXxxx", "PDV TESTE", 14.99, "21988887777");
		ApplicationLogger.authorizeRecharge()
		.log(request);
	}
	
}
