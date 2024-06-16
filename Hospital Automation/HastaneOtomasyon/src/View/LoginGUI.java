package View;

import java.awt.EventQueue;
import Helper.*;
import Model.Bashekim;
import Model.Doctor;
import Model.Hasta;

import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import java.awt.Color;
import javax.swing.JTabbedPane;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.awt.event.ActionEvent;
import javax.swing.JPasswordField;

public class LoginGUI extends JFrame {

	private static final long serialVersionUID = 1L;
	private JPanel w_pane;
	private JTextField fld_hastaTc;
	private JTextField fld_doctorTc;
	private JPasswordField fld_doctorPass;
	private JPasswordField fld_hastaPass;
	private DBConnection conn = new DBConnection();

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					LoginGUI frame = new LoginGUI();
					frame.setVisible(true);
				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the frame.
	 */
	public LoginGUI() {
		setResizable(false);
		setTitle("Hastane Yönetim Sistemi");
		setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		setBounds(100, 100, 501, 404);
		w_pane = new JPanel();
		w_pane.setBackground(new Color(255, 255, 255));
		w_pane.setBorder(new EmptyBorder(5, 5, 5, 5));

		setContentPane(w_pane);
		w_pane.setLayout(null);

		JLabel lbl_logo = new JLabel(new ImageIcon(getClass().getResource("logo.png")));
		lbl_logo.setBounds(193, 0, 92, 100);
		w_pane.add(lbl_logo);

		JLabel lblNewLabel = new JLabel("Hastane Yönetim Sistemine Hoşgeldiniz");
		lblNewLabel.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblNewLabel.setBounds(85, 95, 308, 48);
		w_pane.add(lblNewLabel);

		JTabbedPane w_tabpane = new JTabbedPane(JTabbedPane.TOP);
		w_tabpane.setBounds(0, 137, 487, 230);
		w_pane.add(w_tabpane);

		JPanel w_hastaLogin = new JPanel();
		w_hastaLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Hasta Giriş", null, w_hastaLogin, null);
		w_hastaLogin.setLayout(null);

		JLabel lblTcNo = new JLabel("T.C. Numaranız :");
		lblTcNo.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNo.setBounds(69, 20, 133, 48);
		w_hastaLogin.add(lblTcNo);

		JLabel lblifre = new JLabel("Şifre : ");
		lblifre.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifre.setBounds(69, 78, 133, 48);
		w_hastaLogin.add(lblifre);

		JButton btn_register = new JButton("Kayıt Ol");
		btn_register.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				RegisterGUI rGUI = new RegisterGUI();
				rGUI.setVisible(true);
				dispose();
				
				
			}
		});
		btn_register.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_register.setBounds(69, 147, 115, 27);
		w_hastaLogin.add(btn_register);

		JButton btn_hastaLogin = new JButton("Giriş ");
		btn_hastaLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				
				if(fld_hastaTc.getText().length()==0 || fld_hastaPass.getText().length() == 0) {
					Helper.showMsg("fill");
				}else {
					boolean key = true;
					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						
						while (rs.next()) {
							if (fld_hastaTc.getText().equals(rs.getString("tcno"))
									&& fld_hastaPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("Hasta")) {
									Hasta hasta = new Hasta();
									hasta.setId(rs.getInt("id"));
									hasta.setPassword(rs.getString("password"));
									hasta.setTcno(rs.getString("tcno"));
									hasta.setName(rs.getString("name"));
									hasta.setType(rs.getString("type"));
									HastaGUI hGUI = new HastaGUI(hasta);
									hGUI.setVisible(true);
									dispose();
									key = false;
								}

								

							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					
					
					
				}
					
					if(key) {
						Helper.showMsg("Böyle Bir Kullanıcı Bulunamadı Lütfen Kayıt Olunuz");
					}
				
				
				
				
				
				
				
				
				
				
				}
			}
		});
		btn_hastaLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_hastaLogin.setBounds(273, 147, 115, 27);
		w_hastaLogin.add(btn_hastaLogin);

		fld_hastaTc = new JTextField();
		fld_hastaTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hastaTc.setColumns(10);
		fld_hastaTc.setBounds(239, 33, 149, 21);
		w_hastaLogin.add(fld_hastaTc);

		fld_hastaPass = new JPasswordField();
		fld_hastaPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_hastaPass.setBounds(239, 96, 149, 21);
		w_hastaLogin.add(fld_hastaPass);

		JPanel w_doctorLogin = new JPanel();
		w_doctorLogin.setBackground(new Color(255, 255, 255));
		w_tabpane.addTab("Doktor Giriş", null, w_doctorLogin, null);
		w_doctorLogin.setLayout(null);

		JLabel lblTcNo_1 = new JLabel("T.C. Numaranız :");
		lblTcNo_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblTcNo_1.setBounds(82, 23, 133, 48);
		w_doctorLogin.add(lblTcNo_1);

		fld_doctorTc = new JTextField();
		fld_doctorTc.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_doctorTc.setColumns(10);
		fld_doctorTc.setBounds(252, 36, 149, 21);
		w_doctorLogin.add(fld_doctorTc);

		JLabel lblifre_1 = new JLabel("Şifre : ");
		lblifre_1.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 17));
		lblifre_1.setBounds(82, 81, 133, 48);
		w_doctorLogin.add(lblifre_1);

		JButton btn_doctorLogin = new JButton("Giriş ");
		btn_doctorLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (fld_doctorTc.getText().length() == 0 || fld_doctorPass.getText().length() == 0) {
					Helper.showMsg("fill");
				} else {

					try {
						Connection con = conn.connDb();
						Statement st = con.createStatement();
						ResultSet rs = st.executeQuery("SELECT * FROM user");
						while (rs.next()) {
							if (fld_doctorTc.getText().equals(rs.getString("tcno"))
									&& fld_doctorPass.getText().equals(rs.getString("password"))) {
								if (rs.getString("type").equals("BasHekim")) {
									Bashekim bhekim = new Bashekim();
									bhekim.setId(rs.getInt("id"));
									bhekim.setPassword(rs.getString("password"));
									bhekim.setTcno(rs.getString("tcno"));
									bhekim.setName(rs.getString("name"));
									bhekim.setType(rs.getString("type"));
									BashekimGUI bGUI = new BashekimGUI(bhekim);
									bGUI.setVisible(true);
									dispose();
								}

								if (rs.getString("type").equals("Doktor")) {
									Doctor doctor = new Doctor();
									doctor.setId(rs.getInt("id"));
									doctor.setPassword(rs.getString("password"));
									doctor.setTcno(rs.getString("tcno"));
									doctor.setName(rs.getString("name"));
									doctor.setType(rs.getString("type"));
									DoctorGUI dGUI = new DoctorGUI(doctor);
									dGUI.setVisible(true);
									dispose();

								}

							}
						}
					} catch (SQLException e1) {

						e1.printStackTrace();
					}
				}

			}
		});
		btn_doctorLogin.setFont(new Font("Yu Gothic UI Semibold", Font.PLAIN, 15));
		btn_doctorLogin.setBounds(82, 150, 319, 27);
		w_doctorLogin.add(btn_doctorLogin);

		fld_doctorPass = new JPasswordField();
		fld_doctorPass.setFont(new Font("Yu Gothic UI Light", Font.PLAIN, 18));
		fld_doctorPass.setBounds(252, 99, 149, 21);
		w_doctorLogin.add(fld_doctorPass);
	}
}
