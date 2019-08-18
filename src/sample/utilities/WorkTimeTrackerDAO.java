package sample.utilities;

import sample.models.Project;
import sample.models.User;

public interface WorkTimeTrackerDAO {

    void addUser(User user);

    void addProject(Project project);

    boolean checkIsPasswordValid(String username, String password);

    User getUserByUsername(String username);
}
