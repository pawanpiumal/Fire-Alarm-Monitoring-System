package fireAlarm;

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
import javax.swing.JTextField;
import javax.swing.SwingConstants;
import javax.swing.border.EmptyBorder;

import desktopClientGUI.FireAlarmClientMain;
import fireAlarmRMIServer.FireAlarmDTO;
import fireAlarmRMIServer.FireAlarmSensor;
import userRMIServer.UserDTO;

public class EditFireAlarm extends JFrame {

	/**
	 * This JFrame is used to edit fire alarm details
	 * The table's selected row information are already set to the
	 *  required fields that need to be edited
	 */
	private static final long serialVersionUID = 1L;
	private JPanel contentPane;
	private JTextField txtRoomNo;
	private JTextField txtFloorNo;
	private static UserDTO user;
	private static FireAlarmSensor fire;
	private static int roomNo;
	private static int floorNo;
	private static String id;
	
	/**
	 * Launch the application.
	 */
	public static void executeMain(FireAlarmSensor fireIn,UserDTO userIn,String idIn,int roomNoIn,int floorNoIn) {
		user = userIn;
		fire = fireIn;
		roomNo = roomNoIn;
		floorNo = floorNoIn;
		id = idIn;
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					EditFireAlarm frame = new EditFireAlarm();
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
	public EditFireAlarm() {
		setBackground(Color.WHITE);
		setBounds(100, 100, 284, 226);
		contentPane = new JPanel();
		contentPane.setBackground(Color.DARK_GRAY);
		contentPane.setBorder(new EmptyBorder(5, 5, 5, 5));
		setContentPane(contentPane);
		contentPane.setLayout(null);
		setTitle("Edit Fire Alarm");
		setAlwaysOnTop(true);
		setResizable(false);
		
		float titileColorHSB[] = Color.RGBtoHSB(255, 69, 0, null);
		JLabel lblEditFireAlarm = new JLabel("Edit Fire Alarm");
		lblEditFireAlarm.setForeground(Color.getHSBColor(titileColorHSB[0], titileColorHSB[1], titileColorHSB[2]));
		lblEditFireAlarm.setFont(new Font("Tahoma", Font.BOLD, 20));
		lblEditFireAlarm.setBounds(61, 11, 151, 34);
		contentPane.add(lblEditFireAlarm);

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
		txtRoomNo.setText(String.valueOf(roomNo));
		txtRoomNo.setColumns(10);

		txtFloorNo = new JTextField();
		txtFloorNo.setBounds(116, 90, 142, 20);
		contentPane.add(txtFloorNo);
		txtFloorNo.setText(String.valueOf(floorNo));
		txtFloorNo.setColumns(10);

		JLabel lblError = new JLabel("");
		lblError.setHorizontalAlignment(SwingConstants.CENTER);
		lblError.setForeground(Color.RED);
		lblError.setFont(new Font("Tahoma", Font.BOLD, 11));
		lblError.setBounds(10, 121, 248, 14);
		contentPane.add(lblError);
		
		//Editing fire alarm details if the required field data are valid
		//or else show an error
		JButton btnEditFireAlarm = new JButton("Edit Fire Alarm");
		btnEditFireAlarm.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if(!txtRoomNo.getText().equals("")) {
					if(!txtFloorNo.getText().equals("")) {
						try {
							int room = Integer.parseInt(txtRoomNo.getText());
							int floor = Integer.parseInt(txtFloorNo.getText());
							if(user.getToken()!=null) {
								try {
									FireAlarmDTO fireaddedDTO = fire.editFireAlarmDetails(id, user.getToken(), room, floor);
									if(fireaddedDTO.isSuccess()) {
										lblError.setText("Fire Alarm Edited SUccessfully");
										txtFloorNo.setText("");
										txtRoomNo.setText("");
										dispose();
										FireAlarmClientMain.setTime(0);
									}else {
										if(fireaddedDTO.getMsg()!=null) {
											lblError.setText("Error - "+fireaddedDTO.getMsg());
										}else {
											lblError.setText("Error Editing Fire Alarm");
										}
									}
								} catch (RemoteException e1) {
									lblError.setText("Error Editing Fire Alarm");
									e1.printStackTrace();
								} catch (IOException e1) {
									lblError.setText("Error Editing Fire Alarm");
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
		btnEditFireAlarm.setBounds(10, 146, 248, 23);
		contentPane.add(btnEditFireAlarm);
	}

}
