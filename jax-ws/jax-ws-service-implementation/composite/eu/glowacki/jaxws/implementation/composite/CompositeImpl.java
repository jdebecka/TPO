package eu.glowacki.jaxws.implementation.composite;

import eu.glowacki.jaxws.api.composite.AddRequest;
import eu.glowacki.jaxws.api.composite.AddResponse;
import eu.glowacki.jaxws.api.composite.IComposite;

import javax.jws.WebService;
import javax.xml.ws.Endpoint;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Logger;

@WebService( //
		name = "IComposite", //
		targetNamespace = "http://glowacki.eu/composite" //
)
public final class CompositeImpl implements IComposite {
	static List<Person> people = CompositeImpl.generatePeopleMap();

	private static final Logger LOGGER = Logger.getAnonymousLogger();

	public static void main(String... args) {
		Endpoint.publish(IComposite.URI, new CompositeImpl());
		LOGGER.info("SERVICE STARTED");
	}

	public CompositeImpl() {
	}

	public AddResponse filterLastName(final AddRequest request) {
		List<Person> result = new ArrayList<Person>();

		for(Person person: people) {
			if(person.getSurname().equals(request.surname)) {
				result.add(person);
			}
		}

		return new AddResponse(result);
	}

	public AddResponse filterBirthDate(final AddRequest request) {
		List<Person> result = new ArrayList<Person>();

		for(Person person: people) {
			if(person.getBirthDate().equals(request.birthDate)) {
				result.add(person);
			}
		}

		return new AddResponse(result);
	}


	public AddResponse add(final AddRequest request) {
		List<Person> result = new ArrayList<Person>();

		for(Person person: people) {
			if(person.getSurname().equals(request.surname)&& person.getBirthDate().equals(request.birthDate)) {
				result.add(person);
			}
		}

		return new AddResponse(result);
	}

	private static List<Person> generatePeopleMap() {

		Person p1 = new Person("Julia", "Debecka", "26-06-1997");
		Person p2 = new Person("Jan", "Witkowski", "01-12-1996");
		Person p3 = new Person("Parker", "Hicks", "05-09-1998");
		Person p4 = new Person("Kamil", "Sikora", "25-01-1997");
		Person p5 = new Person("Natalia", "Debecka", "26-06-1997");
		Person p6 = new Person("Antonina", "Debecka", "23-08-2005");
		Person p7 = new Person("Bozenka", "Hultaj", "26-06-1997");

		List<Person> people = new ArrayList<Person>();
		people.add(p1);
		people.add(p2);
		people.add(p3);
		people.add(p4);
		people.add(p5);
		people.add(p6);
		people.add(p7);
		return people;
	}


}