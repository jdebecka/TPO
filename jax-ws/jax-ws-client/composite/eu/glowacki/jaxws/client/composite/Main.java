package eu.glowacki.jaxws.client.composite;

import eu.glowacki.jaxws.api.IService;
import eu.glowacki.jaxws.client.composite.proxy.AddRequestMessage;
import eu.glowacki.jaxws.client.composite.proxy.AddResponseMessage;
import eu.glowacki.jaxws.client.composite.proxy.CompositeImplService;
import eu.glowacki.jaxws.client.composite.proxy.IComposite;
import eu.glowacki.jaxws.client.delayed.proxy.ServiceException_Exception;
import eu.glowacki.jaxws.implementation.composite.Person;

import java.net.MalformedURLException;
import java.net.URL;
import java.util.logging.Logger;

public final class Main {

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	static {
		System.setProperty("com.sun.xml.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.ws.transport.http.HttpAdapter.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.client.HttpTransportPipe.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dump", "true");
		System.setProperty("com.sun.xml.internal.ws.transport.http.HttpAdapter.dumpTreshold", "999999");
	}

	public static void main(String... args) throws MalformedURLException, ServiceException_Exception {
		URL wsdl = new URL(eu.glowacki.jaxws.api.composite.IComposite.URI + IService.WSDL_SUFFIX);
		CompositeImplService service = new CompositeImplService(wsdl);
		IComposite proxy = service.getICompositePort();
		AddRequestMessage request = new AddRequestMessage();

		request.setSurname("Debecka");
		request.setBirthDate("26-06-1997");


		AddResponseMessage response = proxy.add(request);

		AddResponseMessage responseSurname = proxy.filterLastName(request);

		AddResponseMessage responseBirthday = proxy.filterBirthDate(request);

		LOGGER.info("\nRequest 1 {surname/DOB}\n");
		for(Person person : response.getResult()){

			LOGGER.info(person.toString());
		}

		LOGGER.info("\nRequest 2 {just surname}\n");
		for(Person person : responseSurname.getResult()){

			LOGGER.info(person.toString());
		}

		LOGGER.info("\nRequest 3 {just DOB}\n");

		for(Person person : responseBirthday.getResult()){

			LOGGER.info(person.toString());
		}
	}
}