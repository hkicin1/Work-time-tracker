package sample.utilities;

import sample.models.Admin;
import sample.models.Employee;
import sample.models.Person;
import sample.models.Project;

public interface WorkTimeTrackerDAO {

    void addPerson(Person person);

    void addEmployee(Employee employee);

    void addProject(Project project);

    void addAdmin(Admin admin);

    boolean checkIsPasswordValid(String username, String password);

    Person getPersonByUsername(String username);
}
