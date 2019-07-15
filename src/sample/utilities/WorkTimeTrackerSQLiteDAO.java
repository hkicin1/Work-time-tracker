package sample.utilities;

import sample.models.Admin;
import sample.models.Employee;
import sample.models.Person;
import sample.models.Project;

import java.io.BufferedReader;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.IOException;
import java.net.URL;
import java.sql.*;
import java.time.LocalDate;
import java.util.Scanner;

public class WorkTimeTrackerSQLiteDAO implements WorkTimeTrackerDAO {

    private static Connection connection;

    private static PreparedStatement addPerson, addAdmin, addEmployee, addProject;
    private static PreparedStatement getPersonById, getAdminById, getEmployeeById;
    private static PreparedStatement deletePerson, deleteAdmin, deleteEmployee;
    private static PreparedStatement getNewPersonId, getNewAdminId, getNewEmployeeId, getNewProjectId;
    private static PreparedStatement getEmployeeWorkTime, getProjectWorkTimeForEmployee;
    private static PreparedStatement getPersonByUsername;

    WorkTimeTrackerSQLiteDAO(){
        try {
            connection = DriverManager.getConnection("jdbc:sqlite:work_time_tracker.db");
            initializeStatements();
        } catch (SQLException e) {
            System.out.println(e.getMessage());
            try {
                initialiseDatabase();
                initializeStatements();
            } catch (SQLException ex) {

            }
        }
    }

    private void initializeStatements() throws SQLException {
        addPerson = connection.prepareStatement("insert into person values(?,?,?,?,?,?,?,?)");
        addAdmin = connection.prepareStatement("insert into admin values(?,?)");
        addEmployee = connection.prepareStatement("insert into employee values(?,?)");
        addProject = connection.prepareStatement("insert into project values(?,?,?,?,?)");

        getPersonById = connection.prepareStatement("select * from person where id = ?");
        getAdminById = connection.prepareStatement("select * from admin where id = ?");
        getEmployeeById = connection.prepareStatement("select * from person, employee where person.id = employee.id and person.id = ?");
        getEmployeeWorkTime = connection.prepareStatement("select work_hours from work_hours,employee where work_hours.employee_id = employee.id");
        getProjectWorkTimeForEmployee = connection.prepareStatement("select work_hours from project_work_hours,employee,project " +
                "where work_hours.employee_id = employee.id and project.id = project_work_hours.id");

        deletePerson = connection.prepareStatement("delete from person where id = ?");
        deleteAdmin = connection.prepareStatement("delete from admin where id = ?");
        deleteEmployee = connection.prepareStatement("delete from employee where id = ?");

        getNewPersonId = connection.prepareStatement("select MAX(id) + 1 FROM person");
        getNewAdminId = connection.prepareStatement("select MAX(id) + 1 FROM admin");
        getNewEmployeeId = connection.prepareStatement("select MAX(id) + 1 FROM employee");
        getNewProjectId = connection.prepareStatement("select MAX(id) + 1 FROM project");

        getPersonByUsername = connection.prepareStatement("select * from person where person.username = ?");

    }

    
    private void initialiseDatabase() {
        String sql="";
        java.net.URL x = getClass().getResource("work_time_tracker.db.sql");
        FileReader fileReader = null;
        try {
            fileReader = new FileReader(x.getFile());

            // Always wrap FileReader in BufferedReader.
            BufferedReader bufferedReader =
                    new BufferedReader(fileReader);
            Scanner scanner = new Scanner(bufferedReader);
            while(scanner.hasNextLine()){
                sql+=scanner.nextLine();
            }
            try {
                scanner.close();
                bufferedReader.close();
                fileReader.close();
            } catch (IOException e) {

            }
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        }
        sql = sql.replace("\n"," ");
        sql = sql.replace(";","\n");
        String[] upiti = sql.split("\n");
        try {
            Statement statement = connection.createStatement();
            for (String upit : upiti){
                statement.execute(upit);
            }
        } catch (SQLException e) {

        }
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
            addPerson.setString(8, p.getPassword());
            addPerson.executeUpdate();
        } catch (SQLException e) {
            e.printStackTrace();
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
        }
    }

    public Date convertToDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
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
            }
    }

    public Person getPerson(PreparedStatement preparedStatement) throws SQLException {
        ResultSet r = preparedStatement.executeQuery();
        if(r.next()) {
            return new Person(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                    r.getInt(5), r.getString(6), r.getString(7), r.getString(8));
        }
        return null;
    }

    public Person getPersonByUsername(String username) {
        try {

            getPersonByUsername.setString(1,username);
            ResultSet r = getPersonByUsername.executeQuery();
            int id = r.getInt(1);
            Person person = new Person(r.getInt(1), r.getString(2), r.getString(3), r.getString(4),
                    r.getInt(5), r.getString(6), r.getString(7), r.getString(8));

            getPersonByUsername.setString(1,username);
            ResultSet rs = getPersonByUsername.executeQuery();
            if(rs.next()){
                Person p = getPersonById(rs.getInt(1));
                if(p != null) {
                    return new Admin(rs.getInt(1), person);
                }
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

    public Person getPersonById(long id) {
        try {
            PreparedStatement preparedStatement = null;
            preparedStatement = connection.prepareStatement("select * from person where id = ?");
            return getPerson(preparedStatement);
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return null;
    }

}
