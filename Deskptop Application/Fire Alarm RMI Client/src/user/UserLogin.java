package user;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;
import javax.swing.SwingConstants;

public class UserLogin extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;

	/**
	 * Launch the application.
	 */
	public static void executeMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserLogin frame = new UserLogin();
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
	public UserLogin() {
		setBackground(Color.WHITE);
		setBounds(100, 100, 284, 226);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Login");
		setAlwaysOnTop(true);
		setResizable(false);

		float titileColorHSB[] = Color.RGBtoHSB(255, 69, 0, null);
		JLabel lblSignUp = new JLabel("Login");
		lblSignUp.setForeground(Color.getHSBColor(titileColorHSB[0], titileColorHSB[1], titileColorHSB[2]));
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSignUp.setBounds(93, 8, 55, 34);
		contentPane.add(lblSignUp);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(10, 56, 79, 14);
		contentPane.add(lblUserName);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(10, 93, 79, 14);
		contentPane.add(lblPassword);

		txtUsername = new JTextField();
		txtUsername.setBounds(116, 53, 142, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(116, 90, 142, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();

			}
		});
		btnLogin.setBounds(10, 146, 248, 23);
		contentPane.add(btnLogin);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setBounds(10, 121, 248, 14);
		contentPane.add(lblError);

	}

}
