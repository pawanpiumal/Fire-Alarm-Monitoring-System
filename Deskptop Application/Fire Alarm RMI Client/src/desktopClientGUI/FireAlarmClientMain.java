package desktopClientGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import java.awt.Canvas;
import java.awt.Color;
import javax.swing.JLabel;
import javax.swing.JOptionPane;

import java.awt.Font;
import javax.swing.JScrollPane;
import javax.swing.JTable;
import javax.swing.ListSelectionModel;
import javax.swing.table.DefaultTableModel;

import fireAlarm.AddFireAlarm;
import fireAlarm.EditFireAlarm;
import fireAlarm.FireAlarmDesktop;
import fireAlarmRMIServer.FireAlarmDTO;
import fireAlarmRMIServer.FireAlarmSensor;
import user.UserLogin;
import user.UserRegistration;
import userRMIServer.User;
import userRMIServer.UserDTO;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FireAlarmClientMain extends UnicastRemoteObject implements FireAlarmDesktop {

	/**
	 * This is the Fire Alarm Desktop Clients Main GUI This includes the function to
	 * view fire alarm sensor details and Login and registering as an administrator.
	 * 
	 */
	private static final long serialVersionUID = 1L;

	private JFrame frame;
	private JTable table_1;
	private JScrollPane scrollPane;
	private static FireAlarmSensor fireAlarm;
	private static User user;
	private JLabel lblNextUpdateIn;
	private DefaultTableModel tdm;
	private static JButton btnAddNewFire;
	private static JButton btnEditFireAlarm;
	private static JLabel lblLogin;
	private static JLabel lblRegister;
	private static JButton btnDeleteFireAlarm;

	private static UserDTO UserDTO;

	private static FireAlarmClientMain clientMain;

	

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					clientMain = new FireAlarmClientMain();
					clientMain.frame.setVisible(true);

					// Getting the Remote Objects from the RMI Registry
					int PORT = 3000;
					String fireAlarmRegistration = "//localhost:" + PORT + "/FireAlarm";
					String userRegistration = "//localhost:" + PORT + "/User";

					Remote fireAlarmService = Naming.lookup(fireAlarmRegistration);
					fireAlarm = (FireAlarmSensor) fireAlarmService;

					Remote userService = Naming.lookup(userRegistration);
					user = (User) userService;

					// Registering the Client Application in the RMI Server in order to get
					// alerts when a fire alarm sensor exceeds the warning limit
					fireAlarm.registerFireAlarmDesktopClient(clientMain);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FireAlarmClientMain() throws RemoteException {
		initialize();
		refreshData();
	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {

		frame = new JFrame();
		float backgroudnColorHSB[] = Color.RGBtoHSB(51, 51, 51, null);
		frame.getContentPane()
				.setBackground(Color.getHSBColor(backgroudnColorHSB[0], backgroudnColorHSB[1], backgroudnColorHSB[2]));

		frame.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
		frame.setTitle("Fire Alarm System");
		frame.setBounds(0, 0, 1366, 744);
		frame.setExtendedState(JFrame.MAXIMIZED_BOTH);

		JLabel lblTitle = new JLabel("Fire Alarm System");
		lblTitle.setBounds(30, 0, 421, 70);
		lblTitle.setBackground(Color.BLACK);
		lblTitle.setFont(new Font("Tahoma", Font.BOLD, 37));
		float titileColorHSB[] = Color.RGBtoHSB(255, 69, 0, null);
		frame.getContentPane().setLayout(null);
		lblTitle.setOpaque(true);
		lblTitle.setForeground(Color.getHSBColor(titileColorHSB[0], titileColorHSB[1], titileColorHSB[2]));
		frame.getContentPane().add(lblTitle);

		lblLogin = new JLabel("Login");

		lblLogin.setOpaque(true);
		float loginRegeisterColorHSB[] = Color.RGBtoHSB(255, 215, 0, null);
		float loginRegeisterMouseFloatColorHSB[] = Color.RGBtoHSB(13, 58, 179, null);
		lblLogin.setForeground(
				Color.getHSBColor(loginRegeisterColorHSB[0], loginRegeisterColorHSB[1], loginRegeisterColorHSB[2]));
		lblLogin.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblLogin.setBackground(Color.BLACK);
		lblLogin.setBounds(1166, 11, 46, 52);
		frame.getContentPane().add(lblLogin);

		lblLogin.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				// Changing the Color On mouse hover
				lblLogin.setForeground(Color.getHSBColor(loginRegeisterMouseFloatColorHSB[0],
						loginRegeisterMouseFloatColorHSB[1], loginRegeisterMouseFloatColorHSB[2]));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblLogin.setForeground(Color.getHSBColor(loginRegeisterColorHSB[0], loginRegeisterColorHSB[1],
						loginRegeisterColorHSB[2]));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				UserLogin.executeMain(user);
			}
		});

		lblRegister = new JLabel("Register");

		lblRegister.setOpaque(true);
		lblRegister.setForeground(
				Color.getHSBColor(loginRegeisterColorHSB[0], loginRegeisterColorHSB[1], loginRegeisterColorHSB[2]));
		lblRegister.setFont(new Font("Tahoma", Font.BOLD, 14));
		lblRegister.setBackground(Color.BLACK);
		lblRegister.setBounds(1254, 11, 65, 52);
		frame.getContentPane().add(lblRegister);
		lblRegister.addMouseListener(new MouseAdapter() {
			@Override
			public void mouseEntered(MouseEvent e) {
				lblRegister.setForeground(Color.getHSBColor(loginRegeisterMouseFloatColorHSB[0],
						loginRegeisterMouseFloatColorHSB[1], loginRegeisterMouseFloatColorHSB[2]));
			}

			@Override
			public void mouseExited(MouseEvent e) {
				lblRegister.setForeground(Color.getHSBColor(loginRegeisterColorHSB[0], loginRegeisterColorHSB[1],
						loginRegeisterColorHSB[2]));
			}

			@Override
			public void mouseClicked(MouseEvent e) {
				UserRegistration.executeMain(user);
			}
		});

		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, 1366, 70);
		canvas.setBackground(Color.BLACK);
		frame.getContentPane().add(canvas);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 86, 1330, 561);

		frame.getContentPane().add(scrollPane);

		// Setting Table Headers
		String[] headders = { "ID", "Status", "Room No", "Floor No", "Smoke Level", "CO2 Level", "Last Updated" };
		tdm = new DefaultTableModel(headders, 0);
		table_1 = new JTable(tdm);
		table_1.setForeground(Color.BLACK);

		table_1.setDefaultEditor(Object.class, null);
		table_1.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
		scrollPane.setViewportView(table_1);

		lblNextUpdateIn = new JLabel("Next Update In : ");
		lblNextUpdateIn.setFont(new Font("Tahoma", Font.BOLD, 16));
		lblNextUpdateIn.setForeground(Color.WHITE);
		lblNextUpdateIn.setBounds(20, 658, 208, 24);
		frame.getContentPane().add(lblNextUpdateIn);

		btnAddNewFire = new JButton("Add New Fire Alarm");
		btnAddNewFire.setEnabled(false);
		// Opening the Add Fire Alarm Frame
		btnAddNewFire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				AddFireAlarm.executeMain(fireAlarm, UserDTO);
			}
		});
		btnAddNewFire.setBounds(831, 658, 163, 23);
		frame.getContentPane().add(btnAddNewFire);

		btnEditFireAlarm = new JButton("Edit Fire Alarm");
		btnEditFireAlarm.setEnabled(false);
		// Opening the Edit Fire Alarm Frame
		btnEditFireAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow() != -1) {
					int row = table_1.getSelectedRow();
					EditFireAlarm.executeMain(fireAlarm, UserDTO, (String) table_1.getValueAt(row, 0),
							(Integer.valueOf((String) table_1.getValueAt(row, 2))),
							(Integer.valueOf((String) table_1.getValueAt(row, 3))));
				} else {
					JOptionPane.showMessageDialog(frame, "Select a Row to Edit.", "Error", JOptionPane.ERROR_MESSAGE);

				}

			}
		});
		btnEditFireAlarm.setBounds(1004, 658, 163, 23);
		frame.getContentPane().add(btnEditFireAlarm);
		// Deleting a Fire Alarm
		btnDeleteFireAlarm = new JButton("Delete Fire Alarm");
		btnDeleteFireAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (table_1.getSelectedRow() != -1) {
					int row = table_1.getSelectedRow();
					try {
						
						if (JOptionPane.showConfirmDialog(frame, "Do you want to delete this Fire Alarm Sensor ?",
								"Confirm", JOptionPane.WARNING_MESSAGE) == JOptionPane.YES_OPTION) {
							FireAlarmDTO deleteDTO = fireAlarm.deleteFireAlarmDetails((String) table_1.getValueAt(row, 0),
									UserDTO.getToken());
							if (deleteDTO.isSuccess()) {
								JOptionPane.showMessageDialog(frame, deleteDTO.getMsg(), "Deleted",
										JOptionPane.ERROR_MESSAGE);
								setTime(0);
							} else {
								if (deleteDTO.getMsg() != null) {
									JOptionPane.showMessageDialog(frame, "Error Delete. Error - " + deleteDTO.getMsg(),
											"Error", JOptionPane.ERROR_MESSAGE);
								} else {
									JOptionPane.showMessageDialog(frame, "Error Delete", "Error",
											JOptionPane.ERROR_MESSAGE);
								}
							}
						}
					} catch (RemoteException e1) {
						JOptionPane.showMessageDialog(frame, "Error Delete", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame, "Error Delete", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					}
				} else {
					JOptionPane.showMessageDialog(frame, "Select a Row to Delete.", "Error", JOptionPane.ERROR_MESSAGE);

				}
			}
		});
		btnDeleteFireAlarm.setEnabled(false);
		btnDeleteFireAlarm.setBounds(1177, 658, 163, 23);
		frame.getContentPane().add(btnDeleteFireAlarm);

		// Unregistering the Client from the RMI Server on Window Close
		frame.addWindowListener(new java.awt.event.WindowAdapter() {

			@Override
			public void windowClosing(java.awt.event.WindowEvent windowEvent) {
				int val = JOptionPane.showConfirmDialog(frame, "Are you sure you want to close this window?",
						"Close Window?", JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
				if (val == JOptionPane.YES_OPTION) {
					try {
						fireAlarm.unregisterFireAlarmDesktopClient(clientMain);
					} catch (RemoteException e) {
						e.printStackTrace();
					} catch (IOException e) {
						e.printStackTrace();
					} finally {
						System.exit(0);
					}
				}
			}
		});

	}

	// Setting the Timer to refresh data in intervals
	private static int timetoRefresh = 0;

	/// This static method is used to reset the timer to refresh the Data in the
	/// client
	// This is used when fire alarms are added or edited
	public static void setTime(int timeIn) {
		timetoRefresh = timeIn;
	}

	// Refreshing the data in the table
	// A separate thread is used to run the timer and refresh the data when the
	// timer reaches 0
	public void refreshData() {

		Thread thre = new Thread(new Runnable() {

			@Override
			public void run() {

				for (;;) {
					try {
						Thread.sleep(1000);
						lblNextUpdateIn.setText("Next Update In : " + timetoRefresh);
						timetoRefresh -= 1;
						if (timetoRefresh <= 0) {
							try {
								// Emptying the Table data
								tdm.setRowCount(0);
								// Adding the new Data to the Table
								for (FireAlarmDTO fdto : fireAlarm.getFireSensorDetails()) {
									Object[] obj = { fdto.getId(), fdto.getStatus(), fdto.getRoomNo(),
											fdto.getFloorNo(), fdto.getSmokeLevel(), fdto.getCo2Level(),
											fdto.getLastUpdated() };
									tdm.addRow(obj);
								}
							} catch (RemoteException e1) {
								JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error",
										JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error",
										JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							} finally {
								timetoRefresh = 30;
							}
						}
					} catch (InterruptedException e) {
						e.printStackTrace();
					}
				}
			}
		});

		thre.start();
	}

	// Removing the login and register labels once a user is logged in
	// This method also set the user object which contains the administrator token
	// This methods enables the button to add, Edit and delete Fire alarm details
	public static void registerLogin(UserDTO user) {
		UserDTO = user;
		btnAddNewFire.setEnabled(true);
		btnEditFireAlarm.setEnabled(true);
		lblLogin.setVisible(false);
		lblRegister.setVisible(false);
		btnDeleteFireAlarm.setEnabled(true);

	}

	// This is method used to display alerts once RMI Server sends info that a fire
	// alarm sensor has
	// exceeded CO2 or Smoke level
	// It also sets the refresh timer to 0 which makes the table reload
	@Override
	public void showAlert(FireAlarmDTO fireDto) throws RemoteException {
		JOptionPane.showMessageDialog(frame, "Room No " + fireDto.getRoomNo() + ", Floor No " + fireDto.getFloorNo()
				+ " has exceeded CO2 or Smoke Level Limit", "Alert", JOptionPane.WARNING_MESSAGE);
		setTime(0);

	}
}
