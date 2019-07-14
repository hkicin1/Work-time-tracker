package sample.utilities;

import sample.models.Admin;
import sample.models.Employee;
import sample.models.Person;
import sample.models.Project;

import java.sql.PreparedStatement;
import java.util.List;

public interface WorkTimeTrackerDAO {

    void addPerson(Person p);

    void addEmployee(Employee e);

    void addProject(Project project);

    void addAdmin(Admin a);

}
