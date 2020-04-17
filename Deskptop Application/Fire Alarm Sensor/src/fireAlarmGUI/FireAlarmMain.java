package fireAlarmGUI;

import java.awt.EventQueue;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JComboBox;
import javax.swing.JButton;
import java.awt.Color;
import java.awt.event.ActionListener;
import java.io.IOException;
import java.util.Hashtable;
import java.util.List;
import java.awt.event.ActionEvent;
import javax.swing.JSlider;
import javax.swing.JCheckBox;

import fireAlarmSensor.FireAlarm;
import fireAlarmSensor.FireAlarmDTO;
import fireAlarmSensor.FireAlarmImpl;

public class FireAlarmMain {

	private JFrame frame;
	private JLabel lblTime;
	private JComboBox<String> comboBox;
	private JSlider sliderCo2;
	private JSlider sliderSmoke;
	private FireAlarm fireAlarm;

	/**
	 * Launch the application.
	 */
	public static void main(String[] args) {
		EventQueue.invokeLater(new Runnable() {
			public void run() {
				try {
					FireAlarmMain window = new FireAlarmMain();
					window.frame.setVisible(true);
					window.frame.setResizable(false);

				} catch (Exception e) {
					e.printStackTrace();
				}
			}
		});
	}

	/**
	 * Create the application.
	 */
	public FireAlarmMain() {
		initialize();
		updateDataInIntervals();

	}

