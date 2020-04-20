package fireAlarm;

import java.awt.Color;
import java.awt.EventQueue;
import java.awt.Font;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import desktopClientGUI.FireAlarmClientMain;
import fireAlarmRMIServer.FireAlarmDTO;
import fireAlarmRMIServer.FireAlarmSensor;
import userRMIServer.UserDTO;

import java.awt.event.ActionListener;
import java.io.IOException;
import java.rmi.RemoteException;
import java.awt.event.ActionEvent;

public class AddFireAlarm extends JFrame {

	/*
	 * 
	 * This JFrame is used to Add Fire Alarms=
	 * 
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRoomNo;
	private JTextField txtFloorNo;
	private static UserDTO user;
	private static FireAlarmSensor fire;

	/**
	 * Launch the application.
	 */
	public static void executeMain(FireAlarmSensor fireIn,UserDTO userIn) {
		user = userIn;
		fire = fireIn;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					AddFireAlarm frame = new AddFireAlarm();
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
	public AddFireAlarm() {
		setBackground(Color.WHITE);
		setBounds(100, 100, 284, 226);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Add Fire Alarm");
		setAlwaysOnTop(true);
		setResizable(false);
		
		float titileColorHSB[] = Color.RGBtoHSB(255, 69, 0, null);
		JLabel lblAddFireAlarm = new JLabel("Add Fire Alarm");
		lblAddFireAlarm.setForeground(Color.getHSBColor(titileColorHSB[0], titileColorHSB[1], titileColorHSB[2]));
		lblAddFireAlarm.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblAddFireAlarm.setBounds(61, 11, 151, 34);
		contentPane.add(lblAddFireAlarm);

		JLabel lblRoomNo = new JLabel("Room No");
		lblRoomNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblRoomNo.setForeground(Color.WHITE);
		lblRoomNo.setBounds(10, 56, 79, 14);
		contentPane.add(lblRoomNo);

		JLabel lblFloorNo = new JLabel("Floor No");
		lblFloorNo.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblFloorNo.setForeground(Color.WHITE);
		lblFloorNo.setBounds(10, 93, 79, 14);
		contentPane.add(lblFloorNo);

		txtRoomNo = new JTextField();
		txtRoomNo.setBounds(116, 53, 142, 20);
		contentPane.add(txtRoomNo);
		txtRoomNo.setColumns(10);

		txtFloorNo = new JTextField();
		txtFloorNo.setBounds(116, 90, 142, 20);
		contentPane.add(txtFloorNo);
		txtFloorNo.setColumns(10);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setBounds(10, 121, 248, 14);
		contentPane.add(lblError);

		//Add a fire alarm if the required fields are set
		//or else show an error
		JButton btnAddFireAlarm = new JButton("Add Fire Alarm");
		btnAddFireAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtRoomNo.getText().equals("")) {
					if(!txtFloorNo.getText().equals("")) {
						try {
							int room = Integer.parseInt(txtRoomNo.getText());
							int floor = Integer.parseInt(txtFloorNo.getText());
							if(user.getToken()!=null) {
								try {
									FireAlarmDTO fireaddedDTO = fire.addFireAlarm(user.getToken(), room, floor);
									if(fireaddedDTO.isSuccess()) {
										lblError.setText("Fire Alarm Added Successfully");
										txtFloorNo.setText("");
										txtRoomNo.setText("");
										FireAlarmClientMain.setTime(0);
									}else {
										if(fireaddedDTO.getMsg()!=null) {
											lblError.setText("Error - "+fireaddedDTO.getMsg());
										}else {
											lblError.setText("Error Adding Fire Alarm");
										}
									}
								} catch (RemoteException e1) {
									lblError.setText("Error Adding Fire Alarm");
									e1.printStackTrace();
								} catch (IOException e1) {
									lblError.setText("Error Adding Fire Alarm");
									e1.printStackTrace();
								} catch (Exception e1) {
									lblError.setText("Error Adding Fire Alarm");
									e1.printStackTrace();
								}
							}else {
								lblError.setText("Admin Access Required");
							}
						}catch ( NumberFormatException ex){
							lblError.setText("Room No and Floor No must be Numbers");
						}
					}else {
						lblError.setText("Floor No is invalid");
					}
				}else {
					lblError.setText("Room No is invalid");
				}
			}
		});
		btnAddFireAlarm.setBounds(10, 146, 248, 23);
		contentPane.add(btnAddFireAlarm);
	}

}
