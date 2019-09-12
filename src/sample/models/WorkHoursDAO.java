package sample.models;

import javax.naming.InsufficientResourcesException;
import java.sql.*;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

import static java.time.temporal.ChronoUnit.MINUTES;

public class WorkHoursDAO {
    private static WorkHoursDAO inst;
    private Connection connection;

    public static WorkHoursDAO getInst() {
        if (inst == null) {
            initialize();
        }
        return inst;
    }

    private static void initialize() {
        inst = new WorkHoursDAO();
    }

    public static void deleteInstance() {
        if (inst != null) {
            try {
                inst.connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        inst = null;
    }

    public void close() {
        try {
            connection.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
    PreparedStatement getIdWorkHours,deleteWorkHours;

    public WorkHoursDAO(){
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:work_time_tracker.db");
            getIdWorkHours = connection.prepareStatement("SELECT MAX(id)+1 FROM work_hours");
            deleteWorkHours = connection.prepareStatement("DELETE FROM work_hours where id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public int getIdWorkHours(){
        int id = 0;
        try {
            String sql = "SELECT MAX(id)+1 FROM work_hours";
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            ResultSet rs = getIdWorkHours.executeQuery();
            while(rs.next()){
                id = rs.getInt(1);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return id;
    }

    public Date convertToDateViaSqlDate(LocalDate dateToConvert) {
        return java.sql.Date.valueOf(dateToConvert);
    }

    public boolean addWorkHours(WorkHours workHours){
        String sql = "insert into work_hours values(?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = getIdWorkHours.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            preparedStatement.setInt(1,id);
            preparedStatement.setInt(2, workHours.getUser().getId());
            preparedStatement.setDate(3, convertToDateViaSqlDate(workHours.getDate()));
            preparedStatement.setString(4, workHours.getStartedWorking());
            preparedStatement.setString(5, workHours.getFinishedWorking());
            preparedStatement.setString(6, workHours.getWorkHours());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }
    }
/*
    public void updateStartedWorkingTime(LocalTime startedWorking, int id){
        String sql = "update work_hours set started_working = ? where id = ?";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, startedWorking.toString());
            preparedStatement.setInt(2, id);
            preparedStatement.executeUpdate();
            preparedStatement.close();
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }
*/
    public void updateFinishedWorkingTime(LocalTime finishedWorking, int userId, LocalDate date){
        String sql = "update work_hours set finished_working = ? where user_id = ? and date = ?";
        String sqlUpdateWorkHours = "update work_hours set work_hours = ? where user_id = ? and date = ?";
        String sql1 = "select started_working from work_hours where user_id = ? and date = ?";
        String sql2 = "select finished_working from work_hours where user_id = ? and date = ?";

        try {
            // update finished_working time
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);
            preparedStatement.setString(1, finishedWorking.toString());
            preparedStatement.setInt(2, userId);
            preparedStatement.setDate(3, convertToDateViaSqlDate(date));
            preparedStatement.executeUpdate();

            System.out.println(date);
            //get started_working time
            PreparedStatement preparedStatement1 = this.connection.prepareStatement(sql1);
            preparedStatement1.setInt(1, userId);
            preparedStatement1.setDate(2, convertToDateViaSqlDate(date));
            System.out.println(convertToDateViaSqlDate(date));
            ResultSet rs1 = preparedStatement1.executeQuery();

            String beginning = null;
            while (rs1.next()) beginning = rs1.getString(1);

            //get finished working time
            PreparedStatement preparedStatement2 = this.connection.prepareStatement(sql2);
            preparedStatement2.setInt(1, userId);
            preparedStatement2.setDate(2, convertToDateViaSqlDate(date));
            ResultSet rs2 = preparedStatement2.executeQuery();
            String ending = null;
            if (rs2.next()) ending = rs2.getString(1);


            LocalTime localTimeBeg = LocalTime.parse(beginning);
            LocalTime localTimeEnd = LocalTime.parse(ending);
            long todaysWorkHours = (MINUTES.between(localTimeBeg, localTimeEnd) + 1440) % 1440;


            // update work_hours
            PreparedStatement preparedStatementWh = this.connection.prepareStatement(sqlUpdateWorkHours);
            preparedStatementWh.setString(1, String.valueOf(todaysWorkHours));
            preparedStatementWh.setInt(2, userId);
            preparedStatementWh.setDate(3, convertToDateViaSqlDate(date));
            preparedStatementWh.executeUpdate();

            // close all statements
            preparedStatement.close();
            preparedStatement1.close();
            preparedStatement2.close();
            preparedStatementWh.close();
        } catch (SQLException | NullPointerException e) {
            e.printStackTrace();
        }
    }

    public String secondsToHMS(int seconds) {
        int hours = seconds / 3600;
        seconds = seconds - hours * 3600;
        int minutes = seconds / 60;
        seconds = seconds - minutes * 60;
        String time = hours + ":" + minutes + ":" + seconds;
        return time;
    }

    public void removeWorkingHours(WorkHours workHours){
        try {
            if (workHours != null) {
                deleteWorkHours.setInt(1, workHours.getId());
                deleteWorkHours.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<WorkHours> listWorkHours(){
        String sql = "SELECT * FROM workHours";
        List<WorkHours> workHoursList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.prepareStatement(sql).executeQuery();
            while (rs.next()){
                WorkHours workHours = new WorkHours();
                workHours.setId(rs.getInt(1));

                User user;
                PreparedStatement preparedStatement = connection.prepareStatement("select * from user where id = ?");
                preparedStatement.setInt(1, rs.getInt(2));
                ResultSet rs1 = preparedStatement.executeQuery();

                Position position;
                PreparedStatement ps = connection.prepareStatement("select * from position were id = ?");
                preparedStatement.setInt(1, rs1.getInt(7));
                ResultSet rs2 = preparedStatement.executeQuery();
                position = new Position(rs2.getInt(1), rs2.getString(2));

                user = new User(rs1.getInt(1), rs1.getString(2),rs1.getString(3),rs1.getString(4),rs1.getInt(5),rs1.getString(6), position,rs1.getString(8),rs1.getString(9),rs1.getInt(10));

                workHours.setUser(user);
                workHours.setDate(rs.getDate(3).toLocalDate());
                workHours.setStartedWorking(rs.getString(4));
                workHours.setFinishedWorking(rs.getString(5));
                workHours.setWorkHours(rs.getString(6));
                workHoursList.add(workHours);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return workHoursList;
    }
}

