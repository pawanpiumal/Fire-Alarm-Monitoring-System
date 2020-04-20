package user;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import javax.swing.border.EmptyBorder;

import desktopClientGUI.FireAlarmClientMain;
import userRMIServer.User;
import userRMIServer.UserDTO;

import javax.swing.SwingConstants;

public class UserLogin extends JFrame {

	/**
	 * This is the User Login JFrame
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtUsername;
	private JTextField txtPassword;
	private static User user;

	/**
	 * Launch the application.
	 */
	public static void executeMain(User userIn) {
		user = userIn;
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

		txtPassword = new JPasswordField();
		txtPassword.setBounds(116, 90, 142, 20);
		contentPane.add(txtPassword);
		txtPassword.setColumns(10);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setBounds(10, 121, 248, 14);
		contentPane.add(lblError);

		//LogIn a user if the user name and password are valid
		//or else show an error
		JButton btnLogin = new JButton("Login");
		btnLogin.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (!txtUsername.getText().equals("")) {
					if (!txtPassword.getText().equals("")) {
						UserDTO usdto = new UserDTO();
						usdto.setUserName(txtUsername.getText());
						usdto.setPassword(txtPassword.getText());
						try {
							UserDTO loggedInDTO = user.login(usdto);
							if (loggedInDTO.getSuccess()) {
								lblError.setText("Logged In Successfully");
								FireAlarmClientMain.registerLogin(loggedInDTO);
								dispose();
							} else {
								if (loggedInDTO.getMsg() != null) {
									lblError.setText("Error - " + loggedInDTO.getMsg());
								} else {
									lblError.setText("Error");
								}
							}
						} catch (RemoteException e1) {
							lblError.setText("Error Login");
							e1.printStackTrace();
						} catch (IOException e1) {
							lblError.setText("Error Login");
							e1.printStackTrace();
						} catch (Exception e1) {
							lblError.setText("Error Login");
							e1.printStackTrace();
						} 

					} else {
						lblError.setText("Password is invalid");
					}
				} else {
					lblError.setText("Username is invalid");
				}
			}
		});
		btnLogin.setBounds(10, 146, 248, 23);
		contentPane.add(btnLogin);

	}

}
