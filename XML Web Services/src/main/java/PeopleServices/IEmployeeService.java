package PeopleServices;

import PeopleManagment.Employee;

import javax.jws.WebMethod;
import javax.jws.WebService;

@WebService
public interface IEmployeeService {
    public static final String URI = "http://localhost:8080/sample";
    @WebMethod(action = "http://glowacki.eu/calculator/add")
    Employee filterByBirthDate(String birthDate);

    @WebMethod(action = "http://glowacki.eu/calculator/subtract")
    Employee filterBySurname(String surname);
}
