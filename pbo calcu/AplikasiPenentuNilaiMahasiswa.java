import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class AplikasiPenentuNilaiMahasiswa extends JFrame {

    private JTextField txtNamaLengkap;
    private JTextField txtTugasMurni;
    private JTextField txtNilaiUTS;
    private JTextField txtNilaiUAS;

    private JLabel lblNamaOutput;
    private JLabel lblRataRataOutput;
    private JLabel lblGradeOutput;
    private JLabel lblHasilOutput;

    private JButton btnHitung;
    private JButton btnBersihkan;
    private JButton btnSimpan;
    private JButton btnKeluar;

    public AplikasiPenentuNilaiMahasiswa() {
        super("Aplikasi Penentu Nilai Mahasiswa");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout(5, 5));

        // 1. PANEL UTARA
        JPanel panelUtara = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        panelUtara.setBackground(new Color(255, 192, 203)); 
        
        panelUtara.add(new JLabel("Nama Lengkap"));
        txtNamaLengkap = new JTextField(20);
        panelUtara.add(txtNamaLengkap);
        
        add(panelUtara, BorderLayout.NORTH);

        // 2. PANEL TENGAH
        JPanel panelTengah = new JPanel(new GridLayout(1, 2, 5, 5));
        
        // 2a. PANEL INPUT NILAI
        JPanel panelInput = new JPanel(new GridLayout(3, 2, 5, 10));
        panelInput.setBorder(BorderFactory.createTitledBorder("Nilai"));
        panelInput.setBackground(new Color(152, 251, 152)); 
        
        panelInput.add(new JLabel("Tugas Murni"));
        txtTugasMurni = new JTextField();
        panelInput.add(txtTugasMurni);

        panelInput.add(new JLabel("Nilai UTS"));
        txtNilaiUTS = new JTextField();
        panelInput.add(txtNilaiUTS);

        panelInput.add(new JLabel("Nilai UAS"));
        txtNilaiUAS = new JTextField();
        panelInput.add(txtNilaiUAS);
        
        // 2b. PANEL HASIL
        JPanel panelHasil = new JPanel(new GridLayout(4, 2, 5, 10));
        panelHasil.setBorder(BorderFactory.createTitledBorder("Hasil"));
        panelHasil.setBackground(new Color(255, 255, 153));

        lblNamaOutput = new JLabel("-");
        lblRataRataOutput = new JLabel("-");
        lblGradeOutput = new JLabel("-");
        lblHasilOutput = new JLabel("-");

        panelHasil.add(new JLabel("Nama"));
        panelHasil.add(lblNamaOutput);

        panelHasil.add(new JLabel("Nilai Rata-rata"));
        panelHasil.add(lblRataRataOutput);

        panelHasil.add(new JLabel("Grade"));
        panelHasil.add(lblGradeOutput);

        panelHasil.add(new JLabel("Hasil"));
        panelHasil.add(lblHasilOutput);
        
        panelTengah.add(panelInput);
        panelTengah.add(panelHasil);

        add(panelTengah, BorderLayout.CENTER);

        // 3. PANEL SELATAN
        JPanel panelSelatan = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        panelSelatan.setBackground(new Color(173, 216, 230)); 
        
        btnHitung = new JButton("Hitung");
        btnBersihkan = new JButton("Bersihkan");
        btnSimpan = new JButton("Simpan"); 
        btnKeluar = new JButton("Keluar");
        
        panelSelatan.add(btnHitung);
        panelSelatan.add(btnBersihkan);
        panelSelatan.add(btnSimpan);
        panelSelatan.add(btnKeluar);

        add(panelSelatan, BorderLayout.SOUTH);
        
        // 4. LOGIKA TOMBOL
        btnKeluar.addActionListener(e -> System.exit(0));

        btnBersihkan.addActionListener(e -> bersihkanForm());

        btnHitung.addActionListener(e -> hitungNilai());

        pack(); 
        setLocationRelativeTo(null);
        setVisible(true);
    }
    
    private void bersihkanForm() {
        txtNamaLengkap.setText("");
        txtTugasMurni.setText("");
        txtNilaiUTS.setText("");
        txtNilaiUAS.setText("");
        
        lblNamaOutput.setText("-");
        lblRataRataOutput.setText("-");
        lblGradeOutput.setText("-");
        lblHasilOutput.setText("-");
    }

    private void hitungNilai() {
        String nama = txtNamaLengkap.getText();
        lblNamaOutput.setText(nama);
        
        try {
            double tugas = Double.parseDouble(txtTugasMurni.getText());
            double uts = Double.parseDouble(txtNilaiUTS.getText());
            double uas = Double.parseDouble(txtNilaiUAS.getText());
            
            double rataRata = (tugas + uts + uas) / 3.0;
            String rataRataStr = String.format("%.2f", rataRata);
            lblRataRataOutput.setText(rataRataStr);
            
            String grade;
            String hasil;

            if (rataRata >= 80) {
                grade = "A";
                hasil = "LULUS";
            } else if (rataRata >= 70) {
                grade = "B";
                hasil = "LULUS";
            } else if (rataRata >= 60) {
                grade = "C";
                hasil = "LULUS";
            } else if (rataRata >= 50) {
                grade = "D";
                hasil = "GAGAL";
            } else {
                grade = "E";
                hasil = "GAGAL";
            }
            
            lblGradeOutput.setText(grade);
            lblHasilOutput.setText(hasil);
            
        } catch (NumberFormatException e) {
            JOptionPane.showMessageDialog(this, 
                    "Input nilai harus berupa angka!", 
                    "Kesalahan Input", 
                    JOptionPane.ERROR_MESSAGE);
            
            lblRataRataOutput.setText("-");
            lblGradeOutput.setText("-");
            lblHasilOutput.setText("-");
        }
    }

    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> new AplikasiPenentuNilaiMahasiswa());
    }
}