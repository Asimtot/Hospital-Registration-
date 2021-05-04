import Database.Database;
import GUI.Others.frmLogin;
import GUI.PatientGUI.AppointmentApproval;
import Person.*;

import javax.swing.*;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.util.Arrays;

public class Main {
    public static void main(String[] args) throws SQLException {
        JFrame frame = new frmLogin();
        frame.setLocationRelativeTo(null);
        frame.setVisible(true);
        frame.setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE);
    }
}
