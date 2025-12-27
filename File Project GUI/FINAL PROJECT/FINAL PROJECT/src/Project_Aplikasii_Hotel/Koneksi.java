package Project_Aplikasii_Hotel;

import javax.swing.*;
import java.sql.Connection;
import java.sql.DriverManager;

public class Koneksi {

    public static Connection getKoneksi() {
        Connection con = null;
        try {
            Class.forName("com.mysql.cj.jdbc.Driver");
            con = DriverManager.getConnection("jdbc:mysql://localhost/db_hotel", "root", "");
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "Koneksi GAGAL \n" + e);
        }
        return con;
    }
}
