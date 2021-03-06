package sample.utilities;

import net.sf.jasperreports.engine.*;
import net.sf.jasperreports.swing.JRViewer;
import sample.enums.ReportType;

import javax.swing.*;
import java.sql.Connection;
import java.util.ArrayList;
import java.util.HashMap;

public class PrintReport extends JFrame {
    private final String resultsByWorkHours = "/reports/workHoursReport.jrxml";
    private final String resultsByProjectWorkHours = "/reports/projectWorkHoursReport.jrxml";
    private final String resultsByMyWorkHours = "/reports/myWorkHoursReport.jrxml";
    private final String resultsByMyProjectWorkHours = "/reports/myProjectWorkHoursReport.jrxml";

    public void showReport(Connection conn, ReportType reportType, Integer id) throws JRException {
        String reportSrcFile = getClass().getResource(chooseFilePath(reportType)).getFile();
        String reportsDir = getClass().getResource("/reports/").getFile();

        JasperReport jasperReport = JasperCompileManager.compileReport(reportSrcFile);
        // Fields for resources path
        HashMap<String, Object> parameters = new HashMap<String, Object>();
        if (id != null){
            parameters.put("USER_ID", id);
        } else{
            parameters.put("reportsDirPath", reportsDir);
        }
        ArrayList<HashMap<String, Object>> list = new ArrayList<HashMap<String, Object>>();
        list.add(parameters);
        JasperPrint print = JasperFillManager.fillReport(jasperReport, parameters, conn);
        JRViewer viewer = new JRViewer(print);
        viewer.setOpaque(true);
        viewer.setVisible(true);
        this.add(viewer);
        this.setSize(700, 500);
        this.setVisible(true);
    }

    private String chooseFilePath(ReportType reportType){
        if(reportType.equals(ReportType.RESULTS_BY_WORK_HOURS)){
            return resultsByWorkHours;
        }else if(reportType.equals(ReportType.RESULTS_BY_PROJECT_WORK_HOURS)){
            return resultsByProjectWorkHours;
        }else if(reportType.equals(ReportType.MY_RESULTS_BY_WORK_HOURS)){
            return resultsByMyWorkHours;
        }else if(reportType.equals(ReportType.MY_RESULTS_BY_PROJECT_WORK_HOURS)){
            return resultsByMyProjectWorkHours;
        }
        return null;
    }
}

