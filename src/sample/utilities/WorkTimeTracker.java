package sample.utilities;

import sample.exceptions.InvalidCredentialException;
import sample.exceptions.PersonDoesNotExistException;
import sample.models.Person;

public class WorkTimeTracker {
    private WorkTimeTrackerDAO dao;

    public WorkTimeTracker(WorkTimeTrackerDAO dao) {
        this.dao = dao;
    }

    public Person loginPerson(String username, String password) throws PersonDoesNotExistException, InvalidCredentialException {
        Person person = dao.getPersonByUsername(username);

        // Check if person exists
        // throw @PersonDoesNotExistException if person is not found
        if (person == null) throw new PersonDoesNotExistException();

        // Check is password valid
        // throw @InvalidCredentialException if password is not valid
        if (!dao.checkIsPasswordValid(username,password)) throw new InvalidCredentialException();

        return person;
    }
}
