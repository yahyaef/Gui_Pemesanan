package Project_Aplikasii_Hotel;

import javax.swing.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

public class Form_Transaksi extends JFrame {
    private JComboBox<String> combonik;
    private JButton simpanButton;
    private JButton kembaliButton;
    private JTextField txbayar;
    private JButton bayarButton;
    private JLabel txketerangan;
    private JPanel panel;
    private JLabel txnama;
    private JLabel txjkelamin;
    private JLabel txalamat;
    private JLabel txtelp;
    private JLabel txtpkamar;
    private JLabel txharga;
    private JLabel txlnginap;
    private JLabel txtotal;
    private JComboBox<String> combonokamar;
    private JLabel txtgldigunakan;
    private JTextField txkembalian;
    private JTextField txkdtrans;
    private JButton clearAllButton;

    public Form_Transaksi() {
        setContentPane(panel);
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        this.setResizable(false);
        this.pack();
        cmbnik();
        cmbnokamar();

    }
    public void cmbnik() {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement stat = con.createStatement();
            String sql = "Select * from identitas";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String data = rs.getString("Nik");
                combonik.addItem(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error : " + "Data tidak ada");
        }

        combonik.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "Select * from identitas where Nik = '" + combonik.getSelectedItem() + "'";
                    ResultSet rs = stat.executeQuery(sql);
                    while (rs.next()) {
                        //identitas
                        txnama.setText(rs.getString(2));
                        txjkelamin.setText(rs.getString(3));
                        txalamat.setText(rs.getString(4));
                        txtelp.setText(rs.getString(5));

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data belum tersimpan");
                }
            }
        });
    }
    public void cmbnokamar() {
        try {
            Connection con = Koneksi.getKoneksi();
            Statement stat = con.createStatement();
            String sql = "Select * from kamar";
            ResultSet rs = stat.executeQuery(sql);
            while (rs.next()) {
                String data = rs.getString("NoKamar");
                combonokamar.addItem(data);
            }
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Error : " + "Data tidak ada");
        }

        combonokamar.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = "Select * from kamar where NoKamar = '" + combonokamar.getSelectedItem() + "'";
                    ResultSet rs = stat.executeQuery(sql);
                    while (rs.next()) {
                        //kamar
                        txtpkamar.setText(rs.getString(2));
                        txharga.setText(rs.getString(3));
                        txlnginap.setText(rs.getString(4));
                        txtotal.setText(rs.getString(5));
                        txtgldigunakan.setText(rs.getString(6));

                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, "Error : " + "Data belum tersimpan");
                }
            }
        });
        bayarButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int jumlahbayar = Integer.parseInt(txbayar.getText());
                int total = Integer.parseInt(txtotal.getText());
                if (jumlahbayar>=total) {
                    txkembalian.setText(Integer.toString(jumlahbayar - total));
                } else {
                    txketerangan.setText("Maaf Pembayaran Uang Anda Kurang");
                }
            }
        });
        simpanButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String kdt = txkdtrans.getText();
                //data tamu
                String nik = (String) combonik.getSelectedItem();
                String nama = txnama.getText();
                String jkelamin = txjkelamin.getText();
                String alamat = txalamat.getText();
                String telp = txtelp.getText();

                //data kamar
                String nokamar = (String) combonokamar.getSelectedItem();
                String tpkamar = txtpkamar.getText();
                String harga = txharga.getText();
                String lnginap = txlnginap.getText();
                String total = txtotal.getText();
                String tglpgn = txtgldigunakan.getText();

                //transaksi
                String bayar = txbayar.getText();
                String kembalian = txkembalian.getText();

                try {
                    Connection con = Koneksi.getKoneksi();
                    Statement stat = con.createStatement();
                    String sql = ("Insert into datapesan values ('"+kdt+"', '"+nik+"', '"+nama+"'," +
                            " '"+jkelamin+"', '"+alamat+"', '"+telp+"'," +
                            " '"+nokamar+"', '"+tpkamar+"', "+harga+"," +
                            " '"+lnginap+"', "+total+", '"+tglpgn+"'," +
                            " "+bayar+", "+kembalian+");");
                    stat.executeUpdate(sql);
                    stat.close();
                    con.close();
                    JOptionPane.showMessageDialog(null, "Anda berhasil Form_Transaksi");
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(null, ex.getMessage());
                }
            }
        });
        clearAllButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                txkdtrans.setText(" ");
                //data pemesan
                txnama.setText(" ");
                txjkelamin.setText(" ");
                txalamat.setText(" ");
                txtelp.setText(" ");
                //data kamar
                txtpkamar.setText(" ");
                txharga.setText(" ");
                txlnginap.setText(" ");
                txtotal.setText(" ");
                txtgldigunakan.setText(" ");
                //transaksi
                txbayar.setText(" ");
                txkembalian.setText(" ");
            }
        });
        kembaliButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
    }
    public static void main(String[] args) {
        JFrame jf = new JFrame("Data Transaksi");
        jf.setContentPane(new Form_Transaksi().getRootPane());
        jf.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        jf.setLocationRelativeTo(null);
        jf.setSize(700, 400);;
        jf.setVisible(true);
    }
}
