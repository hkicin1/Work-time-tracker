package sample.utilities;

import sample.models.Admin;
import sample.models.Person;

public class WorkTimeTracker {
    private WorkTimeTrackerSQLiteDAO dao;

    private WorkTimeTracker(WorkTimeTrackerSQLiteDAO dao){
        this.dao = dao;
    }

    public static WorkTimeTracker createWorkTimeTracker(){
        return new WorkTimeTracker(new WorkTimeTrackerSQLiteDAO());
    }

    public Person findPersonByUsernameAndPasaword(boolean adminNotEmployee, String username, String password){
        if(adminNotEmployee){
            Person adminPerson = dao.getPersonByUsername(username);
            if(adminPerson != null && adminPerson.getPassword().equals(password)) return adminPerson;
            else return null;
        } else {    //employeeNotAdmin
            Person employeePerson = dao.getPersonByUsername(username);
            if(employeePerson != null && employeePerson.getPassword().equals(password)) return employeePerson;
        }
        return null;
    }
}
