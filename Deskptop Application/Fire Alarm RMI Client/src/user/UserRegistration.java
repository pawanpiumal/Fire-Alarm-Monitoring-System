package user;

import java.awt.BorderLayout;
import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.border.EmptyBorder;
import java.awt.Color;
import javax.swing.JLabel;
import java.awt.Font;
import javax.swing.JTextField;
import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;
import javax.swing.SwingConstants;

public class UserRegistration extends JFrame {

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtEmail;
	private JTextField txtPassword;
	private JTextField txtConfirmpassword;
	private JTextField txtMobile;

	/**
	 * Launch the application.
	 */
	public static void executeMain() {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					UserRegistration frame = new UserRegistration();
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
	public UserRegistration() {
		setBackground(Color.WHITE);
		setBounds(100, 100, 284, 363);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setResizable(false);

		float titileColorHSB[] = Color.RGBtoHSB(255, 69, 0, null);
		JLabel lblSignUp = new JLabel("Sign Up");
		lblSignUp.setForeground(Color.getHSBColor(titileColorHSB[0], titileColorHSB[1], titileColorHSB[2]));
		lblSignUp.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblSignUp.setBounds(87, 8, 105, 34);
		contentPane.add(lblSignUp);

		JLabel lblUserName = new JLabel("User Name");
		lblUserName.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblUserName.setForeground(Color.WHITE);
		lblUserName.setBounds(10, 56, 79, 14);
		contentPane.add(lblUserName);

		JLabel lblEmail = new JLabel("Email");
		lblEmail.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblEmail.setForeground(Color.WHITE);
		lblEmail.setBounds(10, 99, 48, 14);
		contentPane.add(lblEmail);

		JLabel lblPassword = new JLabel("Password");
		lblPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblPassword.setForeground(Color.WHITE);
		lblPassword.setBounds(10, 142, 79, 14);
		contentPane.add(lblPassword);

		JLabel lblConfirmPassword = new JLabel("Confirm Password");
		lblConfirmPassword.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblConfirmPassword.setForeground(Color.WHITE);
		lblConfirmPassword.setBounds(10, 186, 111, 14);
		contentPane.add(lblConfirmPassword);

		JLabel lblMobileNumber = new JLabel("Mobile Number");
		lblMobileNumber.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblMobileNumber.setForeground(Color.WHITE);
		lblMobileNumber.setBounds(10, 227, 111, 14);
		contentPane.add(lblMobileNumber);

		txtUsername = new JTextField();
		txtUsername.setBounds(116, 53, 142, 20);
		contentPane.add(txtUsername);
		txtUsername.setColumns(10);

		txtEmail = new JTextField();
		txtEmail.setBounds(116, 96, 142, 20);
		contentPane.add(txtEmail);
		txtEmail.setColumns(10);

		txtPassword = new JTextField();
		txtPassword.setBounds(116, 139, 142, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		txtConfirmpassword = new JTextField();
		txtConfirmpassword.setBounds(116, 183, 142, 20);
		contentPane.add(txtConfirmpassword);
		txtConfirmpassword.setColumns(10);

		txtMobile = new JTextField();
		txtMobile.setBounds(116, 224, 142, 20);
		contentPane.add(txtMobile);
		txtMobile.setColumns(10);

		JButton btnSignup = new JButton("SignUp");
		btnSignup.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {

				dispose();
			}
		});
		btnSignup.setBounds(10, 290, 248, 23);
		contentPane.add(btnSignup);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setBounds(10, 265, 248, 14);
		contentPane.add(lblError);
		setTitle("Sign Up");
		setAlwaysOnTop(true);

	}
}
