package eu.glowacki.jaxws.implementation.sample;

import eu.glowacki.jaxws.api.sample.ICalculator;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.logging.Logger;

@WebService( //
		name = "ICalculator", //
		targetNamespace = "http://glowacki.eu/sample" //
)
public final class CalculatorImpl implements ICalculator {

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	public static void main(String... args) {
		Endpoint.publish(ICalculator.URI, new CalculatorImpl());
		LOGGER.info("SERVICE STARTED");
	}

	public CalculatorImpl() {
	}

	public int add(int component1, int component2) {
		return component1 + component2;
	}

	public int subtract(int minuend, int subtrahend) {
		return minuend - subtrahend;
	}
}