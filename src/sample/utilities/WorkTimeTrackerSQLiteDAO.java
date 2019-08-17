package sample.utilities;

import org.sqlite.JDBC;
import sample.models.Admin;
import sample.models.Employee;
import sample.models.Person;
import sample.models.Project;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class WorkTimeTrackerSQLiteDAO implements WorkTimeTrackerDAO {

    private static WorkTimeTrackerSQLiteDAO instance;

    private Connection connection;

    private PreparedStatement addPerson, addAdmin, addEmployee, addProject;
    private PreparedStatement getPersonById, getAdminById, getEmployeeById;
    private PreparedStatement deletePerson, deleteAdmin, deleteEmployee;
    private PreparedStatement getNewPersonId, getNewAdminId, getNewEmployeeId, getNewProjectId;
    private PreparedStatement getEmployeeWorkTime, getProjectWorkTimeForEmployee;
    private PreparedStatement getPersonByUsername;

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
            getPersonById = connection.prepareStatement("select * from person where id = ?");
        }
        catch (SQLException e) {
            initializeDatabase();
        }

        try {
            addPerson = connection.prepareStatement("insert into person values(?,?,?,?,?,?,?,?)");
            addAdmin = connection.prepareStatement("insert into admin values(?,?)");
            addEmployee = connection.prepareStatement("insert into employee values(?,?)");
            addProject = connection.prepareStatement("insert into project values(?,?,?,?,?)");

            getPersonById = connection.prepareStatement("select * from person where id = ?");
            getAdminById = connection.prepareStatement("select * from admin where id = ?");
            getEmployeeById = connection.prepareStatement("select * from person, employee where person.id = employee.id and person.id = ?");
            getEmployeeWorkTime = connection.prepareStatement("select work_hours from work_hours w,employee e where w.employee_id = e.id");

            deletePerson = connection.prepareStatement("delete from person where id = ?");
            deleteAdmin = connection.prepareStatement("delete from admin where id = ?");
            deleteEmployee = connection.prepareStatement("delete from employee where id = ?");

            getNewPersonId = connection.prepareStatement("select MAX(id) + 1 FROM person");
            getNewAdminId = connection.prepareStatement("select MAX(id) + 1 FROM admin");
            getNewEmployeeId = connection.prepareStatement("select MAX(id) + 1 FROM employee");
            getNewProjectId = connection.prepareStatement("select MAX(id) + 1 FROM project");

            getPersonByUsername = connection.prepareStatement("select * from person where username = ?");
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
    public void addPerson(Person p) {
        ResultSet rs = null;
        try {
            rs = getNewPersonId.executeQuery();
            int id = 1;
            if (rs.next()) id = rs.getInt(1);
            p.setId(id);

            addPerson.setInt(1, p.getId());
            addPerson.setString(2, p.getName());
            addPerson.setString(3, p.getSurname());
            addPerson.setString(4, p.getAddress());
            addPerson.setInt(5, p.getPostalNumber());
            addPerson.setString(6, p.getCity());
            addPerson.setString(7, p.getUserName());
            addPerson.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
        } finally {
            if (getNewPersonId != null) {
                try {
                    getNewPersonId.close();
                } catch (SQLException d) {
                    d.printStackTrace();
                }
            }
            if (addPerson != null) {
                try {
                    addPerson.close();
                } catch (SQLException d) {
                    d.printStackTrace();
                }
            }
        }

    }

    @Override
    public void addEmployee(Employee e) {
        ResultSet rs = null;
        try {
            rs = getNewEmployeeId.executeQuery();
            int id = 1;
            if (rs.next()) id = rs.getInt(1);
            e.setId(id);

            addEmployee.setInt(1, e.getId());
            addEmployee.setInt(2, e.getPerson().getId());
            addEmployee.executeUpdate();
        } catch (SQLException a) {
            a.printStackTrace();
        } finally {
            if (addEmployee != null) {
                try {
                    addEmployee.close();
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
    public void addAdmin(Admin a) {

            try {
                ResultSet rs = null;
                rs = getNewAdminId.executeQuery();
                int id = 1;
                if (rs.next()) id = rs.getInt(1);
                a.setId(id);

                addAdmin.setInt(1, a.getId());
                addAdmin.setInt(5, a.getPerson().getId());
                addAdmin.executeUpdate();
            } catch (SQLException e) {
                e.printStackTrace();
            } finally {
                if (getNewAdminId != null) {
                    try {
                        getNewAdminId.close();
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
            preparedStatement = connection.prepareStatement("select * from person where username = ?");

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

    public Person getPerson(PreparedStatement preparedStatement) throws SQLException {
        ResultSet r = preparedStatement.executeQuery();
        if(r.next()) {
            return new Person(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                    r.getInt(5), r.getString(6), r.getString(7));
        }
        return null;
    }

    public Person getPersonByUsername(String username) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from person where username = ?");

            preparedStatement.setString(1,username);
            ResultSet r = preparedStatement.executeQuery();
            if (r.next()) {
                Person person = new Person(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                        r.getInt(5), r.getString(6), r.getString(7));
                return person;
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

    public Person getPersonById(long id) {
        PreparedStatement preparedStatement = null;
        try {
            preparedStatement = connection.prepareStatement("select * from person where id = ?");
            preparedStatement.setLong(1, id);
            return getPerson(preparedStatement);
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

}
