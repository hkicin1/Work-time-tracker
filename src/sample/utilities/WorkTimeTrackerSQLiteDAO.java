package sample.utilities;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import org.sqlite.JDBC;
import sample.models.Project;
import sample.models.User;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class WorkTimeTrackerSQLiteDAO implements WorkTimeTrackerDAO {

    private static WorkTimeTrackerSQLiteDAO instance;

    private Connection connection;

    private ObservableList<Project> projects = FXCollections.observableArrayList();

    private PreparedStatement addUser, addProject, addWorkHoursForUser;

    private PreparedStatement getUserById, getUserByUsername, getProjectById;

    private PreparedStatement deleteUser, deleteProject;

    private PreparedStatement getNewUserId, getNewProjectId;

    private PreparedStatement getEmployeeWorkTime, getProjectWorkTimeForEmployee;

    private PreparedStatement getAllProjects, getAllEmployees;


    public static WorkTimeTrackerSQLiteDAO getInstance() {
        if(instance == null) instance = new WorkTimeTrackerSQLiteDAO();
        return instance;
    }

    public WorkTimeTrackerSQLiteDAO(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:work_time_tracker.db");
        } catch (SQLException e) {
           e.printStackTrace();
        }

        try {
            getUserById = connection.prepareStatement("select * from user where id = ?");
        }
        catch (SQLException e) {
            initializeDatabase();
        }

        try {
            addUser = connection.prepareStatement("insert into user values(?,?,?,?,?,?,?,?,?)");
            addProject = connection.prepareStatement("insert into project values(?,?,?,?,?)");
//            addWorkHoursForUser = connection.prepareStatement("insert into work_hours values(?,?,?,?) where id = ?");

            getUserById = connection.prepareStatement("select * from user where id = ?");
            getProjectById = connection.prepareStatement("select * from project where id = ?");

            deleteUser = connection.prepareStatement("delete from user where id = ?");
            deleteProject = connection.prepareStatement("delete from project where id = ?");

            getNewUserId = connection.prepareStatement("select MAX(id) + 1 FROM user");
            getNewProjectId = connection.prepareStatement("select MAX(id) + 1 FROM project");

            getUserByUsername = connection.prepareStatement("select * from user where username = ?");
            getAllProjects = connection.prepareStatement("select * from project");
            getAllEmployees = connection.prepareStatement("select * from user");
        } catch (SQLException e){
            e.printStackTrace();
        }
    }

    static {
        try {
            DriverManager.registerDriver(new JDBC());
        }
        catch (SQLException e) {
            e.printStackTrace();
        }
    }


    private void initializeDatabase() {
        Scanner input = null;
        try {
            input = new Scanner(new FileInputStream("work_time_tracker.db.sql"));
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        String sqlQuery = "";
        while(input.hasNext()) {
            sqlQuery += input.nextLine();
            if ( sqlQuery.length() == 0 ) {
                continue;
            }
            else if (sqlQuery.charAt(sqlQuery.length() -1 ) == ';') {
                try {
                    Statement stmt = connection.createStatement();
                    stmt.execute(sqlQuery);
                    sqlQuery = "";
                }
                catch (SQLException ex) {
                    ex.printStackTrace();
                }
            }
        }

        input.close();

    }


    @Override
    public void addUser(User p) {
        ResultSet rs = null;
        try {
            rs = getNewUserId.executeQuery();
            int id = 1;
            if (rs.next()) id = rs.getInt(1);
            p.setId(id);

            addUser.setInt(1, p.getId());
            addUser.setString(2, p.getName());
            addUser.setString(3, p.getSurname());
            addUser.setString(4, p.getAddress());
            addUser.setInt(5, p.getPostalNumber());
            addUser.setString(6, p.getCity());
            addUser.setString(7, p.getUserName());
            addUser.setInt(8, p.getIsAdmin());
            addUser.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (getNewUserId != null) {
                try {
                    getNewUserId.close();
                } catch (SQLException d) {
                    d.printStackTrace();
                }
            }
            if (addUser != null) {
                try {
                    addUser.close();
                } catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        }

    }


    public Date convertToDate(LocalDate dateToConvert) {
        return Date.valueOf(dateToConvert);
    }

    @Override
    public void addProject(Project project) {
        ResultSet rs = null;
        try {
            rs = getNewProjectId.executeQuery();
            int id = 1;
            if (rs.next()) id = rs.getInt(1);
            project.setId(id);

            addProject.setInt(1, project.getId());
            addProject.setString(2, project.getName());
            addProject.setDate(3, convertToDate(project.getStartDate()));
            addProject.setDate(4, convertToDate(project.getFinishDate()));
            addProject.setInt(5, project.getActivity());
            addProject.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (getNewProjectId != null) {
                try {
                    getNewProjectId.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
            if (addProject != null) {
                try {
                    addProject.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
    }


    @Override
    public boolean checkIsPasswordValid(String username, String password) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from user where username = ?");

            preparedStatement.setString(1,username);
            ResultSet r = preparedStatement.executeQuery();
            if (r.next()) {
                String test = r.getString(8);
                return test.equals(password);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return false;
    }

    public User getUser(PreparedStatement preparedStatement) throws SQLException {
        ResultSet r = preparedStatement.executeQuery();
        if(r.next()) {
            return new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                    r.getInt(5), r.getString(6), r.getString(7), r.getString(8), r.getInt(9));
        }
        return null;
    }

    public User getUserByUsername(String username) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from user where username = ?");

            preparedStatement.setString(1,username);
            ResultSet r = preparedStatement.executeQuery();
            if (r.next()) {
                User user = new User(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                        r.getInt(5), r.getString(6), r.getString(7), r.getString(8), r.getInt(9));
                return user;
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }

    public User getUserById(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from user where id = ?");
            preparedStatement.setLong(1, id);
            return getUser(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (preparedStatement != null) {
                try {
                    preparedStatement.close();
                } catch (SQLException e) {
                    e.printStackTrace();
                }
            }
        }
        return null;
    }
    public ArrayList<Project> getAllProjects() throws SQLException {
        Statement statement = null;
        try {
            statement = connection.createStatement();
            ResultSet r = statement.executeQuery("select * from project");
            ArrayList<Project> allProjects = new ArrayList<>();
            while (r.next()){
                allProjects.add(getProjectById(r.getInt(1)));
            }
            return allProjects;
        } catch (SQLException e) {
            e.printStackTrace();
        }finally {
            statement.close();
        }
        return null;
    }

    private Project getProjectById(int id) throws SQLException {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from project where id = ?");
            preparedStatement.setLong(1, id);
            ResultSet r = preparedStatement.executeQuery();
            if(r.next()){
                return new Project(r.getInt(1), r.getString(2), r.getDate(3).toLocalDate(), r.getDate(4).toLocalDate(), r.getInt(5));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            preparedStatement.close();
        }
        return null;

    }
}
