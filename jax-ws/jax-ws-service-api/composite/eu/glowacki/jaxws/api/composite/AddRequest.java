package eu.glowacki.jaxws.api.composite;

import javax.xml.bind.annotation.XmlType;

@XmlType( //
		name = "AddRequestMessage", // name of the XmlType should be different from the name of the class
		namespace = "http://glowacki.eu/composite" //
)
public final class AddRequest {

	public String surname;
	public String birthDate;

	/**
	 * empty parameterless constructor is required for unmarshalling
	 */
	public AddRequest() {
	}

	public AddRequest(String surname, String birthDate) {
		this.surname = surname;
		this.birthDate = birthDate;
	}
}