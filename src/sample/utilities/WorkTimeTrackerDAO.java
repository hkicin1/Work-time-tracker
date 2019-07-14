package sample.utilities;

import sample.models.Admin;
import sample.models.Employee;
import sample.models.Person;
import sample.models.Project;

public interface WorkTimeTrackerDAO {

    Person addPerson(Person p);

    Employee addEmployee(Employee e);

    Project addProject(Project project);

    Admin addAdmin(Admin a);

    Admin getAdminById(long id);

    Person getPersonById(long id);

}