	/**
	 * Initialize the contents of the frame.
	 */
	private void initialize() {
		frame = new JFrame();
		frame.getContentPane().setBackground(Color.GRAY);
		frame.setTitle("Fire Alarm");
		frame.setBounds(100, 100, 289, 406);
		frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
		frame.getContentPane().setLayout(null);

		fireAlarm = new FireAlarmImpl();

		comboBox = new JComboBox<String>();

		try {
			List<String> IdList = fireAlarm.getFireAlarmIDs();
			for (String id : IdList) {
				comboBox.addItem(id);
			}

		} catch (IOException e) {
			JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error", JOptionPane.ERROR_MESSAGE);
			e.printStackTrace();
		} catch (Exception e2) {
			JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error", JOptionPane.ERROR_MESSAGE);
			e2.printStackTrace();
		}

		comboBox.setBackground(Color.LIGHT_GRAY);
		comboBox.setBounds(10, 11, 253, 22);
		comboBox.setSelectedIndex(-1);
		frame.getContentPane().add(comboBox);

		JButton btnConnect = new JButton("Connect");
		btnConnect.setBackground(Color.LIGHT_GRAY);

		btnConnect.setBounds(10, 44, 253, 23);
		frame.getContentPane().add(btnConnect);

		sliderCo2 = new JSlider();

		sliderCo2.setForeground(Color.WHITE);
		sliderCo2.setMaximum(10);
		sliderCo2.setValue(0);
		sliderCo2.setBackground(Color.GRAY);
		Hashtable<Integer, JLabel> labelTable = new Hashtable<Integer, JLabel>();
		for (int i = 0; i <= 10; i++) {
			labelTable.put(i, new JLabel(String.valueOf(i)));
		}
		sliderCo2.setLabelTable(labelTable);
		sliderCo2.setPaintLabels(true);
		sliderCo2.setBounds(10, 203, 253, 26);

		frame.getContentPane().add(sliderCo2);

		sliderSmoke = new JSlider();
		sliderSmoke.setValue(0);
		sliderSmoke.setMaximum(10);
		sliderSmoke.setBackground(Color.GRAY);
		sliderSmoke.setBounds(10, 271, 253, 26);
		sliderSmoke.setLabelTable(labelTable);
		sliderSmoke.setPaintLabels(true);
		frame.getContentPane().add(sliderSmoke);

		JCheckBox chckbxStatus = new JCheckBox("Status");
		chckbxStatus.setBackground(Color.GRAY);
		chckbxStatus.setBounds(10, 138, 97, 23);
		frame.getContentPane().add(chckbxStatus);

		JButton btnChangeData = new JButton("Change Data");

		btnChangeData.setBackground(Color.LIGHT_GRAY);
		btnChangeData.setBounds(10, 308, 253, 23);
		frame.getContentPane().add(btnChangeData);

		JLabel lblRoomNo = new JLabel("Room No : ");
		lblRoomNo.setBounds(10, 78, 253, 22);
		frame.getContentPane().add(lblRoomNo);

		JLabel lblFloorNo = new JLabel("Floor No : ");
		lblFloorNo.setBounds(10, 109, 253, 22);
		frame.getContentPane().add(lblFloorNo);

		JLabel lblCo2Level = new JLabel("CO2 Level");
		lblCo2Level.setBounds(10, 168, 253, 22);
		frame.getContentPane().add(lblCo2Level);

		JLabel lblSmokeLevel = new JLabel("SmokeLevel");
		lblSmokeLevel.setBounds(10, 240, 253, 22);
		frame.getContentPane().add(lblSmokeLevel);

		lblTime = new JLabel("Update In :");
		lblTime.setBounds(10, 342, 108, 14);
		frame.getContentPane().add(lblTime);

		chckbxStatus.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				boolean checked = chckbxStatus.isSelected();
				String status = null;
				if (checked) {
					status = "Active";
				} else {
					status = "Not Active";
				}
				if (comboBox.getSelectedItem() != null) {
					String id = (String) comboBox.getSelectedItem();
					try {
						FireAlarmDTO fad = fireAlarm.updateFireAlarmData(id, status);
						if (fad.isSuccess()) {
							JOptionPane.showMessageDialog(frame, "Status Updated", "Updated",
									JOptionPane.INFORMATION_MESSAGE);
						} else {

							JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
									JOptionPane.ERROR_MESSAGE);

						}
					} catch (IOException e1) {

						JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
					}
				}

			}
		});

		btnConnect.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					String id = (String) comboBox.getSelectedItem();
					try {
						FireAlarmDTO fiDto = fireAlarm.getFireAlarmData(id);
						if (fiDto != null) {
							sliderSmoke.setValue(fiDto.getSmokeLevel());
							sliderCo2.setValue(fiDto.getCo2Level());
							lblFloorNo.setText("Floor No\t:\t" + fiDto.getFloorNo());
							lblRoomNo.setText("Room No\t:\t" + fiDto.getRoomNo());
							chckbxStatus.setSelected(fiDto.getStatus().equals("Active"));
						} else {
							JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error", JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame, "Error Getting Data.", "Error", JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
					}
				}
			}
		});

		btnChangeData.addActionListener(new ActionListener() {
			public void actionPerformed(ActionEvent e) {
				if (comboBox.getSelectedItem() != null) {
					String id = (String) comboBox.getSelectedItem();
					try {
						FireAlarmDTO fiDto = fireAlarm.updateFireAlarmData(id, sliderCo2.getValue(),
								sliderSmoke.getValue());
						if (fiDto.isSuccess()) {
							JOptionPane.showMessageDialog(frame, "Data Updated.", "Updated",
									JOptionPane.INFORMATION_MESSAGE);
						} else {
							JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
									JOptionPane.ERROR_MESSAGE);
						}
					} catch (IOException e1) {
						JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e1.printStackTrace();
					} catch (Exception e2) {
						JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
								JOptionPane.ERROR_MESSAGE);
						e2.printStackTrace();
					}
				}

			}
		});
	}

	private int time = 30;

	public void updateDataInIntervals() {
		Thread t = new Thread(new Runnable() {

			@Override
			public void run() {
				for (;;) {
					try {
						Thread.sleep(1000);
						lblTime.setText("Update in : " + time);
						if (time <= 0) {
							if (comboBox.getSelectedItem() != null) {
								if (sliderCo2 != null && sliderSmoke != null) {
									String id = (String) comboBox.getSelectedItem();
									try {
										FireAlarmDTO fiDto = fireAlarm.updateFireAlarmData(id, sliderCo2.getValue(),
												sliderSmoke.getValue());
										if (fiDto.isSuccess()) {
//											JOptionPane.showMessageDialog(frame, "Data Updated.", "Updated",
//													JOptionPane.INFORMATION_MESSAGE);
										} else {
											JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
													JOptionPane.ERROR_MESSAGE);
										}
									} catch (IOException e1) {
										JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
												JOptionPane.ERROR_MESSAGE);
										e1.printStackTrace();
									} catch (Exception e2) {
										JOptionPane.showMessageDialog(frame, "Error Updating Data.", "Error",
												JOptionPane.ERROR_MESSAGE);
										e2.printStackTrace();
									}finally{
										time=30;
									}

								}
							}else {
								time=10;
							}
						}
						time--;
					} catch (InterruptedException e) {
						e.printStackTrace();
					} catch(Exception e2) {
						e2.printStackTrace();
					}

				}
			}
		});

		t.start();
	}
}
