package sample.utilities;

import sample.exceptions.InvalidCredentialException;
import sample.exceptions.PersonDoesNotExistException;
import sample.models.User;

public class WorkTimeTracker {
    private WorkTimeTrackerDAO dao;

    public WorkTimeTracker(WorkTimeTrackerDAO dao) {
        this.dao = dao;
    }

    public static WorkTimeTracker createWorkTimeTracker() {
        return new WorkTimeTracker(new WorkTimeTrackerSQLiteDAO());
    }

    public User loginPerson(String username, String password) throws PersonDoesNotExistException, InvalidCredentialException {
        User user = dao.getUserByUsername(username);
        System.out.println(user);

        // Check if user exists
        // throw @PersonDoesNotExistException if user is not found
        if (user == null) throw new PersonDoesNotExistException();

        // Check is password valid
        // throw @InvalidCredentialException if password is not valid
        if (!dao.checkIsPasswordValid(username,password)) throw new InvalidCredentialException();

        return user;
    }

}
