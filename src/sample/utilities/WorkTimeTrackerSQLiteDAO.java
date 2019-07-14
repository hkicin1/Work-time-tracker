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
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.Scanner;

public class WorkTimeTrackerSQLiteDAO implements WorkTimeTrackerDAO {

    private static final String URL = "jdbc:sqlite:work_time_tracker.db";
    private Connection connection;

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
    }


    private void initialiseDatabase() {
        String sql="";
        java.net.URL x = getClass().getResource("work_time_tracker.db.sql");
        FileReader fileReader =
                null;
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
    public Person addPerson(Person p) {
        return null;
    }

    @Override
    public Employee addEmployee(Employee e) {
        return null;
    }

    @Override
    public Project addProject(Project project) {
        return null;
    }

    @Override
    public Admin addAdmin(Admin a) {
        return null;
    }

    @Override
    public Admin getAdminById(long id) {
        return null;
    }

    @Override
    public Person getPersonById(long id) {
        return null;
    }

}
