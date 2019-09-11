package sample.utilities;

import sample.models.User;

public interface WorkTimeTrackerDAO {

    boolean checkIsPasswordValid(String username, String password);

    User getUserByUsername(String username);
}
