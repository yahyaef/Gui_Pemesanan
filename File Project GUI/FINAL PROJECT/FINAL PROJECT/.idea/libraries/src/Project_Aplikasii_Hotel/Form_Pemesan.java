package Project_Aplikasii_Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Form_Pemesan extends JFrame {
    private JPanel panel;
    private JTextField txnik;
    private JTextField txnama;
    private JRadioButton radio1;
    private JTextField txalamat;
    private JTextField txtelp;
    private JRadioButton radio2;
    private JButton simpanButton;
    private JTextArea txarea;
    private JButton cariButton;
    private JButton hapusButton;
    private JButton keluarButton;
    private JButton editButton;
    private JButton clearAllButton;

    String pesan = " ";
    public Form_Pemesan() {
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        //Button Simpan
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nik = txnik.getText();
                String nama = txnama.getText();
                String alamat = txalamat.getText();
                String telp = txtelp.getText();

                String jkelamin;
                if (radio1.isSelected()) {
                    jkelamin = radio1.getText();
                } else {
                    jkelamin = radio2.getText();
                }
                pesan = pesan + "Jenis Kelamin : "+jkelamin+"\n";

                txarea.setText("NIK \t: "+txnik.getText()+"\n"+"Nama \t: "+txnama.getText()+"\n"
                        +"Jenis Kelamin \t: "+jkelamin+"\n"+"Alamat \t: "+ txalamat.getText()+"\n"
                        +"Telp \t: "+txtelp.getText());

                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = ("insert into identitas values ('" + nik + "'," +
                            "'" + nama + "', '" + jkelamin + "', " +
                            "'" + alamat + "', '" + telp + "');");
                    stat.executeUpdate(sql);
                    stat.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Data berhasil disimpan");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data belum tersimpan");
                }
            }
        });
        //Button Edit
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nik = txnik.getText();
                String nama = txnama.getText();
                String alamat = txalamat.getText();
                String telp = txtelp.getText();

                String jkelamin;
                if (radio1.isSelected()) {
                    jkelamin = radio1.getText();
                } else {
                    jkelamin = radio2.getText();
                }
                pesan = pesan + "Jenis Kelamin : "+jkelamin+"\n";

                txarea.setText("NIK \t: "+txnik.getText()+"\n"+"Nama \t: "+txnama.getText()+"\n"
                        +"Jenis Kelamin \t: "+jkelamin+"\n"+"Alamat \t: "+ txalamat.getText()+"\n"
                        +"Telp \t: "+txtelp.getText());

                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "update identitas set nik = '"+nik+"', nama = '"+nama+"'," +
                            " jeniskel = '"+jkelamin+"', alamat = '"+alamat+"'," +
                            " telp = '"+telp+"' where nik = '"+nik+"'";
                    stat.executeUpdate(sql);
                    stat.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Data berhasil diedit");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data gagal diedit");
                }
            }
        });
        //Button Cari
        cariButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cari = txnik.getText();
                String jkelamin;
                if (radio1.isSelected()) {
                    jkelamin = radio1.getText();
                } else {
                    jkelamin = radio2.getText();
                }
                txarea.setText("NIK \t: "+txnik.getText()+"\n"+"Nama \t: "+txnama.getText()+"\n"
                        +"Jenis Kelamin \t: "+jkelamin+"\n"+"Alamat \t: "+ txalamat.getText()+"\n"
                        +"Telp \t: "+txtelp.getText());

                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "select * from identitas where Nik = '"+cari+"'";
                    ResultSet rs = stat.executeQuery(sql);
                    while (rs.next()) {
                        txarea.setText("NIK\t: "+rs.getString(1)+
                                "\nNama\t: " + rs.getString(2)+
                                "\nJenis Kelamin\t: "+rs.getString(3)+
                                "\nAlamat\t: "+rs.getString(4)+
                                "\nTelp\t: "+rs.getString(5));

                        txnama.setText(rs.getString(2));
                        txalamat.setText(rs.getString(4));
                        txtelp.setText(rs.getString(5));
                    }
                }
                catch (Exception ex) {
                    JOptionPane.showMessageDialog(null,"Error : "+"Data tidak tersedia");
                }
            }
        });
        //Button Hapus
        hapusButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String nik = txnik.getText();
                String nama = txnama.getText();
                String alamat = txalamat.getText();
                String telp = txtelp.getText();

                String jkelamin;
                if (radio1.isSelected()) {
                    jkelamin = radio1.getText();
                } else {
                    jkelamin = radio2.getText();
                }

                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "Delete from identitas "+ "Where nik = '"+txnik.getText()+"'";
                    stat.executeUpdate(sql);
                    stat.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Data telah berhasil dihapus");

                    txarea.setText(" ");
                    txnik.setText(" ");
                    txnama.setText(" ");
                    txalamat.setText(" ");
                    txtelp.setText(" ");

                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data gagal dihapus");
                }
            }
        });
        //Button Keluar
        keluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txarea.setText(" ");
                txnik.setText(" ");
                txnama.setText(" ");
                txalamat.setText(" ");
                txtelp.setText(" ");
            }
        });
    }
    public static void main(String[] args) {
        JFrame jf = new JFrame("BIODATA PEMESAN");
        jf.setContentPane(new Form_Pemesan().getRootPane());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setSize(600, 400);
        jf.setVisible(true);
    }
}
