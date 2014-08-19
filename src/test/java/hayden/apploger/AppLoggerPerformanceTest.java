package hayden.apploger;

import hayden.applogger.ApplicationLogger;
import org.databene.contiperf.PerfTest;
import org.databene.contiperf.Required;
import org.databene.contiperf.junit.ContiPerfRule;
import org.junit.Rule;
import org.junit.Test;

public class AppLoggerPerformanceTest {

	@Rule
	public ContiPerfRule rule = new ContiPerfRule();

	@Test
	@PerfTest(threads = 10, invocations = 10000)
	@Required(max = 200, median = 1, totalTime = 2500)
	public void teste1() {
		ApplicationLogger.startTransaction().customer("5521983044044").receiver("21981073084").amount(12.35).log();
	}

}
