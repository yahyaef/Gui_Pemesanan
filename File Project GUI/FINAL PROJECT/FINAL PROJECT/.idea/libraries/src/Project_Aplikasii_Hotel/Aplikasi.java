package Project_Aplikasii_Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Locale;

public class Aplikasi extends JFrame {
    private JPanel panel;
    private JButton pemesanButton;
    private JButton KamarButton;
    private JButton keluarButton;
    private JButton transaksiButton;
    private JButton rincianButton;
    private JLabel waktu;
    private JLabel tgl;

    public Aplikasi() {
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        t_jam();
        tampil_tgl();
    }
    public void t_jam() {
        ActionListener taskPerformer = new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent evt) {
                String nol_jam = "", nol_menit = "", nol_detik = "";

                java.util.Date dateTime = new java.util.Date();
                int nilai_jam = dateTime.getHours();
                int nilai_menit = dateTime.getMinutes();
                int nilai_detik = dateTime.getSeconds();

                if (nilai_jam <= 9) nol_jam = "0";
                if (nilai_menit <= 9) nol_menit = "0";
                if (nilai_detik <= 9) nol_detik = "0";

                String jam = nol_jam + Integer.toString(nilai_jam);
                String menit = nol_menit + Integer.toString(nilai_menit);
                String detik = nol_detik + Integer.toString(nilai_detik);

                waktu.setText(jam + ":" + menit + ":" + detik + "");
            }
        };
        new Timer(1000, taskPerformer).start();
    }
    public void tampil_tgl() {
        java.util.Date tglsekarang = new java.util.Date();
        SimpleDateFormat smpdtfmt = new SimpleDateFormat("dd MMMMMMMMM yyyy", Locale.getDefault());
        String tanggal = smpdtfmt.format(tglsekarang);
        tgl.setText(tanggal);

        pemesanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Form_Pemesan().setVisible(true);
            }
        });
        KamarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Form_Kamar().setVisible(true);
            }
        });
        transaksiButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Form_Transaksi().setVisible(true);
            }
        });
        rincianButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                new Form_Rincian().setVisible(true);
            }
        });
        keluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JFrame frame = new JFrame("keluar");
                System.exit(0);
            }
        });
    }
    public static void main(String[] args) {
        JFrame jf = new JFrame("APLIKASI");
        jf.setContentPane(new Aplikasi().getRootPane());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setSize(400, 400);
        jf.setVisible(true);
    }
}
