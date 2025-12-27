package Project_Aplikasii_Hotel;

import net.sf.jasperreports.engine.JasperCompileManager;
import net.sf.jasperreports.engine.JasperFillManager;
import net.sf.jasperreports.engine.JasperPrint;
import net.sf.jasperreports.engine.JasperReport;
import net.sf.jasperreports.view.JasperViewer;

import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.HashMap;

public class Form_Rincian extends JFrame {
    private JPanel panel;
    private JTable tblData;
    private JButton keluarButton;
    private JTextField txtransaksi;
    private JButton cetakButton;
    private JButton ceriButton;
    private JButton refreshButton;
    private DefaultTableModel model;

    public Form_Rincian() {
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setResizable(false);
        pack();

        keluarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });

        //tabel
        model = new DefaultTableModel();
        tblData.setModel(model);
        model.addColumn("Kode Form_Transaksi");
        model.addColumn("NIK");
        model.addColumn("Nama");
        model.addColumn("Jenis Kelelamin");
        model.addColumn("Alamat");
        model.addColumn("Telp");
        //kamar
        model.addColumn("No Kamar");
        model.addColumn("Tipe Kamar");
        model.addColumn("Harga");
        model.addColumn("Lama Mengginap");
        model.addColumn("Total");
        model.addColumn("Tanggal Mengginap");

        //bayar
        model.addColumn("Bayar");
        model.addColumn("Kembalian");

        getData();
    }
    public void getData() {
        model.getDataVector().removeAllElements();
        model.fireTableDataChanged();
        try {
            Connection con = Koneksi.getKoneksi();
            String sql = "Select * from datapesan";
            Statement stat = con.createStatement();
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                Object[] obj = new Object[14];
                //tamu
                obj[0] = rs.getString(1);
                obj[1] = rs.getString(2);
                obj[2] = rs.getString(3);
                obj[3] = rs.getString(4);
                obj[4] = rs.getString(5);
                //kamar
                obj[5] = rs.getString(6);
                obj[6] = rs.getString(7);
                obj[7] = rs.getString(8);
                obj[8] = rs.getString(9);
                obj[9] = rs.getString(10);
                obj[10] = rs.getString(11);
                //pembayaran
                obj[11] = rs.getString(12);
                obj[12] = rs.getString(13);
                obj[13] = rs.getString(14);
                model.addRow(obj);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error : " + "Data tidak tersedia");
        }
        ceriButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String cari = txtransaksi.getText();
                model = new DefaultTableModel();
                tblData.setModel(model);
                model.addColumn("Kode Kamar");
                //tamu
                model.addColumn("NIK");
                model.addColumn("Nama");
                model.addColumn("Jenis Kelelamin");
                model.addColumn("Alamat");
                model.addColumn("Telp");
                //kamar
                model.addColumn("No Kamar");
                model.addColumn("Tipe Kamar");
                model.addColumn("Harga");
                model.addColumn("Lama Mengginap");
                model.addColumn("Total");
                model.addColumn("tglmengginap");

                model.addColumn("Bayar");
                model.addColumn("Kembalian");

                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "select * from datapesan where Kode_Transaksi = '"+cari+"'";
                    ResultSet rs = stat.executeQuery(sql);
                    while (rs.next()) {
                        Object[] obj = new Object[14];
                        //tamu
                        obj[0] = rs.getString(1);
                        obj[1] = rs.getString(2);
                        obj[2] = rs.getString(3);
                        obj[3] = rs.getString(4);
                        obj[4] = rs.getString(5);
                        //kamar
                        obj[5] = rs.getString(6);
                        obj[6] = rs.getString(7);
                        obj[7] = rs.getString(8);
                        obj[8] = rs.getString(9);
                        obj[9] = rs.getString(10);
                        obj[10] = rs.getString(11);
                        //pembayaran
                        obj[11] = rs.getString(12);
                        obj[12] = rs.getString(13);
                        obj[13] = rs.getString(14);
                        model.addRow(obj);
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data belum tersimpan");
                }
            }
        });
        refreshButton.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                super.mouseClicked(e);
                DefaultTableModel model = (DefaultTableModel) tblData.getModel();
                model.setRowCount(0);
                getData();
            }
        });
        cetakButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    String file = "src/report/datalaporanpesan.jrxml";
                    JasperPrint jp = JasperFillManager.fillReport(file, null, Koneksi.getKoneksi());
                    JasperViewer jv = new JasperViewer(jp, false);
                    jv.setVisible(true);
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
    }
    public static void main(String[] args) {
        JFrame jf = new JFrame("DAFTAR PEMESANAN KAMAR HOTEL");
        jf.setContentPane(new Form_Rincian().getRootPane());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setSize(800, 400);
        jf.setVisible(true);
    }
}
