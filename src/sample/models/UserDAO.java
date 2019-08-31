package sample.models;

import javafx.geometry.Pos;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class UserDAO {

    private static UserDAO inst;
    private Connection connection;

    public static UserDAO getInst() {
        if (inst == null) {
            initialize();
        }
        return inst;
    }

    private static void initialize() {
        inst = new UserDAO();
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
    PreparedStatement getIdUser,deleteUser;
    public UserDAO(){
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:work_time_tracker.db");
            getIdUser =connection.prepareStatement("SELECT MAX(id)+1 FROM user");
            deleteUser = connection.prepareStatement("DELETE FROM user where id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public boolean addUser(User user){
        String sql = "insert into user values(?,?,?,?,?,?,?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = getIdUser.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            preparedStatement.setString(2, user.getName());
            preparedStatement.setString(3, user.getSurname());
            preparedStatement.setString(4, user.getAddress());
            preparedStatement.setInt(5, user.getPostalNumber());
            preparedStatement.setString(6, user.getCity());
            preparedStatement.setString(7, user.getUserName());
            preparedStatement.setInt(8, user.getIsAdmin());
            preparedStatement.execute();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public void removeUser(User user){
        try {
            if (user != null) {
                deleteUser.setInt(1, user.getId());
                deleteUser.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }

    public String findUserName(String username){
        for(User user: this.listUsers()){
            if (user.getName().toLowerCase().equals(username.toLowerCase())){
                return user.getName();
            }
        }
        return " ";
    }


    public List<User> listUsers(){
        String sql = "SELECT * FROM user WHERE is_admin = 0";
        List<User> userList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.prepareStatement(sql).executeQuery();

            while (rs.next()){
                User user = new User();
                user.setId(rs.getInt(1));
                user.setName(rs.getString(2));
                user.setSurname(rs.getString(3));
                user.setAddress(rs.getString(4));
                user.setPostalNumber(rs.getInt(5));
                user.setCity(rs.getString(6));

                Position position = new Position();
                PreparedStatement preparedStatement = connection.prepareStatement("select * from position where id = ?");;
                preparedStatement.setInt(1, rs.getInt(7));
                ResultSet rs1 = preparedStatement.executeQuery();
                position = new Position(rs1.getInt(1), rs1.getString(2));

                user.setPosition(position);
                user.setUserName(rs.getString(8));
                user.setPassword(rs.getString(9));
                user.setIsAdmin(rs.getInt(10));
                userList.add(user);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return userList;
    }



}
