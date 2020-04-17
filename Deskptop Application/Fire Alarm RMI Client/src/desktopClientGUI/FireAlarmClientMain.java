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

import fireAlarmRMIServer.FireAlarmDTO;
import fireAlarmRMIServer.FireAlarmSensor;
import user.UserLogin;
import user.UserRegistration;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.io.IOException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.util.Vector;

import javax.swing.JButton;
import java.awt.event.ActionListener;
import java.awt.event.ActionEvent;

public class FireAlarmClientMain {

	private JFrame frame;
	private JTable table_1;
	private JScrollPane scrollPane;
	private static FireAlarmSensor sensor;
	private JLabel lblNextUpdateIn;
	private DefaultTableModel tdm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FireAlarmClientMain window = new FireAlarmClientMain();
					window.frame.setVisible(true);
					window.frame.setResizable(false);

					int PORT = 3000;
					String fireAlarmRegistration = "//localhost:" + PORT + "/FireAlarm";

					Remote remoteService = Naming.lookup(fireAlarmRegistration);
					sensor = (FireAlarmSensor) remoteService;

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FireAlarmClientMain() {
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

		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
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

		JLabel lblLogin = new JLabel("Login");

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
				UserLogin.executeMain();
			}
		});

		JLabel lblRegister = new JLabel("Register");

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
				UserRegistration.executeMain();
			}
		});

		Canvas canvas = new Canvas();
		canvas.setBounds(0, 0, 1366, 70);
		canvas.setBackground(Color.BLACK);
		frame.getContentPane().add(canvas);

		scrollPane = new JScrollPane();
		scrollPane.setBounds(20, 86, 1330, 561);

		frame.getContentPane().add(scrollPane);

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

		JButton btnAddNewFire = new JButton("Add New Fire Alarm");
		btnAddNewFire.setEnabled(false);
		btnAddNewFire.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnAddNewFire.setBounds(993, 658, 163, 23);
		frame.getContentPane().add(btnAddNewFire);

		JButton btnEditFireAlarm = new JButton("Edit Fire Alarm");
		btnEditFireAlarm.setEnabled(false);
		btnEditFireAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
			}
		});
		btnEditFireAlarm.setBounds(1177, 658, 163, 23);
		frame.getContentPane().add(btnEditFireAlarm);

	}

	private int time = 0;

	public void refreshData() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				for (;;) {
					try {
						Thread.sleep(1000);
						lblNextUpdateIn.setText("Next Update In : " + time);
						
						if (time <= 0) {
							try {
								tdm.setRowCount(0);
								for(FireAlarmDTO fdto: sensor.getFireSensorDetails()) {
									Object[] obj = {fdto.getId(),fdto.getStatus(),fdto.getRoomNo(),fdto.getFloorNo(),fdto.getSmokeLevel(),fdto.getCo2Level(),fdto.getLastUpdated()};
									tdm.addRow(obj);
								}
							} catch (RemoteException e1) {
								JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							} catch (IOException e1) {
								JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error", JOptionPane.ERROR_MESSAGE);
								e1.printStackTrace();
							}finally {
								time=30;
							}
						}
						time--;
					} catch (InterruptedException e) {

						e.printStackTrace();
					}
				}
			}
		});

		t.start();
	}
}
