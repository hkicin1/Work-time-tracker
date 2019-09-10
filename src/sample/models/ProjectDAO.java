package sample.models;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ProjectDAO {
    private static ProjectDAO inst;
    private Connection connection;

    public static ProjectDAO getInst() {
        if (inst == null) {
            initialize();
        }
        return inst;
    }

    private static void initialize() {
        inst = new ProjectDAO();
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
    PreparedStatement getIdProject,deleteProject;

    public ProjectDAO(){
        try {
            this.connection = DriverManager.getConnection("jdbc:sqlite:work_time_tracker.db");
            getIdProject =connection.prepareStatement("SELECT MAX(id)+1 FROM project");
            deleteProject = connection.prepareStatement("DELETE FROM project where id = ?");
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public boolean addProject(Project project){
        String sql = "insert into project values(?,?,?)";

        try {
            PreparedStatement preparedStatement = this.connection.prepareStatement(sql);

            ResultSet rs = getIdProject.executeQuery();
            int id = 1;
            if (rs.next()) {
                id = rs.getInt(1);
            }
            preparedStatement.setInt(1,id);
            preparedStatement.setString(2, project.getName());
            preparedStatement.setInt(3, project.getActivity());
            preparedStatement.executeUpdate();
            preparedStatement.close();
            return true;
        } catch (SQLException e) {
            e.printStackTrace();
            return false;
        }

    }
    public void removeProject(Project project){
        try {
            if (project != null) {
                deleteProject.setInt(1, project.getId());
                deleteProject.executeUpdate();
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    }


    public List<Project> listProjects(){
        String sql = "SELECT * FROM project";
        List<Project> projectList = new ArrayList<>();
        try {
            ResultSet rs = this.connection.prepareStatement(sql).executeQuery();
            while (rs.next()){
                Project project = new Project();
                project.setId(rs.getInt(1));
                project.setName(rs.getString(2));
                project.setActivity(rs.getInt(3));
                projectList.add(project);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return projectList;
    }
}

