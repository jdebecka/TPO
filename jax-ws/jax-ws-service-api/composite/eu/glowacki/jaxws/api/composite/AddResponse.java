package eu.glowacki.jaxws.api.composite;

import eu.glowacki.jaxws.implementation.composite.Person;

import javax.xml.bind.annotation.XmlType;
import java.util.List;

@XmlType( //
		name = "AddResponseMessage", // name of the XmlType should be different from the name of the class
		namespace = "http://glowacki.eu/composite" //
)
public final class AddResponse {

	public List<Person> result;

	/**
	 * empty parameterless constructor is required for unmarshalling
	 */
	public AddResponse() {
	}

	public AddResponse(List<Person> result) {
		this.result = result;
	}
}