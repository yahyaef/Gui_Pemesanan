package Project_Aplikasii_Hotel;

import com.toedter.calendar.JDateChooser;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Objects;

public class Form_Kamar extends JFrame {
    private JTextField txlmenginap;
    private JComboBox combotipekamar;
    private JButton editButton;
    private JButton simpanButton;
    private JButton hapusButton;
    private JButton keluarButton;
    private JTextField txjumlahkamar;
    private JButton cariButton;
    private JTextField txharga;
    private JPanel panel;
    private JTextField txnoKamar;
    private JScrollPane tabeldata;
    private JTextArea txarea;
    private JTextField txtotal;
    private JButton hitungButton;
    private JPanel cal;


    //jar jcalender memanggil dari data chooser
    Calendar cl = Calendar.getInstance();
    JDateChooser dateChooser = new JDateChooser();

    int harga_kamar, total;

    public Form_Kamar() {
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        //panel kalender
        cal.add(dateChooser);
        dateChooser.setDateFormatString("yyyy-MM-dd");

        //tipe kamar
        combotipekamar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pilihan = (String) combotipekamar.getSelectedItem();
                //pemilihan tipe kamar dan menetukan harga tiap tipe kamar
                switch (pilihan) {
                    case "Deluxe Room":
                        harga_kamar = 2000000;
                        break;
                    case "Superior Room":
                        harga_kamar = 1000000;
                        break;
                    case "Standard Room":
                        harga_kamar = 500000;
                        break;
                }
                //untuk memunculkan txharga secara otomatis jika memilih tipe kamar salah satu
                txharga.setText(""+Integer.toString(harga_kamar));
            }
        });
        //Button Simpan
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nokamar = txnoKamar.getText();
                String tipekamar = (String) combotipekamar.getSelectedItem();
                String harga = txharga.getText();
                String lmenginap = txlmenginap.getText();
                String total = txtotal.getText();
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                String tglP = sdf.format(dateChooser.getDate());

                txarea.setText("No Kamar \t: "+nokamar+"\n"+"Tipe Kamar \t: "+tipekamar+"\n"
                        +"Harga \t: "+harga+"\n"+"Lama Menginap : "+lmenginap+"\n"
                        +"Total \t: "+total+"\nTanggal Menginap : "+tglP);
                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "insert into kamar values ('"+nokamar+"', '"+tipekamar+"'," +
                            "'"+harga+"', '"+lmenginap+"', '"+total+"', '"+tglP+"');";
                    stat.executeUpdate(sql);
                    stat.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : "+ex.getMessage());
                }
            }
        });
        //Button Edit
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nokamar = txnoKamar.getText();
                String tipekamar = (String) combotipekamar.getSelectedItem();
                String harga = txharga.getText();
                String lmenginap = txlmenginap.getText();
                String totalh = txtotal.getText();

                SimpleDateFormat sdf = new SimpleDateFormat("dd/MM/yyyy");
                String tglmasuk = sdf.format(dateChooser.getDate());

                txarea.setText("No Kamar \t: "+txnoKamar.getText()+"\n"+"Tipe Kamar \t: "+combotipekamar.getSelectedItem()+"\n"
                        +"Harga \t: "+txharga.getText()+"\n"+"Lama Menginap : "+ txlmenginap.getText()+"\n"
                        +"Tanggal Menginap\t: "+tglmasuk);
                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "update kamar set nokamar = '"+nokamar+"', tipekamar = '"+tipekamar+"', harga = '"+harga+"', " +
                            " lamanginap = '"+lmenginap+"', total = '"+totalh+"', tglmenginap = '"+tglmasuk+"' where nokamar = '"+nokamar+"'";
                    stat.executeUpdate(sql);
                    stat.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Data berhasil diedit");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data belum tersimpan");
                }
            }
        });
        //Button Hapus
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "Delete from kamar "+ "Where nokamar = '"+txnoKamar.getText()+"'";
                    stat.executeUpdate(sql);
                    stat.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Data berhasil dihapus");

                    txarea.setText(" ");
                    txnoKamar.setText(" ");
                    txharga.setText(" ");
                    txlmenginap.setText(" ");
                    txtotal.setText(" ");

                } catch (Exception ex) {
                    System.out.println("Error : "+ex);
                    JOptionPane.showMessageDialog(null, "Error : " + "Data belum terhapus");
                }
            }
        });
        //Button Cari
        cariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "Select * from kamar Where nokamar = '"+txnoKamar.getText()+"'";
                    ResultSet rs = stat.executeQuery(sql);
                    while (rs.next()) {
                        txarea.setText("No Kamar\t: "+rs.getString(1)+
                                "\nTipe Kamar\t: " + rs.getString(2)+
                                "\nHarga \t: "+rs.getString(3)+
                                "\nLama Menginap : "+rs.getString(4)+
                                "\nTotal\t: "+rs.getString(5)+
                                "\nTaggal Menginap : "+rs.getString(6));
                        txharga.setText(rs.getString(3));
                        txlmenginap.setText(rs.getString(4));
                        txtotal.setText(rs.getString(5));
                    }
                    stat.close();
                    con.close();

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data tidak tersedia");
                }
            }
        });
        //Button Hitung
        hitungButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String pil;
                int m = Integer.parseInt(txlmenginap.getText());
                String pilih = (String) combotipekamar.getSelectedItem();
                //menghitung salah satu harga tipe kamar kali lama mengginap
                switch (Objects.requireNonNull(pilih)) {
                    case "Deluxe Room" -> {
                        total = (2000000 * m);
                        pil = String.valueOf("Rp." + total);
                        txtotal.setText(pil);
                    }
                    case "Superior Room" -> {
                        total = (1000000 * m);
                        pil = String.valueOf("Rp." + total);
                        txtotal.setText(pil);
                    }
                    case "Standard Room" -> {
                        total = (500000 * m);
                        pil = String.valueOf("Rp." + total);
                        txtotal.setText(pil);
                    }
                }
                txtotal.setText(""+Integer.toString(total));
            }
        });
        keluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        JFrame jf = new JFrame("Data Kamar");
        jf.setContentPane(new Form_Kamar().getRootPane());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setSize(600, 400);
        jf.setVisible(true);
    }
}
