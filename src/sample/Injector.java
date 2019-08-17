package sample;

import sample.utilities.WorkTimeTracker;
import sample.utilities.WorkTimeTrackerDAO;
import sample.utilities.WorkTimeTrackerSQLiteDAO;

public class Injector {

    private static WorkTimeTrackerDAO workTimeTrackerDAOInstance = null;

    public static WorkTimeTrackerDAO getWorkTimeTrackerDao() {
        if (workTimeTrackerDAOInstance == null)
            workTimeTrackerDAOInstance = new WorkTimeTrackerSQLiteDAO();
        return workTimeTrackerDAOInstance;
    }

    private static WorkTimeTracker workTimeTrackerInstance = null;

    public static WorkTimeTracker getWorkTimeTracker() {
        if (workTimeTrackerInstance == null)
            workTimeTrackerInstance = new WorkTimeTracker(getWorkTimeTrackerDao());
        return workTimeTrackerInstance;
    }
}
