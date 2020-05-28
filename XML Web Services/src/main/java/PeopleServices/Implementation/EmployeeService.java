package PeopleServices.Implementation;
import javax.jws.WebService;

import PeopleManagment.Employee;
import PeopleServices.IEmployeeService;


public class EmployeeService implements IEmployeeService {

    @Override
    public Employee filterByBirthDate(String birthDate) {
        return null;
    }

    @Override
    public Employee filterBySurname(String surname) {
        return null;
    }
}